
package tienda.logica;

/**
 *
 * @author Gorosito Juan
 */
public interface Descuento {
    public void setPorcentajeDescuento(double porcentajeDescuento);
    public double getPorcentajeDescuento();
    
    public double calcularPrecioVentaConDescuento();
}
