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

import com.pe.compartamosfinanciera.sistemabancario.entity.Banco;
import com.pe.compartamosfinanciera.sistemabancario.entity.OrdenPago;
import com.pe.compartamosfinanciera.sistemabancario.entity.Sucursal;
import com.pe.compartamosfinanciera.sistemabancario.repository.BancoRepository;
import com.pe.compartamosfinanciera.sistemabancario.repository.OrdenPagoRepository;
import com.pe.compartamosfinanciera.sistemabancario.repository.SucursalRepository;
import com.pe.compartamosfinanciera.sistemabancario.service.SucursalService;
import com.pe.compartamosfinanciera.sistemabancario.utilitario.Constantes;

@Service
public class SucursalServiceImpl implements SucursalService {

	private static final Logger logger = LoggerFactory.getLogger(SucursalServiceImpl.class);

	@Autowired
	private SucursalRepository sucursalRepository;
	
	@Autowired
	private BancoRepository bancoRepository;
	
	@Autowired
	private OrdenPagoRepository ordenPagoRepository;

	@Override
	public List<Sucursal> listarSucursal() {
		List<Sucursal> listaSucursal = new ArrayList<Sucursal>();
		String nombreMetodo = Constantes.listarSucursal;
		String msjTx = "[ " + nombreMetodo + " ]";
		long tiempoInicio = System.currentTimeMillis();
		logger.info(msjTx + Constantes.InicioMetodo + nombreMetodo);

		try {
			listaSucursal = sucursalRepository.listarSucursal();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			logger.info(Constantes.FinMetodo + nombreMetodo + (System.currentTimeMillis() - tiempoInicio)
					+ Constantes.milisegundos);
		}
		return listaSucursal;
	}

	@Override
	public Sucursal agregarSucursal(Sucursal sucursal) {
		Sucursal sucursalResponse = new Sucursal();
		Banco banco = new Banco();
		OrdenPago ordenPago = new OrdenPago();
		String nombreMetodo = Constantes.agregarSucursal;
		String msjTx = "[ " + nombreMetodo + " ]";
		long tiempoInicio = System.currentTimeMillis();
		logger.info(msjTx + Constantes.InicioMetodo + nombreMetodo);

		try {
			ordenPago = ordenPagoRepository.encontrarOrdenPago(sucursal.getOrdenPago().getIdOrdenPago());
			banco = bancoRepository.encontrarBanco(sucursal.getBanco().getIdBanco());
			sucursal.setBanco(banco);
			sucursal.setOrdenPago(ordenPago);
			sucursalResponse = sucursalRepository.agregarSucursal(sucursal);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			logger.info(Constantes.FinMetodo + nombreMetodo + (System.currentTimeMillis() - tiempoInicio)
					+ Constantes.milisegundos);
		}
		return sucursalResponse;
	}

	@Override
	public Sucursal encontrarSucursal(Integer idSucursal) {
		Sucursal sucursalResponse = new Sucursal();
		String nombreMetodo = Constantes.encontrarSucursal;
		String msjTx = "[ " + nombreMetodo + " ]";
		long tiempoInicio = System.currentTimeMillis();
		logger.info(msjTx + Constantes.InicioMetodo + nombreMetodo);

		try {
			sucursalResponse = sucursalRepository.encontrarSucursal(idSucursal);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			logger.info(Constantes.FinMetodo + nombreMetodo + (System.currentTimeMillis() - tiempoInicio)
					+ Constantes.milisegundos);
		}
		return sucursalResponse;
	}

	@Override
	public Sucursal modificarSucursal(Sucursal sucursal, Integer idSucursal) {
		Sucursal sucursalActual = sucursalRepository.encontrarSucursal(idSucursal);
		Sucursal sucursalActualizado = null;
		Banco banco = new Banco();
		OrdenPago ordenPago = new OrdenPago();
		String nombreMetodo = Constantes.modificarSucursal;
		String msjTx = "[ " + nombreMetodo + " ]";
		long tiempoInicio = System.currentTimeMillis();
		logger.info(msjTx + Constantes.InicioMetodo + nombreMetodo);

		try {
			ordenPago = ordenPagoRepository.encontrarOrdenPago(sucursal.getOrdenPago().getIdOrdenPago());
			banco = bancoRepository.encontrarBanco(sucursal.getBanco().getIdBanco());
			sucursal.setBanco(banco);
			sucursal.setOrdenPago(ordenPago);
			sucursalActual.setNombre(sucursal.getNombre());
			sucursalActual.setDireccion(sucursal.getDireccion());
			sucursalActual.setFechaRegistro(sucursal.getFechaRegistro());
			sucursalActual.setBanco(sucursal.getBanco());
			sucursalActual.setOrdenPago(sucursal.getOrdenPago());

			sucursalActualizado = sucursalRepository.modificarSucursal(sucursalActual);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			logger.info(Constantes.FinMetodo + nombreMetodo + (System.currentTimeMillis() - tiempoInicio) + Constantes.milisegundos);
		}
		return sucursalActualizado;
	}

	@Override
	public ResponseEntity<?> eliminarSucursal(int idSucursal) {
		Map<String, Object> response = new HashMap<String, Object>();
		String nombreMetodo = Constantes.eliminarSucursal;
		String msjTx = "[ " + nombreMetodo + " ]";
		long tiempoInicio = System.currentTimeMillis();
		logger.info(msjTx + Constantes.InicioMetodo + nombreMetodo);

		try {
			sucursalRepository.eliminarSucursal(idSucursal);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.put(Constantes.mensaje, Constantes.errorEliminarSucursal);
			response.put(Constantes.error, e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			logger.info(Constantes.FinMetodo + nombreMetodo + (System.currentTimeMillis() - tiempoInicio) + Constantes.milisegundos);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@Override
	public List<Sucursal> listarSucursalPorBanco(int idBanco) {
		List<Sucursal> listaSucursal = new ArrayList<Sucursal>();
		String nombreMetodo = Constantes.listarSucursalPorBanco;
		String msjTx = "[ " + nombreMetodo + " ]";
		long tiempoInicio = System.currentTimeMillis();
		logger.info(msjTx + Constantes.InicioMetodo + nombreMetodo);

		try {
			listaSucursal = sucursalRepository.listarSucursalPorBanco(idBanco);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			logger.info(Constantes.FinMetodo + nombreMetodo + (System.currentTimeMillis() - tiempoInicio)
					+ Constantes.milisegundos);
		}
		return listaSucursal;
	}

}
