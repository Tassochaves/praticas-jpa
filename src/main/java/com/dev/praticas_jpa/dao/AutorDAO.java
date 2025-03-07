package com.dev.praticas_jpa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dev.praticas_jpa.entity.Autor;
import com.dev.praticas_jpa.entity.InforAutor;
import com.dev.praticas_jpa.projection.AutorInfoProjection;

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

    @Transactional(readOnly = false)
    public void atualizar(Autor autor){
        this.manager.merge(autor);
    }

    @Transactional(readOnly = false)
    public void excluir(Long id){
        this.manager.remove(this.manager.getReference(Autor.class, id));
    }

    @Transactional(readOnly = true)
    public Autor buscarPorId(Long id){
        return this.manager.find(Autor.class, id);

    }

    @Transactional(readOnly = true)
    public List<Autor> buscarTodos(){
        String query = "SELECT a FROM Autor a";
        return this.manager.createQuery(query, Autor.class).getResultList();

    }

    @Transactional(readOnly = true)
    public List<Autor> buscarTodosPorNomeOuSobrenome(String termo){
        String query = "SELECT a FROM Autor a WHERE a.nome LIKE :termo OR a.sobrenome LIKE :termo";
        return this.manager.createQuery(query, Autor.class)
        .setParameter("termo", "%" + termo + "%")
        .getResultList();

    }

    @Transactional(readOnly = true)
    public Long buscarTotalDeAutor(){
        String query = "SELECT COUNT(1) FROM Autor a";
        return this.manager.createQuery(query, Long.class).getSingleResult();

    }

    @Transactional(readOnly = false)
    public Autor salvarInfoAutor(InforAutor inforAutor, Long autorId){
         Autor autor = buscarPorId(autorId);
         autor.setInforAutor(inforAutor);

         return autor;
    }

    @Transactional(readOnly = true)
    public List<Autor> buscarPorCargo(String cargo){
        
        String query = """
                SELECT a FROM Autor a
                WHERE a.inforAutor.cargo LIKE :cargo
                ORDER BY a.nome ASC
                """;
        
        return this.manager.createQuery(query, Autor.class)
            .setParameter("cargo", "%" + cargo + "%").getResultList();
    }

    @Transactional(readOnly = true)
    public AutorInfoProjection buscarAutorInforPorId(Long id){
        
        String query = """
                SELECT new AutorInfoProjection(a.nome, a.sobrenome, a.inforAutor.cargo, a.inforAutor.bio)
                FROM Autor a    
                WHERE a.id = :id
                """;
        
        return this.manager.createQuery(query, AutorInfoProjection.class)
            .setParameter("id", id).getSingleResult();
    }
}
