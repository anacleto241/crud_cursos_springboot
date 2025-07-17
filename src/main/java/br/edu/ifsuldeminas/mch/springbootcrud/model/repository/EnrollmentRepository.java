package br.edu.ifsuldeminas.mch.springbootcrud.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    
    List<Enrollment> findByStudentId(int studentId);
    
    List<Enrollment> findByCourseId(int courseId);
    
    @Query("SELECT e FROM Enrollment e WHERE e.student.id = ?1 AND e.course.id = ?2")
    Enrollment findByStudentIdAndCourseId(int studentId, int courseId);
    
    boolean existsByStudentIdAndCourseId(int studentId, int courseId);
}
