package org.MendezGalindoEmiliano.pixup.repository.jdbc.impl;

import org.MendezGalindoEmiliano.pixup.model.GeneroMusical;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.GeneroMusicalJdbc;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeneroMusicalJdbcImplTest {

    @Test
    void getInstance() {
        assertNotNull(GeneroMusicalJdbcImpl.getInstance());
        System.out.println("instancia encontrada");
    }

    @Test
    void findAll() {
        GeneroMusicalJdbc generoMusicalJdbc=GeneroMusicalJdbcImpl.getInstance();
        List<GeneroMusical> list=generoMusicalJdbc.findAll();
        assertNotNull(list);
        assertTrue(list.size()>=1);
        list.stream().forEach(System.out::println);
    }

    @Test
    void save(){
        GeneroMusical generoMusical=new GeneroMusical();
        boolean res=false;
        GeneroMusicalJdbc generoMusicalJdbc=GeneroMusicalJdbcImpl.getInstance();
        generoMusical.setGeneroMusical("generoGuardado");
        res= generoMusicalJdbc.save(generoMusical);
        assertEquals(true, res);
        System.out.println("guardado");

    }

    @Test
    void update(){
        GeneroMusical generoMusical=new GeneroMusical();
        boolean res=false;
        generoMusical.setGeneroMusical("generoModiicado");
        generoMusical.setId(5);
        GeneroMusicalJdbc generoMusicalJdbc=GeneroMusicalJdbcImpl.getInstance();
        res= generoMusicalJdbc.update(generoMusical);
        assertEquals(true, res);
        System.out.println("cambiado");
    }

    @Test
    void delete(){
        GeneroMusical generoMusical=new GeneroMusical();
        boolean res=false;
        generoMusical.setId(5);
        GeneroMusicalJdbc generoMusicalJdbc=GeneroMusicalJdbcImpl.getInstance();
        res= generoMusicalJdbc.delete(generoMusical);
        assertEquals(true, res);
        System.out.println("eliminado");
    }

    @Test
    void findById(){
        GeneroMusicalJdbc generoMusicalJdbc=GeneroMusicalJdbcImpl.getInstance();
        GeneroMusical generoMusical=generoMusicalJdbc.findById(4);
        assertNotNull(generoMusical);
        assertTrue("gen".equals(generoMusical.getGeneroMusical()));
        assertEquals(4, generoMusical.getId());
        System.out.println(generoMusical);
    }
}