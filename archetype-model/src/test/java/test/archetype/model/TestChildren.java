package test.archetype.model;

import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.archetype.AbstractTest;
import archetype.dao.IChildrenDao;
import archetype.model.Children;
import archetype.model.Father;
import archetype.utils.jibx.JibxMarshaller;


public class TestChildren extends AbstractTest {

	private static Logger	logger	= LoggerFactory.getLogger(TestChildren.class);
	
	@Test
	public void testMarshall() {
		try {
			IChildrenDao childrenDao = (IChildrenDao) applicationContext.getBean("childrenDao");
			Children children = childrenDao.getById(Integer.valueOf("3"));

			List<Father> fathers = children.getFathers();
			for (Father father : fathers) {
				logger.info(father.getName());
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

	public void testUnMarshall() {
		JibxMarshaller<Children> marshaller = new JibxMarshaller<Children>();
	}
}
