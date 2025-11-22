package grupo3;

import base.Pedido;
import modelo.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PedidoTest {
    private Pedido pedido;
    private Producto productoActivo;

    @BeforeEach
    void setUp() {
        pedido = new Pedido();
        productoActivo = new Producto("Laptop", 1500.0, 10, "SKU001", "Tecnologia", true, true);
    }

    @Test
    void testAgregarProducto_CantidadInvalida() {
        assertFalse(pedido.agregarProducto(productoActivo, 0));
    }

    @Test
    void testAgregarProducto_Duplicado() {
        pedido.agregarProducto(productoActivo, 2);
        assertFalse(pedido.agregarProducto(productoActivo, 3));
    }

    @Test
    void testAgregarProducto_Correcto() {
        assertTrue(pedido.agregarProducto(productoActivo, 2));
        assertEquals(1, pedido.getDetallesPedido().size());
    }

    @Test
    void testAgregarProducto_AtributosRespetados() {
        pedido.agregarProducto(productoActivo, 5);
        Producto agregado = pedido.getDetallesPedido().get(0);
        assertEquals("Laptop", agregado.getNombre());
        assertEquals(1500.0, agregado.getPrecio());
        assertEquals(5, agregado.getCantidad());
    }

    @Test
    void testAgregarProducto_Inactivo() {
        Producto inactivo = new Producto("Producto Viejo", 100.0, 5, "SKU002", "Varios", false, true);
        assertFalse(pedido.agregarProducto(inactivo, 2));
    }

    @Test
    void testValidarStock_ListaVacia() {
        assertTrue(pedido.validarStock());
    }

    @Test
    void testValidarStock_TodoValido() {
        pedido.agregarProducto(productoActivo, 5);
        assertTrue(pedido.validarStock());
    }

    @Test
    void testValidarStock_StockCero() {
        Producto sinStock = new Producto("Sin Stock", 50.0, 0, "SKU003", "Varios", true, true);
        pedido.agregarProducto(sinStock, 0);
        assertFalse(pedido.validarStock());
    }

    @Test
    void testValidarStock_CantidadNegativa() {
        Producto negativo = new Producto("Negativo", 50.0, -1, "SKU004", "Varios", true, true);
        pedido.agregarProducto(negativo, -1);
        assertFalse(pedido.validarStock());
    }

    @Test
    void testValidarStock_ValoresLimite() {
        Producto limite = new Producto("Límite", 999.0, 999, "SKU005", "Varios", true, true);
        pedido.agregarProducto(limite, 999);
        assertTrue(pedido.validarStock());
    }
}