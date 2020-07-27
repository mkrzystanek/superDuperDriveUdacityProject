package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/home")
public class HomeController {

    FileService fileService;

    public HomeController(FileService service) {
        this.fileService = service;
        service.initialize();
    }

    @GetMapping
    public String getHome() {
        return "home";
    }

    @PostMapping
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Model model) {
        fileService.save(file);
        model.addAttribute("files", fileService.getAllFileNames());
        return "home";
    }
}
