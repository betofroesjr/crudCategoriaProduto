package com.josehumberto.testeDev.Controller;

import com.josehumberto.testeDev.Dto.CategoriaDTO;
import com.josehumberto.testeDev.Dto.ProdutoDTO;
import com.josehumberto.testeDev.Service.ProdutoService;
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
class ProdutoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProdutoService produtoService;

    @InjectMocks
    private ProdutoController produtoController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
    }

    @Test
    void listarTodos_DeveRetornarListaDeProdutos() throws Exception {
        List<ProdutoDTO> produtos = Arrays.asList(
                new ProdutoDTO(1L, "Produto 1", 100.0, new CategoriaDTO(1L,"Categoria 1")),
                new ProdutoDTO(2L, "Produto 2", 200.0, new CategoriaDTO(1L,"Categoria 2"))
        );

        when(produtoService.listarTodos()).thenReturn(produtos);

        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nome", is("Produto 1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].nome", is("Produto 2")));

        verify(produtoService, times(1)).listarTodos();
    }

    @Test
    void buscarPorId_DeveRetornarProduto_SeExistir() throws Exception {
        ProdutoDTO produto = new ProdutoDTO(1L, "Produto Teste", 100.0, new CategoriaDTO(1L,"Categoria 1"));

        when(produtoService.buscarPorId(1L)).thenReturn(produto);

        mockMvc.perform(get("/produtos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nome", is("Produto Teste")));

        verify(produtoService, times(1)).buscarPorId(1L);
    }

    @Test
    void listarPorCategoria_DeveRetornarProdutosDaCategoria() throws Exception {
        List<ProdutoDTO> produtos = Arrays.asList(
                new ProdutoDTO(1L, "Produto 1", 100.0, new CategoriaDTO(1L,"Categoria 1")),
                new ProdutoDTO(2L, "Produto 2", 150.0, new CategoriaDTO(1L,"Categoria 1"))
        );

        when(produtoService.listarPorCategoria(1L)).thenReturn(produtos);

        mockMvc.perform(get("/produtos/categoria/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));

        verify(produtoService, times(1)).listarPorCategoria(1L);
    }

    @Test
    void salvar_DeveRetornarProdutoSalvo() throws Exception {
        ProdutoDTO produtoSalvo = new ProdutoDTO(1L, "Novo Produto", 120.0, new CategoriaDTO(1L,"Categoria 1"));

        when(produtoService.salvar(any(ProdutoDTO.class))).thenReturn(produtoSalvo);

        mockMvc.perform(post("/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"Novo Produto\",\"preco\":120.0,\"categoriaId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nome", is("Novo Produto")));

        verify(produtoService, times(1)).salvar(any(ProdutoDTO.class));
    }

    @Test
    void salvarComCategoria_DeveRetornarProdutoComCategoria() throws Exception {
        ProdutoDTO produtoSalvo = new ProdutoDTO(1L, "Produto Cat", 90.0, new CategoriaDTO(1L,"Categoria 2"));

        when(produtoService.salvarComCategoria(eq(2L), any(ProdutoDTO.class))).thenReturn(produtoSalvo);
        
        mockMvc.perform(post("/produtos/categoria/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"Produto Cat\",\"preco\":90.0,\"categoriaId\":2}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nome", is("Produto Cat")));

        verify(produtoService, times(1)).salvarComCategoria(eq(2L), any(ProdutoDTO.class));
    }

    @Test
    void deletar_DeveRetornarNoContent() throws Exception {
        doNothing().when(produtoService).deletar(1L);

        mockMvc.perform(delete("/produtos/1"))
                .andExpect(status().isNoContent());

        verify(produtoService, times(1)).deletar(1L);
    }
}
