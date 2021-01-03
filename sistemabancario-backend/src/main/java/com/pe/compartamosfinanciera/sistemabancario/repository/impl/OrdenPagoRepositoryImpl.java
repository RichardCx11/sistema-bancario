package com.pe.compartamosfinanciera.sistemabancario.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.pe.compartamosfinanciera.sistemabancario.entity.OrdenPago;
import com.pe.compartamosfinanciera.sistemabancario.repository.OrdenPagoRepository;

@Repository
public class OrdenPagoRepositoryImpl implements OrdenPagoRepository {
	
	 private static final String persistenceUnit = "SistemaBancario";
	    private static final EntityManagerFactory entityManagerFactory;
	    
	    static {
	    	entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
	    }
	    
	    public static EntityManager getEntityManager() {
	        return entityManagerFactory.createEntityManager();
	    }
	 
	    EntityManager entityManager = getEntityManager();

	@Override
	public List<OrdenPago> listarOrdenPago() {
		TypedQuery<OrdenPago> query = entityManager.createQuery("SELECT o FROM OrdenPago o", OrdenPago.class);
		List<OrdenPago> ordenPagos = query.getResultList();
		return ordenPagos;
	}

	@Override
	public OrdenPago agregarOrdenPago(OrdenPago ordenPago) {
		entityManager.getTransaction().begin();
		entityManager.persist(ordenPago);
		entityManager.getTransaction().commit();
		return ordenPago;
	}

	@Override
	public OrdenPago encontrarOrdenPago(int idOrdenPago) {
		return entityManager.find(OrdenPago.class, idOrdenPago);
	}

	@Override
	public OrdenPago modificarOrdenPago(OrdenPago ordenPago) {
		entityManager.getTransaction().begin();
		OrdenPago ordenPagoActual = entityManager.find(OrdenPago.class, ordenPago.getIdOrdenPago());
		if(ordenPagoActual != null) {
			ordenPagoActual.setMonto(ordenPago.getMonto());
			ordenPagoActual.setMoneda(ordenPago.getMoneda());
			ordenPagoActual.setEstado(ordenPago.getEstado());
			ordenPagoActual.setFechaPago(ordenPago.getFechaPago());
		}
		entityManager.getTransaction().commit();
		return ordenPago;
	}

	@Override
	public void eliminarOrdenPago(int idOrdenPago) {
		OrdenPago ordenPagoActual = entityManager.find(OrdenPago.class, idOrdenPago);
		entityManager.getTransaction().begin();
		entityManager.remove(ordenPagoActual);
		entityManager.getTransaction().commit();
	}

	@Override
	public List<OrdenPago> listarOrdenPagoPorTipoMoneda(int idMoneda) {
		entityManager.getTransaction().begin();
		TypedQuery<OrdenPago> query = entityManager.createQuery("SELECT o FROM OrdenPago o where o.moneda.idMoneda = ?1", 
				OrdenPago.class);
		query.setParameter(1, idMoneda);
		List<OrdenPago> ordenPagos = query.getResultList();
		entityManager.getTransaction().commit();
		return ordenPagos;
	}

}
