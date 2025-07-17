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

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Teacher;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.TeacherRepository;
import jakarta.validation.Valid;

@Controller
public class TeacherController {
    
    @Autowired
    private TeacherRepository teacherRepository;
    
    @GetMapping("/teachers")
    public String teachers(Model model) {
        List<Teacher> teachers = teacherRepository.findAll();
        model.addAttribute("teachers", teachers);
        return "teacher/list.html";
    }
    
    @GetMapping("/teachers/form")
    public String teacherForm(@ModelAttribute("teacher") Teacher teacher) {
        return "teacher/form.html";
    }
    
    @PostMapping("/teachers/new")
    public String teacherSave(@Valid @ModelAttribute("teacher") Teacher teacher,
                              BindingResult validationResults) {
        
        if (validationResults.hasErrors()) {
            return "teacher/form.html";
        }
        
        teacherRepository.save(teacher);
        return "redirect:/teachers";
    }
    
    @GetMapping("/teachers/update/{id}")
    public String teacherUpdate(@PathVariable Long id, Model model) {
        
        Optional<Teacher> teacherOpt = teacherRepository.findById(id);
        
        if (!teacherOpt.isPresent()) {
            throw new IllegalArgumentException("Professor de id " + id + " não existe");
        }
        
        Teacher teacher = teacherOpt.get();
        model.addAttribute("teacher", teacher);
        return "teacher/form.html";
    }
    
    @GetMapping("/teachers/delete/{id}")
    public String teacherDelete(@PathVariable Long id) {
        
        Optional<Teacher> teacherOpt = teacherRepository.findById(id);
        
        if (!teacherOpt.isPresent()) {
            throw new IllegalArgumentException("Professor de id " + id + " não existe");
        }
        
        teacherRepository.delete(teacherOpt.get());
        return "redirect:/teachers";
    }
}
