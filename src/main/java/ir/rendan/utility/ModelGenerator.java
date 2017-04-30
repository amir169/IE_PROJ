/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.rendan.utility;

import ir.rendan.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ModelGenerator {
    
    
    public Student getStudent(){
        return  new Student("ali","alavi",22);
    }
    
    
    
    public List<Student> getStudents(){
     
        List<Student> result = new ArrayList<>();
        result.add(new Student("ali","alavi",22));
        result.add(new Student("maryam","alavi",24));
        result.add(new Student("ahmad","mousavi",30));
        return  result;
    }
    
}
