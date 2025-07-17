package br.edu.ifsuldeminas.mch.springbootcrud.model.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "courses")
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @NotBlank(message = "Modalidade não pode ser vazia.")
    @Size(min = 2, max = 50, message = "Modalidade deve ter entre 2 e 50 caracteres.")
    private String modality;
    
    @NotBlank(message = "Nome do curso não pode ser vazio.")
    @Size(min = 2, max = 100, message = "Nome do curso deve ter entre 2 e 100 caracteres.")
    private String name;
    
    @NotNull(message = "Data de início não pode ser nula.")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @NotNull(message = "Data de término não pode ser nula.")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @NotNull(message = "Professor deve ser selecionado.")
    private Teacher teacher;
    
    public Course() {
        setModality("");
        setName("");
        setStartDate(new Date());
        setEndDate(new Date());
    }
    
    public Course(String modality, String name, Date startDate, Date endDate, Teacher teacher) {
        this.modality = modality;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teacher = teacher;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
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
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
               + ", startDate=" + startDate + ", endDate=" + endDate 
               + ", teacher=" + (teacher != null ? teacher.getName() : "null") + "]";
    }
}
