package org.MendezGalindoEmiliano.pixup.gui.consola;

import org.MendezGalindoEmiliano.pixup.model.GeneroMusical;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.GeneroMusicalJdbc;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.impl.GeneroMusicalJdbcImpl;
import org.MendezGalindoEmiliano.pixup.util.ReadUtil;

import java.util.List;

public class GeneroMusicalCatalogo extends Catalogos<GeneroMusical> {
    private static GeneroMusicalCatalogo GeneroMusicalCatalogo;

    private GeneroMusicalCatalogo() {
    }

    public static GeneroMusicalCatalogo getInstance() {
        if (GeneroMusicalCatalogo == null) {
            GeneroMusicalCatalogo = new GeneroMusicalCatalogo();
        }
        return GeneroMusicalCatalogo;
    }

    @Override
    public GeneroMusical newT() {
        return new GeneroMusical();
    }

    @Override
    public boolean removeT(GeneroMusical generoMusical) {
        System.out.println("Dame el id del generoMusical:");
        GeneroMusicalJdbc generoMusicalJdbc = GeneroMusicalJdbcImpl.getInstance();

        generoMusical.setId(ReadUtil.readInt());
        boolean res = generoMusicalJdbc.delete(generoMusical);

        return res;
    }


@Override
public boolean editT(GeneroMusical generoMusical) {
    System.out.println("Dame el id del generoMusical:");
    GeneroMusicalJdbc generoMusicalJdbc = GeneroMusicalJdbcImpl.getInstance();
    generoMusical.setId(ReadUtil.readInt());
    System.out.println("Dame el nuevo nombre");
    generoMusical.setGeneroMusical(ReadUtil.read());
    boolean res = generoMusicalJdbc.update(generoMusical);

    return res;
}

@Override
public boolean processNewT1(GeneroMusical generoMusical) {
    System.out.println("Dame el nombre del generoMusical:");
    GeneroMusicalJdbc generoMusicalJdbc = GeneroMusicalJdbcImpl.getInstance();

    generoMusical.setGeneroMusical(ReadUtil.read());
    boolean res = generoMusicalJdbc.save(generoMusical);

    return res;
}

@Override
public void processEditT(GeneroMusical GeneroMusical) {
    System.out.println("El genero musical es: " + GeneroMusical.getGeneroMusical());
    System.out.println("El id del genero musical es: " + GeneroMusical.getId());
    System.out.println("Dame el nuevo genero musical");
    GeneroMusical.setGeneroMusical(ReadUtil.read());
}

@Override
public List<GeneroMusical> processList() {
    return List.of();
}

@Override
public String getTitulo() {
    return "Generos musicales";
}
}
