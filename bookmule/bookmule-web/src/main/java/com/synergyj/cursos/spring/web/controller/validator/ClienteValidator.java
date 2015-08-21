/**
 * Copyright (c) SynergyJ. 
 * Todos los derechos reservados.
 *
 * Este software es de propósito educativo, puede ser
 * empleado para fines sin lucro haciendo referencia 
 * al autor intelectual.
 */
package com.synergyj.cursos.spring.web.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergyj.bookmule.core.domain.Cliente;

/**
 * @author Jorge Rodríguez Campos (jorge.rodriguez@synergyj.com)
 */
@Component("clienteValidator")
public class ClienteValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.isAssignableFrom(Cliente.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		Cliente cliente = (Cliente) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellidoPaterno",
				"required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "direccion",
				"required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rfc", "required");
		ValidationUtils
				.rejectIfEmptyOrWhitespace(errors, "usuario", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"confirmacionPassword", "required");
		// TODO D) Agregar una validación para el campo tipoCliente
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoCliente",
				"required");

		if (cliente.getPassword() == null
				|| cliente.getConfirmacionPassword() == null
				|| !cliente.getPassword().equals(
						cliente.getConfirmacionPassword())) {
			errors.rejectValue("password", "bookmule.password.match.error");
		}
		// TODO E) Agregar una validación en la que se verifique que el tipo de
		// cliente seleccionado
		// debe ser AFICIONADO. Emplear bookmule.tipoCliente.error como clave
		// del mensaje de error.
		if (cliente.getTipoCliente() != TipoCliente.AFICIONADO) {
			errors.rejectValue("tipoCliente", "bookmule.tipoCliente.error",
					new Object[] { TipoCliente.AFICIONADO.getClave() }, "error");
		}

	}

}
