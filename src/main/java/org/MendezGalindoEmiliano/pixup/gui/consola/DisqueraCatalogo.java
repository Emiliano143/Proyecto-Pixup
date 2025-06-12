package org.MendezGalindoEmiliano.pixup.gui.consola;

import org.MendezGalindoEmiliano.pixup.model.Disquera;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.DisqueraJdbc;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.DisqueraJdbc;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.DisqueraJdbc;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.impl.DisqueraJdbcImpl;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.impl.DisqueraJdbcImpl;
import org.MendezGalindoEmiliano.pixup.repository.jdbc.impl.DisqueraJdbcImpl;
import org.MendezGalindoEmiliano.pixup.util.ReadUtil;

import java.io.File;
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
    public boolean removeT(Disquera disquera) {
        System.out.println("Dame el id de disquera:");
        DisqueraJdbc disqueraJdbc = DisqueraJdbcImpl.getInstance();

        disquera.setId(ReadUtil.readInt());
        boolean res = disqueraJdbc.delete(disquera);

        return res;
    }

    @Override
    public boolean editT(Disquera disquera) {
        System.out.println("Dame el id de disquera:");
        DisqueraJdbc disqueraJdbc = DisqueraJdbcImpl.getInstance();
        disquera.setId(ReadUtil.readInt());
        System.out.println("Dame el nuevo nombre");
        disquera.setDisquera(ReadUtil.read());
        boolean res = disqueraJdbc.update(disquera);

        return res;
    }

    @Override
    public boolean processNewT1(Disquera disquera) {
        System.out.println("Dame el nombre de la disquera:");
        DisqueraJdbc disqueraJdbc = DisqueraJdbcImpl.getInstance();

        disquera.setDisquera(ReadUtil.read());
        boolean res = disqueraJdbc.save(disquera);

        return res;
    }

    @Override
    public void processEditT(Disquera Disquera) {
        System.out.println("El nombre del Disquera es: "+Disquera.getDisquera());
        System.out.println("El id del Disquera es: "+Disquera.getId());
        System.out.println("Dame el nuevo nombre del Disquera");
        Disquera.setDisquera(ReadUtil.read());
    }

    @Override
    public List<Disquera> processList() {
        return List.of();
    }

    @Override
    public String getTitulo() {
        return "Disqueras";
    }
}
