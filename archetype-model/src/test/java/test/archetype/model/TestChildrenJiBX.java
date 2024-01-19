package test.archetype.model;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.archetype.AbstractTest;
import archetype.dao.IChildrenDao;
import archetype.model.Children;
import archetype.utils.jibx.JibxMarshaller;

public class TestChildrenJiBX extends AbstractTest {

	private static Logger	logger	= LoggerFactory.getLogger(TestChildrenJiBX.class);

	@Test
	public void testMarshall() {
		try {
			IChildrenDao childrenDao = (IChildrenDao) applicationContext.getBean("childrenDao");
			Children children = childrenDao.getById(Integer.valueOf("2"));

			JibxMarshaller<Children> marshaller = new JibxMarshaller<Children>();
			Object obj = marshaller.toString(children);

			if (obj instanceof String) {
				String value = (String) obj;
				logger.info(value);
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

	public void testUnMarshall() {
		JibxMarshaller<Children> marshaller = new JibxMarshaller<Children>();
	}

}
