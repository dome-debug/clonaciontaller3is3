package grupo3;

import base.Pedido;
import modelo.Producto;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PedidoTest {

    @Test
    void testListaVacia() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> Pedido.calcularTotalPedido(List.of(), 10));
        assertEquals("Error: no hay productos en el pedido", ex.getMessage());
    }

    @Test
    void testSubtotalCero() {
        List<Producto> productos = List.of(new Producto("X", 0, 1));
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> Pedido.calcularTotalPedido(productos, 10));
        assertEquals("Error: monto inválido", ex.getMessage());
    }

    @Test
    void testDescuentoValido() {
        List<Producto> productos = List.of(new Producto("A", 100, 2));
        double total = Pedido.calcularTotalPedido(productos, 10);
        assertEquals(180.0, total);
    }

    @Test
    void testDescuentoCero() {
        List<Producto> productos = List.of(new Producto("B", 100, 2));
        double total = Pedido.calcularTotalPedido(productos, 0);
        assertEquals(200.0, total);
    }
}
