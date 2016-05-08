package cn.dayuanzi.service;


import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;




@Service
public class ServiceRegistry implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		ServiceRegistry.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static SessionFactory getSessionFactory() {
		return applicationContext.getBean(SessionFactory.class);
	}
	
	public static ManagerUserService getManagerUserService(){
		return applicationContext.getBean(ManagerUserService.class);
	}
	
	public static UserService getUserService(){
		return applicationContext.getBean(UserService.class);
	}
}