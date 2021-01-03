package com.pe.compartamosfinanciera.sistemabancario.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "moneda")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Moneda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMoneda;

	private String nombre;

	public Integer getIdMoneda() {
		return idMoneda;
	}

	public void setIdMoneda(Integer idMoneda) {
		this.idMoneda = idMoneda;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
