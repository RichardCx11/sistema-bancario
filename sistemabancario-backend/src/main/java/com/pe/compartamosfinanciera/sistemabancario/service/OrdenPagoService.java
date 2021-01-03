package com.pe.compartamosfinanciera.sistemabancario.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pe.compartamosfinanciera.sistemabancario.entity.OrdenPago;

public interface OrdenPagoService {
	
	public List<OrdenPago> listarOrdenPago();
	public OrdenPago agregarOrdenPago(OrdenPago ordenPago);
	public OrdenPago encontrarOrdenPago(Integer idOrdenPago);
	public OrdenPago modificarOrdenPago(OrdenPago ordenPago, Integer idOrdenPago);
	public ResponseEntity<?> eliminarOrdenPago(int idOrdenPago);
	
	public List<OrdenPago> listarOrdenPagoTipoMoneda(int idMoneda);

}
