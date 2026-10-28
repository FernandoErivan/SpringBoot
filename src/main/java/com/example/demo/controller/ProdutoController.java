package com.example.demo.controller;

import com.example.demo.service.ProdutoService;

import com.example.demo.model.ProdutoModel;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/loja")
@RequiredArgsConstructor 
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping("/newProduto")
    public ResponseEntity<ProdutoModel> criarProduto(
            @Valid @RequestBody ProdutoModel produto) {

        ProdutoModel produtoSalvo = produtoService.createProduto(produto);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    @GetMapping("/buscarProdutos")
    public ResponseEntity<List<ProdutoModel>> listarProdutos() {

        List<ProdutoModel> produtos = produtoService.listarProdutos();

        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<ProdutoModel> buscarProdutoPorId(@PathVariable Long id) {

        ProdutoModel produto = produtoService.buscarProdutoPorId(id);

        return ResponseEntity.ok(produto);
    }

    @PutMapping("/produto/{id}")
    public ResponseEntity<ProdutoModel> atualizarProduto(@PathVariable Long id,
            @Valid @RequestBody ProdutoModel produto) {

        ProdutoModel produtoAtualizado = produtoService.atualizarProduto(id, produto);

        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/produto/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
        produtoService.excluirProduto(id);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> tratarProdutoNaoEncontrado(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}
