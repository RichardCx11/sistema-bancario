package com.pe.compartamosfinanciera.sistemabancario.repository;

import java.util.List;

import com.pe.compartamosfinanciera.sistemabancario.entity.OrdenPago;

public interface OrdenPagoRepository {

	public List<OrdenPago> listarOrdenPago();
	public OrdenPago agregarOrdenPago(OrdenPago ordenPago);
	public OrdenPago encontrarOrdenPago(int idOrdenPago);
	public OrdenPago modificarOrdenPago(OrdenPago ordenPago);
	public void eliminarOrdenPago(int idOrdenPago);
	
	public List<OrdenPago> listarOrdenPagoPorTipoMoneda(int idMoneda);

}
