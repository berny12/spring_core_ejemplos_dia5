/**
 * Copyright (c) SynergyJ. 
 * Todos los derechos reservados.
 *
 * Este software es de propósito educativo, puede ser
 * empleado para fines sin lucro haciendo referencia 
 * al autor intelectual.
 */
package com.synergyj.bookmule.persistence.dao;

import java.util.Set;

import com.synergyj.bookmule.core.domain.Cliente;

/**
 * @author Jorge Rodríguez Campos (jorge.rodriguez@synergyj.com)
 */
public interface ClienteDAO {

	void crea(Cliente cliente);

	Set<Cliente> busca(Cliente cliente);

	Cliente findById(Long id);

	boolean existeCliente(String rfc, String username, String email);
}
