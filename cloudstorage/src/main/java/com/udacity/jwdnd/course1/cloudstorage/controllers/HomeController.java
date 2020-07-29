package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/home")
public class HomeController {

    FileService fileService;
    NoteService noteService;
    UserService userService;

    public HomeController(FileService fileService, NoteService noteService, UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.userService  = userService;
        fileService.initialize();
    }

    @GetMapping
    public String getHome(Model model, Authentication auth) {
        model.addAttribute("files", fileService.getAllFileNames());
        model.addAttribute("notes", noteService.getAllNoteTitles(userService.getActiveUserId(auth)));
        return "home";
    }

    @PostMapping("/file")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Authentication auth, Model model) {
        fileService.save(file);
        return getHome(model, auth);
    }

    @GetMapping("/file/{filename}")
    public ResponseEntity<Resource> viewFile(@PathVariable("filename") String fileName) {
        InputStreamResource resource = fileService.view(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @PostMapping("/file/{filename}")
    public String deleteFile(@PathVariable("filename") String fileName, Authentication auth, Model model) {
        fileService.delete(fileName);
        return getHome(model, auth);
    }

    @PostMapping("/note")
    public String addNote(@ModelAttribute Note note, Authentication auth, Model model) {
        note.setUserid(userService.getActiveUserId(auth));
        noteService.addNote(note);
        return getHome(model, auth);
    }
}
