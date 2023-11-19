package Laboratorio_4;

class Producto {
    private String nombre;
    private String tipo;
    private int cantidad;
    private double precio;

    public Producto(String nombre, String tipo, int cantidad, double precio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }
}