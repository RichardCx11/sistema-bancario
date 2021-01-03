package com.pe.compartamosfinanciera.sistemabancario.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.pe.compartamosfinanciera.sistemabancario.entity.Banco;
import com.pe.compartamosfinanciera.sistemabancario.repository.BancoRepository;

@Repository
public class BancoRepositoryImpl implements BancoRepository {

	
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
	public List<Banco> listarBanco() {
		TypedQuery<Banco> query = entityManager.createQuery("SELECT b FROM Banco b", Banco.class);
		List<Banco> bancos = query.getResultList();
		return bancos;
	}

	@Override
	public Banco agregarBanco(Banco banco) {
		entityManager.getTransaction().begin();
		entityManager.persist(banco);
		entityManager.getTransaction().commit();
		return banco;
	}

	@Override
	public Banco encontrarBanco(int idBanco) {
		return entityManager.find(Banco.class, idBanco);
	}

	@Override
	public Banco modificarBanco(Banco banco) {
		entityManager.getTransaction().begin();
		Banco bancoActual = entityManager.find(Banco.class, banco.getIdBanco());
		if(bancoActual != null) {
			bancoActual.setNombre(banco.getNombre());
			bancoActual.setDireccion(banco.getDireccion());
			bancoActual.setFechaRegistro(banco.getFechaRegistro());
		}
		entityManager.getTransaction().commit();
		return banco;
	}

	@Override
	public void eliminarBanco(int idBanco) {
		Banco bancoActual = entityManager.find(Banco.class, idBanco);
		entityManager.getTransaction().begin();
		entityManager.remove(bancoActual);
		entityManager.getTransaction().commit();
	}

}