package com.pe.compartamosfinanciera.sistemabancario.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pe.compartamosfinanciera.sistemabancario.entity.Banco;

public interface BancoService {
	
	public List<Banco> listarBanco();
	public Banco agregarBanco(Banco banco);
	public Banco encontrarBanco(Integer idBanco);
	public Banco modificarBanco(Banco banco, Integer idBanco);
	public ResponseEntity<?> eliminarBanco(int idBanco);

}
