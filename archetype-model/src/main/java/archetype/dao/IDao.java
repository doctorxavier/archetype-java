package archetype.dao;

import java.util.List;

import archetype.dao.utils.DataModelFilter;


public interface IDao<T> {

	void create(T entity);

	List<T> getAll();

	T getEntityByPrimaryKey(T entity);

	T getEntityByRef(T entity);

	void update(T entity);

	void delete(T entity);

	T getById(Integer id);

	void evict(Object primaryKey);

	void detach(Object object);
	
	long getRowCount();

	List<T> getFilteredItems(DataModelFilter filters, Integer start, Integer rows) throws SecurityException, NoSuchFieldException;

	List<T> getFilteredItems(DataModelFilter filters) throws SecurityException, NoSuchFieldException;
	
}
