/**
 *
 */
package com.synergyj.cursos.spring.service.impl.tx.anotaciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.synergyj.cursos.spring.service.BusinessException;
import com.synergyj.cursos.spring.service.TransaccionService;

/**
 * Servicio de negocio empleado par ilustrar el uso de transacciones.
 */
@Service("transaccionServiceAnotaciones")
public class TransaccionServiceAnotacionesImpl implements TransaccionService {

	/**
	 * Logger para todas las instancias de la clase
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(TransaccionServiceAnotacionesImpl.class);

	@Override
	// TODO B) configurar transaccion
	public String getString(String fooName) {
		logger.debug("Antes de la excepcion... XD");
		throw new UnsupportedOperationException();
	}

	@Override
	// TODO C) configurar transaccion
	public String getMoreStrings(String fooName, String barName) throws BusinessException {
		logger.debug("Antes de la excepcion... ?:)");
		throw new BusinessException("Negocio fallo");
	}

	@Override
	// TODO D) configurar transaccion
	public void insertFoo(String foo) {
		throw new UnsupportedOperationException();
	}

	@Override
	// TODO E) configurar transaccion
	public void updateFoo(String foo) {
		logger.debug("en updateFoo, actualizando ..");
		logger.debug("proceso concluido");

	}

}
