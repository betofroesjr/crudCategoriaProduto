package com.josehumberto.testeDev.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josehumberto.testeDev.Dto.CategoriaDTO;
import com.josehumberto.testeDev.Exception.ResourceNotFoundException;
import com.josehumberto.testeDev.Repository.CategoriaRepository;
import com.josehumberto.testeDev.model.Categoria;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> listarTodas() {
        return categoriaRepository.findAll()
        			.stream()
        			.map(categoria -> convertEntity(categoria))
        			.collect(Collectors.toList());
    }

    public CategoriaDTO buscarPorId(Long id) {
    	Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoria com ID " + id + " não encontrado"));   	
        return convertEntity(categoria);
    }

    public CategoriaDTO salvar(CategoriaDTO categoria) {
        return convertEntity(categoriaRepository.save(convertDto(categoria)));
    }

    public void deletar(Long id) {
        categoriaRepository.deleteById(id);
    }
    
    protected static CategoriaDTO convertEntity(Categoria entidade) {
    	return CategoriaDTO.builder()
    			.id(entidade.getId())
    			.nome(entidade.getNome())
    			.build();
    }

    protected static Categoria convertDto(CategoriaDTO entidade) {
    	return Categoria.builder()
    			.id(entidade.getId())
    			.nome(entidade.getNome())
    			.build();
    }
}