package grupo3;

public class ServicioX {

    public static double calcularIGV(double total) {
        if (total < 0) {
            throw new IllegalArgumentException("Total inválido");
        }
        return total * 1.18; // IGV = 18%
    }
}
