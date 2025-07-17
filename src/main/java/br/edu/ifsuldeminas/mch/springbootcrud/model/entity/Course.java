package br.edu.ifsuldeminas.mch.springbootcrud.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "courses")
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @NotBlank(message = "Modalidade não pode ser vazia.")
    @Size(min = 2, max = 50, message = "Modalidade deve ter entre 2 e 50 caracteres.")
    private String modality;
    
    @NotBlank(message = "Nome do curso não pode ser vazio.")
    @Size(min = 2, max = 100, message = "Nome do curso deve ter entre 2 e 100 caracteres.")
    private String name;
    
    @NotBlank(message = "Descrição não pode ser vazia.")
    @Size(min = 10, max = 500, message = "Descrição deve ter entre 10 e 500 caracteres.")
    @Column(length = 500)
    private String description;
    
    @NotNull(message = "Carga horária não pode ser nula.")
    @Min(value = 1, message = "Carga horária deve ser pelo menos 1 hora.")
    private Integer workloadHours;
    
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @NotNull(message = "Professor deve ser selecionado.")
    private Teacher teacher;
    
    public Course() {
        setModality("");
        setName("");
        setDescription("");
        setWorkloadHours(0);
    }
    
    public Course(String modality, String name, String description, Integer workloadHours, Teacher teacher) {
        this.modality = modality;
        this.name = name;
        this.description = description;
        this.workloadHours = workloadHours;
        this.teacher = teacher;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getModality() {
        return modality;
    }
    
    public void setModality(String modality) {
        this.modality = modality;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Integer getWorkloadHours() {
        return workloadHours;
    }
    
    public void setWorkloadHours(Integer workloadHours) {
        this.workloadHours = workloadHours;
    }
    
    public Teacher getTeacher() {
        return teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    
    @Override
    public String toString() {
        return "Course [id=" + id + ", modality=" + modality + ", name=" + name 
               + ", description=" + description + ", workloadHours=" + workloadHours 
               + ", teacher=" + (teacher != null ? teacher.getName() : "null") + "]";
    }
}
