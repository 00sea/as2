package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.engine.AttributeName;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;
import com.example.demo.models.Student;
import com.example.demo.models.StudentRepository;
import com.example.demo.models.UserRepository;

import jakarta.servlet.http.HttpServletResponse;


@Controller
public class StudentsController {

    @Autowired
    private StudentRepository studentRepo;

    @GetMapping("/students/view")
    public String getAllStudents(Model model) {
        System.out.println("Getting all students");
        List<Student> students = studentRepo.findAll();
        model.addAttribute("stu", students);
        return "students/showAll";
    }

    @PostMapping("/students/add")
    public String addStudent(@RequestParam Map<String,String> newstudent, HttpServletResponse response) {
        System.out.println("ADD student");
        String newName = newstudent.get("name");
        int newWeight = Integer.parseInt(newstudent.get("weight"));
        int newHeight = Integer.parseInt(newstudent.get("height"));
        String newHairColour = newstudent.get("hair_colour");
        Double newGPA = Double.parseDouble(newstudent.get("gpa"));
        studentRepo.save(new Student(newName, newWeight, newHeight, newHairColour, newGPA));
        response.setStatus(201);
        return "redirect:/students/view";
    }

    @PostMapping("/students/delete")
    public String deleteStudent(@RequestParam("sid") int studentId, HttpServletResponse response) {
        System.out.println("DELETE student");
        studentRepo.deleteById(studentId);
        response.setStatus(204);
        return "redirect:/students/view";
    }

    @PostMapping("/students/modify")
    public String modifyStudent(@RequestParam("sid") int studentId, @RequestParam("name") String newName, 
    @RequestParam("weight") int newWeight, @RequestParam("height") int newHeight, @RequestParam("hair_colour") String newHairColour, @RequestParam("gpa") Double newGPA ) {
        Student modifiedStudent = studentRepo.findById(studentId).get();
        modifiedStudent.setName(newName);
        modifiedStudent.setWeight(newWeight);
        modifiedStudent.setHeight(newHeight);
        modifiedStudent.setHair_colour(newHairColour);
        modifiedStudent.setGpa(newGPA);
        studentRepo.save(modifiedStudent);
        return "redirect:/students/view";
    }


    /* 
    @DeleteMapping("/students/{id}")
    public String removeStudent(@PathVariable("id") Long studentId, HttpServletResponse response) {
        System.out.println("REMOVE student");

        Optional<Student> optionalStudent = studentRepo.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            studentRepo.delete(student);
            response.setStatus(204);
            return "students/removedStudent";
        }
        else {
            response.setStatus(404);
            return "students/studentNotFound"
        }
    }
    */
}
