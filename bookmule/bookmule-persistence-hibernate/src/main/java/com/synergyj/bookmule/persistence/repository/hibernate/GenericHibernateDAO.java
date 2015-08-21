package com.synergyj.bookmule.persistence.repository.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

/**
 * Interface que puese ser empleada por las interfaces de los daos para tener disponible de forma
 * directa los métodos básicos para realizar persistencia con hibernate.
 * @author Jorge A. Rodríguez Campos
 * @version 1.0
 */

public interface GenericHibernateDAO {

	/**
	 * Realiza obtiene todos los atributos de una entidad o null si esta no existe.
	 * @author Jorge A. Rodríguez Campos
	 * @param id
	 * @param clazz el tipo de entidad a regresar
	 * @return La entidad solicitada con base a su id, o null si no existe.
	 */

	<T> T get(Class<T> clazz, Serializable id);

	/**
	 * Ejecuta un query de consulta esperando una unica entidad. A diferencia del método
	 * <code>queryForEntity</code> regresa null si la entidad no existe. Si se obtienen mas de un
	 * resultado, se lanza {@link IncorrectResultSizeDataAccessException}
	 * @param clazz
	 * @param query
	 * @param params
	 * @return la entidad solicitada, null si no existe, o excepcion s se obtiene mas de un
	 *         registro.
	 */
	<T> T get(Class<T> clazz, String query, Object... params);

	/**
	 * Realiza una carga empleando lazy loading. El método no garantiza que la instancia exista, se
	 * asume que existe y la obtención de sus atributos se realiza empleando lazy loading.
	 * @param clazz el tipo de entidad a regresar
	 * @param id
	 * @return Un proxy de la entidad solicitada.
	 */

	<T> T load(Class<T> clazz, Serializable id);

	/**
	 * Ejecuta una consulta empleando HQL con un conjunto de parametros.
	 * @param query
	 * @param params
	 * @return Una lista de entidades asociadas al DAO, o lista vacia si no existen.
	 */

	<T> List<T> find(String query, Object... params);

	/**
	 * Ejecuta HQL en la que se espera un solo registro, lanza
	 * {@link IncorrectResultSizeDataAccessException} en caso de no obtener exactamente una entidad
	 * @param query
	 * @param params
	 * @return la entidad solicitada
	 */

	<T> T queryForEntity(String query, Object... params);

	/**
	 * Realiza búsquedas de entidades empleando la funcionalidad de QBE (query by example) de
	 * Hibernate
	 * @param example
	 * @return Una lista de objetos que coinciden con las propiedades que se pasan en la entidad de
	 *         ejemplo.
	 */
	<T> List<T> queryByExample(T example);

	/**
	 * Misma funcionalidad que el metodo sobrecargado con la diferencia en que este acepta un objeto
	 * {@link Example} previamente personalizado y configurado. Hibernate
	 * @param example
	 * @param entityClass el tipo de entidad que se espera.
	 * @return Una lista de objetos que coinciden con las propiedades que se pasan en la entidad de
	 *         ejemplo.
	 */
	<T> List<T> queryByExample(Example example, Class<T> entityClass);

	/**
	 * Método que obtiene todos los regitros de la entidad <T>
	 * @param clazz el tipo de entidad a regresar
	 * @return lista de los registros obtenidos
	 */

	<T> List<T> findAll(Class<T> clazz);

	/**
	 * Persiste la información de la entidad proporcionada
	 * @param entity
	 */
	void save(Serializable entity);

	/**
	 * Aplica una operacion de save o update sobre la entidad que se pasa como parámetro.
	 * @param entity
	 */
	void saveOrUpdate(Serializable entity);

	/**
	 * Actualiza la información de la entidad proporcionada
	 * @param entity Esta entidad no necesariamente esta asociada a la sesion.
	 * @return La instancia actualizada y asociada a la sesion.
	 */

	<T> T merge(final T entity);

	/**
	 * Realiza una operación de update. El objeto que se recibe no debe esta asociado a la sesion.
	 * Si existe un objeto en sesion con el mismo id, se lanza excepcion.
	 * @author Jorge A. Rodríguez Campos (jorge.rodriguez@ife.org.mx, jorgerdc79@hotmail.com)
	 * @param entity
	 */
	<T> void update(T entity);

	/**
	 * Realiza una operación de update empleando hql
	 * @param query
	 * @param params
	 * @return el número de registros afectados.
	 */
	int update(String query, Object... params);

	/**
	 * Elimina la entidad proporcionada
	 * @param entity
	 */
	<T> void delete(final T entity);

	/**
	 * Invoca una operación de refresh de la entidad que se pasa como parámetro.
	 * @param entity
	 */
	void refresh(Serializable entity);

	/**
	 * Construye un objeto {@link Criteria} para ser empleado por los DAOs y emplear esta
	 * funcionalidad.
	 * @return
	 */
	<T> Criteria createCriteria(Class<T> clazz);

}
