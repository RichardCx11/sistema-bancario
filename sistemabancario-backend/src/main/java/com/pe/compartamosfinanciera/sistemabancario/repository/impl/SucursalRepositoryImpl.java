package com.pe.compartamosfinanciera.sistemabancario.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.pe.compartamosfinanciera.sistemabancario.entity.Sucursal;
import com.pe.compartamosfinanciera.sistemabancario.repository.SucursalRepository;

@Repository
public class SucursalRepositoryImpl implements SucursalRepository {
	
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
	public List<Sucursal> listarSucursal() {
		TypedQuery<Sucursal> query = entityManager.createQuery("SELECT s FROM Sucursal s", Sucursal.class);
		List<Sucursal> sucursales = query.getResultList();
		return sucursales;
	}

	@Override
	public Sucursal agregarSucursal(Sucursal sucursal) {
		entityManager.getTransaction().begin();
		entityManager.persist(sucursal);
		entityManager.getTransaction().commit();
		return sucursal;
	}

	@Override
	public Sucursal encontrarSucursal(int idSucursal) {
		return entityManager.find(Sucursal.class, idSucursal);
	}

	@Override
	public Sucursal modificarSucursal(Sucursal sucursal) {
		entityManager.getTransaction().begin();
		Sucursal sucursalActual = entityManager.find(Sucursal.class, sucursal.getIdSucursal());
		if(sucursalActual != null) {
			sucursalActual.setNombre(sucursal.getNombre());
			sucursalActual.setDireccion(sucursal.getDireccion());
			sucursalActual.setFechaRegistro(sucursal.getFechaRegistro());
			sucursalActual.setBanco(sucursal.getBanco());
		}
		entityManager.getTransaction().commit();
		return sucursal;
	}

	@Override
	public void eliminarSucursal(int idSucursal) {
		Sucursal sucursalActual = entityManager.find(Sucursal.class, idSucursal);
		entityManager.getTransaction().begin();
		entityManager.remove(sucursalActual);
		entityManager.getTransaction().commit();
	}

	@Override
	public List<Sucursal> listarSucursalPorBanco(int idBanco) {
		entityManager.getTransaction().begin();
		TypedQuery<Sucursal> query = entityManager.createQuery("SELECT s FROM Sucursal s where s.banco.idBanco = ?1", 
				Sucursal.class);
		query.setParameter(1, idBanco);
		List<Sucursal> sucursales = query.getResultList();
		entityManager.getTransaction().commit();
		return sucursales;
	}

}
