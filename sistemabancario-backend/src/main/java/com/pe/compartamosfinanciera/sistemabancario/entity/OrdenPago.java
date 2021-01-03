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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ordenpago")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class OrdenPago implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idOrdenPago;
	private Double monto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idMoneda")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Moneda moneda;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idEstado")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Estado estado;

	@Column(name = "fechaPago")
	private Date fechaPago;

	
	@JsonIgnoreProperties({ "ordenPago", "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ordenPago", cascade = CascadeType.ALL)
	private List<Sucursal> sucursales;

	public OrdenPago() {
		this.sucursales = new ArrayList<>();
	}

	public Integer getIdOrdenPago() {
		return idOrdenPago;
	}

	public void setIdOrdenPago(Integer idOrdenPago) {
		this.idOrdenPago = idOrdenPago;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public List<Sucursal> getSucursales() {
		return sucursales;
	}

	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}

}
