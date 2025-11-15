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

        boolean resultadoStock = ServicioX.verificarStock(productos);
        assertFalse(resultadoStock);
    }

}
