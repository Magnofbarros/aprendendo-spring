package com.javanauta.aprendendospring.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name= "cidade", length = 150)
    private String cidade;
    @Column(name = "rua")
    private  String rua;
    @Column(name = "barrio")
    private String barrio;
    @Column(name = "numero")
    private int numero;
    @Column(name = "cep", length = 10)
    private int cep;
    @Column(name = "pontoDeReferencia", length = 40)
    private String pontoDeReferencia;
    @Column(name = "estado", length = 2)
    private String estado;
}
