package br.edu.ifsuldeminas.mch.springbootcrud.model.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "enrollments")
public class Enrollment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "student_id")
    @NotNull(message = "Aluno deve ser selecionado.")
    private User student;
    
    @ManyToOne
    @JoinColumn(name = "course_id")
    @NotNull(message = "Curso deve ser selecionado.")
    private Course course;
    
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Data de matrícula não pode ser nula.")
    private Date enrollmentDate;
    
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status = EnrollmentStatus.ACTIVE;
    
    private Double finalGrade;
    
    public Enrollment() {
        this.enrollmentDate = new Date();
        this.status = EnrollmentStatus.ACTIVE;
    }
    
    public Enrollment(User student, Course course, Date enrollmentDate) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.status = EnrollmentStatus.ACTIVE;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public User getStudent() {
        return student;
    }
    
    public void setStudent(User student) {
        this.student = student;
    }
    
    public Course getCourse() {
        return course;
    }
    
    public void setCourse(Course course) {
        this.course = course;
    }
    
    public Date getEnrollmentDate() {
        return enrollmentDate;
    }
    
    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
    
    public EnrollmentStatus getStatus() {
        return status;
    }
    
    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }
    
    public Double getFinalGrade() {
        return finalGrade;
    }
    
    public void setFinalGrade(Double finalGrade) {
        this.finalGrade = finalGrade;
    }
    
    @Override
    public String toString() {
        return "Enrollment [id=" + id + ", student=" + (student != null ? student.getName() : "null") 
               + ", course=" + (course != null ? course.getName() : "null") 
               + ", enrollmentDate=" + enrollmentDate + ", status=" + status + "]";
    }
    
    public enum EnrollmentStatus {
        ACTIVE("Ativo"),
        COMPLETED("Concluído"),
        CANCELLED("Cancelado"),
        SUSPENDED("Suspenso");
        
        private String description;
        
        private EnrollmentStatus(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
}
