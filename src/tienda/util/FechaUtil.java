package tienda.util;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Gorosito Juan
 */
public class FechaUtil {

    public static LocalDate generarFechaVencimientoAleatoria() {
        LocalDate fechaActual = LocalDate.now();
        int maxDias = 365; // Rango máximo de días para la fecha de vencimiento
        int diasAleatorios = ThreadLocalRandom.current().nextInt(maxDias) + 1;
        return fechaActual.plusDays(diasAleatorios);
    }
}
