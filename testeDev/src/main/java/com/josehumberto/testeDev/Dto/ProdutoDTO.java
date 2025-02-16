package com.josehumberto.testeDev.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    private Long id;
    private String nome;
    private Double preco;
    private CategoriaDTO categoria;
}
