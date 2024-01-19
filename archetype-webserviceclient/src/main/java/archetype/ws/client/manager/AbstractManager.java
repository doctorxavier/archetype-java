package archetype.ws.client.manager;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.JiBXException;
import org.jibx.ws.WsBindingException;
import org.jibx.ws.WsConfigurationException;
import org.jibx.ws.client.Client;
import org.jibx.ws.soap.client.SoapClient;

public abstract class AbstractManager {

	protected String	endPoint	= "archetype-service";
	private String		target		= "http://localhost:8080/webservice";

	protected Client getClient(Class<?> factoryClass) throws JiBXException, WsBindingException, WsConfigurationException {
		IBindingFactory factory = BindingDirectory.getFactory(factoryClass);
		Client client = new SoapClient(target + "/" + endPoint, factory);
		return client;
	}

}
