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
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.synergyj.bookmule.core.domain.Libro;
import com.synergyj.bookmule.core.domain.beans.CriterioBusquedaLibro;
import com.synergyj.bookmule.persistence.dao.LibroDAO;

/**
 * @author Jorge Rodríguez Campos (jorge.rodriguez@synergyj.com)
 */
@Repository("libroDAO")
public class LibroDAOHibernate extends GenericHibernateDAOImpl implements LibroDAO {

	/*
	 * (non-Javadoc)
	 * @see
	 * com.synergyj.bookmule.persistence.dao.LibroDAO#crea(com.synergyj.bookmule.core.domain.Libro)
	 */
	@Override
	public void crea(final Libro libro) {
		// TODO C) Implementar
		save(libro);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.synergyj.bookmule.persistence.dao.LibroDAO#busca(com.synergyj.bookmule.core.domain.beans
	 * .CriterioBusquedaLibro)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<Libro> busca(CriterioBusquedaLibro criterios) {

		Criteria criteria;
		criteria = createCriteria(Libro.class);
		if (criterios.getTitulo() != null) {
			criteria.add(Restrictions.like("titulo", criterios.getTitulo(), MatchMode.ANYWHERE));
		}
		if (criterios.getIsbn() != null) {
			criteria.add(Restrictions.like("isbn", criterios.getIsbn()));
		}
		// TODO D) Agregar el criterio para la editorial
		if (criterios.getEditorial() != null) {
			criteria.add(Restrictions.eq("editorial.id", criterios.getEditorial().getId()));
		}
		if (criterios.getPrecioInicial() != null && criterios.getPrecioFinal() != null) {
			criteria.add(Restrictions.between("precio", criterios.getPrecioInicial(),
					criterios.getPrecioFinal()));
		}
		// Esta línea hace un inner Join en lugar de un subquery
		criteria.setFetchMode("editorial", FetchMode.JOIN);

		return new HashSet<>(criteria.list());
	}

	/*
	 * (non-Javadoc)
	 * @see com.synergyj.bookmule.persistence.dao.LibroDAO#findById(java.lang.Long)
	 */
	@Override
	public Libro findById(Long id) {
		return get(Libro.class,
				"from Libro l left join fetch l.autores a left join fetch l.categorias ca "
						+ "left join fetch l.editorial e left join fetch l.imagenes im "
						+ "left join fetch l.proovedores p where l.id=?",
				id);
	}
}
