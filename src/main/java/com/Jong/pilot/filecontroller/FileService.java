package com.Jong.pilot.filecontroller;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileService {

    public static void saveFile(String directories, String imageName, MultipartFile multipartFile) throws IOException {

        Path uploadWay = Paths.get(directories);
        if (!Files.exists(uploadWay)) {
            Files.createDirectories(uploadWay);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadWay.resolve(imageName);
            System.out.println(filePath);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex){
            throw new IOException("해당 하는 파일을 저장할 수 없습니다.",ex);
        }
    }
}
