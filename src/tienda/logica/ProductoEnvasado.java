
package tienda.logica;

import java.time.LocalDate;
import tienda.util.FechaUtil;

/**
 *
 * @author Gorosito Juan
 */
public class ProductoEnvasado extends Producto implements Comestible, Descuento{
    private String tipoEnvase;
    private LocalDate fechaVencimiento;
    private int calorias;
    private double porcentajeDescuento;

    public ProductoEnvasado(String tipoEnvase, int calorias, double porcentajeDescuento, String id_prod, String descripcion, int cantStock, double precioUnitario, double costoUnidad, boolean disponible, boolean esImportado) {
        super(id_prod, descripcion, cantStock, precioUnitario, costoUnidad, disponible, esImportado);
        // Generar fecha de vencimiento aleatoria utilizando FechaUtil
        this.fechaVencimiento = FechaUtil.generarFechaVencimientoAleatoria();
        this.tipoEnvase = tipoEnvase;
        this.calorias = calorias;
        this.porcentajeDescuento = porcentajeDescuento;
    }
    

    

   

    

    private boolean validarIdentificador(String id_prod) {
        // Verificar si el identificador cumple con el formato ABXXX
        if (id_prod.length() != 5) {
            return false;
        }
        
        String prefijo = id_prod.substring(0, 2);
        String digitos = id_prod.substring(2);

        return prefijo.equals("AB") && digitos.matches("\\d{3}");
    }

    public String getTipoEnvase() {
        return tipoEnvase;
    }

    public void setTipoEnvase(String tipoEnvase) {
        this.tipoEnvase = tipoEnvase;
    }


    @Override
    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }
    @Override
    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    @Override
    public int getCalorias() {
        return calorias;
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
