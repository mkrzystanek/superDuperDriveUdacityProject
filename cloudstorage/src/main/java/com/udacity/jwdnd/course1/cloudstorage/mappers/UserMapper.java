package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USERS WHERE username = #{userName}")
    User getUser(String userName);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname)" +
            "VALUES (#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    int createUser(User newUser);
}
