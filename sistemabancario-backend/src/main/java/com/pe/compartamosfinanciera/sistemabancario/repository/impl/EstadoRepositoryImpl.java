package com.pe.compartamosfinanciera.sistemabancario.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

import com.pe.compartamosfinanciera.sistemabancario.entity.Estado;
import com.pe.compartamosfinanciera.sistemabancario.repository.EstadoRepository;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository {

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
	public Estado encontrarEstado(int idEstado) {
		return entityManager.find(Estado.class, idEstado);
	}

}
