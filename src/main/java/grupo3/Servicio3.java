package grupo3;
import java.util.List;
import modelo.Producto;

public class Servicio3 {

    //FunciónSecundaria2
    public static boolean validarDescuento(double descuento) {
        if  (descuento < 0 || descuento > 50) {
            return false;
        }
        return true;
    }

    //FunciónSecundaria3
    public static double calcularIGV(double total) {
        if (total < 0) {
            throw new IllegalArgumentException("Total inválido");
        }
        return total * 1.18; // IGV = 18%
    }

    //FunciónSecundaria5
    //VERIFICAR STOCK
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

}
