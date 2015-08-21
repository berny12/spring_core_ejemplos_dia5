/**
 * Copyright (c) SynergyJ. 
 * Todos los derechos reservados.
 *
 * Este software es de propósito educativo, puede ser
 * empleado para fines sin lucro haciendo referencia 
 * al autor intelectual.
 */
package com.synergyj.cursos.spring.web.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergyj.bookmule.core.domain.Cliente;
import com.synergyj.bookmule.core.services.ClienteService;
import com.synergyj.cursos.spring.web.controller.validator.ClienteValidator;

/**
 * @author Jorge Rodríguez Campos (jorge.rodriguez@synergyj.com)
 */
@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Resource
	private ClienteService clienteService;

	@Resource
	private ClienteValidator clienteValidator;

	private static final String capturaClienteView = "cliente/capturaCliente";

	/**
	 * Logger para todas las instancias de la clase
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(ClienteController.class);

	// TODO A) Asociar el validador a este controller
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(clienteValidator);
	}

	/**
	 * @param model
	 */
	@RequestMapping("capturaCliente")
	public void capturaCliente(ModelMap model) {
		// TODO B) completar este método
		// para poder realizar el llenado automatco la clase Cliente se
		// transforma a camello por eso machea con el jsp
		model.addAttribute(new Cliente());
	}

	/**
	 * @param cliente
	 * @param result
	 * @return
	 */
	@RequestMapping("creaCliente")
	// TODO C) Completar este método
	// bindingResult -> contiene el resultador de lvalidador
	public String creaCliente(@Valid Cliente cliente, BindingResult result) {
		if (result.hasErrors()) {
			return capturaClienteView;
		}
		logger.debug("creando un cliente nuevo...");
		clienteService.creaCliente(cliente);
		// se deja null par que se mande a la vista por convencion
		return null;
	}

}
