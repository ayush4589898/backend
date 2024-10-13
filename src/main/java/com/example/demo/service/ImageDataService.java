package com.example.demo.service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.example.demo.Model.ImageData;
import com.example.demo.Model.PatientData;


@Service
public class ImageDataService {
    
     private static final String path="/mnt/data/mt23mcs013/archive/data";
     private static int PAGE_SIZE=10;
     public HashMap<String,List<ImageData>> getImageFromSub(int page) throws IOException{
        HashMap<String ,List<ImageData>> map=new HashMap<>();
        File basef=new File(path);
        File[] sub=basef.listFiles(File::isDirectory);
        if(sub!=null){
            for(File subd:sub){
                List<ImageData> images=getImagesFromDirectory(subd.toPath(),page);
                if(!images.isEmpty()){
                  map.put(subd.getName(),images);
                }
            }
        }
        return map;
        
     }
    private List<ImageData> getImagesFromDirectory(Path directory, int page) throws IOException {
        return Files.list(directory)
                .filter(path -> isImageFile(path))
                .skip((long) (page - 1) * PAGE_SIZE)
                .limit(PAGE_SIZE)
                .map(path -> new ImageData(path.getFileName().toString(), path.toString()))
                .collect(Collectors.toList());
    }

    private boolean isImageFile(Path path) {
        String fileName = path.getFileName().toString().toLowerCase();
        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || 
               fileName.endsWith(".png") || fileName.endsWith(".gif");
    }


 


    
}
