package com.pe.compartamosfinanciera.sistemabancario.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pe.compartamosfinanciera.sistemabancario.entity.Sucursal;
import com.pe.compartamosfinanciera.sistemabancario.service.SucursalService;
import com.pe.compartamosfinanciera.sistemabancario.utilitario.Constantes;

@RestController
@RequestMapping("/sistemabancario")
public class SucursalController {
	
	@Autowired
	private SucursalService sucursalService;

	@RequestMapping(value = "/sucursal", method = RequestMethod.GET, produces = Constantes.applicationjson)
	public ResponseEntity<List<Sucursal>> obtenerSucursales() {

		List<Sucursal> listSucursal = sucursalService.listarSucursal();
		return new ResponseEntity<>(listSucursal, HttpStatus.OK);

	}

	@RequestMapping(value = "/sucursal/{idSucursal}", method = RequestMethod.GET, produces = Constantes.applicationjson)
	public ResponseEntity<?> obtenerSucursal(@PathVariable Integer idSucursal) {

		Map<String, Object> response = new HashMap<String, Object>();
		Sucursal sucursal = sucursalService.encontrarSucursal(idSucursal);
		if(sucursal == null) {
			response.put(Constantes.mensaje, "La sucursal ID: ".concat(idSucursal.toString().concat(Constantes.errorObtener)));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(sucursal, HttpStatus.OK);

	}

	@RequestMapping(value = "/sucursal", method = RequestMethod.POST, produces = Constantes.applicationjson)
	public ResponseEntity<?> agregarSucursal(@RequestBody Sucursal sucursal) {

		String idSucursal = sucursal.getBanco().getIdBanco().toString();
		Map<String, Object> response = new HashMap<String, Object>();
		Sucursal sucursalNuevo = sucursalService.agregarSucursal(sucursal);
		if(sucursalNuevo.getIdSucursal() == null) {
			response.put(Constantes.mensaje, "El banco ID: ".concat(idSucursal.concat(Constantes.errorObtener)));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(sucursalNuevo, HttpStatus.OK);
	}

	@RequestMapping(value = "/sucursal/{idSucursal}", method = RequestMethod.PUT, produces = Constantes.applicationjson)
	public ResponseEntity<?> modificarSucursal(@RequestBody Sucursal sucursal, @PathVariable Integer idSucursal) {

		String idSucursalB = sucursal.getBanco().getIdBanco().toString();
		Map<String, Object> response = new HashMap<String, Object>();
		Sucursal sucursalModificado = sucursalService.modificarSucursal(sucursal, idSucursal);
		if(sucursalModificado == null) {
			response.put(Constantes.mensaje, "El banco ID: ".concat(idSucursalB.concat(Constantes.errorObtener)));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(sucursalModificado, HttpStatus.OK);

	}

	@RequestMapping(value = "/sucursal/{idSucursal}", method = RequestMethod.DELETE, produces = Constantes.applicationjson)
	public ResponseEntity<?> eliminarSucursal(@PathVariable Integer idSucursal) {

		ResponseEntity<?> response = sucursalService.eliminarSucursal(idSucursal);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/sucursal/banco/{idBanco}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<?> obtenerSucursalPorBanco(@PathVariable Integer idBanco) {

		Map<String, Object> response = new HashMap<String, Object>();
		List<Sucursal> sucursal = sucursalService.listarSucursalPorBanco(idBanco);
		if(sucursal.isEmpty()) {
			response.put(Constantes.mensaje, "El banco ID: ".concat(idBanco.toString().concat(Constantes.errorObtener)));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(sucursal, HttpStatus.OK);

	}

}
