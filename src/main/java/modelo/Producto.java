package modelo;

public class Producto {
    private String nombre;
    private double precio;
    private int cantidad;
    private String sku;
    private String categoria;
    private boolean esActivo;
    private boolean descuentoAplicable;

    public Producto(String nombre, double precio, int cantidad, String sku,
                String categoria, boolean esActivo, boolean descuentoAplicable) {

        this.precio = precio < 0 ? 0 : precio;
    
        this.cantidad = cantidad < 0 ? 0 : cantidad;
    
        this.nombre = nombre;
        this.sku = sku;
        this.categoria = categoria;
        this.esActivo = esActivo;
        this.descuentoAplicable = descuentoAplicable;
    }


    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }
    public String getSku() { return sku; }
    public String getCategoria() { return categoria; }
    public boolean isEsActivo() { return esActivo; }
    public boolean isDescuentoAplicable() { return descuentoAplicable; }

    public void setCantidad(int cantidad) {
        if (cantidad < 0) throw new IllegalArgumentException("Cantidad debe ser >= 0");
        this.cantidad = cantidad;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Producto producto = (Producto) obj;
        return sku.equals(producto.sku);
    }

    public int hashCode() {
        return sku.hashCode();
    }
}
