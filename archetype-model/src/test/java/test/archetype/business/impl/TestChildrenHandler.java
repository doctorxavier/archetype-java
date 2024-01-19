package test.archetype.business.impl;

import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.archetype.AbstractTest;
import archetype.business.IChildrenHandler;
import archetype.model.Children;

public class TestChildrenHandler extends AbstractTest {

	private static Logger	logger	= LoggerFactory.getLogger(TestChildrenHandler.class);
	
	// @Test
	public void testCRUD() {
		testCreate();
		testRead();
		testUpdate();
		testDelete();
	}

	public void testSave() {

	}

//	@Test
	public void testCreate() {
		try {
			IChildrenHandler childrenHandler = (IChildrenHandler) applicationContext.getBean("childrenHandler");
			Children children = new Children();
//			father.setId(Integer.valueOf("99999"));
			children.setName("children1");
			childrenHandler.create(children);

			logger.info("Create Ok");
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

//	@Test
	public void testRead() {
		try {
			IChildrenHandler childrenHandler = (IChildrenHandler) applicationContext.getBean("childrenHandler");
			Children children = childrenHandler.getById(Integer.valueOf("99999"));
			
			if (children == null) {
				throw new Exception("The entity not exists.");
			} else {
				logger.info("Children name: " + children.getName());
			}

			logger.info("Read Ok");
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

//	@Test
	public void testUpdate() {
		try {
			IChildrenHandler childrenHandler = (IChildrenHandler) applicationContext.getBean("childrenHandler");
			Children children = childrenHandler.getById(Integer.valueOf("99999"));
			children.setName("children1updated");
			
			// TODO check with eclipselink
			childrenHandler.update(children);

			logger.info("Update Ok");
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

//	@Test
	public void testDelete() {
		try {
			IChildrenHandler childrenHandler = (IChildrenHandler) applicationContext.getBean("childrenHandler");
			Children children = new Children();
			children.setId(Integer.valueOf("121"));
			childrenHandler.delete(children);

			logger.info("Delete Ok");
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

	@Test
	public void getChildrens() {
		try {
			IChildrenHandler childrenHandler = (IChildrenHandler) applicationContext.getBean("childrenHandler");
			List<Children> childrens = childrenHandler.getChildrens(1, 3);
			for (Children children : childrens) {
				logger.info(children.getName());
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

}
