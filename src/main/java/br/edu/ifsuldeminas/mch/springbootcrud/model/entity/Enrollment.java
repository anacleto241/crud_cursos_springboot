package br.edu.ifsuldeminas.mch.springbootcrud.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    
    @NotNull(message = "Data de matrícula não pode ser nula.")
    private LocalDate enrollmentDate;
    
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status = EnrollmentStatus.ACTIVE;
    
    public Enrollment() {
        this.enrollmentDate = LocalDate.now();
        this.status = EnrollmentStatus.ACTIVE;
    }
    
    public Enrollment(User student, Course course, LocalDate enrollmentDate) {
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
    
    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }
    
    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
    
    public EnrollmentStatus getStatus() {
        return status;
    }
    
    public void setStatus(EnrollmentStatus status) {
        this.status = status;
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
