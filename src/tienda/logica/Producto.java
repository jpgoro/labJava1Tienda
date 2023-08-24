
package tienda.logica;

/**
 *
 * @author Gorosito Juan
 */
public abstract class Producto {
    protected String id_prod;
    protected String descripcion;
    protected int cantStock;
    protected double precioUnitario;
    protected double costoUnidad;
    protected boolean disponibleVentas;

    public Producto(String id_prod, String descripcion, int cantStock, double precioUnitario, double costoUnidad, boolean disponibleVentas) {
        this.id_prod = id_prod;
        this.descripcion = descripcion;
        this.cantStock = cantStock;
        this.precioUnitario = precioUnitario;
        this.costoUnidad = costoUnidad;
        this.disponibleVentas = disponibleVentas;
    }
    
    /*public abstract void vender(int cantidad);
    public abstract void reponerStock(int cantidad);*/

    public String getId_prod() {
        return id_prod;
    }

    public void setId_prod(String id_prod) {
        this.id_prod = id_prod;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantStock() {
        return cantStock;
    }

    public void setCantStock(int cantStock) {
        this.cantStock = cantStock;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getCostoUnidad() {
        return costoUnidad;
    }

    public void setCostoUnidad(double costoUnidad) {
        this.costoUnidad = costoUnidad;
    }

    public boolean isDisponibleVentas() {
        return disponibleVentas;
    }

    public void setDisponibleVentas(boolean disponibleVentas) {
        this.disponibleVentas = disponibleVentas;
    }   
}
