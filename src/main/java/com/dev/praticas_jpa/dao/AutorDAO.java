package com.dev.praticas_jpa.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dev.praticas_jpa.entity.Autor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class AutorDAO {

    @PersistenceContext
    private EntityManager manager;

    @Transactional(readOnly = false)
    public void salvar(Autor autor){
        this.manager.persist(autor);
    }
}
