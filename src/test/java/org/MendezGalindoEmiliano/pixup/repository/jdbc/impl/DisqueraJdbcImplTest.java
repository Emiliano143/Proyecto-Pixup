package org.MendezGalindoEmiliano.pixup.repository.jdbc.impl;

import org.MendezGalindoEmiliano.pixup.model.Disquera;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.DisqueraJdbc;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class DisqueraJdbcImplTest {

    @Test
    void getInstance() {
        assertNotNull(DisqueraJdbcImpl.getInstance());
        System.out.println("instancia encontrada");
    }


    @Test
    void findAll() {
        DisqueraJdbc disqueraJdbc=DisqueraJdbcImpl.getInstance();
        List<Disquera> list=disqueraJdbc.findAll();
        assertNotNull(list);
        assertTrue(list.size()>=1);
        list.stream().forEach(System.out::println);
    }

    @Test
    void save(){
        Disquera disquera=new Disquera();
        boolean res=false;
        DisqueraJdbc disqueraJdbc=DisqueraJdbcImpl.getInstance();
        disquera.setDisquera("DisqueraGuardado");
        res= disqueraJdbc.save(disquera);
        assertEquals(true, res);
        System.out.println("guardado");

    }

    @Test
    void update(){
        Disquera disquera=new Disquera();
        boolean res=false;
        disquera.setDisquera("DisqueraModificado");
        disquera.setId(4);
        DisqueraJdbc disqueraJdbc=DisqueraJdbcImpl.getInstance();
        res= disqueraJdbc.update(disquera);
        assertEquals(true, res);
        System.out.println("cambiado");
    }

    @Test
    void delete(){
        Disquera disquera=new Disquera();
        boolean res=false;
        disquera.setId(4);
        DisqueraJdbc disqueraJdbc=DisqueraJdbcImpl.getInstance();
        res= disqueraJdbc.delete(disquera);
        assertEquals(true, res);
        System.out.println("eliminado");
    }

    @Test
    void findById(){
        DisqueraJdbc disqueraJdbc=DisqueraJdbcImpl.getInstance();
        Disquera disquera=disqueraJdbc.findById(3);
        assertNotNull(disquera);
        assertTrue("Dis".equals(disquera.getDisquera()));
        assertEquals(3, disquera.getId());
        System.out.println(disquera);
    }
}