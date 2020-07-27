package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.exceptions.FileException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileService {

    private final Path root = Paths.get("uploads");

    public void initialize() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new FileException("Failed to initialize upload directory!", e);
        }
    }

    public void save(MultipartFile file) {
        try {
            String fileName = Objects.requireNonNull(file.getOriginalFilename());
            Files.copy(file.getInputStream(), this.root.resolve(fileName));
        } catch (Exception e) {
            throw new FileException("Failed to store the file on server!", e);
        }
    }

    public List<String> getAllFileNames() {
        try (Stream<Path> walk = Files.walk(this.root)) {
            return walk.filter(Files::isRegularFile)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileException("Failed to load saved files!", e);
        }
    }
}
