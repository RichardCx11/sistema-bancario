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
import com.pe.compartamosfinanciera.sistemabancario.repository.BancoRepository;
import com.pe.compartamosfinanciera.sistemabancario.service.BancoService;
import com.pe.compartamosfinanciera.sistemabancario.utilitario.Constantes;

@Service
public class BancoServiceImpl implements BancoService {

	private static final Logger logger = LoggerFactory.getLogger(BancoServiceImpl.class);

	@Autowired
	private BancoRepository bancoRepository;

	@Override
	public List<Banco> listarBanco() {
		List<Banco> listaBanco = new ArrayList<Banco>();
		String nombreMetodo = Constantes.listarBanco;
		String msjTx = "[ " + nombreMetodo + " ]";
		long tiempoInicio = System.currentTimeMillis();
		logger.info(msjTx + Constantes.InicioMetodo + nombreMetodo);

		try {
			listaBanco = bancoRepository.listarBanco();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			logger.info(Constantes.FinMetodo + nombreMetodo + (System.currentTimeMillis() - tiempoInicio) + Constantes.milisegundos);
		}
		return listaBanco;
	}

	@Override
	public Banco agregarBanco(Banco banco) {

		Banco bancoResponse = new Banco();
		String nombreMetodo = Constantes.agregarBanco;
		String msjTx = "[ " + nombreMetodo + " ]";
		long tiempoInicio = System.currentTimeMillis();
		logger.info(msjTx + Constantes.InicioMetodo + nombreMetodo);

		try {
			bancoResponse = bancoRepository.agregarBanco(banco);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			logger.info(Constantes.FinMetodo + nombreMetodo + (System.currentTimeMillis() - tiempoInicio) + Constantes.milisegundos);
		}
		return bancoResponse;
	}

	@Override
	public Banco encontrarBanco(Integer idBanco) {

		Banco bancoResponse = new Banco();
		String nombreMetodo = Constantes.encontrarBanco;
		String msjTx = "[ " + nombreMetodo + " ]";
		long tiempoInicio = System.currentTimeMillis();
		logger.info(msjTx + Constantes.InicioMetodo + nombreMetodo);

		try {
			bancoResponse = bancoRepository.encontrarBanco(idBanco);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			logger.info(Constantes.FinMetodo + nombreMetodo + (System.currentTimeMillis() - tiempoInicio) + Constantes.milisegundos);
		}
		return bancoResponse;
	}

	@Override
	public Banco modificarBanco(Banco banco, Integer idBanco) {

		Banco bancoActual = bancoRepository.encontrarBanco(idBanco);
		Banco bancoActualizado = null;
		String nombreMetodo = Constantes.modificarBanco;
		String msjTx = "[ " + nombreMetodo + " ]";
		long tiempoInicio = System.currentTimeMillis();
		logger.info(msjTx + Constantes.InicioMetodo + nombreMetodo);

		try {
			bancoActual.setNombre(banco.getNombre());
			bancoActual.setDireccion(banco.getDireccion());
			bancoActual.setFechaRegistro(banco.getFechaRegistro());

			bancoActualizado = bancoRepository.modificarBanco(bancoActual);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			logger.info(Constantes.FinMetodo + nombreMetodo + (System.currentTimeMillis() - tiempoInicio) + Constantes.milisegundos);
		}
		return bancoActualizado;
	}

	@Override
	public ResponseEntity<?> eliminarBanco(int idBanco) {

		Map<String, Object> response = new HashMap<String, Object>();
		String nombreMetodo = Constantes.eliminarBanco;
		String msjTx = "[ " + nombreMetodo + " ]";
		long tiempoInicio = System.currentTimeMillis();
		logger.info(msjTx + Constantes.InicioMetodo + nombreMetodo);

		try {
			bancoRepository.eliminarBanco(idBanco);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.put(Constantes.mensaje, Constantes.errorEliminarBanco);
			response.put(Constantes.error, e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			logger.info(Constantes.FinMetodo + nombreMetodo + (System.currentTimeMillis() - tiempoInicio) + Constantes.milisegundos);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
