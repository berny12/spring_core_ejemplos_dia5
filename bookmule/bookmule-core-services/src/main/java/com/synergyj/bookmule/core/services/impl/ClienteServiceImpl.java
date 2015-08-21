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

import com.synergyj.bookmule.core.domain.Cliente;
import com.synergyj.bookmule.core.services.ClienteService;
import com.synergyj.bookmule.persistence.dao.ClienteDAO;

/**
 * @author Jorge Rodríguez Campos (jorge.rodriguez@synergyj.com)
 */
@Service("clienteService")
public class ClienteServiceImpl implements ClienteService {

	@Resource
	private ClienteDAO clienteDAO;

	/**
	 * Logger para todas las instancias de la clase
	 */
	private static final Logger logger = LoggerFactory.getLogger(ClienteServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.synergyj.bookmule.core.services.ClienteService#creaCliente(com.synergyj.bookmule.core.
	 * domain.Cliente)
	 */
	@Override
	@Transactional
	public void creaCliente(Cliente cliente) {

		logger.debug("validando si en cliente existe");

		if (clienteDAO.existeCliente(cliente.getRfc(), cliente.getUsuario(), cliente.getEmail())) {
			throw new IllegalArgumentException("El cliente ya est registrado en la BD. ");
		}

		logger.debug("creando cliente");
		clienteDAO.crea(cliente);

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.synergyj.bookmule.core.services.ClienteService#buscaClientes(com.synergyj.bookmule.core.
	 * domain.Cliente)
	 */
	@Override
	@Transactional(readOnly = true)
	public Set<Cliente> buscaClientes(Cliente criterios) {

		return clienteDAO.busca(criterios);
	}

	/*
	 * (non-Javadoc)
	 * @see com.synergyj.bookmule.core.services.ClienteService#obtenClienteById(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public Cliente obtenClienteById(Long id) {

		return clienteDAO.findById(id);
	}

}
