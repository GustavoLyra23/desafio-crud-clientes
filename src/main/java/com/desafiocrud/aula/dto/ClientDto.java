package com.desafiocrud.aula.dto;

import com.desafiocrud.aula.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class ClientDto {

    private Long id;

    @NotBlank(message = "nome cant be empty")
    private String nome;

    private String cpf;
    private Double income;

    @PastOrPresent(message = "birthDate must be present or past")
    private LocalDate birthDate;
    private Integer children;


    public ClientDto() {
    }

    public ClientDto(Client client) {
        id = client.getId();
        nome = client.getNome();
        cpf = client.getCpf();
        income = client.getIncome();
        birthDate = client.getBirthDate();
        children = client.getChildren();
    }

    public Long getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }


    public String getCpf() {
        return cpf;
    }


    public Double getIncome() {
        return income;
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }


    public Integer getChildren() {
        return children;
    }

}