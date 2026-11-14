package com.example.demo.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class PedidoRequest {
    private Long Clienteid;
    private List<Long> ProdutosIds;
}
