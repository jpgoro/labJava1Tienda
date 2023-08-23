
package tienda.logica;

import java.time.LocalDate;
import tienda.util.FechaUtil;

/**
 *
 * @author Gorosito Juan
 */
public class Bebida extends Producto implements Comestible, Descuento{
    private boolean esAlcoholica;
    private double graduacionAlcoholica;
    private LocalDate fechaVencimiento;
    private int calorias;
    private double porcentajeDescuento;

    public Bebida(boolean esAlcoholica, double graduacionAlcoholica, int calorias, double porcentajeDescuento, String id_prod, String descripcion, int cantStock, double precioUnitario, double costoUnidad, boolean disponible, boolean esImportado) {
        super(id_prod, descripcion, cantStock, precioUnitario, costoUnidad, disponible, esImportado);
        
        if (!validarIdentificador(id_prod)) {
            throw new IllegalArgumentException("Identificador inválido para bebida");
        }
        
        if(esAlcoholica){
            this.graduacionAlcoholica = graduacionAlcoholica;
        }
        
        this.esAlcoholica = esAlcoholica;
        this.graduacionAlcoholica = graduacionAlcoholica;
        // Generar fecha de vencimiento aleatoria utilizando FechaUtil
        this.fechaVencimiento = FechaUtil.generarFechaVencimientoAleatoria();
        this.calorias = calorias;
        this.porcentajeDescuento = porcentajeDescuento;
        
        
    }
    

    

    private boolean validarIdentificador(String id_prod) {
        // Verificar si el identificador cumple con el formato ACXXX
        if (id_prod.length() != 5) {
            return false;
        }
        
        String prefijo = id_prod.substring(0, 2);
        String digitos = id_prod.substring(2);

        return prefijo.equals("AC") && digitos.matches("\\d{3}");
    }

    public boolean isEsAlcoholica() {
        return esAlcoholica;
    }

    public void setEsAlcoholica(boolean esAlcoholica) {
        this.esAlcoholica = esAlcoholica;
    }

    public double getGraduacionAlcoholica() {
        if (esAlcoholica) {
            return graduacionAlcoholica;
        } else {
            throw new IllegalStateException("La bebida no es alcohólica, no tiene graduación alcohólica");
        }
    }

    public void setGraduacionAlcoholica(double graduacionAlcoholica) {
        this.graduacionAlcoholica = graduacionAlcoholica;
    }

    @Override
    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
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
