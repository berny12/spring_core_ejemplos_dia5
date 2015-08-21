/**
 * Copyright (c) SynergyJ. 
 * Todos los derechos reservados.
 *
 * Este software es de propósito educativo, puede ser
 * empleado para fines sin lucro haciendo referencia 
 * al autor intelectual.
 */
package com.synergyj.bookmule.core.services.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synergyj.bookmule.core.domain.Autor;
import com.synergyj.bookmule.core.services.AutorService;
import com.synergyj.bookmule.persistence.dao.AutorDAO;

/**
 * @author Jorge Rodríguez Campos (jorge.rodriguez@synergyj.com)
 */
@Service("autorService")
public class AutorServiceImpl implements AutorService {

	@Resource
	private AutorDAO autorDAO;

	/**
	 * Logger para todas las instancias de la clase
	 */
	private static final Logger logger = LoggerFactory.getLogger(AutorServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * @see com.synergyj.bookmule.core.services.AutorService#creaAutor()
	 */
	@Override
	@Transactional
	public void creaAutor(Autor autor) {
		Set<Autor> autorSet;
		logger.debug("Creando autor, verificando que el autor no exista");
		autorSet = buscaAutores(autor);
		if (autorSet.size() > 0) {
			throw new IllegalArgumentException("El autor ya se encuentra registrado en la BD");
		}
		autorDAO.crea(autor);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.synergyj.bookmule.core.services.AutorService#buscaAutores(com.synergyj.bookmule.core.
	 * domain.Autor)
	 */
	@Override
	@Transactional(readOnly = true)
	public Set<Autor> buscaAutores(Autor criterios) {

		return autorDAO.busca(criterios);
	}

	/*
	 * (non-Javadoc)
	 * @see com.synergyj.bookmule.core.services.AutorService#obtenAutorPorId(java.lang.Long)
	 */
	@Override
	@Transactional
	public Autor obtenAutorPorId(Long id) {

		return autorDAO.findAutorById(id);
	}

}
