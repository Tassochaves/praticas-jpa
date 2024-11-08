package com.dev.praticas_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
