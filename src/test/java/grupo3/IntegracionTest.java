package grupo3;

import base.Pedido;
import modelo.Producto;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class IntegracionTest {

    @Test
    void ValoresLimiteTest() {
        List<Producto> productos = List.of(
                new Producto("Laptop", 2500.0, 2, "SKU1", "Tecnologia", true, true)
        );
        double total = Pedido.calcularTotalPedido(productos, 0);
        boolean resultado = Servicio3.verificarLimite(total);
        assertTrue(resultado);
    }

    @Test
    void ErrorFuncionBaseTest() {
        List<Producto> productos = List.of(
                new Producto("Laptop", 0.0, 1, "SKU2", "Tecnologia", true, true),
                new Producto("Mouse", 0.0, 2, "SKU3", "Tecnologia", true, true)
        );

        double descuento = 10.0;
        boolean descuentoValido = Servicio3.validarDescuento(descuento);
        assertTrue(descuentoValido);

        assertThrows(IllegalArgumentException.class, () -> {
            Pedido.calcularTotalPedido(productos, descuento);
        });
    }

    @Test
    void FlujoCorrectoTest() {
        List<Producto> productos = List.of(
                new Producto("Laptop", 2500, 1, "SKU4", "Tecnologia", true, true),
                new Producto("Mouse", 100, 2, "SKU5", "Tecnologia", true, true)
        );

        double total = Pedido.calcularTotalPedido(productos, 10);
        double totalIGV = Servicio3.calcularIGV(total);
        assertEquals(2867.4, totalIGV, 0.1);
    }

    @Test
    void Combinacion_TodosFallanTest(){
        List<Producto> productos = null;
        String nombreClienteInvalido = null;
        double descuento = 10.0;

        Exception exceptionPedido = assertThrows(IllegalArgumentException.class, () -> {
            Pedido.calcularTotalPedido(productos, descuento);
        });

        boolean clienteValido = Servicio3.validarCliente(nombreClienteInvalido);
        assertFalse(clienteValido);
        assertEquals("Error: no hay productos en el pedido", exceptionPedido.getMessage());
    }

    @Test
    void ErrorFuncionSecundariaTest() {
        List<Producto> productos = List.of(
                new Producto("Laptop", 2500.0, 1, "SKU6", "Tecnologia", true, true),
                new Producto("Mouse", 100.0, 0, "SKU7", "Tecnologia", true, true)
        );
        double total = Pedido.calcularTotalPedido(productos, 0);
        boolean stockValido = Servicio3.verificarStock(productos);
        assertTrue(total > 0 && !stockValido);
    }

    @Test
    void testFlujoExitoso() {
        Pedido pedido = new Pedido();
        Producto producto = new Producto("Laptop", 2000.0, 5, "SKU101", "Tecnologia", true, true);

        boolean agregado = pedido.agregarProducto(producto, 3);
        boolean stockValido = pedido.validarStock();
        boolean descuentoValido = Servicio3.validarDescuentoAplicable(producto, 20.0);

        assertTrue(agregado);
        assertTrue(stockValido);
        assertTrue(descuentoValido);
    }

    @Test
    void testErrorDuplicado() {
        Pedido pedido = new Pedido();
        Producto producto = new Producto("Mouse", 50.0, 10, "SKU102", "Tecnologia", true, true);

        pedido.agregarProducto(producto, 2);
        boolean duplicado = pedido.agregarProducto(producto, 1);

        assertFalse(duplicado);
        assertTrue(pedido.validarStock());
    }

    @Test
    void testStockInvalido() {
        Pedido pedido = new Pedido();
        Producto sinStock = new Producto("Teclado", 100.0, 0, "SKU103", "Tecnologia", true, true);

        pedido.agregarProducto(sinStock, 0);
        boolean stockValido = pedido.validarStock();
        boolean descuentoValido = Servicio3.validarDescuentoAplicable(sinStock, 10.0);

        assertFalse(stockValido);
        assertTrue(descuentoValido);
    }
}