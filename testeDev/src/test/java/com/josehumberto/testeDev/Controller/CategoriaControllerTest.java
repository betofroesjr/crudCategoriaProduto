package com.josehumberto.testeDev.Controller;

import com.josehumberto.testeDev.Dto.CategoriaDTO;
import com.josehumberto.testeDev.Service.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CategoriaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CategoriaService categoriaService;

    @InjectMocks
    private CategoriaController categoriaController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoriaController).build();
    }

    @Test
    void listarTodas_DeveRetornarListaDeCategorias() throws Exception {
        List<CategoriaDTO> categorias = Arrays.asList(
                new CategoriaDTO(1L, "Categoria 1"),
                new CategoriaDTO(2L, "Categoria 2")
        );

        when(categoriaService.listarTodas()).thenReturn(categorias);

        mockMvc.perform(get("/categorias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nome", is("Categoria 1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].nome", is("Categoria 2")));

        verify(categoriaService, times(1)).listarTodas();
    }

    @Test
    void buscarPorId_DeveRetornarCategoria_SeExistir() throws Exception {
        CategoriaDTO categoria = new CategoriaDTO(1L, "Categoria Teste");

        when(categoriaService.buscarPorId(1L)).thenReturn(categoria);

        mockMvc.perform(get("/categorias/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nome", is("Categoria Teste")));

        verify(categoriaService, times(1)).buscarPorId(1L);
    }

    @Test
    void salvar_DeveRetornarCategoriaSalva() throws Exception {
        CategoriaDTO categoria = new CategoriaDTO(null, "Nova Categoria");
        CategoriaDTO categoriaSalva = new CategoriaDTO(1L, "Nova Categoria");

        when(categoriaService.salvar(any(CategoriaDTO.class))).thenReturn(categoriaSalva);

        mockMvc.perform(post("/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"Nova Categoria\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nome", is("Nova Categoria")));

        verify(categoriaService, times(1)).salvar(any(CategoriaDTO.class));
    }

    @Test
    void deletar_DeveRetornarNoContent() throws Exception {
        doNothing().when(categoriaService).deletar(1L);

        mockMvc.perform(delete("/categorias/1"))
                .andExpect(status().isNoContent()); // ✅ Verifica que retorna 204

        verify(categoriaService, times(1)).deletar(1L);
    }
}
