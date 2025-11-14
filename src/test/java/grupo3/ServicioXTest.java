package grupo3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ServicioXTest {

    @Test
    void testIGVCorrecto() {
        assertEquals(118.0, ServicioX.calcularIGV(100.0));
    }

    @Test
    void testIGVCero() {
        assertEquals(0.0, ServicioX.calcularIGV(0.0));
    }

    @Test
    void testIGVDecimales() {
        assertEquals(59.0, ServicioX.calcularIGV(50.0));
    }

    @Test
    void testIGVGrande() {
        assertEquals(1180.0, ServicioX.calcularIGV(1000.0));
    }

    @Test
    void testIGVInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> ServicioX.calcularIGV(-5));
    }
}
