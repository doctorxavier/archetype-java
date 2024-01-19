package archetype.business;

import java.util.List;

import archetype.model.Children;

public interface IChildrenHandler extends IHandler<Children> {

	List<Children> getChildrens(int start, int rows) throws SecurityException, NoSuchFieldException;

}
