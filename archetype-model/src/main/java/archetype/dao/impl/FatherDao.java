package archetype.dao.impl;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import archetype.dao.IFatherDao;
import archetype.model.Father;
import archetype.utils.Marshaller;

public class FatherDao extends AbstractDao<Father> implements IFatherDao {

	// private static Logger	logger	= LoggerFactory.getLogger(FatherDao.class);

	@SuppressWarnings("unchecked")
	public void loadAllFathersInCache() {
		Cache cache = CacheManager.getInstance().getCache("archetype.model.Father");
		List<Father> fathers = dao.listEntities(Father.class);
		Element element;
		Marshaller<Father> marshaller = new Marshaller<Father>();

		for (Father father : fathers) {
			Father fatherCopy = marshaller.marshall(father);
			element = new Element(fatherCopy.getId(), fatherCopy);
			cache.put(element);
		}

	}

	public Father getCacheFather(Integer id) {
		Father father = (Father) dao.load(Father.class, id);

		Cache cache = CacheManager.getInstance().getCache("archetype.model.Father");
		Element element;
		Marshaller<Father> marshaller = new Marshaller<Father>();

		Father fatherCopy = marshaller.marshall(father);
		element = new Element(fatherCopy.getId(), fatherCopy);
		cache.put(element);

		return fatherCopy;
	}

}
