
package tienda.logica;

import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author Gorosito Juan
 */
public class Tienda {

    private String nombre;
    private int maxProductosEnStock;
    private double saldoCaja;
    private Map<String, Producto> productosEnStock;

    public Tienda(String nombre, int maxProductosEnStock, double saldoCaja) {
        this.nombre = nombre;
        this.maxProductosEnStock = maxProductosEnStock;
        this.saldoCaja = saldoCaja;
        this.productosEnStock = new HashMap<>();
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMaxProductosEnStock() {
        return maxProductosEnStock;
    }

    public void setMaxProductosEnStock(int maxProductosEnStock) {
        this.maxProductosEnStock = maxProductosEnStock;
    }

    public double getSaldoCaja() {
        return saldoCaja;
    }

    public void setSaldoCaja(double saldoCaja) {
        this.saldoCaja = saldoCaja;
    }

    public Map<String, Producto> getProductosEnStock() {
        return productosEnStock;
    }

    public void agregarProductoEnStock(Producto producto) {
        if (productosEnStock.size() < maxProductosEnStock) {
            productosEnStock.put(producto.getId_prod(), producto);
        } else {
            System.out.println("No se puede agregar más productos al stock. Stock máximo alcanzado.");
        }
    }

    public void mostrarProductosEnStock() {
        System.out.println("Productos en stock:");
        for (Producto producto : productosEnStock.values()) {
            System.out.println("ID: " + producto.getId_prod() + ", Descripción: " + producto.getDescripcion());
        }
    }
}
