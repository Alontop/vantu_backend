package com.vantu.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_ventas")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private int cantidad;

  
    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;


    public void setVenta(Venta venta) { this.venta = venta; }
    public void setProducto(Producto producto) { this.producto = producto; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }
    public Double getPrecioUnitario() { return precioUnitario; }
}