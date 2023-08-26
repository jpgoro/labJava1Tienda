package labJava1Tienda.logica;

/**
 *
 * @author Gorosito Juan
 */
public interface Descuento {

    public void setPorcentajeDescuento(double porcentajeDescuento);

    public double getPorcentajeDescuento();

    public double calcularPrecioVentaConDescuento();
}
