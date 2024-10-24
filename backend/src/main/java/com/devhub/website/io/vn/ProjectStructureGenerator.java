package com.devhub.website.io.vn;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProjectStructureGenerator {

    public static void main(String[] args) {
        // Root directory
        String rootDir = "backend/src/main";

        // List of directories to create
        String[] directories = {
                rootDir + "/java/com/devhub/website/io/vn/config",
                rootDir + "/java/com/devhub/website/io/vn/controller",
                rootDir + "/java/com/devhub/website/io/vn/dto",
                rootDir + "/java/com/devhub/website/io/vn/model",
                rootDir + "/java/com/devhub/website/io/vn/repository",
                rootDir + "/java/com/devhub/website/io/vn/service",
                rootDir + "/resources/templates",
                rootDir + "/resources/static/css",
                rootDir + "/resources/static/js",
                "src/test/java/com/devhub/website/io/vn"
        };

        // List of files to create
        String[][] files = {
                {rootDir + "/java/com/devhub/website/io/vn/config/SecurityConfig.java"},
                {rootDir + "/java/com/devhub/website/io/vn/config/JwtAuthenticationFilter.java"},
                {rootDir + "/java/com/devhub/website/io/vn/config/JwtProvider.java"},
                {rootDir + "/java/com/devhub/website/io/vn/controller/AuthController.java"},
                {rootDir + "/java/com/devhub/website/io/vn/dto/LoginRequest.java"},
                {rootDir + "/java/com/devhub/website/io/vn/dto/RegisterRequest.java"},
                {rootDir + "/java/com/devhub/website/io/vn/model/User.java"},
                {rootDir + "/java/com/devhub/website/io/vn/repository/UserRepository.java"},
                {rootDir + "/java/com/devhub/website/io/vn/service/AuthService.java"},
                {rootDir + "/java/com/devhub/website/io/vn/service/JwtUserDetailsService.java"},
                {rootDir + "/resources/templates/login.html"},
                {rootDir + "/resources/templates/register.html"},
                
               
        };
        //{rootDir + "/resources/application.properties"},
        //   {rootDir + "/java/com/devhub/website/io/vn/Application.java"},
        // {"src/test/java/com/devhub/website/io/vn/ApplicationTests.java"}

        // Create directories
        for (String dir : directories) {
            createDirectory(dir);
        }

        // Create files
        for (String[] filePath : files) {
            createFile(filePath[0]);
        }
    }

    // Method to create directories
    private static void createDirectory(String dir) {
        Path path = Paths.get(dir);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                System.out.println("Directory created: " + dir);
            } catch (IOException e) {
                System.err.println("Failed to create directory: " + dir);
                e.printStackTrace();
            }
        } else {
            System.out.println("Directory already exists: " + dir);
        }
    }

    // Method to create files
    private static void createFile(String filePath) {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
                System.out.println("File created: " + filePath);
            } catch (IOException e) {
                System.err.println("Failed to create file: " + filePath);
                e.printStackTrace();
            }
        } else {
            System.out.println("File already exists: " + filePath);
        }
    }
}
