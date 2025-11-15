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
        double totalIGV = Servicio3.calcularIGV(total);

        assertEquals(2867.4, totalIGV, 0.1);
    }

    @Test
    void ErrorFuncionSecundariaTest() {
        List<Producto> productos = List.of(
                new Producto("Laptop", 2500, 1),
                new Producto("Mouse", 100, 0)
        );
        double total = Pedido.calcularTotalPedido(productos, 0);
        assertTrue(total > 0);//Verificamos que la función base produzca un total positivo

        boolean resultadoStock = Servicio3.verificarStock(productos);
        assertFalse(resultadoStock);
    }

     @Test //Piero
    void testIntegracion_ValorLimite_SubtotalCero() {

        // Caso límite: un producto con precio 0 → subtotal 0 (no válido)
        List<Producto> productos = List.of(
                new Producto("ProductoGratis", 0.0, 1)
        );

        // Se espera que calcularTotalPedido NO permita subtotal <= 0
        assertThrows(IllegalArgumentException.class, () -> {

            // Paso 1: calcular subtotal (fallará)
            double subtotal = Pedido.calcularTotalPedido(productos, 0);

            // Paso 2: si no fallara, recién se aplicaría el IGV
            Servicio3.calcularIGV(subtotal);
        });
    }


}
