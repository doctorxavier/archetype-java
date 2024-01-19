package archetype.dao.impl;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import archetype.dao.IChildrenDao;
import archetype.model.Children;
import archetype.utils.Marshaller;

public class ChildrenDao extends AbstractDao<Children> implements IChildrenDao {
	
	protected static Logger	logger	= LoggerFactory.getLogger(ChildrenDao.class);

	@SuppressWarnings("unchecked")
	public void loadAllChildrensInCache() {
		Cache cache = CacheManager.getInstance().getCache("archetype.model.Children");
		List<Children> childrens = dao.listEntities(Children.class);
		Element element;
		Marshaller<Children> marshaller = new Marshaller<Children>();
		
		for (Children children : childrens) {
			Children childrenCopy = marshaller.marshall(children);
			element = new Element(childrenCopy.getId(), childrenCopy);
			cache.put(element);
		}

	}
	
	public Children getCacheFather(Integer id) {
		Children children = (Children) dao.load(Children.class, id);
		
		Cache cache = CacheManager.getInstance().getCache("archetype.model.Children");
		Element element;
		Marshaller<Children> marshaller = new Marshaller<Children>();

		Children childrenCopy = marshaller.marshall(children);
		element = new Element(childrenCopy.getId(), childrenCopy);
		cache.put(element);
		
		return childrenCopy;
	}

}
