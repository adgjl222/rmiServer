package com.tian.service;

import com.tian.model.User;

public interface UserService {


    public int checkPhoneCode(String phoneNumber, String code);

    public int sendPhone(String phoneNumber, String msgCode);


    public int checkEmailCode(String email, String code);
    public int sendEmai(String email, String msgCode);


    User getUserByPhone(String phoneNumber);

    User getUserByEmail(String email);

    User getUserByName(String userName);

    /**
     * 更新用户信息
     * @param User
     * @return  更新结果：true-----更新成功，false------更新失败
     */

    Boolean updateUser(User User);

    //用户注册
    int regist(User user) throws Exception;

    //用户登陆
    boolean login(String userName, String userPassword) throws Exception;
}
