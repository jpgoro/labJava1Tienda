
package tienda.logica;

/**
 *
 * @author Gorosito Juan
 */
public class ProductoLimpieza extends Producto implements Descuento{
    private TipoAplicacion tipoAplicacion;
    private double porcentajeDescuento;

    public ProductoLimpieza(TipoAplicacion tipoAplicacion, double porcentajeDescuento, String id_prod, String descripcion, int cantStock, double precioUnitario, double costoUnidad, boolean disponible, boolean esImportado) {
        super(id_prod, descripcion, cantStock, precioUnitario, costoUnidad, disponible, esImportado);
        if (!validarIdentificador(id_prod)) {
            throw new IllegalArgumentException("Identificador inválido para producto de limpieza");
        }
        
        this.tipoAplicacion = tipoAplicacion;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    

    private boolean validarIdentificador(String id_prod) {
        // Verificar si el identificador cumple con el formato AZXXX
        if (id_prod.length() != 5) {
            return false;
        }
        
        String prefijo = id_prod.substring(0, 2);
        String digitos = id_prod.substring(2);

        return prefijo.equals("AZ") && digitos.matches("\\d{3}");
    }

    public TipoAplicacion getTipoAplicacion() {
        return tipoAplicacion;
    }

    public void setTipoAplicacion(TipoAplicacion tipoAplicacion) {
        this.tipoAplicacion = tipoAplicacion;
    }

    @Override
    public void setPorcentajeDescuento(double porcentajeDescuento) {
        if (porcentajeDescuento > 0 && porcentajeDescuento <= 100) {
            this.porcentajeDescuento = porcentajeDescuento;
        } else {
            throw new IllegalArgumentException("Porcentaje de descuento inválido");
        }
    }

    @Override
    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    @Override
    public double calcularPrecioVentaConDescuento() {
        return precioUnitario - (precioUnitario * porcentajeDescuento / 100);
    }
    
    
}
