package br.edu.ifsuldeminas.mch.springbootcrud.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "teachers")
public class Teacher {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank(message = "Nome do professor não pode ser vazio.")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres.")
    private String name;
    
    @NotBlank(message = "Especialização não pode ser vazia.")
    @Size(min = 2, max = 150, message = "Especialização deve ter entre 2 e 150 caracteres.")
    private String specialization;
    
    @NotBlank(message = "Email do professor não pode ser vazio.")
    @Email(message = "Email inválido.")
    private String email;
    
    @NotBlank(message = "Telefone não pode ser vazio.")
    @Size(min = 10, max = 15, message = "Telefone deve ter entre 10 e 15 caracteres.")
    private String phone;
    
    public Teacher() {
        setName("");
        setSpecialization("");
        setEmail("");
        setPhone("");
    }
    
    public Teacher(String name, String specialization, String email, String phone) {
        this.name = name;
        this.specialization = specialization;
        this.email = email;
        this.phone = phone;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSpecialization() {
        return specialization;
    }
    
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Override
    public String toString() {
        return "Teacher [id=" + id + ", name=" + name + ", specialization=" + specialization 
               + ", email=" + email + ", phone=" + phone + "]";
    }
}
