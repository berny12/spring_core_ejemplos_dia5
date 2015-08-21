/**
 * Copyright (c) SynergyJ. 
 * Todos los derechos reservados.
 *
 * Este software es de propósito educativo, puede ser
 * empleado para fines sin lucro haciendo referencia 
 * al autor intelectual.
 */
package com.synergyj.cursos.spring.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jorge Rodríguez Campos (jorge.rodriguez@synergyj.com)
 */
// TODO C) Anotar el controller con el path correcto para resolver los paths en
// index.jsp, agregar
// @RequestMapping con el path "/mapeos"
@Controller
@RequestMapping("/mapeos")
public class MuitiController {

	// TODO D) Anotar con @RequestMapping segun corresponda
	// no es necesario porner el /path, spring lo pone por default al crear el
	// root
	@RequestMapping("path")
	public @ResponseBody
	String matchPorPath() {
		return "match por path";
	}

	// TODO E) Anotar con @RequestMapping segun corresponda
	// las ligas se ponen con metodo get
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	String matchPorMetodoHttp() {
		return "match por metodo http 2";
	}

	// TODO F) Anotar con @RequestMapping segun corresponda
	@RequestMapping(params = { "foo", "bar" })
	public @ResponseBody
	String matchPorParamertros(@RequestParam(required = true) String foo,
			@RequestParam String bar) {
		return "match por  parametros foo=" + foo + ", bar=" + bar;
	}

	// TODO G) Anotar con @RequestMapping segun corresponda
	@RequestMapping("headers")
	public @ResponseBody
	String matchPorHeader(@RequestHeader("Accept-encoding") String encoding,
			@RequestHeader("Host") String host, @RequestParam boolean headers) {
		return "match por header, encoding =" + encoding + ", host: " + host
				+ ", headers? " + headers;
	}

	// TODO H) Anotar con @RequestMapping segun corresponda
	@RequestMapping("variables/{a}/{b}/{c}")
	public @ResponseBody
	String matchPorUriTemplate(@PathVariable("a") int a,
			@PathVariable("b") double b, @PathVariable("c") char c) {
		return String
				.format("match por uri template a=%d, b=%f, c=%s", a, b, c);
	}

	// TODO I) Anotar con @RequestMapping segun corresponda
	@RequestMapping("matrix/{values}")
	public @ResponseBody
	String matchPorMatrixVariable(
			@MatrixVariable(value = "x", pathVar = "values") int x) {
		return String.format("match por matrix variable, x = %d", x);
	}

	@RequestMapping("matrixMap/{values}")
	public @ResponseBody
	String matchPorMatrixMapVariable(
			@MatrixVariable(pathVar = "values") Map<String, Integer> map
	// TODO J) Completar los parámetros del método
	) {
		// Quitar esta línea al completar los parámetros.
		// Map<String, Integer> map = null;
		return "match por matrix map variable, map = " + map;
	}

}
