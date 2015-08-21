/**
 * Copyright (c) SynergyJ. 
 * Todos los derechos reservados.
 *
 * Este software es de propósito educativo, puede ser
 * empleado para fines sin lucro haciendo referencia 
 * al autor intelectual.
 */
package com.synergyj.cursos.spring.pruebas;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.synergyj.cursos.spring.service.BusinessException;
import com.synergyj.cursos.spring.service.TransaccionService;

/**
 * TestCase empleado para ilustrar las diferentes estrategias para configurar
 * transacciones en Spring.
 * 
 * @author Jorge Rodríguez Campos (jorge.rodriguez@synergyj.com)
 */

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration("/sinTransaccionesAppContext.xml")
// @ContextConfiguration("/txAppContext.xml")
@ContextConfiguration("/txAnotacionesAppContext.xml")
public class TransactionServiceTestCase {

	/**
	 * Logger para todas las instancias de la clase
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(TransactionServiceTestCase.class);

	@Resource
	private TransaccionService transaccionService;

	/**
	 * El metodo getString lanza {@link UnsupportedOperationException}
	 * (Runtime), bajo un ambiente transaccional normal se esperaría un rollback
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void getString() {
		logger.debug("iniciando prueba");
		transaccionService.getString("Test");
	}

	/**
	 * El método insertFoo también lanza {@link UnsupportedOperationException}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void insertFoo() {

		transaccionService.insertFoo("hola");

	}

	/**
	 * El método getMorestrings lanza {@link BusinessException}. En un ambiente
	 * transaccional común. este tipo de excepciones no provocan rollback, pero
	 * en Spring podemos modificar ese comportamiento
	 * 
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void getMoreStrings() throws BusinessException {

		transaccionService.getMoreStrings("Test", "ss");
	}

	/**
	 * El metodo updateFoo no lanza excepción pero tarda 5 segundos en procesar.
	 */
	@Test
	public void updateFoo() {
		transaccionService.updateFoo("foo");
	}

}
