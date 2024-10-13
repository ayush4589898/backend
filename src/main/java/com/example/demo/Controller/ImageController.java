package com.example.demo.Controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Model.ImageData;
import com.example.demo.service.ImageDataService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageDataService imageService;

    @GetMapping("/")
    public Map<String, List<ImageData>> getImages(@RequestParam(defaultValue = "1") int page) throws IOException {
        return imageService.getImageFromSub(page);
    }
    
    
  
    @GetMapping("/home")
    public ResponseEntity<Resource> fetchImage(@RequestParam String imageUrl) {
        try {
            System.out.println("Received imageUrl: " + imageUrl);
            imageUrl=imageUrl.trim();
            // Use the File class to read the image from a local path
            // File file = new File(im);  // Assuming imageUrl is the full local path
            
            File file = new File(imageUrl);

            if (!file.exists()) {
                System.out.println("File does not exist: " + imageUrl);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 if file not found
            }

            System.out.println("File is created");
            FileInputStream inputStream = new FileInputStream(file); // Get the image as stream
            byte[] imageBytes = StreamUtils.copyToByteArray(inputStream); // Convert to byte array

            // Determine the content type of the image
            String contentType = Files.probeContentType(file.toPath());
            if (contentType == null) {
                contentType = "application/octet-stream"; // Fallback to a generic binary type if unknown
            }

            // Create a ByteArrayResource from the image bytes
            ByteArrayResource resource = new ByteArrayResource(imageBytes);

            // Return the image along with the appropriate headers for displaying
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, contentType)  // Use the dynamically determined content type
                    .body(resource);
        } catch (IOException e) {
            System.out.println("IOException occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
