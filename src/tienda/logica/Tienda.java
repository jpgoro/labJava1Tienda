/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gorosito Juan
 */
public class Tienda {
    private String nombre;
    private int maxProductosEnStock;
    private double saldoCaja;
    private Map<Class<? extends Producto>, List<Producto>> stockProductos;

    public Tienda(String nombre, int maxProductosEnStock, double saldoCaja) {
        this.nombre = nombre;
        this.maxProductosEnStock = maxProductosEnStock;
        this.saldoCaja = saldoCaja;
        this.stockProductos = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getMaxProductosEnStock() {
        return maxProductosEnStock;
    }

    public double getSaldoCaja() {
        return saldoCaja;
    }

    public void setSaldoCaja(double saldoCaja) {
        this.saldoCaja = saldoCaja;
    }

    public void agregarProducto(Producto producto) {
        Class<? extends Producto> tipoProducto = producto.getClass();
        
        if (!stockProductos.containsKey(tipoProducto)) {
            stockProductos.put(tipoProducto, new ArrayList<>());
        }
        
        List<Producto> productosTipo = stockProductos.get(tipoProducto);
        if (productosTipo.size() < maxProductosEnStock) {
            productosTipo.add(producto);
        } else {
            System.out.println("No se puede agregar más productos de tipo " + tipoProducto.getSimpleName());
        }
    }

    public List<Producto> getProductosTipo(Class<? extends Producto> tipoProducto) {
        return stockProductos.getOrDefault(tipoProducto, new ArrayList<>());
    }

    public void mostrarStock() {
        for (Class<? extends Producto> tipoProducto : stockProductos.keySet()) {
            List<Producto> productosTipo = stockProductos.get(tipoProducto);
            System.out.println("Tipo de producto: " + tipoProducto.getSimpleName());
            for (Producto producto : productosTipo) {
                System.out.println(" - " + producto.getDescripcion() + ", Precio: " + producto.getPrecioUnitario());
            }
            System.out.println();
        }
    }
}
