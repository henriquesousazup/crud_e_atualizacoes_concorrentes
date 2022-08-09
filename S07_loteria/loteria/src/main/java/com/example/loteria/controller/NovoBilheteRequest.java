package com.example.loteria.controller;

import com.example.loteria.model.Bilhete;
import com.example.loteria.model.Sorteio;
import com.example.loteria.repository.SorteioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.*;

import static com.example.loteria.util.StringUtils.hash;

public class NovoBilheteRequest {

    @NotBlank
    private String nome;

    @NotBlank
    @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}")
    private String celular;

    @NotNull
    private Long sorteioId;

    @NotNull
    @Positive
    @Min(1) @Max(9999)
    private Integer numeroDaSorte;
    
    public Bilhete toModel(SorteioRepository sorteioRepository){

        Sorteio sorteio = sorteioRepository.findById(sorteioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Sorteio não encontrado."));

        return new Bilhete(nome,celular,numeroDaSorte, sorteio);
    }
    
    public String getNome() {
        return nome;
    }

    public String getCelular() {
        return celular;
    }

    public String getHashDoCelular(){
        return hash(celular);
    }

    public Long getSorteioId() {
        return sorteioId;
    }

    public Integer getNumeroDaSorte() {
        return numeroDaSorte;
    }
}
