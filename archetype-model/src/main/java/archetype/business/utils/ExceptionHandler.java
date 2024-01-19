package archetype.business.utils;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.springframework.transaction.TransactionSystemException;

public final class ExceptionHandler {

	private ExceptionHandler() {

	}

	public static void handleConstraintViolationException(Exception e, Logger logger) throws ConstraintViolationException {
		if (e instanceof TransactionSystemException) {
			RollbackException rollbackException = (RollbackException) e.getCause();
			if (rollbackException.getCause() instanceof ConstraintViolationException) {
				ConstraintViolationException constraintViolation = (ConstraintViolationException) rollbackException.getCause();
				throw constraintViolation;
			} else {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		} else {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
	
}
