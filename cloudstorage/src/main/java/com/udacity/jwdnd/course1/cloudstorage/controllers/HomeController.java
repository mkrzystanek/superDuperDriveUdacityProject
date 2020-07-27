package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String getHome(Model model) {
        model.addAttribute("files", fileService.getAllFileNames());
        return "home";
    }

    @PostMapping
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Model model) {
        fileService.save(file);
        model.addAttribute("files", fileService.getAllFileNames());
        return "home";
    }

    @GetMapping("/{filename}")
    public ResponseEntity<Resource> viewFile(@PathVariable("filename") String fileName) {
        InputStreamResource resource = fileService.view(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
