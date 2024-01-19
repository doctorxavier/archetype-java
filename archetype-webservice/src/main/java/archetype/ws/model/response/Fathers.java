package archetype.ws.model.response;

import java.io.Serializable;
import java.util.List;

import archetype.model.Father;

public class Fathers implements Serializable {

	private static final long	serialVersionUID	= -8916084409624687267L;
	private List<Father>		fathers;

	public List<Father> getFathers() {
		return fathers;
	}

	public void setFathers(List<Father> fathers) {
		this.fathers = fathers;
	}

}
