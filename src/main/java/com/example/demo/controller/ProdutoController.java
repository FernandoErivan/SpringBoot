package com.example.demo.controller;

import com.example.demo.service.ProdutoService;

import com.example.demo.model.ProdutoModel;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loja")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping("/newProduto")
    public ProdutoModel criarNovoProduto(@RequestBody ProdutoModel produto) {
        return produtoService.createProduto(produto);
    }

    @GetMapping("/buscarProdutos")
    public List<ProdutoModel> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @GetMapping("/produto/{id}")
    public ProdutoModel produtoById(@PathVariable Long id) {
        return produtoService.buscarProdutoPorId(id);
    }

    @PutMapping("/produto/{id}")
    public ProdutoModel atualizarProdutoPorId(@PathVariable Long id) {
        return produtoService.atualizarProduto(id, null);
    }

    @DeleteMapping("/produto/{id}")
    public void excluirProdutoPorId(@PathVariable Long id) {
        produtoService.excluirProduto(id);
    }

}
