package com.vantu.backend.controller;

import com.vantu.backend.model.*;
import com.vantu.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional; // Necesario para manejar el Optional

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaRepository ventaRepository;
    
    @Autowired
    private DetalleVentaRepository detalleRepository;
    
    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping
    public String registrarVenta(@RequestBody PedidoDTO pedido) {
        // 1. Guardar la cabecera de la venta
        Venta venta = new Venta();
        venta.setNombreCliente(pedido.getNombreCliente());
        // Por seguridad, si el frontend envía null, ponemos un valor por defecto
        venta.setMetodoPago(pedido.getMetodoPago() != null ? pedido.getMetodoPago() : "Efectivo");
        venta.setTotal(pedido.getTotal());
        ventaRepository.save(venta);

        // 2. Guardar cada producto en detalle_ventas
        for (ProductoCantidadDTO item : pedido.getProductos()) {
            // Buscamos el producto en la base de datos
            Optional<Producto> optProducto = productoRepository.findById(item.getId());
            
            if (optProducto.isPresent()) {
                Producto producto = optProducto.get(); // Obtenemos el objeto Producto real
                
                DetalleVenta detalle = new DetalleVenta();
                detalle.setVenta(venta);
                detalle.setProducto(producto);
                detalle.setCantidad(item.getCantidad());
                
                // Ahora sí podemos llamar a getPrecio() sobre la variable 'producto'
                detalle.setPrecioUnitario(producto.getPrecio());
                
                detalleRepository.save(detalle);
            } else {
                // Opcional: manejar el caso donde el producto no existe
                System.out.println("Producto con ID " + item.getId() + " no encontrado.");
            }
        }
        return "Venta exitosa";
    }
}