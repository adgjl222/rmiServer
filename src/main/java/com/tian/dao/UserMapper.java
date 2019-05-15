package com.tian.dao;

import com.tian.model.User;

import java.util.List;

public interface UserMapper {





    int insert(User record);

    int updateUser(User User);

    /**
     * 注册 新增用户
     * @param user
     * @return
     */
    int insertSelective(User user);


    /**
     * 登陆 根据用户名和密码查询用户
     * @param userName
     */
    String findPwdByName(String userName);

    List<Integer> selectName(String userName);




    User getUserByPhone(String phoneNumber);

    User getUserByEmail(String email);

    User getUserByName(String userName);

}