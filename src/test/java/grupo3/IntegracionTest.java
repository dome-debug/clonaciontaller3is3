package grupo3;

import base.Pedido;
import modelo.Producto;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class IntegracionTest {

    @Test
    void testFlujoCorrecto() {
        // Caso exitoso - Pedido + IGV
        List<Producto> productos = List.of(
                new Producto("Laptop", 2500.0, 1),
                new Producto("Mouse", 100.0, 2)
        );
        double total = Pedido.calcularTotalPedido(productos, 10); // 10% descuento
        double totalConIGV = ServicioX.calcularIGV(total);
        assertEquals(3186.0, totalConIGV); // (2500+200-270)=2430 + 18% = 2867.4
    }

    @Test
    void testErrorFuncionBase() {
        // Error en función base - lista vacía
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Pedido.calcularTotalPedido(List.of(), 10);
        });
        assertEquals("Error: no hay productos en el pedido", exception.getMessage());
    }

    @Test
    void testFuncionSecundariaSinError() {
        // IGV siempre funciona, incluso con 0
        List<Producto> productos = List.of(new Producto("Producto", 0, 1));
        // Primero probamos que Pedido lanza excepción con precio 0
        assertThrows(IllegalArgumentException.class, () -> {
            Pedido.calcularTotalPedido(productos, 10);
        });

        // Pero IGV con 0 funciona
        double resultado = ServicioX.calcularIGV(0);
        assertEquals(0.0, resultado);
    }

    @Test
    void testValoresLimite() {
        // Valores límite - cálculo exacto
        List<Producto> productos = List.of(new Producto("Producto", 100.0, 1));
        double total = Pedido.calcularTotalPedido(productos, 0); // 100 sin descuento
        double totalConIGV = ServicioX.calcularIGV(total);
        assertEquals(118.0, totalConIGV); // 100 + 18% = 118
    }

    @Test
    void testCombinacionValidaciones() {
        // Combinación de validaciones - descuento + IGV
        List<Producto> productos = List.of(
                new Producto("Monitor", 300.0, 3) // 900 total
        );
        double total = Pedido.calcularTotalPedido(productos, 10); // 810 con 10% descuento
        double totalConIGV = ServicioX.calcularIGV(total);
        assertEquals(955.8, totalConIGV, 0.1); // 810 + 18% = 955.8
    }
}