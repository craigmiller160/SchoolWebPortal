package io.craigmiller160.school.persist;

import java.util.List;

public interface GenericDao<T> {

	void insertEntity(T entity);
	
	void updateEntity(T entity);
	
	void deleteEntity(T entity);
	
	T getEntityById(int entityId);
	
	long getEntityCount();
	
	List<T> getAllEntities();
	
}
