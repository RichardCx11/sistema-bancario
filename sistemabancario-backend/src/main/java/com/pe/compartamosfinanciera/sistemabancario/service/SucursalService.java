package com.pe.compartamosfinanciera.sistemabancario.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pe.compartamosfinanciera.sistemabancario.entity.Sucursal;

public interface SucursalService {
	
	public List<Sucursal> listarSucursal();
	public Sucursal agregarSucursal(Sucursal sucursal);
	public Sucursal encontrarSucursal(Integer idSucursal);
	public Sucursal modificarSucursal(Sucursal sucursal, Integer idSucursal);
	public ResponseEntity<?> eliminarSucursal(int idSucursal);
	
	public List<Sucursal> listarSucursalPorBanco(int idBanco);

}
