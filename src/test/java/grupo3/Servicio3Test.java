package grupo3;
import java.util.List;
import modelo.Producto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Servicio3Test {

    @Test
    void verificarLimiteValidoTest() {
        double totalValido = 5000.00;
        assertTrue(Servicio3.verificarLimite(totalValido));
    }

    @Test
    void verificarLimiteExcedidoTest() {
        double totalExcedido = 5000.01;
        assertFalse(Servicio3.verificarLimite(totalExcedido));
    }

    @Test
    void descuentoValidoTest(){
        double descuento = 49.0;
        boolean resultado = Servicio3.validarDescuento(descuento);
        assertTrue(resultado);
    }

    @Test
    void descuentoInvalidoTest(){
        double descuento = 51.0;
        boolean resultado = Servicio3.validarDescuento(descuento);
        assertFalse(resultado);
    }

    @Test
    void testIGVConProductoSimulado() {
        Producto producto = new Producto("Laptop", 200.0, 1, "SKU-TEST", "Tecnologia", true, true);
        double total = producto.getPrecio() * producto.getCantidad();
        double totalConIGV = Servicio3.calcularIGV(total);
        assertEquals(236.0, totalConIGV);
    }

    @Test
    void validarCliente_NombreValidoTest(){
        String nombreValido = " Juan Pérez ";
        boolean resultado = Servicio3.validarCliente(nombreValido);
        assertTrue(resultado);
    }

    @Test
    void validarCliente_NombreVacioTest(){
        String nombreVacio = "  ";
        boolean resultado = Servicio3.validarCliente(nombreVacio);
        assertFalse(resultado);
    }

    @Test
    void verificarStock_cantidadCeroTest() {
        List<Producto> productosConUnoEnCero = List.of(
                new Producto("Mouse", 100.0, 2, "SKU1", "Tecnologia", true, true),
                new Producto("Teclado", 50.0, 0, "SKU2", "Tecnologia", true, true),
                new Producto("Monitor", 20.0, 1, "SKU3", "Tecnologia", true, true)
        );
        boolean resultado = Servicio3.verificarStock(productosConUnoEnCero);
        assertFalse(resultado);
    }

    @Test
    void verificarStock_cantidadValidaTest() {
        List<Producto> productosConStockValido = List.of(
                new Producto("Mouse", 100.0, 2, "SKU4", "Tecnologia", true, true),
                new Producto("Teclado", 50.0, 1, "SKU5", "Tecnologia", true, true),
                new Producto("Monitor", 20.0, 3, "SKU6", "Tecnologia", true, true)
        );
        boolean resultado = Servicio3.verificarStock(productosConStockValido);
        assertTrue(resultado);
    }
}