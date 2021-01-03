package com.pe.compartamosfinanciera.sistemabancario.repository;

import java.util.List;

import com.pe.compartamosfinanciera.sistemabancario.entity.Sucursal;

public interface SucursalRepository {
	
	public List<Sucursal> listarSucursal();
	public Sucursal agregarSucursal(Sucursal sucursal);
	public Sucursal encontrarSucursal(int idSucursal);
	public Sucursal modificarSucursal(Sucursal sucursal);
	public void eliminarSucursal(int idSucursal);
	
	public List<Sucursal> listarSucursalPorBanco(int idBanco);

}
