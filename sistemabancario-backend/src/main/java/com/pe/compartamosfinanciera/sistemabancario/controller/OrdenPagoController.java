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

import com.pe.compartamosfinanciera.sistemabancario.entity.OrdenPago;
import com.pe.compartamosfinanciera.sistemabancario.service.OrdenPagoService;
import com.pe.compartamosfinanciera.sistemabancario.utilitario.Constantes;

@RestController
@RequestMapping("/sistemabancario")
public class OrdenPagoController {
	
	@Autowired
	private OrdenPagoService ordenPagoService;
	
	@RequestMapping(value = "/ordenpago", method = RequestMethod.GET, produces = Constantes.applicationjson)
	public ResponseEntity<List<OrdenPago>> obtenerOrdenesPagos() {

		List<OrdenPago> listOrdenPago = ordenPagoService.listarOrdenPago();
		return new ResponseEntity<>(listOrdenPago, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/ordenpago/{idOrdenPago}", method = RequestMethod.GET, produces = Constantes.applicationjson)
	public ResponseEntity<?> obtenerOrdenPago(@PathVariable Integer idOrdenPago) {

		Map<String, Object> response = new HashMap<String, Object>();
		OrdenPago ordenPago = ordenPagoService.encontrarOrdenPago(idOrdenPago);
		if(ordenPago == null) {
			response.put(Constantes.mensaje, "La orden de pago ID: ".concat(idOrdenPago.toString().concat(Constantes.errorObtener)));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ordenPago, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/ordenpago", method = RequestMethod.POST, produces = Constantes.applicationjson)
	public ResponseEntity<?> agregarOrdenPago(@RequestBody OrdenPago ordenPago) {

		String idMoneda = ordenPago.getMoneda().getIdMoneda().toString();
		Map<String, Object> response = new HashMap<String, Object>();
		OrdenPago ordenPagoNuevo = ordenPagoService.agregarOrdenPago(ordenPago);
		if(ordenPagoNuevo.getIdOrdenPago() == null) {
			response.put(Constantes.mensaje, "La moneda ID: ".concat(idMoneda.concat(Constantes.errorObtener)));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ordenPagoNuevo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ordenpago/{idOrdenPago}", method = RequestMethod.PUT, produces = Constantes.applicationjson)
	public ResponseEntity<?> modificarOrdenPago(@RequestBody OrdenPago ordenPago, @PathVariable Integer idOrdenPago) {

		String idOrdenPagoM = ordenPago.getMoneda().getIdMoneda().toString();
		Map<String, Object> response = new HashMap<String, Object>();
		OrdenPago ordenPagoModificado = ordenPagoService.modificarOrdenPago(ordenPago, idOrdenPago);
		if(ordenPagoModificado == null) {
			response.put(Constantes.mensaje, "La moneda ID: ".concat(idOrdenPagoM.concat(Constantes.errorObtener)));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ordenPagoModificado, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/ordenpago/{idOrdenPago}", method = RequestMethod.DELETE, produces = Constantes.applicationjson)
	public ResponseEntity<?> eliminarOrdenPago(@PathVariable Integer idOrdenPago) {

		ResponseEntity<?> response = ordenPagoService.eliminarOrdenPago(idOrdenPago);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/ordenpago/tipomoneda/{tipoMoneda}", method = RequestMethod.GET, produces = Constantes.applicationjson)
	public ResponseEntity<?> obtenerFiltradoTipoMoneda(@PathVariable Integer tipoMoneda) {

		Map<String, Object> response = new HashMap<String, Object>();
		List<OrdenPago> ordenPago = ordenPagoService.listarOrdenPagoTipoMoneda(tipoMoneda);
		if(ordenPago.isEmpty()) {
			response.put(Constantes.mensaje, "El tipo de moneda ID: ".concat(tipoMoneda.toString().concat(Constantes.errorObtener)));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ordenPago, HttpStatus.OK);

	}

}
