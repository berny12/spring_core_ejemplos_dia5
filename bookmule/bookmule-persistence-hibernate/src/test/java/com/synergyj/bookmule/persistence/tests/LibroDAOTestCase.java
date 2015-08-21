/**
 * Copyright (c) SynergyJ. 
 * Todos los derechos reservados.
 *
 * Este software es de propósito educativo, puede ser
 * empleado para fines sin lucro haciendo referencia 
 * al autor intelectual.
 */
package com.synergyj.bookmule.persistence.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.FileCopyUtils;

import com.synergyj.bookmule.core.domain.Autor;
import com.synergyj.bookmule.core.domain.Editorial;
import com.synergyj.bookmule.core.domain.ImagenLibro;
import com.synergyj.bookmule.core.domain.Libro;
import com.synergyj.bookmule.core.domain.beans.CriterioBusquedaLibro;
import com.synergyj.bookmule.core.domain.catalog.Categoria;
import com.synergyj.bookmule.core.domain.catalog.StatusLibro;
import com.synergyj.bookmule.persistence.dao.AutorDAO;
import com.synergyj.bookmule.persistence.dao.LibroDAO;
import com.synergyj.bookmule.persistence.repository.hibernate.GenericHibernateDAOImpl;

/**
 * @author Jorge Rodríguez Campos (jorge.rodriguez@synergyj.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/hibernate4ApplicationContext.xml")
public class LibroDAOTestCase extends AbstractTransactionalJUnit4SpringContextTests {

	/**
	 * Logger para todas las instancias de la clase
	 */
	private static final Logger logger = LoggerFactory.getLogger(LibroDAOTestCase.class);

	@Resource
	private LibroDAO libroDAO;

	@Resource
	private AutorDAO autorDAO;

	/**
	 * Observar que al terminar la prueba se hace rollback para dejar la base intacta, esto con el
	 * simple hecho de extender de la clase {@link AbstractTransactionalJUnit4SpringContextTests}
	 * @throws IOException
	 */
	@Test
	public void creaYbuscaLibro() throws IOException {
		Libro libro, libroObtenido;
		CriterioBusquedaLibro criterios;
		Set<Libro> libroSet;

		libro = construyeLibroFicticio();
		libroDAO.crea(libro);
		logger.debug("Libro creado {}", libro);
		Assert.assertNotNull(libro.getId());

		logger.debug("consultando el libro para verificar");
		// flush para forzar la persistencia en la BD y evitar falsos positivos.
		((GenericHibernateDAOImpl) libroDAO).flush();

		criterios = new CriterioBusquedaLibro();
		criterios.setTitulo(libro.getTitulo());
		criterios.setPrecioVenta(libro.getPrecioVenta());
		criterios.setIsbn(libro.getIsbn());
		libroSet = libroDAO.busca(criterios);
		Assert.assertEquals(new Integer(1), new Integer(libroSet.size()));
		libroObtenido = libroSet.iterator().next();
		logger.debug("libro obtenido: {}", libroObtenido);

		// comparando properties para validar su correcta persistencia.
		BeanPropertiesComparator.compare(libro, libroObtenido, "id", "titulo", "edicion", "isbn",
				"codigoColor", "codigoClasificacion", "numeroEjemplares", "precioVenta",
				"editorial.id", "statusLibro.id");

		// validando categorias
		Assert.assertTrue(libro.getCategorias().size() == jdbcTemplate.queryForObject(
				"select count(*) from categoria_libro where libro_id =?", Integer.class,
				libro.getId()));

	}

	/**
	 * @return
	 * @throws IOException
	 */
	private Libro construyeLibroFicticio() throws IOException {
		Libro libro;
		Editorial editorial;
		Set<Categoria> setCategoria;
		Set<Autor> autores;
		Autor autor;
		List<ImagenLibro> imagenes;
		ImagenLibro imagenLibro;
		File[] files;

		libro = new Libro();
		libro.setCodigoClasificacion("B034");
		libro.setCodigoColor("002343");
		libro.setCodigoClasificacion("WL0092");
		libro.setEdicion(1);
		// asignando editorial
		editorial = new Editorial();
		editorial.setId(
				jdbcTemplate.queryForObject("select min(editorial_id) from editorial", Long.class));
		Assert.assertNotNull(editorial.getId());
		libro.setEditorial(editorial);
		libro.setIsbn("9788481812275");
		libro.setNumeroEjemplares(1);
		libro.setPrecioVenta(new Float(234.56));
		libro.setStatusLibro(StatusLibro.DISPONIBLE);
		libro.setTitulo("100 años de Soledad");
		// categorias
		setCategoria = new HashSet<>();
		setCategoria.add(Categoria.LITERATURA);
		setCategoria.add(Categoria.HISTORIA);
		libro.setCategorias(setCategoria);
		// Autores
		autores = new HashSet<Autor>(3);
		autor = construyeAutorFicticio();
		autorDAO.crea(autor);
		autores.add(autor);
		Assert.assertNotNull(autor.getId());
		libro.setAutores(autores);
		// imagenes
		files = new ClassPathResource("img/libros").getFile().listFiles();
		imagenes = new ArrayList<>();
		for (int i = 0; i < files.length; i++) {
			imagenLibro = new ImagenLibro();
			imagenLibro.setImagen(FileCopyUtils.copyToByteArray(files[i]));
			imagenLibro.setNumImagen(i + 1);
			imagenes.add(imagenLibro);
		}
		libro.setImagenes(imagenes);

		return libro;
	}

	/**
	 * @return
	 */
	private Autor construyeAutorFicticio() {
		Autor autor;
		autor = new Autor();
		autor.setNombre("Pablo");
		autor.setApellidoPaterno("Neruda");
		return autor;
	}
}
