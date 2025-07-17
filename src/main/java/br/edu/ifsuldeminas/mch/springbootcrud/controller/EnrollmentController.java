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
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Enrollment;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.User;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.CourseRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.EnrollmentRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.UserRepository;
import jakarta.validation.Valid;

@Controller
public class EnrollmentController {
    
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/enrollments")
    public String enrollments(Model model) {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        model.addAttribute("enrollments", enrollments);
        return "enrollment/list.html";
    }
    
    @GetMapping("/enrollments/form")
    public String enrollmentForm(@ModelAttribute("enrollment") Enrollment enrollment, Model model) {
        List<Course> courses = courseRepository.findAll();
        List<User> users = userRepository.findAll();
        
        model.addAttribute("courses", courses);
        model.addAttribute("users", users);
        
        return "enrollment/form.html";
    }
    
    @PostMapping("/enrollments/new")
    public String enrollmentSave(@Valid @ModelAttribute("enrollment") Enrollment enrollment,
                                 BindingResult validationResults, Model model) {
        
        if (validationResults.hasErrors()) {
            List<Course> courses = courseRepository.findAll();
            List<User> users = userRepository.findAll();
            
            model.addAttribute("courses", courses);
            model.addAttribute("users", users);
            
            return "enrollment/form.html";
        }
        
        // Verificar se já existe matrícula para este aluno e curso
        if (enrollment.getId() == null) {
            boolean exists = enrollmentRepository.existsByStudentIdAndCourseId(
                enrollment.getStudent().getId(), enrollment.getCourse().getId());
            if (exists) {
                model.addAttribute("error", "Aluno já matriculado neste curso!");
                List<Course> courses = courseRepository.findAll();
                List<User> users = userRepository.findAll();
                
                model.addAttribute("courses", courses);
                model.addAttribute("users", users);
                
                return "enrollment/form.html";
            }
        }
        
        enrollmentRepository.save(enrollment);
        return "redirect:/enrollments";
    }
    
    @GetMapping("/enrollments/update/{id}")
    public String enrollmentUpdate(@PathVariable Long id, Model model) {
        
        Optional<Enrollment> enrollmentOpt = enrollmentRepository.findById(id);
        
        if (!enrollmentOpt.isPresent()) {
            throw new IllegalArgumentException("Matrícula de id " + id + " não existe");
        }
        
        Enrollment enrollment = enrollmentOpt.get();
        List<Course> courses = courseRepository.findAll();
        List<User> users = userRepository.findAll();
        
        model.addAttribute("enrollment", enrollment);
        model.addAttribute("courses", courses);
        model.addAttribute("users", users);
        
        return "enrollment/form.html";
    }
    
    @GetMapping("/enrollments/delete/{id}")
    public String enrollmentDelete(@PathVariable Long id) {
        
        Optional<Enrollment> enrollmentOpt = enrollmentRepository.findById(id);
        
        if (!enrollmentOpt.isPresent()) {
            throw new IllegalArgumentException("Matrícula de id " + id + " não existe");
        }
        
        enrollmentRepository.delete(enrollmentOpt.get());
        return "redirect:/enrollments";
    }
}
