package com.java.java.model;

import com.java.java.model.enums.JobPosition;
import com.java.java.utils.CPFValidator;

import java.util.Date;

public class User {

    private Integer id;
    private String cpf;
    private String name;
    private Date dateOfBirth;
    private JobPosition jobPosition;

    public User(Integer id, String cpf, String name, Date dateOfBirth, JobPosition jobPosition) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.jobPosition = jobPosition;
    }

    public User(String cpf, String name, Date dateOfBirth, JobPosition jobPosition) {
        this.id = null;
        this.cpf = cpf;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.jobPosition = jobPosition;
    }

    public Integer getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public JobPosition getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(JobPosition jobPosition) {
        this.jobPosition = jobPosition;
    }

    public boolean isCPFValid() {
        return CPFValidator.validarCPF(this.cpf);
    }

    @Override
    public String toString() {
        return this.id + " - " + this.cpf + " - " + this.name + " - " + this.jobPosition;
    }
}
