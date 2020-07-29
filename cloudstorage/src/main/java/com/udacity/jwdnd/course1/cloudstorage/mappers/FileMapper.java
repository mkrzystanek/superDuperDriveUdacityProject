package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO FILES (fileId, filename, contenttype, filesize, userid, filedata) " +
            "VALUES (#{fileId}, #{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    public int saveFile(File file);

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    public List<File> getAllFiles(Integer userId);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    public File getFile(Integer fileId);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileId}")
    public void deleteFile(Integer fileId);
}
