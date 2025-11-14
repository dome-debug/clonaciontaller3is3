package grupo3;

import base.Pedido;
import modelo.Producto;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class PedidoTest {

    @Test
    void testListaVacia() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Pedido.calcularTotalPedido(new ArrayList<>(), 10);
        });
        assertEquals("Error: no hay productos en el pedido", exception.getMessage());
    }

    @Test
    void testSubtotalCero() {
        List<Producto> productos = List.of(new Producto("Producto Gratis", 0, 1));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Pedido.calcularTotalPedido(productos, 10);
        });
        assertEquals("Error: monto inválido", exception.getMessage());
    }

    @Test
    void testDescuentoValido() {
        List<Producto> productos = List.of(new Producto("Laptop", 100, 2));
        double total = Pedido.calcularTotalPedido(productos, 10);
        assertEquals(180.0, total);
    }

    @Test
    void testDescuentoCero() {
        List<Producto> productos = List.of(new Producto("Laptop", 100, 2));
        double total = Pedido.calcularTotalPedido(productos, 0);
        assertEquals(200.0, total);
    }

    @Test
    void testDescuentoMaximo() {
        List<Producto> productos = List.of(new Producto("Tablet", 50, 4));
        double total = Pedido.calcularTotalPedido(productos, 100);
        assertEquals(0.0, total);
    }
}