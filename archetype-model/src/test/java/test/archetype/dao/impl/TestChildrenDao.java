package test.archetype.dao.impl;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.archetype.AbstractTest;
import archetype.dao.IChildrenDao;

public class TestChildrenDao extends AbstractTest {

	private static Logger	logger	= LoggerFactory.getLogger(TestChildrenDao.class);

	@Test
	public void testGetById() {
		try {
			IChildrenDao childrenDao = (IChildrenDao) applicationContext.getBean("childrenDao");
			childrenDao.getById(Integer.valueOf("1"));
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

}
