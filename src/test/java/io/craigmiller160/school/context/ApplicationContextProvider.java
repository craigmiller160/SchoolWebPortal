package io.craigmiller160.school.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Utility provider class for <tt>Spring</tt> operations that require 
 * a shared <tt>ApplicationContext</tt> accessible from a static context.
 * It uses the <tt>ApplicationContextAware</tt> interface to inject the
 * context object into the <tt>AppContext</tt> utility class, which then
 * provides the context in a static way to any classes that need it.
 * <p>
 * This bean should be configured in the context xml file of the 
 * <tt>ApplicationContext</tt> object it is to inject into <tt>AppContext</tt>.
 * It requires no other configuration other than to be listed there.
 * 
 * @author craig
 * @version 1.0
 */
@Component ("contextProvider")
public class ApplicationContextProvider implements ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		AppContext.setApplicationContext(context);
	}

}
