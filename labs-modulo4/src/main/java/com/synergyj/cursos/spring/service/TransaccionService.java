package com.synergyj.cursos.spring.service;

/**
 * @author Jorge Rodríguez Campos (jorge.rodriguez@synergyj.com)
 */
public interface TransaccionService {
	/**
	 * @param fooName
	 * @return
	 */
	String getString(String fooName);

	/**
	 * @param fooName
	 * @param barName
	 * @return
	 */
	String getMoreStrings(String fooName, String barName) throws BusinessException;

	/**
	 * @param foo
	 */
	void insertFoo(String foo);

	/**
	 * @param foo
	 */
	void updateFoo(String foo);

}
