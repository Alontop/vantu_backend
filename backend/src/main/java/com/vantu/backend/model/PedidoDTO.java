package com.vantu.backend.model;

import java.util.List;

public class PedidoDTO {
    private String nombreCliente;
    private double total;
    private String metodoPago; // <-- Agregamos este campo
    private List<ProductoCantidadDTO> productos;

    // Getters y Setters
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    
    public String getMetodoPago() { return metodoPago; } // <-- Nuevo Getter
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; } // <-- Nuevo Setter
    
    public List<ProductoCantidadDTO> getProductos() { return productos; }
    public void setProductos(List<ProductoCantidadDTO> productos) { this.productos = productos; }
}
