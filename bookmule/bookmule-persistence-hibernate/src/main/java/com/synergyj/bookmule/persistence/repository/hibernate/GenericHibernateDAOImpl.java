package com.synergyj.bookmule.persistence.repository.hibernate;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.spi.CurrentSessionContext;
import org.hibernate.criterion.Example;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.support.DataAccessUtils;

/**
 * Clase base implementada para el acceso a base de datos con Hibernate 4
 * @author Jorge Rodríguez Campos (jorge.rodriguez@synergyj.com)
 */
public abstract class GenericHibernateDAOImpl implements GenericHibernateDAO {

	@Resource
	private SessionFactory sessionFactory;

	/*
	 * La documentación de este método se encuentra en la clase o interface que lo declara
	 * (non-Javadoc)
	 * @see mx.org.ife.siirfe.comun.dao.hibernate.GenericHibernateDAO#get(java.lang .Class, long)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> clazz, Serializable id) {

		return (T) getCurrentSession().get(clazz, id);
	}

	/*
	 * La documentación de este método se encuentra en la clase o interface que lo declara
	 * (non-Javadoc)
	 * @see mx.org.ife.siirfe.comun.dao.hibernate.GenericHibernateDAO#get(java.lang .Class,
	 * java.lang.String, java.lang.String[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Class<T> clazz, String query, Object... params) {
		Query hqlQuery;
		hqlQuery = getCurrentSession().createQuery(query);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				hqlQuery.setParameter(i, params[i]);
			}
		}

		try {
			return (T) hqlQuery.uniqueResult();
		} catch (NonUniqueResultException e) {
			throw new IncorrectResultSizeDataAccessException(e.getMessage(), 1, e);
		}

	}

	/*
	 * La documentación de este método se encuentra en la clase o interface que lo declara
	 * (non-Javadoc)
	 * @see mx.org.ife.siirfe.comun.dao.hibernate.GenericHibernateDAO#load(java.lang .Class, long)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T load(Class<T> clazz, Serializable id) {
		return (T) getCurrentSession().load(clazz, id);
	}

	/*
	 * La documentación de este método se encuentra en la clase o interface que lo declara
	 * (non-Javadoc)
	 * @see mx.org.ife.siirfe.comun.dao.hibernate.GenericHibernateDAO#find(java.lang .String,
	 * java.lang.Object[])
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> find(String query, Object... params) {

		Query hQuery = getCurrentSession().createQuery(query);

		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				hQuery.setParameter(i, params[i]);
			}
		}
		return hQuery.list();
	}

	/*
	 * La documentación de este método se encuentra en la clase o interface que lo declara
	 * (non-Javadoc)
	 * @see mx.org.ife.siirfe.comun.dao.hibernate.GenericHibernateDAO#queryForEntity
	 * (java.lang.String, java.lang.Object[])
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T queryForEntity(String query, Object... params) {

		Query hQuery = getCurrentSession().createQuery(query);

		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				hQuery.setParameter(i, params[i]);
			}
		}
		return (T) DataAccessUtils.requiredSingleResult(hQuery.list());
	}

	/*
	 * La documentación de este método se encuentra en la clase o interface que lo declara
	 * (non-Javadoc)
	 * @see mx.org.ife.siirfe.comun.dao.hibernate.GenericHibernateDAO#findAll(java .lang.Class)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(Class<T> clazz) {
		return getCurrentSession().createQuery("from " + clazz.getName()).list();
	}

	/*
	 * La documentación de este método se encuentra en la clase o interface que lo declara
	 * (non-Javadoc)
	 * @see mx.org.ife.siirfe.comun.dao.hibernate.GenericHibernateDAO#save(java.io .Serializable)
	 */
	@Override
	public void save(Serializable entity) {
		getCurrentSession().save(entity);
	}

	/*
	 * La documentación de este método se encuentra en la clase o interface que lo declara
	 * (non-Javadoc)
	 * @see mx.org.ife.siirfe.comun.dao.hibernate.GenericHibernateDAO#saveOrUpdate
	 * (java.io.Serializable)
	 */
	@Override
	public void saveOrUpdate(Serializable entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	/*
	 * La documentación de este método se encuentra en la clase o interface que lo declara
	 * (non-Javadoc)
	 * @see mx.org.ife.siirfe.comun.dao.hibernate.GenericHibernateDAO#merge(java. lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T merge(final T entity) {
		return (T) getCurrentSession().merge(entity);
	}

	/*
	 * La documentación de este método se encuentra en la clase o interface que lo declara
	 * (non-Javadoc)
	 * @see mx.org.ife.siirfe.comun.dao.hibernate.GenericHibernateDAO#update(java .lang.Object)
	 */
	@Override
	public <T> void update(T entity) {
		getCurrentSession().update(entity);
	}

	/*
	 * La documentación de este método se encuentra en la clase o interface que lo declara
	 * (non-Javadoc)
	 * @see mx.org.ife.siirfe.comun.dao.hibernate.GenericHibernateDAO#delete(java .lang.Object)
	 */
	@Override
	public <T> void delete(final T entity) {
		getCurrentSession().delete(entity);
	}

	/*
	 * La documentación de este método se encuentra en la clase o interface que lo declara
	 * (non-Javadoc)
	 * @see mx.org.ife.siirfe.comun.dao.hibernate.GenericHibernateDAO#update(java .lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public int update(String query, Object... params) {

		Query hqlQuery = getCurrentSession().createQuery(query);

		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				hqlQuery.setParameter(i, params[i]);
			}
		}
		return hqlQuery.executeUpdate();

	}

	/*
	 * La documentación de este método se encuentra en la clase o interface que lo declara
	 * (non-Javadoc)
	 * @see mx.org.ife.siirfe.comun.dao.hibernate.GenericHibernateDAO#refresh(java .io.Serializable)
	 */
	@Override
	public void refresh(Serializable entity) {
		getCurrentSession().refresh(entity);

	}

	/*
	 * La documentación de este método se encuentra en la clase o interface que lo declara
	 * (non-Javadoc)
	 * @see mx.org.ife.siirfe.comun.dao.hibernate.GenericHibernateDAO#queryByExample
	 * (java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> queryByExample(T entityExample) {

		Example example = Example.create(entityExample);
		return (List<T>) queryByExample(example, entityExample.getClass());

	}

	/*
	 * La documentación de este método se encuentra en la clase o interface que lo declara
	 * (non-Javadoc)
	 * @see mx.org.ife.siirfe.comun.dao.hibernate.GenericHibernateDAO#queryByExample
	 * (org.hibernate.criterion.Example)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> queryByExample(Example example, Class<T> clazz) {
		return getCurrentSession().createCriteria(clazz).add(example).list();

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.synergyj.bookmule.persistence.repository.hibernate.GenericHibernateDAO#createCriteria()
	 */
	@Override
	public <T> Criteria createCriteria(Class<T> clazz) {
		return getCurrentSession().createCriteria(clazz);
	}

	/**
	 * Obtiene la sesion actual, es decir, la mahejada por {@link CurrentSessionContext}
	 * @return la sesion actual
	 */
	public final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Medoto empleado especialmente para pruebas para forzar la persistencia en la BD y evitar
	 * falsos positivos al comparar datos con la sesión.
	 */
	public final void flush() {
		getCurrentSession().flush();
	}
}
