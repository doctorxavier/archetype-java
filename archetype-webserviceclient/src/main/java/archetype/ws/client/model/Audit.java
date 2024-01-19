package archetype.ws.client.model;

import java.io.Serializable;
import java.util.Date;

public class Audit implements Serializable, Cloneable {

	private static final long	serialVersionUID	= -8914689553158200799L;

	private Date				created				= new Date();
	private Date				modified			= new Date();

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

}
