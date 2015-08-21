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

import com.synergyj.bookmule.core.domain.ImagenLibro;

/**
 * @author Jorge Rodr�guez Campos (jorge.rodriguez@synergyj.com)
 */
public interface ImagenLibroDAO {

	void crea(ImagenLibro imagen, Long libroId);

	Set<ImagenLibro> busca(Long libroId);
}
