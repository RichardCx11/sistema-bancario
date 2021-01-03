package com.pe.compartamosfinanciera.sistemabancario.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "sucursal")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Sucursal implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idSucursal")
	private Integer idSucursal;
	private String nombre;
	private String direccion;
	@Column(name = "fechaRegistro")
	private Date fechaRegistro;

	@JoinColumn(name = "idBanco")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	private Banco banco;

	@JoinColumn(name = "idOrdenPago")
	@JsonIgnoreProperties({"sucursales",  "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	private OrdenPago ordenPago;

	public Integer getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
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

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public OrdenPago getOrdenPago() {
		return ordenPago;
	}

	public void setOrdenPago(OrdenPago ordenPago) {
		this.ordenPago = ordenPago;
	}

}
