/**
 * Copyright (c) SynergyJ. 
 * Todos los derechos reservados.
 *
 * Este software es de prop�sito educativo, puede ser
 * empleado para fines sin lucro haciendo referencia 
 * al autor intelectual.
 */
package com.synergyj.bookmule.persistence.dao;

import java.util.Set;

import com.synergyj.bookmule.core.domain.TarjetaCredito;

/**
 * @author Jorge Rodr�guez Campos (jorge.rodriguez@synergyj.com)
 */
public interface TarjetaCreditoDAO {

	void crea(TarjetaCredito tarjeta, Long clienteId);

	TarjetaCredito buscaPorNumero(String numero);

	TarjetaCredito buscaPorId(Long id);

	Set<TarjetaCredito> buscaPorCliente(Long id);

}
