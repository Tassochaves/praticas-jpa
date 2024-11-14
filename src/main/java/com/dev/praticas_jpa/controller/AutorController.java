package com.dev.praticas_jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.praticas_jpa.dao.AutorDAO;
import com.dev.praticas_jpa.entity.Autor;

@RestController
@RequestMapping("autores")
public class AutorController {

    @Autowired
    private AutorDAO autorDAO;

    @PostMapping
    public Autor salvar(@RequestBody Autor autor){
        autorDAO.salvar(autor);

        return autor;
    }

    @PutMapping
    public Autor atualizar(@RequestBody Autor autor){
        autorDAO.atualizar(autor);

        return autor;
    }

    @DeleteMapping("{id}")
    public String excluir(@PathVariable Long id){
        autorDAO.excluir(id);

        return "Autor id: " + id + " foi excluido com sucesso!";
    }

    @GetMapping("{id}")
    public Autor retornaAutor(@PathVariable Long id){
        return autorDAO.buscarPorId(id);
    }

    @GetMapping()
    public List<Autor> retornaTodos(){
        return autorDAO.buscarTodos();
    }

    @GetMapping("nome-ou-sobrenome")
    public List<Autor> retornaTodosPorNomeOuSobrenome(@RequestParam String termo){
        return autorDAO.buscarTodosPorNomeOuSobrenome(termo);
    }

    @GetMapping("total-de-autores")
    public Long retornaTotalDeAutores(){
        return autorDAO.buscarTotalDeAutor();
    }
}
