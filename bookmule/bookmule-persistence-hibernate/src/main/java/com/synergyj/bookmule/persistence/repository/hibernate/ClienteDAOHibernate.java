/**
 * Copyright (c) SynergyJ. 
 * Todos los derechos reservados.
 *
 * Este software es de propósito educativo, puede ser
 * empleado para fines sin lucro haciendo referencia 
 * al autor intelectual.
 */
package com.synergyj.bookmule.persistence.repository.hibernate;

import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.or;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.synergyj.bookmule.core.domain.Cliente;
import com.synergyj.bookmule.persistence.dao.ClienteDAO;

/**
 * @author Jorge Rodríguez Campos (jorge.rodriguez@synergyj.com)
 */
@Repository("clienteDAO")
public class ClienteDAOHibernate extends GenericHibernateDAOImpl implements ClienteDAO {

	/*
	 * (non-Javadoc)
	 * @see com.synergyj.bookmule.persistence.dao.ClienteDAO#crea(com.synergyj.bookmule.core.domain.
	 * Cliente)
	 */
	@Override
	public void crea(Cliente cliente) {
		save(cliente);

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.synergyj.bookmule.persistence.dao.ClienteDAO#busca(com.synergyj.bookmule.core.domain.
	 * Cliente)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<Cliente> busca(Cliente cliente) {
		Criteria criteria;
		criteria = createCriteria(Cliente.class);
		if (cliente.getApellidoMaterno() != null) {
			criteria.add(Restrictions.like("apellidoMaterno", cliente.getApellidoMaterno(),
					MatchMode.ANYWHERE));
		}
		if (cliente.getApellidoPaterno() != null) {
			criteria.add(Restrictions.like("apellidoPaterno", cliente.getApellidoPaterno(),
					MatchMode.ANYWHERE));
		}
		if (cliente.getClienteFrecuenteId() != null) {
			criteria.add(Restrictions.eq("clienteFrecuenteId", cliente.getClienteFrecuenteId()));
		}
		if (cliente.getEmail() != null) {
			criteria.add(Restrictions.like("email", cliente.getEmail(), MatchMode.ANYWHERE));
		}
		if (cliente.getEmail() != null) {
			criteria.add(Restrictions.like("apellidoPaterno", cliente.getApellidoPaterno(),
					MatchMode.ANYWHERE));
		}
		if (cliente.getId() != null) {
			criteria.add(Restrictions.eq("id", cliente.getId()));
		}
		if (cliente.getNombre() != null) {
			criteria.add(Restrictions.like("nombre", cliente.getNombre(), MatchMode.ANYWHERE));
		}
		if (cliente.getRfc() != null) {
			criteria.add(Restrictions.eq("rfc", cliente.getRfc()));
		}
		if (cliente.getTipoCliente() != null) {
			criteria.add(Restrictions.eq("tipoCliente", cliente.getTipoCliente()));
		}
		if (cliente.getUsuario() != null) {
			criteria.add(Restrictions.eq("usuario", cliente.getUsuario()));
		}

		return new HashSet<>(criteria.list());

	}

	/*
	 * (non-Javadoc)
	 * @see com.synergyj.bookmule.persistence.dao.ClienteDAO#findById(java.lang.Long)
	 */
	@Override
	public Cliente findById(Long id) {
		return get(Cliente.class, id);

	}

	/*
	 * (non-Javadoc)
	 * @see com.synergyj.bookmule.persistence.dao.ClienteDAO#existeCliente(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public boolean existeCliente(String rfc, String username, String email) {
		Criteria criteria;

		criteria = super.createCriteria(Cliente.class);
		criteria.add(or(eq("rfc", rfc), eq("usuario", username), eq("email", email)));
		return criteria.list().size() > 0;
	}

}
