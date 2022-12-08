package com.innotree.pilot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dirName = "board-photos";
        Path boardPhotosDir = Paths.get(dirName);

        String boardPhotosPath = boardPhotosDir.toFile().getAbsolutePath();
        System.out.println(boardPhotosPath);
        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + boardPhotosPath + "/");
    }
}
