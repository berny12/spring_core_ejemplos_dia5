/**
 * Fecha de creaci�n: 03/01/2013 15:35:03
 *
 * Copyright (c) 2012 SynergyJ. 
 * Todos los derechos reservados.
 *
 * Este software es de prop�sito educativo, puede ser
 * empleado para fines sin lucro haciendo referencia 
 * al autor intelectual.
 */
package com.synergyj.bookmule.persistence.tests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.synergyj.bookmule.core.domain.Banco;
import com.synergyj.bookmule.core.domain.Cliente;
import com.synergyj.bookmule.core.domain.TarjetaCredito;
import com.synergyj.bookmule.core.domain.catalog.TipoCliente;
import com.synergyj.bookmule.core.domain.catalog.TipoTarjeta;

/**
 * Prueba unitaria que muestra el uso de los principales m�todos de
 * {@link JdbcTemplate}
 * 
 * @author Jorge Rodr�guez Campos (jorge.rodriguez@synergyj.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/jdbcTestAppContext.xml")
public class JdbcTemplateTestCase {

	public static final Logger logger = LoggerFactory.getLogger(JdbcTemplateTestCase.class);

	@Resource
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	private List<Banco> bancosDisponibles;

	/**
	 * Inicializa el JdbcTemplate para emplearse en todos los ejemplos
	 */
	@Before
	public void setup() {

		jdbcTemplate = new JdbcTemplate(dataSource);
		cargaBancos();
	}

	/**
	 * Obtiene la lista de bancos de la BD y la guarda en memoria.
	 */
	private void cargaBancos() {
		// TODO A) Implementar este m�todo
		bancosDisponibles = jdbcTemplate.query("SELECT banco_id, nombre, url_cargo FROM banco", new RowMapper<Banco>() {

			@Override
			public Banco mapRow(ResultSet rs, int rowNum) throws SQLException {
				Banco banco;
				int index = 0;
				banco = new Banco();
				banco.setId(rs.getLong(++index));
				banco.setNombre(rs.getString(++index));
				banco.setUrlCargo(rs.getString(++index));
				return banco;
			}
		});
	}

	/**
	 * Ejemplos b�sicos del uso de JdbcTemplate.
	 */
	@Test
	public void ejemplos() {
		int numTipoCliente = 0;
		int numCategorias = 0;

		// TODO B) Obtener el n�mero de tipos de cliente que existen en el
		// catalogo

		numTipoCliente = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM tipo_cliente", Integer.class);

		// que el numero de registros en la bd sea igual a lo que esta en
		// memoria en este caso 3
		Assert.assertEquals(TipoCliente.values().length, numTipoCliente);

		// TODO C) Obtener el numero de categorias de libros que tengan que ver
		// con mascotas
		numCategorias = jdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM categoria " + "WHERE lower(descripcion) LIKE ? ", Integer.class, "%mascota%");
		Assert.assertTrue(numCategorias > 0);

	}

	/**
	 * Crea un nuevo cliente. Observar que es necesario emplear un
	 * <code>PreparedStatementCreator</code> debido a que se requiere indicar la
	 * constante <code>Statement.RETURN_GENERATED_KEYS</code> para poder
	 * recuperar la PK, esto debido a que se emplean tablas con columnas tipo
	 * identity. En otros manejadores, se emplean secuencias y no requiere
	 * extraer la PK asignada.
	 */
	@Test
	public void creaCliente() {
		final Cliente cliente;
		final String sql;
		int rows = 0;
		GeneratedKeyHolder keyHolder; // sirve para saber la pk del que se
										// inserta

		cliente = creaClienteFicticio();

		sql = "insert into cliente (cliente_frecuente_id,nombre, apellido_paterno, apellido_materno,"
				+ "email,rfc,direccion,telefono,usuario,password,tipo_cliente_id)" + " values(?,?,?,?,?,?,?,?,?,?,?)";

		keyHolder = new GeneratedKeyHolder();

		// TODO D) Completar este m�todo para insertar un cliente empleando
		// JdbcTemplate, para obtener la llave primaria
		rows = jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps;
				ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 0;

				// Emplear este c�digo para hacer la asignaci�n de variables
				ps.setNull(++index, Types.NUMERIC);
				ps.setString(++index, cliente.getNombre());
				ps.setString(++index, cliente.getApellidoPaterno());
				ps.setString(++index, cliente.getApellidoMaterno());
				ps.setString(++index, cliente.getEmail());
				ps.setString(++index, cliente.getRfc());
				ps.setString(++index, cliente.getDireccion());
				ps.setString(++index, cliente.getTelefono());
				ps.setString(++index, cliente.getUsuario());
				ps.setString(++index, cliente.getPassword());
				ps.setInt(++index, cliente.getTipoCliente().getId());
				return ps;
			}
		}, keyHolder);

		Assert.assertEquals(1, rows);
		Assert.assertNotNull(keyHolder.getKey());
		Assert.assertTrue(keyHolder.getKey().longValue() > 0L);
		// asignando el valor de la PK generado al cliente.
		cliente.setId(keyHolder.getKey().longValue());

		logger.debug("cliente creado, registrando tarjeta");
		creaTarjetas(cliente);

		logger.debug("consultando al cliente registrado");
		consultaCliente(cliente.getId());

	}

	/**
	 * Aqui no se recupera el Id de la tarjeta para ilustrar el uso corto del
	 * metodo update.
	 * 
	 * @param cliente
	 */
	private void creaTarjetas(Cliente cliente) {
		String sql;
		int rows = 0;
		for (TarjetaCredito tarjeta : cliente.getTarjetasCredito()) {

			sql = "insert into tarjeta_credito(numero_tarjeta,mes_expiracion,anio_expiracion,"
					+ "numero_seguridad,cliente_id,banco_id,tipo_tarjeta_id) values(?,?,?,?,?,?,?)";

			// TODO E) Completar este m�todo
			rows = jdbcTemplate.update(sql, tarjeta.getNumeroTarjeta(), tarjeta.getMesExpiracion(),
					tarjeta.getAnioExpiracion(), tarjeta.getNumeroSeguridad(), cliente.getId(),
					tarjeta.getBanco().getId(), tarjeta.getTipoTarjeta().getId());

			Assert.assertEquals(1, rows);
			logger.debug("tarjeta de credito creada {}", tarjeta);
		}
	}

	/**
	 * @param clienteId
	 */
	private void consultaCliente(Long clienteId) {
		Cliente cliente = null;
		String sql = "select c.cliente_id, c.nombre, c.apellido_paterno, c.apellido_materno,"
				+ " c.email,c.rfc,c.direccion,c.telefono,c.usuario,c.password,c.tipo_cliente_id "
				+ " from cliente c where c.cliente_id=?";

		// TODO F) completar, �cu�l ser� el m�todo m�s adecuado para
		// implementar?
		cliente = jdbcTemplate.queryForObject(sql, new RowMapper<Cliente>() {

			@Override
			public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
				// Emplear este c�digo para realizar el mapeo.
				Cliente cliente;
				int index = 1;
				cliente = new Cliente();
				cliente.setId(rs.getLong(index++));
				cliente.setNombre(rs.getString(index++));
				cliente.setApellidoPaterno(rs.getString(index++));
				cliente.setApellidoMaterno(rs.getString(index++));
				cliente.setEmail(rs.getString(index++));
				cliente.setRfc(rs.getString(index++));
				cliente.setDireccion(rs.getString(index++));
				cliente.setTelefono(rs.getString(index++));
				cliente.setUsuario(rs.getString(index++));
				cliente.setPassword(rs.getString(index++));
				cliente.setTipoCliente(TipoCliente.valueOf(rs.getInt(index++)));
				return cliente;
			}
		}, clienteId);

		Assert.assertEquals(clienteId, cliente.getId());

		logger.debug("eliminando tarjeta y clientes creados");

		eliminaTarjetaCliente(clienteId);

	}

	/**
	 * Con Spring es posible limpiar la BD despues de haber insertado o
	 * modificado registros sin tener que hacerlo de forma manual como en este
	 * caso. En siguientes laboratorios se ver� su uso.
	 * 
	 * @param id
	 */
	private void eliminaTarjetaCliente(Long id) {
		int rows = 0;
		// TODO G) implementar este m�todo.
		rows = jdbcTemplate.update("DELETE from tarjeta_credito WHERE cliente_id = ?", id);
		rows += jdbcTemplate.update("DELETE FROM cliente WHERE cliente_id = ?", id);
		Assert.assertEquals(2, rows);
	}

	/**
	 * @return
	 */
	private Cliente creaClienteFicticio() {

		Cliente cliente;
		TarjetaCredito tarjeta;
		Set<TarjetaCredito> tarjetas;

		cliente = new Cliente();
		cliente.setNombre("JUAN");
		cliente.setApellidoMaterno("LUNA");
		cliente.setApellidoPaterno("LARA");
		cliente.setEmail("juan@mail.com");
		cliente.setRfc("LALU870902LZ0");
		cliente.setDireccion("AV. CENTRAL 14");
		cliente.setTelefono("5589239832");
		cliente.setUsuario("juanito");
		cliente.setPassword("12345678");
		cliente.setTipoCliente(TipoCliente.AFICIONADO);
		// tarjeta de credito
		tarjetas = new HashSet<TarjetaCredito>();
		tarjeta = new TarjetaCredito();
		tarjeta.setAnioExpiracion(2020);
		tarjeta.setMesExpiracion(10);
		tarjeta.setNumeroSeguridad(111);
		tarjeta.setNumeroTarjeta("9238923892389483");
		tarjeta.setTipoTarjeta(TipoTarjeta.VISA);
		tarjetas.add(tarjeta);
		cliente.setTarjetasCredito(tarjetas);
		// le asigna un banco del catalogo.
		tarjeta.setBanco(bancosDisponibles.get(0));
		return cliente;
	}

}
