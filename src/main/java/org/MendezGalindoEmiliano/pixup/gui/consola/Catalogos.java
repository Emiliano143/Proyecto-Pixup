package org.MendezGalindoEmiliano.pixup.gui.consola;

import org.MendezGalindoEmiliano.pixup.gui.LecturaAccion;
import org.MendezGalindoEmiliano.pixup.model.Catalogo;
import org.MendezGalindoEmiliano.pixup.util.ReadUtil;
import java.util.ArrayList;
import java.util.List;

public abstract class Catalogos<T extends Catalogo> extends LecturaAccion {
    protected List<T> list;
    protected T t;
    protected boolean flag2;

    public Catalogos() {
        list = new ArrayList<>();
    }

    public boolean isListEmpty() {
        return list.isEmpty();
    }

    public void print() {
        List<T> list = processList(); // Siempre consulta la BD
        if (list == null || list.isEmpty()) {
            System.out.println("No hay elementos o error al recuperar los datos.");
            return;
        }
        System.out.println("Registros encontrados:");
        list.forEach(System.out::println);
    }

    public abstract T newT();
    public abstract boolean removeT(T t);
    public abstract boolean editT(T t);
    public abstract boolean processNewT1(T t);
    public abstract void processEditT(T t);
    public abstract List<T> processList();

    public void add() {
        t = newT();
        if (processNewT1(t)) {
            System.out.println("Elemento agregado correctamente a la base de datos.");
        } else {
            System.out.println("Error al guardar el elemento en la base de datos.");
        }
    }

    public void edit() {
        t = newT();
        if (editT(t)) {
            System.out.println("Elemento agregado correctamente a la base de datos.");
        } else {
            System.out.println("Error al guardar el elemento en la base de datos.");
        }
    }

    public void remove() {
        t=newT();
        if (removeT(t)) {
            System.out.println("Elemento borrado correctamente.");
        } else {
            System.out.println("Error al eliminar el elemento en la base de datos.");
        }
    }

    @Override
    public void procesaOpcion() {
        switch (opcion) {
            case 1 -> add();
            case 2 -> edit();
            case 3 -> remove();
            case 4 -> print();
            case 5 -> System.out.println(5);
        }
    }

    public String getTitulo() {
        return getTitulo();
    }

    @Override
    public void despliegaMenu() {
        System.out.println("Menú de " + getTitulo() + ": ");
        System.out.println("Seleccione una opción:");
        System.out.println("1.- Agregar");
        System.out.println("2.- Actualizar");
        System.out.println("3.- Borrar");
        System.out.println("4.- Encontrar Todos");
        System.out.println("5.- Encontrar por ID");
        System.out.println("6.- Salir");
    }

    @Override
    public int valorMinMenu() {
        return 1;
    }

    @Override
    public int valorMaxMenu() {
        return 6;
    }
}

