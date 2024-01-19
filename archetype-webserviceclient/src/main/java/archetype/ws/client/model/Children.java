package archetype.ws.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Children implements Serializable, Cloneable {

	private static final long	serialVersionUID	= 2994849211203597522L;

	private Audit				audit;
	private List<Father>		fathers				= new ArrayList<Father>(0);
	private Integer				id;
	private String				name;
	private Integer				version;

	public Audit getAudit() {
		return audit;
	}

	public void setAudit(Audit audit) {
		this.audit = audit;
	}

	public List<Father> getFathers() {
		return fathers;
	}

	public void setFathers(List<Father> fathers) {
		this.fathers = fathers;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
