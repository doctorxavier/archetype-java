package archetype.ws.model.response;

import java.io.Serializable;
import java.util.List;

import archetype.model.Children;

public class Childrens implements Serializable {

	private static final long	serialVersionUID	= -3763045713276567744L;
	private List<Children>		childrens;

	public List<Children> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<Children> childrens) {
		this.childrens = childrens;
	}

}
