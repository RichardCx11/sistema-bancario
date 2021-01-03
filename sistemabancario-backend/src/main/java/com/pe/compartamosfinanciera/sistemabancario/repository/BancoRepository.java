package com.pe.compartamosfinanciera.sistemabancario.repository;

import java.util.List;

import com.pe.compartamosfinanciera.sistemabancario.entity.Banco;

public interface BancoRepository {
	
	public List<Banco> listarBanco();
	public Banco agregarBanco(Banco banco);
	public Banco encontrarBanco(int idBanco);
	public Banco modificarBanco(Banco banco);
	public void eliminarBanco(int idBanco);

}
