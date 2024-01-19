package archetype.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Audit implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private Date				created				= new Date();
	private Date				modified			= new Date();

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreated() {
		return created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getModified() {
		return modified;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

}
