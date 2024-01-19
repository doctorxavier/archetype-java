package test.archetype.dao;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import test.archetype.AbstractTest;
import archetype.dao.IFatherDao;
import archetype.model.Children;
import archetype.model.Father;
import archetype.utils.Marshaller;
// import org.junit.Test;

public class TestCacheFather extends AbstractTest {

	private static Logger	logger	= Logger.getLogger(TestCacheFather.class);

	// private CacheManager manager;

	private Father setPersistentFather(int id) {
		IFatherDao fatherDao = (IFatherDao) applicationContext.getBean("fatherDao");
		Father father = new Father();
		father.setId(id);
		father = fatherDao.getEntityByPrimaryKey(father);
		return father;
	}

	public Father setLocalFather() {
		Father father = new Father();
		father.setId(1);
		father.setName("Zim, " + System.currentTimeMillis());
		Children firstBorn = new Children();
		firstBorn.setName("Cloe, " + System.currentTimeMillis());
		father.setChildren(firstBorn);

		for (int i = 1; i <= 5; i++) {
			Children children = new Children();
			children.setId(i);
			children.setName("Children " + i + ", " + System.currentTimeMillis());
			father.getChildrens().add(children);
		}
		father.setChildrens(father.getChildrens());
		return father;
	}

	private Father marshallFather(int id) {
		Marshaller<Father> marshaller = new Marshaller<Father>();
		Father father = setPersistentFather(id);
		return marshaller.marshall(father);
	}

	private void putFather(Father father) {
		Cache cache = CacheManager.getInstance().getCache("org.flightmine.archetype.model.Father");
		Element element = new Element(father.getId(), father);
		cache.put(element);
	}

	private void putChildren(Children children) {
		Cache cache = CacheManager.getInstance().getCache("org.flightmine.archetype.model.Children");
		Element element = new Element(children.getId(), children);
		cache.put(element);
	}

	private void putLocalFather(int id) {
		Father father = marshallFather(id);
		for (Children children : father.getChildrens()) {
			putChildren(children);
		}
		father.getChildrens().clear();
		father.setChildrens(father.getChildrens());
		putFather(father);
	}

	public void setLocalCache() {
		putLocalFather(1);
		// putLocalFather(3);
		putLocalFather(5);
	}

	public void setFileCache() {
		marshallInFileFather(1);
		marshallInFileFather(3);
		marshallInFileFather(5);
	}

	private void marshallInFileFather(int id) {
		Marshaller<Father> marshaller = new Marshaller<Father>();
		Father father = setPersistentFather(id);
		marshaller.marshall(father, "father" + id + ".xml");
	}

	public void setCache() throws InterruptedException {
		try {

			// URL url = ResourceUtils.getURL("classpath:ehcache.xml");

			// CacheManager manager = new CacheManager(url);
			// Cache cache =
			// manager.getCache("org.flightmine.archetype.model.Father");

			// CacheManager.create(url);
			putFather(marshallFather(Integer.valueOf("2")));
			putFather(marshallFather(Integer.valueOf("4")));
			putFather(marshallFather(Integer.valueOf("6")));

			// manager.shutdown();
			// CacheManager.getInstance().shutdown();
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

	private void printFather(Father father) {
		logger.info("Father.getId()->" + father.getId() + ", name()->" + father.getName());

		if (father.getChildren() != null) {
			logger.info("FirstBorn.getId()->" + father.getChildren().getId() + ", name()->" + father.getChildren().getName());
		}

		for (Children children : father.getChildrens()) {
			logger.info("Children.getId()->" + children.getId() + ", name()->" + children.getName());
		}
	}

	public void getCache() throws InterruptedException {
		try {
			// URL url = ResourceUtils.getURL("classpath:ehcache.xml");

			// CacheManager manager = new CacheManager(url);
			// Cache cache =
			// manager.getCache("org.flightmine.archetype.model.Father");

			// CacheManager.create(url);
			Cache cache = CacheManager.getInstance().getCache("org.flightmine.archetype.model.Father");

			Element element = cache.get(Integer.valueOf("2"));
			Father father = (Father) element.getObjectValue();
			printFather(father);

			element = cache.get(Integer.valueOf("4"));
			father = (Father) element.getObjectValue();
			printFather(father);

			element = cache.get(Integer.valueOf("6"));
			father = (Father) element.getObjectValue();
			printFather(father);

			// manager.shutdown();
			// CacheManager.getInstance().shutdown();
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

	private void updateCache() throws InterruptedException {
		try {
			Cache cache = CacheManager.getInstance().getCache("org.flightmine.archetype.model.Father");
			Element element = cache.get(Integer.valueOf("2"));
			Father father = (Father) element.getObjectValue();
			father.setName(father.getName() + "updated!!!");
			father.getChildrens().get(0).setName(father.getChildrens().get(0).getName() + "updated!!!");
			logger.info("Father.getId()->" + father.getId() + ", name()->" + father.getName());
			
			putFather(father);

			element = cache.get(Integer.valueOf("2"));
			father = (Father) element.getObjectValue();
			logger.info("Father.getId()->" + father.getId() + ", name()->" + father.getName());

			// manager.shutdown();
			// CacheManager.getInstance().shutdown();
		} catch (Exception e) {
			logger.error("Error en updateCache.");
		}
	}

//	@Test
	public void testCache() {
		try {
			// URL url = ResourceUtils.getURL("classpath:ehcache.xml");
			URL url = ClassLoader.getSystemResource("ehcache.xml");
			CacheManager.create(url);
			// manager = new CacheManager(url);
			// setCache();
			updateCache();
//			 getCache();
			// manager.shutdown();
			CacheManager.getInstance().shutdown();
			// } catch (InterruptedException e) {
			// logger.error(ExceptionUtils.getStackTrace(e));
			// // } catch (FileNotFoundException e) {
			// // logger.error(ExceptionUtils.getStackTrace(e));
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

	
	/*@Test
	public void setAllFathers() {
		try {
			long start = 0L;
			long elapsed = 0L;

			URL url = ClassLoader.getSystemResource("ehcache.xml");
			CacheManager.create(url);
			IFatherDao fatherDao = (IFatherDao) applicationContext.getBean("fatherDao");
			Marshaller<Father> marshaller = new Marshaller<Father>();

			start = System.currentTimeMillis();

			final int iterations = 15;
			final int pageSize = 20;
			int numIterations = iterations;

			for (int i = 0; i < numIterations; i++) {
				List<Father> fathers = fatherDao.pagedFathers(i, pageSize);
				for (Father father : fathers) {
					Father fatherCopy = marshaller.marshall(father);
					putFather(fatherCopy);
					logger.info("Loop: " + numIterations);
					numIterations++;
				}
			}

			elapsed = System.currentTimeMillis() - start;
			printTimeLogger("Time elapsed: ", elapsed);

			CacheManager.getInstance().shutdown();
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}*/
	

//	@Test
	public void testCacheSize() {
		try {
			long start = 0L;
			long elapsed = 0L;

			final int numFathers = 300;

			start = System.currentTimeMillis();

			URL url = ClassLoader.getSystemResource("ehcache.xml");
			CacheManager.create(url);
			List<Father> fathers = new ArrayList<Father>(numFathers);
			Cache cache = CacheManager.getInstance().getCache("org.flightmine.archetype.model.Father");
			List<?> keys = cache.getKeys();

			int i = 0;
			for (Iterator<?> keysIt = keys.iterator(); keysIt.hasNext();) {
				Integer id = (Integer) keysIt.next();
				Element element = cache.get(id);
				Father father = (Father) element.getObjectValue();
				fathers.add(father);
				i++;
			}

			elapsed = System.currentTimeMillis() - start;
			printTimeLogger(i + " Objects recoverd from cache, time elapsed: ", elapsed);

			CacheManager.getInstance().shutdown();
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

	public void printTimeLogger(String message, long elapsed) {
		long elapsedTemp = 0L;
		final int milliseconds = 1000;
		final int seconds = 60;
		final int minuts = 60;
		final int hours = 60;

		int ms = (int) (elapsed % milliseconds);
		elapsedTemp /= milliseconds;
		int sec = (int) (elapsedTemp % seconds);
		elapsedTemp /= seconds;
		int min = (int) (elapsedTemp % minuts);
		elapsedTemp /= minuts;
		int hrs = (int) (elapsedTemp % hours);

		String time = String.format(message + "%02d:%02d:%02d:%04d", hrs, min, sec, ms);
		logger.info(time);
	}

	
	/*@Test
	public void testCache() throws InterruptedException {
		try {
			Father father = new Father();
			father.setId(1);
			father.setName("Zim");
			URL url = ResourceUtils.getURL("classpath:ehcache.xml");
			CacheManager manager = new CacheManager(url);
			Cache cache = manager.getCache("org.flightmine.archetype.model.Father");

			// al cache se suben objetos Element. Un element consta de una clave
			// y un
			valor.
			// La clave sera el medio para recuperar el objeto mas tarde. En el
			// ejemplo, el "id" de la Persona.
			Element element = new Element(father.getId(), father);
			cache.put(element);

			Father fatherCache = (Father) cache.get(father.getId()).getObjectValue();

			assertEquals(father, fatherCache);

			// esperamos unos instantes para que expire el cache...
			Thread.sleep(4100);

			assertNull(cache.get(father.getId()));
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}*/

}
