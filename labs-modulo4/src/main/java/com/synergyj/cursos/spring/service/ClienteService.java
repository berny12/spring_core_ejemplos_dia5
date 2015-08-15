/**
 * Copyright (c) SynergyJ. 
 * Todos los derechos reservados.
 *
 * Este software es de propósito educativo, puede ser
 * empleado para fines sin lucro haciendo referencia 
 * al autor intelectual.
 */
package com.synergyj.cursos.spring.service;

/**
 * @author Jorge Rodríguez Campos (jorge.rodriguez@synergyj.com)
 */
public interface ClienteService {

	/**
	 * @param cliente
	 */
	void creaCliente(Cliente cliente);

	/**
	 * @param id
	 * @return
	 */
	Cliente findClienteById(Long id);

	/**
	 * @param id
	 * @param nombre
	 * @return
	 */
	Cliente actualizaCliente(Long id, String nombre);
}
