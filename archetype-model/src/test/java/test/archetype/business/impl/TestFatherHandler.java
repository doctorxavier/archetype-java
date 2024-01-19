package test.archetype.business.impl;

import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.archetype.AbstractTest;
import archetype.business.IFatherHandler;
import archetype.model.Father;

public class TestFatherHandler extends AbstractTest {

	private static Logger	logger	= LoggerFactory.getLogger(TestFatherHandler.class);
	private Integer id = Integer.valueOf("99999");

	@Test
	public void testCRUD() {
		/*testCreate();
		testRead();
		testUpdate();
		testDelete();*/
	}

	public void testSave() {

	}

//	@Test
	public void testCreate() {
		try {
			IFatherHandler fatherHandler = (IFatherHandler) applicationContext.getBean("fatherHandler");
			Father father = new Father();
			father.setId(id);
			father.setName("father1");
			fatherHandler.create(father);

			logger.info("Create Ok");
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

//	@Test
	public void testRead() {
		try {
			IFatherHandler fatherHandler = (IFatherHandler) applicationContext.getBean("fatherHandler");
			Father father = fatherHandler.getById(id);
			
			if (father == null) {
				throw new Exception("The entity not exists.");
			} else {
				logger.info("Father name: " + father.getName());
			}

			logger.info("Read Ok");
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

//	@Test
	public void testUpdate() {
		try {
			IFatherHandler fatherHandler = (IFatherHandler) applicationContext.getBean("fatherHandler");
			Father father = fatherHandler.getById(id);
			father.setName("father1updated");
			
			// TODO check with eclipselink
			fatherHandler.update(father);

			logger.info("Update Ok");
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

//	@Test
	public void testDelete() {
		try {
			IFatherHandler fatherHandler = (IFatherHandler) applicationContext.getBean("fatherHandler");
			Father father = new Father();
			father.setId(id);
			fatherHandler.delete(father);

			logger.info("Delete Ok");
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

//	@Test
	public void getFathers() {
		try {
			IFatherHandler fatherHandler = (IFatherHandler) applicationContext.getBean("fatherHandler");
			List<Father> fathers = fatherHandler.getFathers(1, 3);
			for (Father father : fathers) {
				logger.info(father.getName());
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

}
