package archetype.ws.client.manager;

import java.io.IOException;

import org.jibx.runtime.JiBXException;
import org.jibx.ws.WsException;
import org.jibx.ws.client.Client;

import archetype.ws.client.model.request.GetChildrens;
import archetype.ws.client.model.response.Childrens;

public class ChildrenManager extends AbstractManager {

	public Childrens getChildrens(GetChildrens getChildrens) throws IOException, WsException, JiBXException {
		Client client = getClient(GetChildrens.class);
		return (Childrens) client.call(getChildrens);
	}

}
