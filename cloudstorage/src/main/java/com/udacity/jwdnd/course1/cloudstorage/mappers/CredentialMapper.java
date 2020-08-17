package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    public List<Credentials> getAllCredentials(Integer userId);

    @Insert("INSERT INTO CREDENTIALS (credentialid, url, username, key, password, userid) " +
            "VALUES (#{credentialid}, #{url}, #{username}, #{key}, #{password}, #{userid})")
    public void addCredential(Credentials credentials);
}
