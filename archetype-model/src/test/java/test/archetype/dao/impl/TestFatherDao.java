package test.archetype.dao.impl;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.archetype.AbstractTest;
import archetype.dao.IFatherDao;
import archetype.model.Children;
import archetype.model.Father;

public class TestFatherDao extends AbstractTest {

	private static Logger	logger	= LoggerFactory.getLogger(TestFatherDao.class);

	@Test
	public void testGetById() {
		try {
			IFatherDao fatherDao = (IFatherDao) applicationContext.getBean("fatherDao");
			Father father = fatherDao.getById(Integer.valueOf("1"));
			String name = father.getName();
			for (Children children : father.getChildrens()) {
				name = children.getName();
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

}
