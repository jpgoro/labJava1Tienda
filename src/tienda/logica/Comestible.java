
package tienda.logica;

import java.time.LocalDate;

/**
 *
 * @author Gorosito Juan
 */
public interface Comestible {

    public void setFechaVencimiento(LocalDate fechaVencimiento);

    public LocalDate getFechaVencimiento();

    public void setCalorias(int calorias);

    public int getCalorias();
}
