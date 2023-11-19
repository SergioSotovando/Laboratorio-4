package Laboratorio_4;

import java.util.List;
import java.util.ArrayList;

class Usuario {
    private String nombre;
    private String contraseña;
    private String plan;
    private List<Producto> seleccion;

    public Usuario(String nombre, String contraseña, String plan) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.plan = plan;
        this.seleccion = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getPlan() {
        return plan;
    }

    public List<Producto> getSeleccion() {
        return seleccion;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}