package archetype.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

public class Marshaller<E> {

	public static final int	INDENT	= 2;
	private static Logger	logger	= Logger.getLogger(Marshaller.class);

	@SuppressWarnings("unchecked")
	public E marshall(E marshall) {
		try {
			ByteArrayOutputStream bufferOut = new ByteArrayOutputStream();
			IBindingFactory bfact = BindingDirectory.getFactory(marshall.getClass());
			IMarshallingContext mctx = bfact.createMarshallingContext();
			mctx.setIndent(INDENT);
			mctx.setOutput(bufferOut, null);
			mctx.marshalDocument(marshall);

			IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
			ByteArrayInputStream bufferIn = new ByteArrayInputStream(bufferOut.toByteArray());
			return (E) uctx.unmarshalDocument(bufferIn, null);
		} catch (JiBXException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

	public void marshall(E marshall, String fileName) {
		try {
			IBindingFactory bfact = BindingDirectory.getFactory(marshall.getClass());
			IMarshallingContext mctx = bfact.createMarshallingContext();
			mctx.setIndent(INDENT);
			FileOutputStream out = new FileOutputStream(fileName);
			mctx.setOutput(out, null);
			mctx.marshalDocument(marshall);
		} catch (JiBXException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		} catch (FileNotFoundException e) {
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
			logger.error(ExceptionUtils.getMessage(e));
		}
		return document;
	}
	
}
