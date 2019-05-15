package com.tian.service.impl;

import com.tian.dao.StudentMapper;
import com.tian.model.Student;
import com.tian.service.StudentService;
import com.tian.util.MemCacheManager;
import com.tian.util.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {

    private  static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Resource
    private StudentMapper studentMapper;

    MemCacheManager memCacheManager = MemCacheManager.getInstance();


    @Resource
    private RedisCache redisCache;




    /**
     *
     * 使用MemCache
     * 查询全表并根据薪酬降序排列（优秀学员：按照薪酬由高到低显示4位）
     * @return
     */
   /* @Override
    public List<Student> select( ) {
        List<Student> studentList;
        List <Student> students;
        if (memCacheManager.get("studentList") == null){
            //查全表根据工资降序排列
            studentList = studentMapper.select();
            memCacheManager.set("studentList",studentList);
            log.info(" 本次从数据库中查询......");
            //取全部结果的工资前4位放置新list中
            students = new ArrayList <>();
            for(int i =0;i <4; i++){
                students.add(studentList.get(i));
            }
            log.info("列表长度为 "+ students.size() +" 工资排名前4的是 "+students);
        }else {
            studentList = (List <Student>) memCacheManager.get("studentList");
            log.info(" 本次从缓存中查询......");
            //取全部结果的工资前4位放置新list中
            students = new ArrayList <>();
            for (int i = 0; i < 4; i++) {
                students.add(studentList.get(i));
            }
            log.info("列表长度为 " + students.size() + " 工资排名前4的是 " + students);
        }
        return students;

    }*/


    /**
     * 使用Redis
     * 查询全表并根据薪酬降序排列（优秀学员：按照薪酬由高到低显示4位）
     * @return
     */
    @Override
    public List<Student> select( ) {
        List<Student> studentList;
        List <Student> students ;

        // 获取redis缓存列表长度
        Object studentsRedis = redisCache.lGet("studentsRedis",0,-1);
        log.info(""+studentsRedis);
        if (studentsRedis == (null) || ((List) studentsRedis).size() == 0 ){
            //查全表根据工资降序排列
            studentList = studentMapper.select();
            log.info(" 本次从数据库中查询......");
            //取全部结果的工资前4位放置新list中
            students = new ArrayList <>();

            for(int i =0;i <4; i++){
                students.add(studentList.get(i));
                redisCache.lSet("studentsRedis",studentList.get(i));
            }


            log.info("列表长度为 "+ students.size() +" 工资排名前4的是 "+students);
        }else {
            students = (List<Student>) studentsRedis;

            log.info("redis"+students);
            log.info(" 本次从redis缓存中查询......");
        }
        return students;

    }

}
