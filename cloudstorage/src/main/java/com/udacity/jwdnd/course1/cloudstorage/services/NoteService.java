package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteMapper noteMapper;

    public List<Note> getAllNotes(Integer userId) {
        return noteMapper.getAllNotes(userId);
    }

    public boolean addNote(Note note) {
        try {
            noteMapper.addNote(note);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateNote(Note note) {
        try {
            noteMapper.updateNote(note);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void deleteNote(Integer noteId) {
        noteMapper.deleteNote(noteId);
    }
}
