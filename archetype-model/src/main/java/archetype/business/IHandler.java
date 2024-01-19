package archetype.business;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.validation.BindException;

public interface IHandler<T> {

	 void create(T entity) throws ConstraintViolationException, BindException;

	 List<T> getAll();

	 T getByPrimaryKey(T entity);

	 T getByRef(T entity);

	 void update(T entity) throws ConstraintViolationException, BindException;

	 void delete(T entity) throws ConstraintViolationException, BindException;

	 T getById(Integer id);
	 
	 void evict(Object primaryKey);
	 
	 void detach(Object object);
	
}
