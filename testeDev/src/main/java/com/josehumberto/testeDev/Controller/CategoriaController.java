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

import com.josehumberto.testeDev.Dto.CategoriaDTO;
import com.josehumberto.testeDev.Service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4200/dashboard"})
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDTO> listarTodas() {
        return categoriaService.listarTodas();
    }

    @GetMapping("/{id}")
    public CategoriaDTO buscarPorId(@PathVariable Long id) {
        return categoriaService.buscarPorId(id);
    }

    @PostMapping
    public CategoriaDTO salvar(@RequestBody CategoriaDTO categoria) {
        return categoriaService.salvar(categoria);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma categoria pelo ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build(); // ✅ Retorna 204 No Content
    }

}