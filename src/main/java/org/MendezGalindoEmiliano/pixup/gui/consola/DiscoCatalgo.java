package org.MendezGalindoEmiliano.pixup.gui.consola;

import org.MendezGalindoEmiliano.pixup.model.*;
import org.MendezGalindoEmiliano.pixup.model.Disco;
import org.MendezGalindoEmiliano.pixup.model.Disco;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.DiscoJdbc;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.DiscoJdbc;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.impl.DiscoJdbcImpl;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.impl.DiscoJdbcImpl;
import org.MendezGalindoEmiliano.pixup.util.ReadUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DiscoCatalgo extends Catalogos<Disco>
{
    public static DiscoCatalgo discoCatalogo;
    private final DiscoJdbc discoJdbc= DiscoJdbcImpl.getInstance();
   
    private DiscoCatalgo( )
    {
        super();
    }

    public static DiscoCatalgo getInstance( )
    {
        if(discoCatalogo==null)
        {
            discoCatalogo = new DiscoCatalgo();
        }
        return discoCatalogo;
    }

    @Override
    public Disco newT()
    {
        return new Disco( );
    }

    @Override
    public boolean processNewT(Disco disco) {
        if (discoJdbc == null) {
            return false;
        }
        return discoJdbc.findAll() != null;
    }
    public static Integer nulo(){
        Integer valor=ReadUtil.readInt();
        if(valor ==null){
            System.out.println("Valor nulo");
            return 0;
        }
        return valor;
    }

    @Override
    public boolean processNewT1(Disco disco) {
        System.out.println("Dame el nombre del disco:");
        DiscoJdbc discoJdbc = DiscoJdbcImpl.getInstance();

        disco.setTitulo(ReadUtil.read());
        System.out.println("Dame la imagen del disco:");
        disco.setImagen(ReadUtil.read());
        System.out.println("Dame la fecha del disco:");
        disco.setFecha(ReadUtil.read());
        System.out.println("Dame el descuento del disco:");
        disco.setDescuento(Integer.valueOf(ReadUtil.read()));
        System.out.println("Dame la existencia del disco:");
        disco.setExistencia(nulo());
        System.out.println("Dame el precio del disco:");
        disco.setPrecio(Integer.valueOf(ReadUtil.read()));
        System.out.println("Dame el id del artista del disco:");
        disco.setArtista(new Artista());
        disco.getArtista().setId(nulo());
        System.out.println("Dame el id de disquera del disco:");
        disco.setDisquera(new Disquera());
        disco.getDisquera().setId(nulo());
        System.out.println("Dame el id del genero musical del disco:");
        disco.setGeneroMusical(new GeneroMusical());
        disco.getGeneroMusical().setId(nulo());
        boolean res = discoJdbc.save(disco);

        return res;
    }

    @Override
    public void processEditT(Disco disco)
    {
        System.out.println("El nombre del disco es: " + disco.getTitulo());
        System.out.println("Dame el nuevo nombre del disco");
        DiscoJdbc discoJdbc = DiscoJdbcImpl.getInstance();
        disco.setTitulo(ReadUtil.read());

        System.out.println("Dame la imagen del disco:");
        disco.setImagen(ReadUtil.read());
        System.out.println("Dame la fecha del disco:");
        disco.setFecha(ReadUtil.read());
        System.out.println("Dame el descuento del disco:");
        disco.setDescuento(Integer.valueOf(ReadUtil.read()));
        System.out.println("Dame la existencia del disco:");
        disco.setExistencia(nulo());
        System.out.println("Dame el precio del disco:");
        disco.setPrecio(Integer.valueOf(ReadUtil.read()));
        System.out.println("Dame el id del artista del disco:");
        disco.setArtista(new Artista());
        disco.getArtista().setId(nulo());
        System.out.println("Dame el id de disquera del disco:");
        disco.setDisquera(new Disquera());
        disco.getDisquera().setId(nulo());
        System.out.println("Dame el id del genero musical del disco:");
        disco.setGeneroMusical(new GeneroMusical());
        disco.getGeneroMusical().setId(nulo());

        discoJdbc.update(disco);
    }

    @Override
    public void processRemoveT(Disco disco)
    {
        System.out.println("El nombre del disco es: " + disco.getTitulo());

        DiscoJdbc discoJdbc = DiscoJdbcImpl.getInstance();

        discoJdbc.delete(disco);
    }

    @Override
    public void processFindByIdT(int id)
    {
        DiscoJdbc discoJdbc = DiscoJdbcImpl.getInstance();

        Disco disco = discoJdbc.findById(id);

        System.out.println("Disco encontrado:");
        System.out.println(disco.toString());
    }

    @Override
    public List<Disco> processList() {
        DiscoJdbc discoJdbc=DiscoJdbcImpl.getInstance();
        List<Disco> list = discoJdbc.findAll();

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
        return "Discos";
    }
}