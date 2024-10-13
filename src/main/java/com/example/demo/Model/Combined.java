package com.example.demo.Model;

public class Combined {
    
    private String name;
    private String email;
    private Long id;
    private String result;
   public Combined(String name,String email,Long id,String result){
            this.name=name;
            this.email=email;
            this.id=id;
            this.result=result;


    }
public Combined() {
}
public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}
public String getEmail() {
    return email;
}
public void setEmail(String email) {
    this.email = email;
}
public Long getId() {
    return id;
}
public void setId(Long id) {
    this.id = id;
}
public String getResult() {
    return result;
}
public void setResult(String result) {
    this.result = result;
}
    

}
