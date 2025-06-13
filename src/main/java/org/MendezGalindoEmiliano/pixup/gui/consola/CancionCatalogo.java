package org.MendezGalindoEmiliano.pixup.gui.consola;

import org.MendezGalindoEmiliano.pixup.model.*;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.CancionJdbc;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.CancionJdbc;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.DiscoJdbc;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.impl.CancionJdbcImpl;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.impl.CancionJdbcImpl;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.impl.DiscoJdbcImpl;
import org.MendezGalindoEmiliano.pixup.util.ReadUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CancionCatalogo extends Catalogos<Cancion>
{
    public static CancionCatalogo cancionCatalogo;
    private CancionCatalogo( )
    {
        super();
    }

    public static CancionCatalogo getInstance( )
    {
        if(cancionCatalogo==null)
        {
            cancionCatalogo = new CancionCatalogo();
        }
        return cancionCatalogo;
    }

    @Override
    public Cancion newT()
    {
        return new Cancion( );
    }

    @Override
    public boolean processNewT(Cancion Cancion)
    {
        System.out.println("Teclee un Cancion" );
        Cancion.setTitulo( ReadUtil.read( ) );
        return true;
    }

    @Override
    public boolean processNewT1(Cancion cancion) {
        System.out.println("Dame el nombre del cancion:");
        CancionJdbc cancionJdbc = CancionJdbcImpl.getInstance();

        cancion.setTitulo(ReadUtil.read());
        System.out.println("Dame la duracion de la cancion:");
        cancion.setDuracion(ReadUtil.read());
        System.out.println("Dame el id del disco de la cancion:");
        cancion.setDisco(new Disco());
        cancion.getDisco().setId(DiscoCatalgo.nulo());
        boolean res = cancionJdbc.save(cancion);

        return res;
    }

    @Override
    public void processEditT(Cancion cancion)
    {
        System.out.println("El nombre del cancion es: " + cancion.getTitulo());
        System.out.println("Dame el nuevo nombre del cancion");
        CancionJdbc cancionJdbc = CancionJdbcImpl.getInstance();
        cancion.setTitulo(ReadUtil.read());
        System.out.println("Dame la duracion de la cancion:");
        cancion.setDuracion(ReadUtil.read());
        System.out.println("Dame el id del disco de la cancion:");
        cancion.setDisco(new Disco());
        cancion.getDisco().setId(DiscoCatalgo.nulo());
        cancionJdbc.update(cancion);
    }

    @Override
    public void processRemoveT(Cancion cancion) {
        System.out.println("El nombre de la cancion es: " + cancion.getTitulo());

        CancionJdbc cancionJdbc = CancionJdbcImpl.getInstance();

        cancionJdbc.delete(cancion);
    }

    @Override
    public void processFindByIdT(int id)
    {
        CancionJdbc cancionJdbc = CancionJdbcImpl.getInstance();

        Cancion cancion = cancionJdbc.findById(id);

        System.out.println("Cancion encontrada:");
        System.out.println(cancion.toString());
    }

    @Override
    public List<Cancion> processList() {
        CancionJdbc cancionJdbc=CancionJdbcImpl.getInstance();
        List<Cancion> list = cancionJdbc.findAll();

        if (list == null) {
            System.out.println("Error al ejecutar la consulta `findAll()`. Verifica la conexión y consulta.");
            return new ArrayList<>();
        }

        if (list.isEmpty()) {
            System.out.println("La base de datos está conectada, pero no hay registros en `tbl_disco`.");
        }

        return list;
    }

    @Override
    public String getTitulo() {
        return "Canciones";
    }
}
