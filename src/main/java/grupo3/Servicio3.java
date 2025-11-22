package grupo3;

import modelo.Producto;
import java.util.List;
import java.util.Set;

public class Servicio3 {

    public static boolean verificarLimite(double total){
        double limite_pedido = 5000;
        return total <= limite_pedido;
    }

    public static boolean validarDescuento(double descuento) {
        if (descuento < 0 || descuento > 50) {
            return false;
        }
        return true;
    }

    public static double calcularIGV(double total) {
        if (total < 0) {
            throw new IllegalArgumentException("Total inválido");
        }
        return total * 1.18;
    }

    public static boolean validarCliente(String nombre) {
        if (nombre == null) {
            return false;
        }
        String nombreLimpio = nombre.trim();
        if(nombreLimpio.isEmpty()){
            return false;
        }
        return true;
    }

    public static boolean verificarStock(List<Producto> productos) {
        if (productos == null || productos.isEmpty()) {
            throw new IllegalArgumentException("Lista de productos invalida");
        }

        for (Producto producto : productos) {
            if (producto == null) {
                throw new IllegalArgumentException("Producto nulo en la lista");
            }
            if (producto.getCantidad() <= 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean validarDescuentoAplicable(Producto p, double porcentaje) {
        return p.isDescuentoAplicable() && porcentaje >= 0 && porcentaje <= 50;
    }
}