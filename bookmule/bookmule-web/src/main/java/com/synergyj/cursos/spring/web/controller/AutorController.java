/**
 * Copyright (c) SynergyJ. 
 * Todos los derechos reservados.
 *
 * Este software es de propósito educativo, puede ser
 * empleado para fines sin lucro haciendo referencia 
 * al autor intelectual.
 */
package com.synergyj.cursos.spring.web.controller;

import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.synergyj.bookmule.core.domain.Autor;
import com.synergyj.bookmule.core.services.AutorService;

/**
 * @author Jorge Rodríguez Campos (jorge.rodriguez@synergyj.com)
 */
@Controller
@RequestMapping("/autor")
public class AutorController {

	@Resource
	private AutorService autorService;

	/**
	 * Logger para todas las instancias de la clase
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(AutorController.class);

	// TODO B) Agregar @RequestMapping con el URL adecuado
	@RequestMapping("consultaAutores")
	public void consultaAutores(ModelMap model) {
		Set<Autor> autorSet;
		logger.debug("invocando consulta de autores");
		autorSet = autorService.buscaAutores(new Autor());
		model.addAttribute(autorSet);
		// ¿Qué vista se va a presentar?
		// la vista se crea usando la convencion del mismo path:
		// /WEB-INF/views/jsp/autor/consultaAutores.jsp
	}

	/**
	 * @param id
	 * @return
	 */
	@RequestMapping("consultaAutorPorId")
	public Autor consultaAutorPorId(@RequestParam(required = true) Long id) {
		return autorService.obtenAutorPorId(id);
	}

	/**
	 * @param autor
	 */
	@RequestMapping("creaAutor")
	// TODO C) Agregar los parametros que requiere este método, modificar el
	// null que se pasa como
	// parámetro al servicio.
	// se realiza el binding sin validacion solo macheo de propiedades
	public void creaAutor(Autor autor) {
		// Esta es una forma sencilla para realizar el manejo de formularios.
		// Sin embargo hay varia
		// cosa que hay que cuidar: validaciones, proceso de binding, etc.
		logger.debug("Creando a un nuevo autor");
		autorService.creaAutor(autor);
	}
}
