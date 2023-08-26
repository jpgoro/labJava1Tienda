package labJava1Tienda.logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Gorosito Juan
 */
public class Tienda {

    private String nombre;
    private int maxProductosEnStock;
    private double saldoCaja;
    private Map<String, Producto> productosEnStock;
    private List<Producto> productosParaVenta = new ArrayList<>();

    public Tienda(String nombre, int maxProductosEnStock, double saldoCaja) {
        this.nombre = nombre;
        this.maxProductosEnStock = maxProductosEnStock;
        this.saldoCaja = saldoCaja;
        this.productosEnStock = new HashMap<>();
        this.productosParaVenta = new ArrayList<>();
    }

    public List<Producto> getProductosParaVenta() {
        return productosParaVenta;
    }

    public void setProductosParaVenta(List<Producto> productosParaVenta) {
        this.productosParaVenta = productosParaVenta;
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

    // Lista para almacenar los productos para venta
    //private List<Producto> productosParaVenta = new ArrayList<>();

    public void agregarProductoParaVenta(Producto producto) {
        productosParaVenta.add(producto);
    }

    public void ventaDeProductos(List<Producto> productos) {
        double totalVenta = 0;
        boolean hayStockInsuficiente = false;

        System.out.println("Detalle de la venta:");

        for (Producto producto : productos) {
            if (!producto.getDisponibleVentas()) {
                System.out.println("El producto " + producto.getId_prod() + " " + producto.getDescripcion() + " no se encuentra disponible");
                continue;
            }

            if (producto.getCantStock() > 0) {
                int unidadesAVender = Math.min(producto.getCantStock(), 10);

                double precioUnitario = 0;
                if (producto instanceof Descuento) {
                    precioUnitario = ((Descuento) producto).calcularPrecioVentaConDescuento();
                } else {
                    precioUnitario = producto.getPrecioUnitario();
                }

                // Aplicar impuesto del 10% para productos importados
                if (producto instanceof ProductoEnvasado || producto instanceof Bebida) {
                    if (producto instanceof ProductoEnvasado && ((ProductoEnvasado) producto).getEsImportado()
                            || producto instanceof Bebida && ((Bebida) producto).getEsImportado()) {
                        precioUnitario *= 1.10;
                    }
                }

                // Verificar porcentaje de ganancia máximo
                if ((producto instanceof ProductoEnvasado || producto instanceof Bebida) && precioUnitario > producto.getCostoUnidad() * 1.20) {
                    System.out.println("El porcentaje de ganancia excede el límite para " + producto.getId_prod());
                    continue;
                } else if (producto instanceof ProductoLimpieza) {
                    double porcentajeGanancia = (precioUnitario - producto.getCostoUnidad()) / producto.getCostoUnidad() * 100;
                    if (porcentajeGanancia < 10 || porcentajeGanancia > 25) {
                        if (!((ProductoLimpieza) producto).getTipoAplicacion().equals(TipoAplicacion.ROPA)
                                && !((ProductoLimpieza) producto).getTipoAplicacion().equals(TipoAplicacion.MULTIUSO)) {
                            System.out.println("El porcentaje de ganancia está fuera del rango permitido para " + producto.getId_prod());
                            continue;
                        }
                    }
                }

                // Aplicar descuento y verificar si genera pérdidas
                if (producto instanceof Descuento) {
                    double precioDescuento = ((Descuento) producto).calcularPrecioVentaConDescuento();
                    if (precioDescuento > producto.getCostoUnidad()) {
                        precioUnitario = precioDescuento;
                    } else {
                        System.out.println("El descuento registrado para el producto " + producto.getId_prod() + " no pudo ser aplicado");
                        continue;
                    }
                }

                totalVenta += precioUnitario * unidadesAVender;

                producto.vender(unidadesAVender);
                producto.setDisponibleVentas(false);

                System.out.println(producto.getId_prod() + " " + producto.getDescripcion() + " " + unidadesAVender + " x " + precioUnitario);
            } else {
                hayStockInsuficiente = true;
            }
        }

        System.out.println("TOTAL VENTA: " + totalVenta);

        if (hayStockInsuficiente) {
            System.out.println("Hay productos con stock disponible menor al solicitado");
        }
    }

    public void comprarProducto(Producto producto, int cantidad) {
        double montoTotal = producto.getPrecioUnitario() * cantidad;

        if (montoTotal > saldoCaja) {
            System.out.println("El producto no puede ser agregado a la tienda debido a saldo insuficiente en la caja");
            return;
        }

        if (productosEnStock.size() + cantidad > maxProductosEnStock) {
            System.out.println("No se pueden agregar más productos a la tienda. Stock máximo alcanzado.");
            return;
        }

        producto.setCantStock(producto.getCantStock() + cantidad);
        saldoCaja -= montoTotal;

        if (!productosEnStock.containsKey(producto.getId_prod())) {
            productosEnStock.put(producto.getId_prod(), producto);
        }

        System.out.println("Compra realizada: " + cantidad + " unidades de " + producto.getDescripcion());
    }

    public void mostrarProductosEnStock() {
        System.out.println("Productos en stock:");
        for (Producto producto : productosEnStock.values()) {
            System.out.println("ID: " + producto.getId_prod() + ", Descripción: " + producto.getDescripcion());
        }
    }

    //------Requerimientos adicionales usando API Streams------
    
    //busca productos que sean comestibles no importados y tengan un descuento menor al porcentaje proporcionado
    public List<String> obtenerComestiblesConMenorDescuento(double porcentajeDescuento) {
        return productosEnStock.values()
                .stream()
                .filter(producto -> (producto instanceof Bebida || producto instanceof ProductoEnvasado)
                && producto instanceof Descuento && ((Descuento) producto).getPorcentajeDescuento() < porcentajeDescuento)
                .filter(producto -> {
                    if (producto instanceof Bebida) {
                        return !((Bebida) producto).getEsImportado();
                    } else if (producto instanceof ProductoEnvasado) {
                        return !((ProductoEnvasado) producto).getEsImportado();
                    }
                    return false;
                })
                .map(producto -> producto.getDescripcion().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
    }
    //busca productos que con descuento, que tengan una utilidad inferior al porcentaje proporcionado y luego imprime su información
    public void listarProductosConUtilidadesInferiores(double porcentajeUtilidad) {
        productosEnStock.values()
                .stream()
                .filter(producto -> producto instanceof Descuento)
                .filter(producto -> {
                    double precioVenta = ((Descuento) producto).calcularPrecioVentaConDescuento();
                    return precioVenta > producto.getCostoUnidad() && (precioVenta - producto.getCostoUnidad()) / producto.getCostoUnidad() * 100 < porcentajeUtilidad;
                })
                .forEach(producto -> System.out.println("Código: " + producto.getId_prod()
                + ", Descripción: " + producto.getDescripcion()
                + ", Cantidad en Stock: " + producto.getCantStock()));
    }

}
