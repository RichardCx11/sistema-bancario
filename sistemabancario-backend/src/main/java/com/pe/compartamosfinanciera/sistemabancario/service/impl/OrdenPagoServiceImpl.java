package com.pe.compartamosfinanciera.sistemabancario.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pe.compartamosfinanciera.sistemabancario.entity.Estado;
import com.pe.compartamosfinanciera.sistemabancario.entity.Moneda;
import com.pe.compartamosfinanciera.sistemabancario.entity.OrdenPago;
import com.pe.compartamosfinanciera.sistemabancario.repository.EstadoRepository;
import com.pe.compartamosfinanciera.sistemabancario.repository.MonedaRepository;
import com.pe.compartamosfinanciera.sistemabancario.repository.OrdenPagoRepository;
import com.pe.compartamosfinanciera.sistemabancario.service.OrdenPagoService;
import com.pe.compartamosfinanciera.sistemabancario.utilitario.Constantes;

@Service
public class OrdenPagoServiceImpl implements OrdenPagoService {
	
	private static final Logger logger = LoggerFactory.getLogger(SucursalServiceImpl.class);

	@Autowired
	private OrdenPagoRepository ordenPagoRepository;
	
	@Autowired
	private MonedaRepository monedaRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;

	@Override
	public List<OrdenPago> listarOrdenPago() {
		List<OrdenPago> listaOrdenPago = new ArrayList<OrdenPago>();
		String nombreMetodo = Constantes.listarOrdenPago;
		String msjTx = "[ " + nombreMetodo + " ]";
		long tiempoInicio = System.currentTimeMillis();
		logger.info(msjTx + Constantes.InicioMetodo + nombreMetodo);

		try {
			listaOrdenPago = ordenPagoRepository.listarOrdenPago();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			logger.info(Constantes.FinMetodo + nombreMetodo + (System.currentTimeMillis() - tiempoInicio)
					+ Constantes.milisegundos);
		}
		return listaOrdenPago;
	}

	@Override
	public OrdenPago agregarOrdenPago(OrdenPago ordenPago) {
		OrdenPago ordenPagoResponse = new OrdenPago();
		Moneda moneda = new Moneda();
		Estado estado = new Estado();
		String nombreMetodo = Constantes.agregarOrdenPago;
		String msjTx = "[ " + nombreMetodo + " ]";
		long tiempoInicio = System.currentTimeMillis();
		logger.info(msjTx + Constantes.InicioMetodo + nombreMetodo);

		try {
			moneda = monedaRepository.encontrarMoneda(ordenPago.getMoneda().getIdMoneda());
			estado = estadoRepository.encontrarEstado(ordenPago.getEstado().getIdEstado());
			ordenPago.setMoneda(moneda);
			ordenPago.setEstado(estado);
			ordenPagoResponse = ordenPagoRepository.agregarOrdenPago(ordenPago);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			logger.info(Constantes.FinMetodo + nombreMetodo + (System.currentTimeMillis() - tiempoInicio)
					+ Constantes.milisegundos);
		}
		return ordenPagoResponse;
	}

	@Override
	public OrdenPago encontrarOrdenPago(Integer idOrdenPago) {
		OrdenPago ordenPagoResponse = new OrdenPago();
		String nombreMetodo = Constantes.encontrarOrdenPago;
		String msjTx = "[ " + nombreMetodo + " ]";
		long tiempoInicio = System.currentTimeMillis();
		logger.info(msjTx + Constantes.InicioMetodo + nombreMetodo);

		try {
			ordenPagoResponse = ordenPagoRepository.encontrarOrdenPago(idOrdenPago);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			logger.info(Constantes.FinMetodo + nombreMetodo + (System.currentTimeMillis() - tiempoInicio)
					+ Constantes.milisegundos);
		}
		return ordenPagoResponse;
	}

	@Override
	public OrdenPago modificarOrdenPago(OrdenPago ordenPago, Integer idOrdenPago) {
		OrdenPago ordenPagoActual = ordenPagoRepository.encontrarOrdenPago(idOrdenPago);
		OrdenPago ordenPagoActualizado = null;
		Moneda moneda = new Moneda();
		Estado estado = new Estado();
		String nombreMetodo = Constantes.modificarOrdenPago;
		String msjTx = "[ " + nombreMetodo + " ]";
		long tiempoInicio = System.currentTimeMillis();
		logger.info(msjTx + Constantes.InicioMetodo + nombreMetodo);

		try {
			moneda = monedaRepository.encontrarMoneda(ordenPago.getMoneda().getIdMoneda());
			estado = estadoRepository.encontrarEstado(ordenPago.getEstado().getIdEstado());
			ordenPago.setMoneda(moneda);
			ordenPago.setEstado(estado);
			ordenPagoActual.setMonto(ordenPago.getMonto());
			ordenPagoActual.setMoneda(moneda);
			ordenPagoActual.setEstado(estado);

			ordenPagoActualizado = ordenPagoRepository.modificarOrdenPago(ordenPagoActual);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			logger.info(Constantes.FinMetodo + nombreMetodo + (System.currentTimeMillis() - tiempoInicio) + Constantes.milisegundos);
		}
		return ordenPagoActualizado;
	}

	@Override
	public ResponseEntity<?> eliminarOrdenPago(int idOrdenPago) {
		Map<String, Object> response = new HashMap<String, Object>();
		String nombreMetodo = Constantes.eliminarOrdenPago;
		String msjTx = "[ " + nombreMetodo + " ]";
		long tiempoInicio = System.currentTimeMillis();
		logger.info(msjTx + Constantes.InicioMetodo + nombreMetodo);

		try {
			ordenPagoRepository.eliminarOrdenPago(idOrdenPago);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.put(Constantes.mensaje, Constantes.errorEliminarOrdenPago);
			response.put(Constantes.error, e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			logger.info(Constantes.FinMetodo + nombreMetodo + (System.currentTimeMillis() - tiempoInicio) + Constantes.milisegundos);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@Override
	public List<OrdenPago> listarOrdenPagoTipoMoneda(int idMoneda) {
		List<OrdenPago> listaOrdenPago = new ArrayList<OrdenPago>();
		String nombreMetodo = Constantes.listarOrdenPagoPorTipoMoneda;
		String msjTx = "[ " + nombreMetodo + " ]";
		long tiempoInicio = System.currentTimeMillis();
		logger.info(msjTx + Constantes.InicioMetodo + nombreMetodo);

		try {
			listaOrdenPago = ordenPagoRepository.listarOrdenPagoPorTipoMoneda(idMoneda);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			logger.info(Constantes.FinMetodo + nombreMetodo + (System.currentTimeMillis() - tiempoInicio)
					+ Constantes.milisegundos);
		}
		return listaOrdenPago;
	}

}
