package archetype.business;

import java.util.List;

import archetype.model.Father;

public interface IFatherHandler extends IHandler<Father> {
	
	List<Father> getFathers(int start, int rows) throws SecurityException, NoSuchFieldException;

}
