package archetype.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.jibx.runtime.IAliasable;
import org.jibx.runtime.IMarshallable;
import org.jibx.runtime.IMarshaller;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshaller;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.MarshallingContext;
import org.jibx.runtime.impl.UnmarshallingContext;

public class HashMapper implements IMarshaller, IUnmarshaller, IAliasable {

	public static final String	SIZE_ATTRIBUTE_NAME	= "size";
	public static final String	ENTRY_ELEMENT_NAME	= "entry";
	public static final String	KEY_ATTRIBUTE_NAME	= "key";
	public static final int		DEFAULT_SIZE		= 10;
	public static final int		INDENT				= 2;

	private String				mUri;
	private int					mIndex;
	private String				mName;

	// private String m_type;

	public HashMapper() {
		mUri = null;
		mIndex = 0;
		mName = "hashmap";
	}

	public HashMapper(String uri, int index, String name) {
		mUri = uri;
		mIndex = index;
		mName = name;
	}

	// public HashMapper(String uri, int index, String name, String type) {
	// m_uri = uri;
	// m_index = index;
	// m_name = name;
	// m_type = type;
	// }

	public boolean isExtension(String mapname) {
		return false;
	}

	public void marshal(Object obj, IMarshallingContext ictx) throws JiBXException {

		if (ictx.getStackDepth() < INDENT) {
			if (!(obj instanceof Collection)) {
				throw new JiBXException("Invalid object type for marshaller");
			} else if (!(ictx instanceof MarshallingContext)) {
				throw new JiBXException("Invalid object type for marshaller");
			} else {
				Collection<?> collection = (Collection<?>) obj;

				MarshallingContext ctx = (MarshallingContext) ictx;

				if (mName != null) {
					ctx.startTag(mIndex, mName);
				}

				Iterator<?> iter = collection.iterator();
				while (iter.hasNext()) {
					Object entry = (Object) iter.next();
					if (entry instanceof IMarshallable) {
						((IMarshallable) entry).marshal(ctx);
					} else if (entry != null) {
						throw new JiBXException("Mapped value is not marshallable");
					}
				}

				ctx.endTag(mIndex, mName);
			}
		}
	}

	public boolean isPresent(IUnmarshallingContext ctx) throws JiBXException {
		return ctx.isAt(mUri, mName);
	}

	@SuppressWarnings("unchecked")
	public Object unmarshal(Object obj, IUnmarshallingContext ictx) throws JiBXException {

		UnmarshallingContext ctx = (UnmarshallingContext) ictx;
		if (!ctx.isAt(mUri, mName)) {
			ctx.throwStartTagNameError(mUri, mName);
		}

		int size = ctx.attributeInt(mUri, SIZE_ATTRIBUTE_NAME, DEFAULT_SIZE);

		Collection<Object> collection = null;
		if (!(obj instanceof Collection<?>)) {
			collection = new HashSet<Object>(size);
		} else {
			collection = (Collection<Object>) obj;
		}

		ctx.parsePastStartTag(mUri, mName);
		while (!ctx.isEnd()) {
			Object value = ctx.unmarshalElement();
			collection.add(value);
		}

		if (mName != null) {
			ctx.parsePastEndTag(mUri, mName);
		}

		return collection;
	}
}
