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

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.synergyj.cursos.spring.service.Cliente;
import com.synergyj.cursos.spring.service.ClienteService;

/**
 * @author Jorge Rodríguez Campos (jorge.rodriguez@synergyj.com)
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/txProgramaticasAppContext.xml")
public class TransaccionesProgramaticasTestCase {

	/**
	 * Logger para todas las instancias de la clase
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(TransaccionesProgramaticasTestCase.class);

	@Resource(name = "clienteServiceTxProgramaticas")
	private ClienteService clienteService;

	@Test
	public void creaCliente() {
		Cliente cliente;

		cliente = new Cliente();
		cliente.setNombre("SynergyJ");
		logger.debug("invocando un servicio transaccional, revisar bitacoras");
		clienteService.creaCliente(cliente);

		logger.debug("invocando un servicio transaccional que hará rollback");
		cliente.setNombre(null);
		clienteService.creaCliente(cliente);
	}

	@Test
	public void consultaCliente() {

		logger.debug("incocando metodo sin transaccion");
		clienteService.findClienteById(1L);
	}

	@Test
	public void actualizaCliente() {
		Cliente cliente;
		logger.debug("invocando actualización del cliente");
		cliente = clienteService.actualizaCliente(1L, "cliente");
		Assert.assertEquals("cliente modificado", cliente.getNombre());
	}

}
