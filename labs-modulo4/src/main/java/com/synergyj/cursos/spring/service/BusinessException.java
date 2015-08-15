package com.synergyj.cursos.spring.service;

/**
 * @author Jorge Rodríguez Campos (jorge.rodriguez@synergyj.com)
 */
public class BusinessException extends Exception {
	private static final long serialVersionUID = -9118042212338480276L;

	public BusinessException(String mensaje) {
		super(mensaje);
	}
}
