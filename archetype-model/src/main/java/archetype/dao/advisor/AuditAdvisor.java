package archetype.dao.advisor;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.beanutils.BeanMap;
import org.springframework.aop.MethodBeforeAdvice;

import archetype.model.Audit;


public class AuditAdvisor implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		String nameMethod = method.getName();
		Boolean persist = false;
		if ((persist = nameMethod.compareTo("persist") == 0) || nameMethod.compareTo("merge") == 0) {
			BeanMap tmpBeanMap = new BeanMap(args[0]);
			if (tmpBeanMap.containsKey("audit")) {
				Audit audit = (Audit) tmpBeanMap.get("audit");
				if (audit == null) {
					audit = new Audit();
				}
				Date date = new Date();
				if (persist) {
					audit.setCreated(date);
				} else {
					audit.setModified(date);
				}
				tmpBeanMap.put("audit", audit);
			}
		}
	}

}
