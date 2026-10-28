package com.example.demo.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.ProdutoModel;
import com.example.demo.repository.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;

    public ProdutoModel createProduto(ProdutoModel produto) {
        return produtoRepository.save(produto);
    }

    public List<ProdutoModel> listarProdutos() {
        return produtoRepository.findAll();
    }

    public ProdutoModel buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado com ID: " + id));
    }

    public ProdutoModel atualizarProduto(Long id, ProdutoModel produto) {
        ProdutoModel antigo = produtoRepository.findById(id).get();

        antigo.setName(produto.getName());
        antigo.setPrice(produto.getPrice());
        antigo.setAmount(produto.getAmount());

        return produtoRepository.save(antigo);
    }

    public void excluirProduto(Long id) {
    ProdutoModel produto = produtoRepository.findById(id)
            .orElseThrow(() ->
                    new EntityNotFoundException(
                            "Produto não encontrado com ID: " + id
                    ));

    produtoRepository.delete(produto);
    }


}