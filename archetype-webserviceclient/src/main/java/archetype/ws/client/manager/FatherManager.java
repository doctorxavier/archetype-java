package archetype.ws.client.manager;

import java.io.IOException;

import org.jibx.runtime.JiBXException;
import org.jibx.ws.WsException;
import org.jibx.ws.client.Client;

import archetype.ws.client.model.request.GetFathers;
import archetype.ws.client.model.response.Fathers;

public class FatherManager extends AbstractManager {

	public Fathers getFathers(GetFathers getFathers) throws IOException, WsException, JiBXException {
		Client client = getClient(GetFathers.class);
		return (Fathers) client.call(getFathers);
	}
	
}
