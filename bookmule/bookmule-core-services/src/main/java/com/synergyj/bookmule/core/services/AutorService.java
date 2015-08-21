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

import com.synergyj.bookmule.core.domain.Autor;

/**
 * @author Jorge Rodr�guez Campos (jorge.rodriguez@synergyj.com)
 */
public interface AutorService {

	void creaAutor(Autor autor);

	Set<Autor> buscaAutores(Autor criterios);

	Autor obtenAutorPorId(Long id);

}
