package com.Jong.pilot.file;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DownloadService {

    private Path findbyFile;

    public Resource findResource(String imageName) throws IOException {
        Path findPhotoPath = Paths.get("board-photos");

        Files.list(findPhotoPath).forEach(file -> {
            if (file.getFileName().toString().startsWith(imageName)) {
                findbyFile = file;
                return;
            };
        });
        if (findbyFile != null) {
            return new UrlResource(findbyFile.toUri());
        }
        return null;
    }
}
