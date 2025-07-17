package br.edu.ifsuldeminas.mch.springbootcrud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Course;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Teacher;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.CourseRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.TeacherRepository;
import jakarta.validation.Valid;

@Controller
public class CourseController {
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private TeacherRepository teacherRepository;
    
    @GetMapping("/courses")
    public String courses(Model model) {
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "course/list.html";
    }
    
    @GetMapping("/courses/form")
    public String courseForm(@ModelAttribute("course") Course course, Model model) {
        List<Teacher> teachers = teacherRepository.findAll();
        
        model.addAttribute("teachers", teachers);
        
        return "course/form.html";
    }
    
    @PostMapping("/courses/new")
    public String courseSave(@Valid @ModelAttribute("course") Course course,
                             BindingResult validationResults, Model model) {
        
        if (validationResults.hasErrors()) {
            List<Teacher> teachers = teacherRepository.findAll();
            
            model.addAttribute("teachers", teachers);
            
            return "course/form.html";
        }
        
        courseRepository.save(course);
        return "redirect:/courses";
    }
    
    @GetMapping("/courses/update/{id}")
    public String courseUpdate(@PathVariable int id, Model model) {
        
        Optional<Course> courseOpt = courseRepository.findById(id);
        
        if (!courseOpt.isPresent()) {
            throw new IllegalArgumentException("Curso de id " + id + " não existe");
        }
        
        Course course = courseOpt.get();
        List<Teacher> teachers = teacherRepository.findAll();
        
        model.addAttribute("course", course);
        model.addAttribute("teachers", teachers);
        
        return "course/form.html";
    }
    
    @GetMapping("/courses/delete/{id}")
    public String courseDelete(@PathVariable int id) {
        
        Optional<Course> courseOpt = courseRepository.findById(id);
        
        if (!courseOpt.isPresent()) {
            throw new IllegalArgumentException("Curso de id " + id + " não existe");
        }
        
        courseRepository.delete(courseOpt.get());
        return "redirect:/courses";
    }
}
