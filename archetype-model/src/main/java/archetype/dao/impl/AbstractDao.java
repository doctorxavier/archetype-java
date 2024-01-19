package archetype.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.beanutils.BeanMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import archetype.dao.IDao;
import archetype.dao.jpa.IJpaDao;
import archetype.dao.utils.DataModelFilter;


public abstract class AbstractDao<T> implements IDao<T> {
	
	protected static Logger	logger	= LoggerFactory.getLogger(AbstractDao.class);

	protected IJpaDao		dao;

	protected Class<?>		entityClass;

	public AbstractDao() {
		super();
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		entityClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];
	}

	@Override
	public void create(T entity) {
		dao.persist(entity);
	}

	@Override
	public void delete(T entity) {
		dao.remove(entity, this.getId(entity));
	}

	public void detach(Object primaryKey) {
		dao.detach(entityClass, primaryKey);
	}

	public void evict(Object primaryKey) {
		dao.evict(entityClass, this.getId(primaryKey));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		return dao.listEntities(entityClass);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(Integer id) {
		return (T) dao.loadById(entityClass, id);
	}

	public IJpaDao getDao() {
		return dao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getEntityByPrimaryKey(T entity) {
		return (T) dao.load(entityClass, this.getId(entity));
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getEntityByRef(T entity) {
		return (T) dao.loadByRef(entityClass, this.getId(entity));
	}

	@SuppressWarnings("unchecked")
	public List<T> getFilteredItems(DataModelFilter filters, Integer start, Integer rows) throws SecurityException, NoSuchFieldException {
		return dao.getFilteredItems(entityClass, filters, start, rows);
	}

	@SuppressWarnings("unchecked")
	public List<T> getFilteredItems(DataModelFilter filters) throws SecurityException, NoSuchFieldException {
		return dao.getFilteredItems(entityClass, filters);
	}

	protected Integer getId(Object entity) {
		return (Integer) new BeanMap(entity).get("id");
	}
	
	public long getRowCount() {
		return dao.getRowCount(entityClass);
	}

	public void setDao(IJpaDao dao) {
		this.dao = dao;
	}

	@Override
	public void update(T entity) {
		dao.merge(entity);
	}
	
}
