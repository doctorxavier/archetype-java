package test.archetype.model;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.archetype.AbstractTest;
import archetype.dao.IFatherDao;
import archetype.model.Father;
import archetype.utils.jibx.JibxMarshaller;

public class TestFatherJiBX extends AbstractTest {

	private static Logger	logger	= LoggerFactory.getLogger(TestFatherJiBX.class);

	@Test
	public void testMarshall() {
		try {
			IFatherDao fatherDao = (IFatherDao) applicationContext.getBean("fatherDao");
			Father father = fatherDao.getById(Integer.valueOf("3"));

			JibxMarshaller<Father> marshaller = new JibxMarshaller<Father>();
			Object obj = marshaller.toString(father);

			if (obj instanceof String) {
				String value = (String) obj;
				logger.info(value);
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

	public void testUnMarshall() {
		JibxMarshaller<Father> marshaller = new JibxMarshaller<Father>();
	}

}
