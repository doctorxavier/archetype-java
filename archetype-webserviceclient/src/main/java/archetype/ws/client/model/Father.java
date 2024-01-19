package archetype.ws.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Father implements Serializable, Cloneable {

	private static final long	serialVersionUID	= 6529886738211199955L;

	private Audit				audit;
	private Children			children;
	private List<Children>		childrens			= new ArrayList<Children>(0);
	private Father				father;
	private Set<Father>			fathers				= new HashSet<Father>(0);
	private Integer				id;
	private boolean				mother;
	private String				name;
	private Integer				version;

	public Audit getAudit() {
		return audit;
	}

	public void setAudit(Audit audit) {
		this.audit = audit;
	}

	public Children getChildren() {
		return children;
	}

	public void setChildren(Children children) {
		this.children = children;
	}

	public List<Children> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<Children> childrens) {
		this.childrens = childrens;
	}

	public Father getFather() {
		return father;
	}

	public void setFather(Father father) {
		this.father = father;
	}

	public Set<Father> getFathers() {
		return fathers;
	}

	public void setFathers(Set<Father> fathers) {
		this.fathers = fathers;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isMother() {
		return mother;
	}

	public void setMother(boolean mother) {
		this.mother = mother;
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
