package test.archetype.dao;

import java.net.URL;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.util.ResourceUtils;

import test.archetype.AbstractTest;
import archetype.business.IPrintData;
import archetype.model.Father;

public class TestTransactions extends AbstractTest {

	private static Logger	logger	= Logger.getLogger(TestTransactions.class);

	public void loadMarshallFather(IPrintData printData) {
		try {
			Father father = new Father();
			father.setId(Integer.valueOf("1"));
			father = printData.load(father);
			printData.marshall(father, "father.xml");
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

	public Father loadUnmarshallFather(IPrintData printData) {
		Father father = null;
		try {
			father = printData.unmarshall("father.xml");
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return father;
	}

	// @Test
	public void doMarshall() {
		IPrintData printData = (IPrintData) applicationContext.getBean("printData");
		Father father = new Father();
		father.setId(Integer.valueOf("1"));
		printData.marshall(father, "father.xml");
	}

	// @Test
	public void doUnmarshall() {
		IPrintData printData = (IPrintData) applicationContext.getBean("printData");
		Father father = printData.unmarshall("father.xml");
		printData.marshall(father, "father2.xml");
	}

	// @Test
	public void testCache() throws InterruptedException {
		try {
			Father father = new Father();
			father.setId(Integer.valueOf("1"));
			father.setName("Zim");
			URL url = ResourceUtils.getURL("classpath:ehcache.xml");
			CacheManager manager = new CacheManager(url);
			Cache cache = manager.getCache("org.flightmine.archetype.model.Father");

			// al cache se suben objetos Element. Un element consta de una clave
			// y
			// un valor.
			// La clave sera el medio para recuperar el objeto mas tarde. En el
			// ejemplo, el "id" de la Persona.
			Element element = new Element(father.getId(), father);
			cache.put(element);

			@SuppressWarnings("unused")
			Father fatherCache = (Father) cache.get(father.getId()).getObjectValue();

			// assertEquals(father, fatherCache);

			// esperamos unos instantes para que expire el cache...
			Thread.sleep(Integer.valueOf("4100"));

			// assertNull(cache.get(father.getId()));
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

}
