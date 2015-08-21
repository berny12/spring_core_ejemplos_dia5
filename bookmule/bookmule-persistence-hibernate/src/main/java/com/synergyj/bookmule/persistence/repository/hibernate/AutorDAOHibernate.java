/**
 * Copyright (c) SynergyJ. 
 * Todos los derechos reservados.
 *
 * Este software es de propósito educativo, puede ser
 * empleado para fines sin lucro haciendo referencia 
 * al autor intelectual.
 */
package com.synergyj.bookmule.persistence.repository.hibernate;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.synergyj.bookmule.core.domain.Autor;
import com.synergyj.bookmule.persistence.dao.AutorDAO;

/**
 * @author Jorge Rodríguez Campos (jorge.rodriguez@synergyj.com)
 */
@Repository("autorDAO")
public class AutorDAOHibernate extends GenericHibernateDAOImpl implements AutorDAO {

	/*
	 * (non-Javadoc)
	 * @see
	 * com.synergyj.bookmule.persistence.dao.AutorDAO#crea(com.synergyj.bookmule.core.domain.Autor)
	 */
	@Override
	public void crea(Autor autor) {
		super.save(autor);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.synergyj.bookmule.persistence.dao.AutorDAO#buscaAutores(com.synergyj.bookmule.core.domain
	 * .Autor)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<Autor> busca(Autor autorCriterio) {
		Criteria criteria;

		criteria = createCriteria(Autor.class);
		if (autorCriterio.getNombre() != null) {
			criteria.add(
					Restrictions.like("nombre", autorCriterio.getNombre(), MatchMode.ANYWHERE));
		}
		if (autorCriterio.getApellidoPaterno() != null) {
			criteria.add(Restrictions.like("apellidoPaterno", autorCriterio.getApellidoPaterno(),
					MatchMode.ANYWHERE));
		}
		if (autorCriterio.getApellidoMaterno() != null) {
			criteria.add(Restrictions.like("apellidoMaterno", autorCriterio.getApellidoMaterno(),
					MatchMode.ANYWHERE));
		}
		return new HashSet<>(criteria.list());
	}

	/*
	 * (non-Javadoc)
	 * @see com.synergyj.bookmule.persistence.dao.AutorDAO#findById(java.lang.Long)
	 */
	@Override
	public Autor findAutorById(Long id) {

		return super.get(Autor.class, id);
	}

}
