package grupo3;
import java.util.List;
import modelo.Producto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Servicio3Test {

    @Test
    void testDescuentoValido(){
        double descuento = 49.0; //Dentro del rango (0 - 50)
        boolean resultado = Servicio3.validarDescuento(descuento);

        assertTrue(resultado);
    }

    @Test
    void testDescuentoInvalido(){
        double descuento = 51.0; //Fuera del rango (0 - 50)
        boolean resultado = Servicio3.validarDescuento(descuento);

        assertFalse(resultado);
    }

    @Test
    void testIGVCorrecto() {
        // Caso básico: total de 100 → 118 con IGV
        assertEquals(118.0, Servicio3.calcularIGV(100.0));
    }
    //simulación de aplicar igv con producto simulado
    @Test
    void testIGVConProductoSimulado() {
        // Creamos un producto para simular algo más real
        Producto producto = new Producto("Laptop", 200.0, 1);

        // Calculamos el total del producto manualmente
        double total = producto.getPrecio() * producto.getCantidad(); // 200 * 1 = 200

        // Verificamos que el IGV se aplique correctamente
        double totalConIGV = Servicio3.calcularIGV(total);

        assertEquals(236.0, totalConIGV);  // 200 * 1.18 = 236
    }

    @Test
    void verificarStock_cantidadCeroTest() {
        List<Producto> productosConUnoEnCero = List.of(
                new Producto("Mouse", 100.0, 2),
                new Producto("Teclado", 50.0, 0),
                new Producto("Monitor", 20.0, 1)
        );
        boolean resultado = Servicio3.verificarStock(productosConUnoEnCero);
        assertFalse(resultado);
    }

    @Test
    void verificarStock_cantidadValidaTest() {
        List<Producto> productosConStockValido = List.of(
                new Producto("Mouse", 100.0, 2),
                new Producto("Teclado", 50.0, 1),
                new Producto("Monitor", 20.0, 3)
        );
        boolean resultado = Servicio3.verificarStock(productosConStockValido);
        assertTrue(resultado);
    }

    @Test //Piero
    void testIGV_SinCompra_TotalCero() {
        double total = 0.0;  // No se compró nada
        double totalConIGV = Servicio3.calcularIGV(total);

        assertEquals(0.0, totalConIGV); 
    }

    @Test //Piero
    void verificarStock_cantidadNegativaTest() {
        List<Producto> productos = List.of(
                new Producto("Mouse", 100.0, 2),
                new Producto("Teclado", 80.0, -1)  // cantidad negativa → inválida
        );

        boolean resultado = Servicio3.verificarStock(productos);

        assertFalse(resultado);
    }


}
