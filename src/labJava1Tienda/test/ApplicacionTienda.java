
package labJava1Tienda.test;

import labJava1Tienda.logica.*;
import labJava1Tienda.util.FechaUtil;

/**
 *
 * @author Gorosito Juan
 */
public class ApplicacionTienda {
    public static void main(String[] args) {
        // Crear una tienda
        Tienda tienda = new Tienda("Tienda Lab Java", 100, 10000);

        // Crear productos
        ProductoEnvasado producto1 = new ProductoEnvasado("Botella", 150, 10, false,
                "AB001", "Aceite", 50, 5.0, 3.0, true);
        Bebida producto2 = new Bebida(true, 12.5, 150, 10, false,
                "AC001", "Vino Tinto", 30, 8.0, 4.5, true);
        ProductoLimpieza producto3 = new ProductoLimpieza(TipoAplicacion.MULTIUSO, 20,
                "AZ001", "Limpiador", 20, 2.5, 1.0, true);

        // Agregar productos a la tienda
        tienda.agregarProductoEnStock(producto1);
        tienda.agregarProductoEnStock(producto2);
        tienda.agregarProductoEnStock(producto3);

        // Ejemplo de compra y venta
        System.out.println("Ejemplo de compra y venta:");
        tienda.mostrarProductosEnStock();
        System.out.println("Saldo en caja: " + tienda.getSaldoCaja());

        tienda.comprarProducto(producto1, 20);
        tienda.comprarProducto(producto2, 15);
        tienda.comprarProducto(producto3, 10);

        tienda.agregarProductoParaVenta(producto1);
        tienda.agregarProductoParaVenta(producto2);
        tienda.agregarProductoParaVenta(producto3);

        tienda.ventaDeProductos(tienda.getProductosParaVenta());

        tienda.mostrarProductosEnStock();
        System.out.println("Saldo en caja: " + tienda.getSaldoCaja());

        System.out.println("\n-----Ejemplo de obtención de productos con menor descuento y utilidades inferiores----"); 
        System.out.println("\nObtención de productos con menor descuento y utilidades inferiores:");
        tienda.obtenerComestiblesConMenorDescuento(15).forEach(System.out::println);
        tienda.listarProductosConUtilidadesInferiores(20);
    }
    
}
