/*******************************************************************************
 * Copyright (c) 1998, 2010 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v.
 * 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 * 
 * Contributors:
 * Oracle - initial API and implementation from Oracle TopLink
 * 
 * @author mobrien
 * @since EclipseLink 1.1 enh# 248748
 *        10/20/2008-1.1M4 Michael O'Brien
 ******************************************************************************/
package archetype.utils.persistence.services.jboss;

import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.sessions.Session;

/**
 * <p>
 * <b>Purpose</b>: Provide a dynamic interface into the EclipseLink Session.
 * <p>
 * <b>Description</b>: This class is meant to provide a framework for gaining
 * access to configuration of the EclipseLink Session during runtime. It will
 * provide the basis for development of a JMX service and possibly other
 * frameworks.
 */
@SuppressWarnings("unchecked")
public class MBeanJBossRuntimeServices extends JBossRuntimeServices implements MBeanJBossRuntimeServicesMBean {

	public MBeanJBossRuntimeServices(Session session) {
		super((AbstractSession) session);
	}
}
