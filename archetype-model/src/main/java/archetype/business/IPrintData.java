package archetype.business;

import archetype.model.Father;

public interface IPrintData {

	void deleteFather(Father father);

	Father load(Father father);

	void loadClone(Integer id);

	Father loadSampleData(int childs);

	Father marshall(Father father, String fileName);

	void printFather(Integer id);

	void printFather(Father father);

	Father unmarshall(String fileName);

	Father updateFather(Father father);

}
