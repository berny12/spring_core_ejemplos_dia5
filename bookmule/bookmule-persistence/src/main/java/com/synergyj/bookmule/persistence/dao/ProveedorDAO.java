/**
 * Copyright (c) SynergyJ. 
 * Todos los derechos reservados.
 *
 * Este software es de prop�sito educativo, puede ser
 * empleado para fines sin lucro haciendo referencia 
 * al autor intelectual.
 */
package com.synergyj.bookmule.persistence.dao;

import java.util.Set;

import com.synergyj.bookmule.core.domain.Proveedor;

/**
 * @author Jorge Rodr�guez Campos (jorge.rodriguez@synergyj.com)
 */
public interface ProveedorDAO {

	void crea(Proveedor proveedor);

	/**
	 * @param criterios de busqueda.
	 * @return
	 */
	Set<Proveedor> busca(Proveedor proveedor);

	/**
	 * @param id
	 * @return
	 */
	Proveedor findById(Long id);
}
