package com.vantu.backend.controller;

import com.vantu.backend.model.*;
import com.vantu.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional; 

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
   
        Venta venta = new Venta();
        venta.setNombreCliente(pedido.getNombreCliente());
  
        venta.setMetodoPago(pedido.getMetodoPago() != null ? pedido.getMetodoPago() : "Efectivo");
        venta.setTotal(pedido.getTotal());
        ventaRepository.save(venta);

  
        for (ProductoCantidadDTO item : pedido.getProductos()) {

            Optional<Producto> optProducto = productoRepository.findById(item.getId());
            
            if (optProducto.isPresent()) {
                Producto producto = optProducto.get(); 
                
                DetalleVenta detalle = new DetalleVenta();
                detalle.setVenta(venta);
                detalle.setProducto(producto);
                detalle.setCantidad(item.getCantidad());

                detalle.setPrecioUnitario(producto.getPrecio());
                
                detalleRepository.save(detalle);
            } else {

                System.out.println("Producto con ID " + item.getId() + " no encontrado.");
            }
        }
        return "Venta exitosa";
    }
}