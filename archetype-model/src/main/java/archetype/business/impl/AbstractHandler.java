package archetype.business.impl;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Validator;

import archetype.business.IHandler;
import archetype.business.utils.ExceptionHandler;
import archetype.dao.IDao;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractHandler.
 * 
 * @param <T>
 *            the generic type
 */
public abstract class AbstractHandler<T> implements IHandler<T> {

	protected static Logger	logger	= LoggerFactory.getLogger(AbstractHandler.class);

	protected IDao<T>		dao;

	protected Validator		validator;

	@Override
	public void create(T entity) throws ConstraintViolationException, BindException {
		BindException errors = new BindException(entity, entity.getClass().getSimpleName());

		if (validator != null) {
			validator.validate(entity, errors);
		}

		if (!errors.hasErrors()) {
			try {
				dao.create(entity);
			} catch (Exception e) {
				ExceptionHandler.handleConstraintViolationException(e, logger);
			}
		} else {
			throw errors;
		}
	}

	@Override
	public List<T> getAll() {
		return dao.getAll();
	}

	@Override
	public T getByPrimaryKey(T entity) {
		return dao.getEntityByPrimaryKey(entity);
	}

	@Override
	public T getByRef(T entity) {
		return dao.getEntityByRef(entity);
	}

	@Override
	public void update(T entity) throws ConstraintViolationException, BindException {
		BindException errors = new BindException(entity, entity.getClass().getSimpleName());

		if (validator != null) {
			validator.validate(entity, errors);
		}

		if (!errors.hasErrors()) {
			try {
				dao.update(entity);
			} catch (Exception e) {
				ExceptionHandler.handleConstraintViolationException(e, logger);
			}
		} else {
			throw errors;
		}
	}

	@Override
	public void delete(T entity) throws ConstraintViolationException, BindException {
		dao.delete(entity);
	}

	@Override
	public T getById(Integer id) {
		return dao.getById(id);
	}

	public IDao<T> getDao() {
		return dao;
	}

	public void setDao(IDao<T> dao) {
		this.dao = dao;
	}

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	@Override
	public void evict(Object primaryKey) {
		dao.evict(primaryKey);
	}

	@Override
	public void detach(Object object) {
		dao.detach(object);
	}
	
}
