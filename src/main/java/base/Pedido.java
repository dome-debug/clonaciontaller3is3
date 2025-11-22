package base;

import modelo.Producto;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<Producto> detallesPedido;

    public Pedido() {
        this.detallesPedido = new ArrayList<>();
    }

    public static double calcularTotalPedido(List<Producto> productos, double descuento) {
        if (productos == null || productos.isEmpty()) {
            throw new IllegalArgumentException("Error: no hay productos en el pedido");
        }
        double subtotal = productos.stream()
                .mapToDouble(p -> p.getPrecio() * p.getCantidad())
                .sum();
        if (subtotal <= 0) {
            throw new IllegalArgumentException("Error: monto inválido");
        }
        return subtotal - (subtotal * (descuento / 100));
    }

    public boolean agregarProducto(Producto producto, int cantidad) {
        if (cantidad <= 0) {
            return false;
        }
        if (!producto.isEsActivo()) {
            return false;
        }

        boolean productoYaExiste = detallesPedido.stream()
                .anyMatch(p -> p.getSku().equals(producto.getSku()));

        if (productoYaExiste) {
            return false;
        } else {
            Producto nuevoProducto = new Producto(
                    producto.getNombre(), producto.getPrecio(), cantidad,
                    producto.getSku(), producto.getCategoria(),
                    producto.isEsActivo(), producto.isDescuentoAplicable()
            );
            detallesPedido.add(nuevoProducto);
            return true;
        }
    }

    public boolean validarStock() {
        if (detallesPedido.isEmpty()) {
            return true;
        }
        return detallesPedido.stream().allMatch(p -> p.getCantidad() > 0);
    }

    public List<Producto> getDetallesPedido() {
        return new ArrayList<>(detallesPedido);
    }
}