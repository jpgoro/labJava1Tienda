package labJava1Tienda.logica;

import java.time.LocalDate;
import labJava1Tienda.util.FechaUtil;

/**
 *
 * @author Gorosito Juan
 */
public class Bebida extends Producto implements Comestible, Descuento {

    private boolean esAlcoholica;
    private double graduacionAlcoholica;
    private LocalDate fechaVencimiento;
    private int calorias;
    private double porcentajeDescuento;
    private boolean esImportado;

    public Bebida(boolean esAlcoholica, double graduacionAlcoholica, int calorias, double porcentajeDescuento, boolean esImportado, String id_prod, String descripcion, int cantStock, double precioUnitario, double costoUnidad, boolean disponibleVentas) {
        super(id_prod, descripcion, cantStock, precioUnitario, costoUnidad, disponibleVentas);
        if (!validarIdentificador(id_prod)) {
            throw new IllegalArgumentException("Identificador inválido para bebida");
        }

        this.esAlcoholica = esAlcoholica;
        if (esAlcoholica) {
            this.graduacionAlcoholica = graduacionAlcoholica;
        }
        // Generar fecha de vencimiento aleatoria utilizando FechaUtil
        this.fechaVencimiento = FechaUtil.generarFechaVencimientoAleatoria();
        this.calorias = calorias;
        this.porcentajeDescuento = porcentajeDescuento;
        this.esImportado = esImportado;
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

    /*@Override
    public void vender(int cantidad) {
        if (disponibleVentas && cantidad <= cantStock) {
            cantStock -= cantidad;
            System.out.println("Venta realizada: " + cantidad + " unidades de bebida");
        } else {
            System.out.println("No se puede realizar la venta");
        }
    }

    @Override
    public void reponerStock(int cantidad) {
        cantStock += cantidad;
        System.out.println("Se han repuesto " + cantidad + " unidades de bebida");
    }*/
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
            return 0.0;
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
            System.out.println("Porcentaje de descuento inválido: Debe estar entre 0 y 100");
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


    @Override
    public void vender(int cantidad) {
         if (disponibleVentas && cantidad <= cantStock) {
        cantStock -= cantidad;
        System.out.println("Venta realizada: " + cantidad + " unidades de producto envasado");
    } else {
        System.out.println("No se puede realizar la venta");
    }
    }

    public boolean getEsImportado() {
        return esImportado;
    }

    public void setEsImportado(boolean esImportado) {
        this.esImportado = esImportado;
    }

    @Override
    public String toString() {
        return "Bebida{" + "esAlcoholica=" + esAlcoholica + ", graduacionAlcoholica=" + graduacionAlcoholica + ", fechaVencimiento=" + fechaVencimiento + ", calorias=" + calorias + ", porcentajeDescuento=" + porcentajeDescuento + ", esImportado=" + esImportado + '}';
    }
    
    

}
