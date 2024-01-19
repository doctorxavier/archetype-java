package archetype.web.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;

import archetype.business.IFatherHandler;
import archetype.model.Father;

@ManagedBean  
@ViewScoped  
public class FatherManager {
	
	private static Logger	logger	= LoggerFactory.getLogger(FatherManager.class);

	private Collection<Object>	simpleSelection;
	private IFatherHandler			fatherHandler;
	private List<Father>		fathers	= new ArrayList<Father>(0);
	private Father				fatherSelected;

	public Father getFatherSelected() {
		return fatherSelected;
	}

	public void setFatherSelected(Father fatherSelected) {
		this.fatherSelected = fatherSelected;
	}

	public Collection<Object> getSimpleSelection() {
		return simpleSelection;
	}

	public void setSimpleSelection(Collection<Object> simpleSelection) {
		this.simpleSelection = simpleSelection;
	}

	public void setFatherHandler(IFatherHandler fatherHandler) {
		this.fatherHandler = fatherHandler;
	}

	public List<Father> getFathers() {
		/*try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}*/
		fathers = fatherHandler.getAll();
		selectFather();
		return fathers;
	}

	public void selectFather() {
		if (simpleSelection != null) {
			Iterator<Object> iterator = simpleSelection.iterator();
			if (iterator.hasNext()) {
				fatherSelected = fathers.get((Integer) iterator.next());
			}
		}
	}

	public void save() {
		try {
			fatherHandler.update(fatherSelected);
		} catch (ConstraintViolationException | BindException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

}
