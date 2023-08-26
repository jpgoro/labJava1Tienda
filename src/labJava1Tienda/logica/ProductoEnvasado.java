package labJava1Tienda.logica;

import java.time.LocalDate;
import labJava1Tienda.util.FechaUtil;

/**
 *
 * @author Gorosito Juan
 */
public class ProductoEnvasado extends Producto implements Comestible, Descuento {

    private String tipoEnvase;
    private LocalDate fechaVencimiento;
    private int calorias;
    private double porcentajeDescuento;
    private boolean esImportado;

    public ProductoEnvasado(String tipoEnvase, int calorias, double porcentajeDescuento, boolean esImportado, String id_prod, String descripcion, int cantStock, double precioUnitario, double costoUnidad, boolean disponibleVentas) {
        super(id_prod, descripcion, cantStock, precioUnitario, costoUnidad, disponibleVentas);

        if (!validarIdentificador(id_prod)) {
            throw new IllegalArgumentException("Identificador inválido para producto envasado");
        }
        // Generar fecha de vencimiento aleatoria utilizando FechaUtil
        this.fechaVencimiento = FechaUtil.generarFechaVencimientoAleatoria();

        this.tipoEnvase = tipoEnvase;
        this.calorias = calorias;
        this.porcentajeDescuento = porcentajeDescuento;
        this.esImportado = esImportado;
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

 /*@Override
    public void reponerStock(int cantidad) {
        cantStock += cantidad;
        System.out.println("Se han repuesto " + cantidad + " unidades de producto envasado");
    }*/
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
        return "ProductoEnvasado{" + "tipoEnvase=" + tipoEnvase + ", fechaVencimiento=" + fechaVencimiento + ", calorias=" + calorias + ", porcentajeDescuento=" + porcentajeDescuento + ", esImportado=" + esImportado + '}';
    }
    

}
