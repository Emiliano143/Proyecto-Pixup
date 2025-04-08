package org.MendezGalindoEmiliano.pixup.gui.consola;

import org.MendezGalindoEmiliano.pixup.model.Disquera;
import org.MendezGalindoEmiliano.pixup.util.ReadUtil;

import java.io.File;

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
        Disquera.setNombre(ReadUtil.read());
        return true;
    }

    @Override
    public void processEditT(Disquera Disquera) {
        System.out.println("El nombre del Disquera es: "+Disquera.getNombre());
        System.out.println("El id del Disquera es: "+Disquera.getId());
        System.out.println("Dame el nuevo nombre del Disquera");
        Disquera.setNombre(ReadUtil.read());
    }

    @Override
    public File getFile() {
        return new File("Disquera.list");
    }

    @Override
    public String getTitulo() {
        return "Disqueras";
    }
}
