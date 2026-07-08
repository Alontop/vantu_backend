package com.vantu.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.vantu.backend.model.Venta;


public interface VentaRepository extends JpaRepository<Venta, Long> {}
