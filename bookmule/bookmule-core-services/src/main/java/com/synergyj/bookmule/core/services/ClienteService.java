/**
 * Copyright (c) SynergyJ. 
 * Todos los derechos reservados.
 *
 * Este software es de prop�sito educativo, puede ser
 * empleado para fines sin lucro haciendo referencia 
 * al autor intelectual.
 */
package com.synergyj.bookmule.core.services;

import java.util.Set;

import com.synergyj.bookmule.core.domain.Cliente;

/**
 * @author Jorge Rodr�guez Campos (jorge.rodriguez@synergyj.com)
 */
public interface ClienteService {

	void creaCliente(Cliente cliente);

	Set<Cliente> buscaClientes(Cliente criterios);

	Cliente obtenClienteById(Long id);
}
