package archetype.ws.client.model.request;

import java.io.Serializable;

public class GetFathers implements Serializable {

	private static final long	serialVersionUID	= -4111754827106531704L;
	private Integer				start;
	private Integer				rows;

	public Integer getRows() {
		return rows;
	}

	public Integer getStart() {
		return start;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

}
