package Laboratorio_4;

import java.util.List;

class Prestamo {
    private Usuario usuario;
    private List<Producto> productos;
    private int diasEntrega;
    private String horarioEntrega;
    private String sucursal;
    private String direccionEnvio;

    public Prestamo(Usuario usuario, List<Producto> productos) {
        this.usuario = usuario;
        this.productos = productos;
        // Inicializar otros atributos
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Producto> getProductos() {
        return productos;
    }
}