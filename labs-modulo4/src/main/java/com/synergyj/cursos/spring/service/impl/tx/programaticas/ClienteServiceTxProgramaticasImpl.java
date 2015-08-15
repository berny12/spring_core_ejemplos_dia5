/**
 * Copyright (c) SynergyJ. 
 * Todos los derechos reservados.
 *
 * Este software es de propósito educativo, puede ser
 * empleado para fines sin lucro haciendo referencia 
 * al autor intelectual.
 */
package com.synergyj.cursos.spring.service.impl.tx.programaticas;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.synergyj.cursos.spring.service.Cliente;
import com.synergyj.cursos.spring.service.ClienteService;

/**
 * Este servicio implementa el uso de transacciones de forma programática.
 * 
 * @author Jorge Rodríguez Campos (jorge.rodriguez@synergyj.com)
 */

@Service("clienteServiceTxProgramaticas")
public class ClienteServiceTxProgramaticasImpl implements ClienteService {

	/**
	 * Logger para todas las instancias de la clase
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(ClienteServiceTxProgramaticasImpl.class);

	@Resource
	private TransactionTemplate transactionTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.synergyj.cursos.spring.service.ClienteService#creaCliente(com.synergyj
	 * .cursos.spring. service.Cliente)
	 */
	@Override
	public void creaCliente(final Cliente cliente) {
		logger.debug("antes de crear cliente, se crea transaccíón");
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				if (cliente.getNombre() == null) {
					logger.debug("cliente con nombre nulo, se invoca un rollback");
					// si encontramos error en la transaccion necestiamos decir
					// rollback para detener la ejecucion
					status.setRollbackOnly();
					logger.debug("status de la transaccion: {}", status);
				}
				logger.debug("simulando la creación de un cliente en una transaccion");
			}
		});
		// si el memtodo temina bien el commit se realiza de forma automatica
		logger.debug("transaccion terminada");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.synergyj.cursos.spring.service.ClienteService#findClienteById(java
	 * .lang.Long)
	 */
	@Override
	public Cliente findClienteById(Long id) {
		Cliente cliente;

		logger.debug("metodo sin transacciones");
		cliente = new Cliente();
		cliente.setId(id);
		cliente.setNombre("Sin nombre");
		return cliente;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.synergyj.cursos.spring.service.ClienteService#actualizaCliente(java
	 * .lang.Long, java.lang.String)
	 */

	@Override
	public Cliente actualizaCliente(final Long id, final String nombre) {
		logger.debug("Actualizando cliente, creando transacción");

		// TODO C ) hacer este código transaccional empleando
		// TransactionCallback
		Cliente cliente = transactionTemplate
				.execute(new TransactionCallback<Cliente>() {

					@Override
					public Cliente doInTransaction(TransactionStatus status) {
						Cliente cliente;
						logger.debug("actualizando datos del cliente en metodo transaccional");
						cliente = new Cliente();
						cliente.setId(id);
						cliente.setNombre(nombre + " modificado");
						return cliente;
					}
				});
		return cliente;

	}
}
