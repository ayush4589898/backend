package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Combined;
import com.example.demo.Model.PatientData;
import com.example.demo.Model.PatientDetail;
import com.example.demo.repo.PatientDataRepo;
import com.example.demo.repo.PatientDetailRepository;
@Service
public class PatientDataSev {
        @Autowired
        PatientDetailRepository pdr;
        @Autowired
        PatientDataRepo repo;

    public List<Combined> get(String query,String value){
        System.out.println(value+" this is value");
        long id=0;
         if(query.trim().equals("patientId")){
            System.out.println();
            try{
                id=Long.parseLong(value);
            }
            catch(Exception e){
               return null;
            }
         }
         List<Long> arr=new ArrayList<Long>();
         if(query.trim().toLowerCase().equals("email")){System.out.println("inside this");arr=pdr.findUniquePatientIdsByEmail(value.trim());}
         if(query.trim().toLowerCase().equals("name")){System.out.println("inside this");
         arr=pdr.findUniquePatientIdsByName(value.trim());}
         System.out.println(arr);
         if(arr.size()==0)arr.add(id);


            System.out.println(arr);
        List<PatientDetail> ans1=pdr.findByPatientIds(arr);
         List<PatientData> ans=  repo.findByPatientIds(arr);
      
           List<Combined> res=new ArrayList<>();

System.out.println(ans1.size());
System.out.println(ans.size());
      boolean ch=false;

           for(PatientDetail p:ans1){



               for(PatientData d:ans){
                 
                     if(p.getPatientId()==d.getPatientId()){
                        Combined temp=new Combined(p.getName(),p.getEmail(),p.getPatientId(),d.getResult());
                        res.add(temp);
                        ch=true;
                        break;
                     }
                    
               }
               if(!ch){
                  Combined temp=new Combined(p.getName(),p.getEmail(),p.getPatientId(),"NA");
                  res.add(temp);
               }
               ch=false;
          
           }
 
           return res;









    }
    
}
