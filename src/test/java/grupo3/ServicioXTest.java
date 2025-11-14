package grupo3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ServicioXTest {

    @Test
    void testCalcularIGVPositivo() {
        double resultado = ServicioX.calcularIGV(100.0);
        assertEquals(118.0, resultado); // 100 + 18% = 118
    }

    @Test
    void testCalcularIGVCero() {
        double resultado = ServicioX.calcularIGV(0.0);
        assertEquals(0.0, resultado);
    }

    @Test
    void testCalcularIGVDecimales() {
        double resultado = ServicioX.calcularIGV(50.0);
        assertEquals(59.0, resultado); // 50 + 9 = 59
    }

    @Test
    void testCalcularIGVGrande() {
        double resultado = ServicioX.calcularIGV(1000.0);
        assertEquals(1180.0, resultado);
    }

    @Test
    void testCalcularIGVPrecision() {
        double resultado = ServicioX.calcularIGV(236.44);
        assertEquals(279.0, resultado, 0.1); // Margen de error 0.1
    }
}