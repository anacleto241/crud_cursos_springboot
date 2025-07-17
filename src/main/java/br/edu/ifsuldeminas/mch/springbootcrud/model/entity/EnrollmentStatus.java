package br.edu.ifsuldeminas.mch.springbootcrud.model.entity;

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
