package com.example.demo.Controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Model.Combined;
import com.example.demo.Model.PatientData;
import com.example.demo.Model.PatientDetail;
import com.example.demo.repo.PatientDataRepo;
import com.example.demo.service.ImageDataService;
import com.example.demo.service.PatientDataSev;
import com.example.demo.service.PatientService;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@RestController
public class PatientController {

    @Value("${image.upload.dir}")
    private String upload;
    @Autowired
    PatientDataRepo patientDataRepo;

    @Autowired
    PatientDataSev ps;
    
    @Autowired
    private PatientService patientService;

    @PostMapping("/home")
    public ResponseEntity<String> postPatient(@RequestBody PatientDetail patientDetail) {
       
        patientService.savePatient(patientDetail); 
         return new ResponseEntity<>("Patient saved successfully", HttpStatus.CREATED);
    


    
        }

    @PostMapping("/saveUser")
     public ResponseEntity<String> done(@RequestParam String name,@RequestParam String email){
        PatientDetail detial=new PatientDetail(name,email);
        return patientService.saveData(detial);
     }
    
     @GetMapping("/details")
       
    public PatientDetail verifyUserandget(@RequestParam String name,@RequestParam String email ){
        System.out.println(name+" "+email);
              PatientDetail pd=patientService.validate(name,email);
              System.out.println(pd.getName()+" "+pd.getEmail());
                return pd;


    }
     @GetMapping("/detailsaddOns")
       
    public List<PatientData> addons(@RequestParam String name,@RequestParam String email ){
        System.out.println(name+" "+email);
              List<PatientData> arr= patientService.getPd(email);
              Collections.sort(arr, (o1, o2) -> o2.getDateWrt().compareTo(o1.getDateWrt()));
return arr;


    }

    @PostMapping("/save")
    public void uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("patientId") String patientId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date,
            @RequestParam("result") String result) {
                if(file.isEmpty())return;
            try{
                Files.createDirectories(Paths.get(upload));
                String filename=patientId+"_"+date.toString()+"_"+file.getOriginalFilename();
                Path filePath=Paths.get(upload, filename);
                file.transferTo(filePath);
                    System.out.println(date+" "+result+" "+patientId);
                   Long patientId1=Long.parseLong(patientId);
                 PatientData pd=new PatientData(patientId1,date,filePath.toString(),result);
                patientDataRepo.save(pd);

            }
            catch(Exception e){
                System.out.println(e);
            }




    }
    @GetMapping("/data")
    public List<Combined> getMethodName(@RequestParam String query,@RequestParam String searchFor) {
    System.out.println("i ma hiited fro the aval "+query+" "+searchFor);

         return ps.get(query,searchFor);



    }

    
    

    
}