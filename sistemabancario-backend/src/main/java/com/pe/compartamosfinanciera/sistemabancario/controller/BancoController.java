package com.pe.compartamosfinanciera.sistemabancario.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pe.compartamosfinanciera.sistemabancario.entity.Banco;
import com.pe.compartamosfinanciera.sistemabancario.service.BancoService;
import com.pe.compartamosfinanciera.sistemabancario.utilitario.Constantes;

@RestController
@RequestMapping("/sistemabancario")
public class BancoController {

	@Autowired
	private BancoService bancoService;

	@RequestMapping(value = "/banco", method = RequestMethod.GET, produces = Constantes.applicationjson)
	public ResponseEntity<List<Banco>> obtenerBancos() {

		List<Banco> listBanco = bancoService.listarBanco();
		return new ResponseEntity<>(listBanco, HttpStatus.OK);

	}

	@RequestMapping(value = "/banco/{idBanco}", method = RequestMethod.GET, produces = Constantes.applicationjson)
	public ResponseEntity<?> obtenerBanco(@PathVariable Integer idBanco) {

		Map<String, Object> response = new HashMap<String, Object>();
		Banco banco = bancoService.encontrarBanco(idBanco);
		if(banco == null) {
			response.put(Constantes.mensaje, "El banco ID: ".concat(idBanco.toString().concat(Constantes.errorObtener)));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(banco, HttpStatus.OK);

	}

	@RequestMapping(value = "/banco", method = RequestMethod.POST, produces = Constantes.applicationjson)
	public ResponseEntity<Banco> agregarBanco(@RequestBody Banco banco) {

		Banco bancoNuevo = bancoService.agregarBanco(banco);
		return new ResponseEntity<>(bancoNuevo, HttpStatus.OK);
	}

	@RequestMapping(value = "/banco/{idBanco}", method = RequestMethod.PUT, produces = Constantes.applicationjson)
	public ResponseEntity<Banco> modificarBanco(@RequestBody Banco banco, @PathVariable Integer idBanco) {

		Banco bancoModificado = bancoService.modificarBanco(banco, idBanco);
		return new ResponseEntity<>(bancoModificado, HttpStatus.OK);

	}

	@RequestMapping(value = "/banco/{idBanco}", method = RequestMethod.DELETE, produces = Constantes.applicationjson)
	public ResponseEntity<?> eliminarBanco(@PathVariable Integer idBanco) {

		ResponseEntity<?> response = bancoService.eliminarBanco(idBanco);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
