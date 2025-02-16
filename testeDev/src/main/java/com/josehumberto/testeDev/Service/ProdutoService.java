package com.josehumberto.testeDev.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josehumberto.testeDev.Dto.CategoriaDTO;
import com.josehumberto.testeDev.Dto.ProdutoDTO;
import com.josehumberto.testeDev.Exception.ResourceNotFoundException;
import com.josehumberto.testeDev.Repository.ProdutoRepository;
import com.josehumberto.testeDev.model.Produto;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaService categoriaService;

    public List<ProdutoDTO> listarTodos() {
        return produtoRepository.findAll()
        		.stream()
        		.map(produto -> convertEntity(produto))
        		.collect(Collectors.toList());
    }

    public ProdutoDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto com ID " + id + " não encontrado"));
        return convertEntity(produto);
    }

    public List<ProdutoDTO> listarPorCategoria(Long categoriaId) {
        return produtoRepository.findByCategoriaId(categoriaId)
        		.stream()
        		.map(produto -> convertEntity(produto))
        		.collect(Collectors.toList());
    }

    public ProdutoDTO salvar(ProdutoDTO produto) {
    	return convertEntity(produtoRepository.save(convertDto(produto)));
    }

    public ProdutoDTO salvarComCategoria(Long categoriaId, ProdutoDTO produto) {
        CategoriaDTO categoria = categoriaService.buscarPorId(categoriaId);
        produto.setCategoria(categoria);
        return convertEntity(produtoRepository.save(convertDto(produto)));
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }
    
    protected static ProdutoDTO convertEntity(Produto entidade) {
    	return ProdutoDTO.builder()
    			.id(entidade.getId())
    			.nome(entidade.getNome())
    			.preco(entidade.getPreco())
    			.categoria(CategoriaService.convertEntity(entidade.getCategoria()))
    			.build();
    }

    protected static Produto convertDto(ProdutoDTO entidade) {
    	return Produto.builder()
    			.id(entidade.getId())
    			.nome(entidade.getNome())
    			.preco(entidade.getPreco())
    			.categoria(CategoriaService.convertDto(entidade.getCategoria()))
    			.build();
    }
}