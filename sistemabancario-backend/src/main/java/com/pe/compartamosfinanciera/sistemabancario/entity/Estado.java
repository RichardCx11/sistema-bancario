package com.pe.compartamosfinanciera.sistemabancario.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "estado")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEstado;

	private String nombre;

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
