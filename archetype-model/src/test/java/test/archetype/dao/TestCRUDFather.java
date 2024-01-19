package test.archetype.dao;

import java.util.Calendar;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;

import test.archetype.AbstractTest;
import archetype.business.IChildrenHandler;
import archetype.business.IFatherHandler;
import archetype.model.Children;
import archetype.model.Father;
import archetype.utils.Utilities;
import archetype.utils.jibx.JibxMarshaller;


public class TestCRUDFather extends AbstractTest {
	
	private static Logger	logger	= LoggerFactory.getLogger(TestCRUDFather.class);
	
	@Test
	public void testCRUD() {
		long start = 0L;
		long elapsed = 0L;
		
		start = System.currentTimeMillis();
		
		testInsert();
		testLoad();
		testLoadMarshall();
		testUpdate();
		testDelete();
		
		elapsed = System.currentTimeMillis() - start;
		logger.info("Total Time elapsed: " + Utilities.parseMilliseconds(elapsed));
	}
	
	//@Test
	public void testInsert() {

		long start = 0L;
		long startTotal = 0L;
		long elapsed = 0L;

		final int iterations = 10;
		final int insertions = 50;
		final int childs = 500;
		Integer rows = 0;
		
		final long sleep = 0L;
		
		try {
			startTotal = System.currentTimeMillis();

			for (int iteration = 1; iteration <= iterations; iteration++) {
				start = System.currentTimeMillis();
				Thread.sleep(sleep);
				for (int i = 1; i <= insertions; i++) {
					this.loadSampleData(childs);
				}
				elapsed = System.currentTimeMillis() - start;
				logger.info("------------------------------------------");
				logger.info("Iteration: " + iteration + " - " + iteration * insertions * childs + " Insertions , time elapsed: " + Utilities.parseMilliseconds(elapsed));

			}
			elapsed = System.currentTimeMillis() - startTotal;
			rows = iterations * insertions * childs;
			logger.info("Total " + rows.toString() + " insertions, time elapsed: " + Utilities.parseMilliseconds(elapsed));

		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
	
	//@Test
	public void testLoad() {

		long start = 0L;
		long elapsed = 0L;
		int fathers = 0;
		int childrens = 0;

		try {
			IFatherHandler fatherHandler = (IFatherHandler) applicationContext.getBean("fatherHandler");
			start = System.currentTimeMillis();

			for (Father father : fatherHandler.getAll()) {
				fathers++;
				String name = father.getName();
				for (Children children : father.getChildrens()) { 
					childrens++;
					name = children.getName();
				}
			}
			
			elapsed = System.currentTimeMillis() - start;
			logger.info("Time elapsed: " + Utilities.parseMilliseconds(elapsed) + ", " + fathers + " fathers, " + childrens + " childrens");

		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}

	}
	
	//@Test
	public void testLoadMarshall() {
		
		long start = 0L;
		long elapsed = 0L;
		
		try {
			IFatherHandler fatherHandler = (IFatherHandler) applicationContext.getBean("fatherHandler");

			JibxMarshaller<Father> marshaller = new JibxMarshaller<Father>();
			
			start = System.currentTimeMillis();
			
			for (Father father : fatherHandler.getAll()) {
				marshaller.toString(father);
			}
			
			elapsed = System.currentTimeMillis() - start;
			logger.info("Time elapsed: " + Utilities.parseMilliseconds(elapsed));
			
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		
	}
	
	//@Test
	public void testUpdate() {
		long start = 0L;
		long elapsed = 0L;
		int i = 1;
		
		try {
			Calendar now = Calendar.getInstance();
			
			IFatherHandler fatherHandler = (IFatherHandler) applicationContext.getBean("fatherHandler");
			IChildrenHandler childrenHandler = (IChildrenHandler) applicationContext.getBean("childrenHandler");
			
			start = System.currentTimeMillis();
			
			for (Father father : fatherHandler.getAll()) {
				Father grandFather = father.getFather();
				if (grandFather != null) {
					father.setName("Father:  " + now.getTimeInMillis() + i++);
					fatherHandler.update(father);

					
					grandFather.setName("GrandFather: " + now.getTimeInMillis() + i++);
					fatherHandler.update(grandFather);

					for (Children children : father.getChildrens()) {
						children.setName("Children:  " + now.getTimeInMillis() + i++);
						childrenHandler.update(children);
					}
				}
			}
			
			elapsed = System.currentTimeMillis() - start;
			logger.info("Time elapsed: " + Utilities.parseMilliseconds(elapsed));
			
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}	
	}
	
	//@Test
	public void testDelete() {
		long start = 0L;
		long elapsed = 0L;
		
		try {
			IFatherHandler fatherHandler = (IFatherHandler) applicationContext.getBean("fatherHandler");
			
			start = System.currentTimeMillis();
			
			for (Father father : fatherHandler.getAll()) {
				Father grandFather = father.getFather();
				if (grandFather != null) {
					fatherHandler.delete(grandFather);
					fatherHandler.delete(father);
				}
			}
			
			elapsed = System.currentTimeMillis() - start;
			logger.info("Time elapsed: " + Utilities.parseMilliseconds(elapsed));
			
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
	
	private Father loadSampleData(int childs) throws BindException {
		IFatherHandler fatherHandler = (IFatherHandler) applicationContext.getBean("fatherHandler");
		Calendar now = Calendar.getInstance();

		Father father = new Father();
		father.setName("Father:  " + now.getTimeInMillis());

		Children firstBorn = new Children();
		firstBorn.setName("FirstBorn: " + now.getTimeInMillis());
		father.setChildren(firstBorn);

		Father grandFather = new Father();
		grandFather.setName("GrandFather: " + now.getTimeInMillis());
		father.setFather(grandFather);

		for (int i = 1; i <= childs; i++) {
			Children children = new Children();
			children.setName("Children:  "
					+ now.getTimeInMillis()
					+ i);
			father.getChildrens().add(children);
		}

		father.setChildrens(father.getChildrens());
		fatherHandler.create(father);
		return father;
	}

}
