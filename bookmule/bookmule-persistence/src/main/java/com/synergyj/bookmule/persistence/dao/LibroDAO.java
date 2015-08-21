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

import com.synergyj.bookmule.core.domain.Libro;
import com.synergyj.bookmule.core.domain.beans.CriterioBusquedaLibro;

/**
 * @author Jorge Rodr�guez Campos (jorge.rodriguez@synergyj.com)
 */
public interface LibroDAO {

	/**
	 * Crea un libro, el id generado se asigna en el atributo ld del objeto que se pasa como
	 * par�metro.
	 * @param libro
	 */
	void crea(Libro libro);

	/**
	 * @param libro
	 * @return
	 */
	Set<Libro> busca(CriterioBusquedaLibro criterios);

	/**
	 * @param id
	 * @return
	 */
	Libro findById(Long id);

}
