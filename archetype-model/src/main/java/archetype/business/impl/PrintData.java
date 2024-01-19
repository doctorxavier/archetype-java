package archetype.business.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Iterator;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

import archetype.business.IPrintData;
import archetype.dao.jpa.IJpaDao;
import archetype.model.Children;
import archetype.model.Father;


public class PrintData implements IPrintData {

	private static Logger	logger	= Logger.getLogger(PrintData.class);
	private IJpaDao		dao;

	public IJpaDao getDao() {
		return dao;
	}

	public void setDao(IJpaDao dao) {
		this.dao = dao;
	}

	public void deleteFather(Father father) {
		dao.remove(Father.class, father.getId());
	}

	public Father load(Father father) {
		return (Father) dao.load(Father.class, father.getId());
	}

	public void loadClone(Integer id) {
		try {
			Father father = new Father();
			father.setId(id);
			father = (Father) dao.load(Father.class, father.getId());
			// Father clone = (Father) father.clone();
			Father clone = new Father();
			BeanUtils.copyProperties(clone, father);
			clone.setId(0);
			clone.getChildren().setId(0);
			// fatherManager.insertFather(clone);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			logger.error(ExceptionUtils.getStackTrace(e));
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

	public Father loadSampleData(int childs) {
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
		dao.persist(father);
		return father;
	}

	public Father marshall(Father father, String fileName) {
		Father fatherCopy = null;
		try {
			fatherCopy = (Father) dao.load(Father.class, father.getId());
			IBindingFactory bfact = BindingDirectory.getFactory(Father.class);
			IMarshallingContext mctx = bfact.createMarshallingContext();
			mctx.setIndent(2);
			FileOutputStream out = new FileOutputStream(fileName);
			mctx.setOutput(out, null);
			fatherCopy.setName("");
			mctx.marshalDocument(fatherCopy);
		} catch (JiBXException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		} catch (FileNotFoundException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return fatherCopy;
	}

	public void printFather(Integer id) {
		Father father = new Father();
		father.setId(id);
		father = (Father) dao.load(Father.class, father.getId());
		printFather(father);
	}

	public void printFather(Father father) {
		logger.debug(father.getName());
		logger.debug("Firstborn: " + father.getChildren().getName());

		Iterator<Children> childrends = father.getChildrens().iterator();
		while (childrends.hasNext()) {
			Children children = childrends.next();
			logger.debug(children.getName());
		}
	}

	public Father updateFather(Father father) {
		Father fatherOrig = (Father) dao.load(Father.class, father.getId());
		fatherOrig.setName(father.getName());

		fatherOrig.getChildren().setName(father.getChildren().getName());
		fatherOrig.getFather().setName(father.getFather().getName());

		for (int i = 0; i < father.getChildrens().size(); i++) {
			fatherOrig.getChildrens().get(i).setName(father.getChildrens().get(i).getName());
		}
		fatherOrig.setChildrens(fatherOrig.getChildrens());

		dao.merge(fatherOrig);
		return fatherOrig;
	}

	public Father unmarshall(String fileName) {
		try {
			IBindingFactory bfact = BindingDirectory.getFactory(Father.class);
			IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
			Object obj = uctx.unmarshalDocument(new FileInputStream(fileName), null);
			return (Father) obj;

		} catch (JiBXException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		} catch (FileNotFoundException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

	// public Father updateFather(Father father) {
	// // Father fatherAux = new Father();
	// // fatherAux.setId(father.getId());
	// // fatherAux = fatherManager.selectFatherByPrimaryKey(fatherAux);
	// // fatherAux.setName(father.getName());
	// fatherManager.updateFather(father);
	// return father;
	// }
}
