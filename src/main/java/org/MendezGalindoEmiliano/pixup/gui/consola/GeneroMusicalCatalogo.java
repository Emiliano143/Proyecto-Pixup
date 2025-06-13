package org.MendezGalindoEmiliano.pixup.gui.consola;

import org.MendezGalindoEmiliano.pixup.model.GeneroMusical;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.GeneroMusicalJdbc;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.impl.GeneroMusicalJdbcImpl;
import org.MendezGalindoEmiliano.pixup.util.ReadUtil;

import java.util.ArrayList;
import java.util.List;

public class GeneroMusicalCatalogo extends Catalogos<GeneroMusical>
{
    private static GeneroMusicalCatalogo GeneroMusicalCatalogo;

    private GeneroMusicalCatalogo()
    {
    }
    public static GeneroMusicalCatalogo getInstance( )
    {
        if(GeneroMusicalCatalogo==null)
        {
            GeneroMusicalCatalogo = new GeneroMusicalCatalogo();
        }
        return GeneroMusicalCatalogo;
    }

    @Override
    public GeneroMusical newT() {
        return new GeneroMusical();
    }

    @Override
    public boolean processNewT(GeneroMusical GeneroMusical) {
        System.out.println("Dame el genero musical");
        GeneroMusical.setGeneroMusical(ReadUtil.read());
        return true;
    }

    @Override
    public boolean processNewT1(GeneroMusical g) {
        System.out.println("Dame el nombre del genero musical:");
        GeneroMusicalJdbc generoMusicalJdbc = GeneroMusicalJdbcImpl.getInstance();

        g.setGeneroMusical(ReadUtil.read());
        boolean res = generoMusicalJdbc.save(g);

        return res;
    }

    @Override
    public void processEditT(GeneroMusical generoMusical) {
        System.out.println("El genero musical es: "+generoMusical.getGeneroMusical());
        System.out.println("Dame el nuevo nombre del genero musical");
        generoMusical.setGeneroMusical(ReadUtil.read());

        GeneroMusicalJdbc disqueraJdbc = GeneroMusicalJdbcImpl.getInstance();

        disqueraJdbc.update(generoMusical);
    }

    @Override
    public void processRemoveT(GeneroMusical generoMusical)
    {
        System.out.println("El nombre del genero musical es: " + generoMusical.getGeneroMusical());

        GeneroMusicalJdbc generoMusicalJdbc = GeneroMusicalJdbcImpl.getInstance();

        generoMusicalJdbc.delete(generoMusical);
    }

    @Override
    public void processFindByIdT(int id)
    {
        GeneroMusicalJdbc generoMusicalJdbc = GeneroMusicalJdbcImpl.getInstance();

        GeneroMusical generoMusical = generoMusicalJdbc.findById(id);

        System.out.println("Genero Musical encontrada:");
        System.out.println(generoMusical.toString());
    }

    @Override
    public List<GeneroMusical> processList() {
        GeneroMusicalJdbc generoMusicalJdbc=GeneroMusicalJdbcImpl.getInstance();
        List<GeneroMusical> list = generoMusicalJdbc.findAll();

        if (list == null) {
            System.out.println("Error al ejecutar la consulta `findAll()`. Verifica la conexión y consulta.");
            return new ArrayList<>();
        }

        if (list.isEmpty()) {
            System.out.println("La base de datos está conectada, pero no hay registros en `tbl_disquera`.");
        }

        return list;
    }

    @Override
    public String getTitulo() {
        return "Generos musicales";
    }
}
