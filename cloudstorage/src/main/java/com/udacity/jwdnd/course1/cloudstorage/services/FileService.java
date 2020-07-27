package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.exceptions.FileException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
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
            if (Files.notExists(root)) {
                Files.createDirectory(root);
            }
        } catch (IOException e) {
            throw new FileException("Failed to initialize upload directory!", e);
        }
    }

    public void save(MultipartFile file) {
        try {
            String fileName = Objects.requireNonNull(file.getOriginalFilename());
            Files.copy(file.getInputStream(), this.root.resolve(fileName));
        } catch (NullPointerException e) {
            throw new FileException("Failed to retrieve file name!", e);
        } catch (IOException e) {
            throw new FileException("Failed to store the file on server!", e);
        }
    }

    public List<String> getAllFileNames() {
        try (Stream<Path> walk = Files.walk(this.root)) {
            return walk.filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileException("Failed to load saved files!", e);
        }
    }

    public InputStreamResource view(String fileName) {
        Path file = this.root.resolve(fileName);
        if (Files.exists(file)) {
            try {
                return new InputStreamResource(new FileInputStream(file.toFile()));
            } catch (IOException e) {
                throw new FileException("Failed to read file!", e);
            }
        } else {
            throw new FileException(String.format("File %s does not exist", fileName));
        }

    }

    public void delete(String fileName) {

    }
}
