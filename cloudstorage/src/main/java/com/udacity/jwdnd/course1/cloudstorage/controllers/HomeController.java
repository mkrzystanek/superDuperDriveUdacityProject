package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    FileService fileService;
    @Autowired
    NoteService noteService;
    @Autowired
    UserService userService;
    @Autowired
    CredentialService credentialService;

    @GetMapping
    public String getHome(Model model, Authentication auth) {
        model.addAttribute("files", fileService.getAllFiles(userService.getActiveUserId(auth)));
        model.addAttribute("notes", noteService.getAllNotes(userService.getActiveUserId(auth)));
        model.addAttribute("credentials", credentialService.getAllCredentials(userService.getActiveUserId(auth)));
        return "home";
    }

    @PostMapping("/file")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Authentication auth, Model model) {
        fileService.save(file, userService.getActiveUserId(auth));
        return getHome(model, auth);
    }

    @GetMapping("/file/{fileid}")
    public ResponseEntity<Resource> viewFile(@PathVariable("fileid") Integer fileId) {
        File file = fileService.getFile(fileId);
        InputStreamResource resource = fileService.view(file);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @PostMapping("/file/{fileid}")
    public String deleteFile(@PathVariable("fileid") Integer fileId, Authentication auth, Model model) {
        fileService.delete(fileId);
        return getHome(model, auth);
    }

    @PostMapping("/note")
    public String addNote(@ModelAttribute Note note, Authentication auth, Model model) {
        note.setUserid(userService.getActiveUserId(auth));
        noteService.addNote(note);
        return getHome(model, auth);
    }

    @PostMapping("/note/delete/{noteid}")
    public String deleteNote(@PathVariable("noteid") Integer noteId, Authentication auth, Model model) {
        noteService.deleteNote(noteId);
        return getHome(model, auth);
    }

    @PostMapping("/credential")
    public String addCredential(@ModelAttribute Credentials credentials, Authentication auth, Model model) {
        credentials.setUserid(userService.getActiveUserId(auth));
        credentialService.addCredential(credentials);
        return getHome(model, auth);
    }

    @PostMapping("/credential/delete/{credentialid}")
    public String deleteCredential(@PathVariable("credentialid") Integer credentialId, Authentication auth, Model model) {
        credentialService.deleteCredential(credentialId);
        return getHome(model, auth);
    }
}
