package archetype.dao.jpa;

import java.util.List;

import archetype.dao.utils.DataModelFilter;

public interface IJpaDao {

	void detach(Class<?> entityClass, Object primaryKey);

	void evict(Class<?> entityClass, Object primaryKey);

	@SuppressWarnings("rawtypes")
	List getFilteredItems(Class<?> entityClass, DataModelFilter filters) throws SecurityException, NoSuchFieldException;

	@SuppressWarnings("rawtypes")
	List getFilteredItems(Class<?> entityClass, DataModelFilter filters, Integer start, Integer rows) throws SecurityException, NoSuchFieldException;
	
	long getRowCount(Class<?> entityClass);

	int getRowCount(Class<?> entityClass, DataModelFilter filters) throws SecurityException, NoSuchFieldException;
	
	@SuppressWarnings("rawtypes")
	List listEntities(Class<?> entityClass);
	
	Object load(Class<?> entityClass, Object primaryKey);
	
	Object loadById(Class<?> entityClass, Integer id);

	Object loadByRef(Class<?> entityClass, Object primaryKey);

	Object merge(Object obj);
	
	@SuppressWarnings("rawtypes")
	List pagedEntities(Class<?> entityClass, int page, int pageSize);
	
	void persist(Object obj);
	
	void remove(Object obj, Integer id);

}
