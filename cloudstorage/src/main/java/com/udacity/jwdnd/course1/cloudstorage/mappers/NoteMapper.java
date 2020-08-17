package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    public List<Note> getAllNotes(Integer userId);

    @Insert("INSERT INTO NOTES (noteid, notetitle, notedescription, userid) " +
            "VALUES (#{noteid}, #{notetitle}, #{notedescription}, #{userid})")
    public void addNote(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    public void deleteNote(Integer noteId);

    @Update("UPDATE NOTES SET notetitle = #{notetitle}, notedescription = #{notedescription} WHERE noteid = #{noteid}")
    public void updateNote(Note note);
}
