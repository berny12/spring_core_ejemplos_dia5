/**
 * Copyright (c) SynergyJ. 
 * Todos los derechos reservados.
 *
 * Este software es de prop�sito educativo, puede ser
 * empleado para fines sin lucro haciendo referencia 
 * al autor intelectual.
 */
package com.synergyj.cursos.spring.web.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jorge Rodr�guez Campos (jorge.rodriguez@synergyj.com)
 */
// @Controller
@RequestMapping("/imagenes")
public class ImagenesController {

	/**
	 * Logger para todas las instancias de la clase
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(ImagenesController.class);

	/**
	 * @param nombre
	 * @param file
	 * @throws IOException
	 */
	@RequestMapping("guardaImagenes")
	// TODO A) Completar el m�todo
	public void guardaImagenes() throws IOException {
	}

	/**
	 * @param exception
	 */
	// TODO B) Agregar un m�todo que maneje excepciones y mande despliegue la
	// pagina de error.

}
