package br.edu.ifsuldeminas.mch.springbootcrud.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Address;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Course;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Enrollment;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Teacher;
import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.User;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.AddressRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.CourseRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.EnrollmentRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.TeacherRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.UserRepository;
import jakarta.transaction.Transactional;

@Component
@Transactional
public class AtSystemStartup implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Address aEmerson = new Address();
		aEmerson.setNumber(123);
		aEmerson.setPlace("Rua A");
		aEmerson.setZipCode("136000");
		addressRepository.save(aEmerson);
		
		Address aNoe = new Address();
		aNoe.setNumber(100);
		aNoe.setPlace("Rua B");
		aNoe.setZipCode("136888");
		addressRepository.save(aNoe);
		
		Address aLu = new Address();
		aLu.setNumber(101);
		aLu.setPlace("Rua L");
		aLu.setZipCode("000888");
		addressRepository.save(aLu);
		
		addressRepository.flush();
		
		User emerson = new User();
		emerson.setName("Emerson A. Carvalho");
		emerson.setGender(User.Gender.M);
		emerson.setEmail("emerson@mail.com");
		emerson.setAddress(aEmerson);
		
		User luiza = new User();
		luiza.setName("Luiza O. Carvalho");
		luiza.setGender(User.Gender.F);
		luiza.setEmail("lu@mail.com");
		luiza.setAddress(aLu);
		
		User noe = new User();
		noe.setName("Noe L. Carvalho");
		noe.setGender(User.Gender.M);
		noe.setEmail("noe@mail.com");
		noe.setAddress(aNoe);
		
		userRepository.save(emerson);
		userRepository.save(luiza);
		userRepository.save(noe);
		
		// Criando professores de exemplo
		Teacher prof1 = new Teacher();
		prof1.setName("Dr. João Silva");
		prof1.setSpecialization("Ciência da Computação");
		prof1.setEmail("joao.silva@academia.com");
		prof1.setPhone("(11) 99999-1111");
		
		Teacher prof2 = new Teacher();
		prof2.setName("Dra. Maria Santos");
		prof2.setSpecialization("Matemática Aplicada");
		prof2.setEmail("maria.santos@academia.com");
		prof2.setPhone("(11) 99999-2222");
		
		Teacher prof3 = new Teacher();
		prof3.setName("Prof. Carlos Oliveira");
		prof3.setSpecialization("Engenharia de Software");
		prof3.setEmail("carlos.oliveira@academia.com");
		prof3.setPhone("(11) 99999-3333");
		
		teacherRepository.save(prof1);
		teacherRepository.save(prof2);
		teacherRepository.save(prof3);
		
		// Criando cursos de exemplo
		Course curso1 = new Course();
		curso1.setName("Programação Java Avançada");
		curso1.setModality("Presencial");
		curso1.setDescription("Curso completo de programação Java com Spring Boot, frameworks e boas práticas de desenvolvimento orientado a objetos.");
		curso1.setWorkloadHours(120);
		curso1.setTeacher(prof1);
		
		Course curso2 = new Course();
		curso2.setName("Algoritmos e Estruturas de Dados");
		curso2.setModality("Online");
		curso2.setDescription("Fundamentos de algoritmos, estruturas de dados lineares e não-lineares, análise de complexidade e otimização.");
		curso2.setWorkloadHours(80);
		curso2.setTeacher(prof2);
		
		Course curso3 = new Course();
		curso3.setName("Desenvolvimento Web Full Stack");
		curso3.setModality("Híbrido");
		curso3.setDescription("Desenvolvimento completo de aplicações web utilizando tecnologias modernas como React, Node.js, APIs REST e Deploy na nuvem.");
		curso3.setWorkloadHours(160);
		curso3.setTeacher(prof3);
		
		courseRepository.save(curso1);
		courseRepository.save(curso2);
		courseRepository.save(curso3);
		
		// Criando matrículas de exemplo
		Enrollment matricula1 = new Enrollment();
		matricula1.setStudent(emerson);
		matricula1.setCourse(curso1);
		matricula1.setEnrollmentDate(LocalDate.now());
		
		Enrollment matricula2 = new Enrollment();
		matricula2.setStudent(luiza);
		matricula2.setCourse(curso2);
		matricula2.setEnrollmentDate(LocalDate.now());
		
		Enrollment matricula3 = new Enrollment();
		matricula3.setStudent(noe);
		matricula3.setCourse(curso3);
		matricula3.setEnrollmentDate(LocalDate.now());
		
		enrollmentRepository.save(matricula1);
		enrollmentRepository.save(matricula2);
		enrollmentRepository.save(matricula3);
	}
}
