package grupo3;

import base.Pedido;
import modelo.Producto;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IntegracionTest {

    @Test
    void testFlujoCorrecto() {
        List<Producto> productos = List.of(
                new Producto("Laptop", 2500, 1),
                new Producto("Mouse", 100, 2)
        );

        double total = Pedido.calcularTotalPedido(productos, 10);
        double totalIGV = ServicioX.calcularIGV(total);

        assertEquals(2867.4, totalIGV, 0.1);
    }

    @Test
    void testErrorFuncionBase() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> Pedido.calcularTotalPedido(List.of(), 10));
        assertEquals("Error: no hay productos en el pedido", ex.getMessage());
    }

    @Test
    void testErrorFuncionSecundaria() {
        assertThrows(IllegalArgumentException.class,
                () -> ServicioX.calcularIGV(-4));
    }

    @Test
    void testValoresLimite() {
        double total = Pedido.calcularTotalPedido(
                List.of(new Producto("A", 1, 1)), 0);
        double resultado = ServicioX.calcularIGV(total);

        assertEquals(1.18, resultado);
    }

    @Test
    void testCombinacionValidaciones() {
        List<Producto> productos = List.of(new Producto("Monitor", 300, 3));

        double total = Pedido.calcularTotalPedido(productos, 10); // 900 → 810
        double totalIGV = ServicioX.calcularIGV(total); // 810 * 1.18 = 955.8

        assertEquals(955.8, totalIGV, 0.1);
    }
}
