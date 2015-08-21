/**
 * Copyright (c) SynergyJ. 
 * Todos los derechos reservados.
 *
 * Este software es de prop�sito educativo, puede ser
 * empleado para fines sin lucro haciendo referencia 
 * al autor intelectual.
 */
package com.synergyj.bookmule.persistence.tests;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Jorge Rodr�guez Campos (jorge.rodriguez@synergyj.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/jdbcTestAppContext.xml")
public class DataSourceTestCase {

	/**
	 * Logger para todas las instancias de la clase
	 */
	private static final Logger logger = LoggerFactory.getLogger(DataSourceTestCase.class);

	// TODO A) Inyectar dataSource
	@Resource
	private DataSource dataSource;

	@Test
	public void getConnection() {
		// TODO B) Implementar m�todo

		try (Connection connection = dataSource.getConnection();) {
			Assert.assertNotNull(connection);
		} catch (SQLException e) {
			logger.error("Error de conexi�n ", e);
			Assert.fail();
		}
	}
}
