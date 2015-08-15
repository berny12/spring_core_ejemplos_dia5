/**
 *
 */
package com.synergyj.cursos.spring.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.synergyj.cursos.spring.service.BusinessException;
import com.synergyj.cursos.spring.service.TransaccionService;

/**
 * Servicio de negocio empleado par ilustrar el uso de transacciones.
 */
public class TransaccionServiceImpl implements TransaccionService {

	/**
	 * Logger para todas las instancias de la clase
	 */
	private static final Logger log = LoggerFactory.getLogger(TransaccionServiceImpl.class);

	@Override
	public String getString(String fooName) {
		log.debug("Antes de la excepcion... XD");
		throw new UnsupportedOperationException();
	}

	@Override
	public String getMoreStrings(String fooName, String barName) throws BusinessException {
		log.debug("Antes de la excepcion... ?:)");
		throw new BusinessException("Negocio fallo");
	}

	@Override
	public void insertFoo(String foo) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateFoo(String foo) {
		log.debug("en updateFoo, actualizando ..");
		log.debug("proceso concluido");

	}

}
