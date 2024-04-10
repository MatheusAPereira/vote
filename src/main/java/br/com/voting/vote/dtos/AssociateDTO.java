package br.com.voting.vote.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public class AssociateDTO {

    private String id;
    @NotBlank (message = "Nome é obrigatório")
    private String name;
    @NotBlank (message = "CPF é obrigatório")
    @CPF (message = "CPF inválido")
    private String cpf;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setNome(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
