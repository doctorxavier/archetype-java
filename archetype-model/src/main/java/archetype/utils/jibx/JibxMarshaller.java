package archetype.utils.jibx;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

public class JibxMarshaller<E> {

	public static final int	INDENT	= 2;
	private static Logger	logger	= Logger.getLogger(JibxMarshaller.class);

	public Object marshall(E marshall) {
		Object obj = null;
		try {
			ByteArrayOutputStream bufferOut = new ByteArrayOutputStream();
			IBindingFactory bfact = BindingDirectory.getFactory(marshall.getClass());
			IMarshallingContext mctx = bfact.createMarshallingContext();
			mctx.setIndent(INDENT);
			mctx.setOutput(bufferOut, null);
			mctx.marshalDocument(marshall);

			IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
			ByteArrayInputStream bufferIn = new ByteArrayInputStream(bufferOut.toByteArray());

			obj = uctx.unmarshalDocument(bufferIn, null);

		} catch (JiBXException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return obj;
	}

	public void marshall(E marshall, FileOutputStream out) {
		try {
			IBindingFactory bfact = BindingDirectory.getFactory(marshall.getClass());
			IMarshallingContext mctx = bfact.createMarshallingContext();
			mctx.setIndent(INDENT);
			mctx.setOutput(out, null);
			mctx.marshalDocument(marshall);
		} catch (JiBXException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

	public String toString(E marshall) {
		String document = null;
		try {
			ByteArrayOutputStream bufferOut = new ByteArrayOutputStream();
			IBindingFactory bfact = BindingDirectory.getFactory(marshall.getClass());
			IMarshallingContext mctx = bfact.createMarshallingContext();
			mctx.setIndent(INDENT);
			mctx.setOutput(bufferOut, null);
			mctx.marshalDocument(marshall);

			document = bufferOut.toString();

		} catch (JiBXException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return document;
	}

}
