package com.pe.compartamosfinanciera.sistemabancario.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "banco")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Banco implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idBanco")
	private Integer idBanco;
	private String nombre;
	private String direccion;
	@Column(name = "fechaRegistro")
	private Date fechaRegistro;

	@JsonIgnoreProperties({ "banco", "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "banco", cascade = CascadeType.ALL)
	private List<Sucursal> sucursales;
	
	public Banco() {
		this.sucursales = new ArrayList<>();
	}

	public Integer getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Integer idBanco) {
		this.idBanco = idBanco;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public List<Sucursal> getSucursales() {
		return sucursales;
	}

	public void setSucursal(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}

}
