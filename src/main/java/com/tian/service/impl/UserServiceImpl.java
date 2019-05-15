package com.tian.service.impl;

import com.tian.dao.UserMapper;
import com.tian.model.User;
import com.tian.service.UserService;
import com.tian.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 业务层
 */

@Service
public class UserServiceImpl implements UserService {

    private  static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;
    MemCacheManager memCacheManager = MemCacheManager.getInstance();

    @Resource
    private RedisCache redisCache;

    @Resource
    SMS sms;



    // 设置验证码过期时间
    final long expireTime = 60 * 10;


    DES des;

    {
        try {
            des = new DES();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册用户
     * @param
     * @return
     */
   /* @Override
    public int regist(User user) throws Exception {
        int regist = 0;
        user.setUserPassword(des.encrypt(user.getUserPassword()));
        log.info("注册人 ："+" username is "+ user.getUserName()+" userpassword is "+ user.getUserPassword());

        List<Integer>  idList = userMapper.selectName(user.getUserName());
        log.info(" 数据库中："+"姓名为 "+ user.getUserName() +" 的用户为 "+idList);

        //判断集合有无元素也可以使用idList.size() == 0    idList为 null时内存中没有为list集合分配空间 实际上不不存在
        if ( idList != null && idList.isEmpty()){
            regist = userMapper.insertSelective(user);
            String name = user.getUserName();
            memCacheManager.add(name,user);
            log.info(" username is "+ user.getUserName()+" userpassword is "+ user.getUserPassword()+" 的用户id为 "+ user.getId()+" ，该用户信息已存入缓存");
            log.info(name+"的用户缓存信息为"+memCacheManager.get(name));
        }else {

            log.info(" 昵称被占用 注册失败");
        }

        return regist;
    }*/


    /**
     * 发送短信验证码，每1分钟之后可以重复发送，每次有效期为10分钟，每天最多发送5次
     * @param phoneNumber 手机号
     * @return  0：发送成功 2:发送过于频繁 3：超过每天最大次数 1：其他
     */
    @Override
    public int sendPhone(String phoneNumber, String msgCode) {
        //默认发送时间为0
        long time = 0;
        //发送次数，默认为0
        int next = 0;
        //判断发送时间间隔是否在1分钟之内
        if ( redisCache.getS("time"+ phoneNumber) != null ){
            time = (long) redisCache.getS("time"+ phoneNumber);
            long curTime  = System.currentTimeMillis();
            if( (curTime -time) <1000*60){
                return 2;
            }
        }

        //判断24小时之内发送次数是否小于等于最大次数
        if (redisCache.getS("next"+ phoneNumber) != null){
            next = (int) redisCache.getS("next"+phoneNumber);
            log.info("手机号发送验证码次数：{}",next);
            if (next >= 5){
                return 3;
            }

        }

        String msgStatus = null;

        //调用容联发送短信验证码
        try{
            msgStatus = sms.sendMessage(phoneNumber,msgCode);
            log.info("容联短信发送：{}",msgStatus);
        } catch (Exception e){
            log.info("容联发送手机信息失败");
            e.printStackTrace();
        }

        //判断发送结果是否成功，将验证码、当前时间以及发送次数放入缓存
        if (msgStatus != null && "true".equals(msgStatus)){
           redisCache.setS("time"+phoneNumber,System.currentTimeMillis(),60);
           redisCache.setS("msgCode"+phoneNumber,msgCode,expireTime);
           redisCache.setS("next"+phoneNumber,++next,60*60*24);
           log.info("手机号发送验证码次数：{]",redisCache.getS("next"+phoneNumber));
           return 0;

        }

        return 1;
    }




    /**
     * 检验手机验证码是否一致
     * @param phoneNumber
     * @param code
     * @return 0:验证成功 1:验证失败
     */
    @Override
    public int checkPhoneCode(String phoneNumber, String code) {
        String redisPhoneCode =(String) redisCache.getS("msgCode"+phoneNumber);
        log.info("redis中短信验证码：{}",redisPhoneCode);
        log.info("输入的验证码:{}",code);
        //比对缓存中验证码与用户输入验证码
        if (redisPhoneCode != null && code.equals(redisPhoneCode)){
            return 0;
        }else {
            return 1;
        }

    }


    /**
     * 发送邮箱验证码
     * @param email
     * @param msgCode
     * @return
     */
    @Override
    public int sendEmai(String email, String msgCode) {
        String msgStatus = null;


        log.info("email===================="+email+"++++++++++++++++++++++"+msgCode);
        if ( email == null  ){
            log.info("邮箱不能为空");
            return 1;
        }else try {
                // 由于每天次数的限制，暂时采用假的，测试其他逻辑有没有问题,基本测试完成，再换成真的
//            msgStatus = DataUtils.getMsgStatus();
                msgCode = "222222";
                msgStatus = SendCloud.sendCommon(email,msgCode);
//                msgStatus = "true";

                log.info("msgStatus:"+msgStatus);
            } catch (Exception e) {
                log.info("sendCloud发送邮件注册信息失败");
                e.printStackTrace();
            }
            if (msgStatus!=null && "true".equals(msgStatus)) {
                redisCache.setS("msgCode"+email, msgCode);
                log.info("邮箱验证码的缓存"+redisCache.setS("msgCode"+email, msgCode)+redisCache.getS("msgCode"+email));
                return 0;
            }else{
                log.info("邮箱验证码的缓存无");
                return 1;

            }


    }


    /**
     * 验证邮箱验证码
     * @param email
     * @param msgCode
     * @return
     */
    @Override
    public int checkEmailCode(String email, String msgCode) {
        log.info(" msgCode is =========="+msgCode);
        String redisEmailCode = (String) redisCache.getS("msgCode"+email);
        log.info("redis中邮箱验证码：{}",redisEmailCode);
        if ((redisEmailCode !=null ) && (msgCode.equals(redisEmailCode))){
            return 0;
        }else{
            return 1;
        }
    }




    @Override
    public User getUserByPhone(String phoneNumber) {
        return userMapper.getUserByPhone(phoneNumber);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public User getUserByName(String userName) {
        return userMapper.getUserByName(userName);
    }


    /**
     * 注册 使用redis进行缓存
     * @param user
     * @return
     * @throws Exception
     */
   @Override
    public int regist(User user) throws Exception {
        int regist = 0;

        user.setUserPassword(des.encrypt(user.getUserPassword()));
        user.setSalt("https://sweetsalt.oss-cn-beijing.aliyuncs.com/ss/20190429203658.png");
        user.setPhoneNumber(user.getPhoneNumber());
        log.info("注册人 ："+" username is "+ user.getUserName()+" userpassword is "+ user.getUserPassword());

        List<Integer>  idList = userMapper.selectName(user.getUserName());
        log.info(" 数据库中："+"姓名为 "+ user.getUserName() +" 的用户为 "+idList);

        //判断集合有无元素也可以使用idList.size() == 0    idList为 null时内存中没有为list集合分配空间 实际上不不存在
        if ( idList != null && idList.isEmpty()){
            regist = userMapper.insertSelective(user);
            String name = user.getUserName();
            redisCache.setS(name,user);
            log.info(" username is "+ user.getUserName()+" userpassword is "+ user.getUserPassword()+" 的用户id为 "+ user.getId()+" ，该用户信息已存入redis缓存");
            log.info(name+"的用户缓存信息为"+redisCache.getS(name));
        }else {

            log.info(" 昵称被占用 注册失败");
        }

        return regist;
    }




    /**
     * 校验用户名密码是否相同
     * @param userName
     * @return
     */
    @Override
    public boolean login(String userName,String userPassword) throws Exception {
        boolean login = false;
        userPassword = des.encrypt(userPassword);
        log.info(" 用户输入的userName is " + userName + "加密后的userPassword is " + userPassword);

        List<Integer>  idList = userMapper.selectName(userName);
        log.info(""+idList);
        if (idList != null && idList.isEmpty() ) {
            log.info("用户名不存在");
        } else {
            String userPasswordTrue = userMapper.findPwdByName(userName);
            log.info("userPasswordTrue is  " + userPasswordTrue);
            if (userPassword.equals(userPasswordTrue)) {
                login = true;
                log.info("密码是否相同 "+userPassword.equals(userPasswordTrue));
                log.info("登陆 " + login);

            } else {
                log.info("密码是否相同 "+userPassword.equals(userPasswordTrue));
                login = false;
                log.info(" 密码错误");
            }
        }

        log.info(""+login);
        return login;
    }


    /**
     * 更新用户信息
     * @param user
     * @return true-----更新成功，false------更新失败
     */
    @Override
    public Boolean updateUser(User user) {
        boolean flag = false;
        int i = userMapper.updateUser(user);
        String name = user.getUserName();
        log.info("更新id:{}", user.getId());
        log.info("更新的信息:"+user);

        if (i != 0) {
            log.info("更新成功，缓存失效");
            redisCache.delete("name");
            flag = true;
        }
        return flag;
    }
}
