package com.josehumberto.testeDev.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josehumberto.testeDev.Dto.ProdutoDTO;
import com.josehumberto.testeDev.Service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4200/dashboard"})
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Operation(summary = "Lista todos os produtos")
    @GetMapping
    public List<ProdutoDTO> listarTodos() {
        return produtoService.listarTodos();
    }

    @Operation(summary = "Busca um produto pelo ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Produto encontrado"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping("/{id}")
    public ProdutoDTO buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @Operation(summary = "Lista todos os produtos por categoria")
    @GetMapping("/categoria/{categoriaId}")
    public List<ProdutoDTO> listarPorCategoria(@PathVariable Long categoriaId) {
        return produtoService.listarPorCategoria(categoriaId);
    }

    @Operation(summary =  "Cadastra um novo produto")
    @PostMapping
    public ProdutoDTO salvar(@RequestBody ProdutoDTO produto) {
        return produtoService.salvar(produto);
    }

    @PostMapping("/categoria/{categoriaId}")
    public ProdutoDTO salvarComCategoria(@PathVariable Long categoriaId, @RequestBody ProdutoDTO produto) {
        return produtoService.salvarComCategoria(categoriaId, produto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um produto pelo ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}