package org.MendezGalindoEmiliano.pixup.gui.consola;

import org.MendezGalindoEmiliano.pixup.model.Disquera;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.DisqueraJdbc;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.impl.DisqueraJdbcImpl;
import org.MendezGalindoEmiliano.pixup.util.ReadUtil;

import java.util.ArrayList;
import java.util.List;

public class DisqueraCatalogo extends Catalogos<Disquera>
{
    private static DisqueraCatalogo disqueraCatalogo;

    private DisqueraCatalogo()
    {
    }
    public static DisqueraCatalogo getInstance( )
    {
        if(disqueraCatalogo==null)
        {
            disqueraCatalogo = new DisqueraCatalogo();
        }
        return disqueraCatalogo;
    }

    @Override
    public Disquera newT() {
        return new Disquera();
    }

    @Override
    public boolean processNewT(Disquera Disquera) {
        System.out.println("Dame el nombre de la disquera");
        Disquera.setDisquera(ReadUtil.read());
        return true;
    }

    @Override
    public boolean processNewT1(Disquera d) {
        System.out.println("Dame el nombre de la disquera:");
        DisqueraJdbc disqueraJdbc = DisqueraJdbcImpl.getInstance();

        d.setDisquera(ReadUtil.read());
        boolean res = disqueraJdbc.save(d);

        return res;
    }

    @Override
    public void processEditT(Disquera disquera) {
        System.out.println("El nombre de la disquera es: "+disquera.getDisquera());
        System.out.println("Dame el nuevo nombre de la disquera");
        disquera.setDisquera(ReadUtil.read());

        DisqueraJdbc disqueraJdbc = DisqueraJdbcImpl.getInstance();

        disqueraJdbc.update(disquera);
    }

    @Override
    public void processRemoveT(Disquera disquera)
    {
        System.out.println("El nombre de la disquera es: " + disquera.getDisquera());

        DisqueraJdbc disqueraJdbc = DisqueraJdbcImpl.getInstance();

        disqueraJdbc.delete(disquera);
    }

    @Override
    public void processFindByIdT(int id)
    {
        DisqueraJdbc disqueraJdbc = DisqueraJdbcImpl.getInstance();

        Disquera disquera = disqueraJdbc.findById(id);

        System.out.println("Disquera encontrada:");
        System.out.println(disquera.toString());
    }

    @Override
    public List<Disquera> processList() {
        DisqueraJdbc disqueraJdbc=DisqueraJdbcImpl.getInstance();
        List<Disquera> list = disqueraJdbc.findAll();

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
        return "Disqueras";
    }
}
