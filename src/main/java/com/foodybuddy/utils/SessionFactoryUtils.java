package com.foodybuddy.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * The Class SessionFactoryUtils.
 */
public class SessionFactoryUtils {
	
	/** The log. */
	static Log log = LogFactory.getLog(SessionFactoryUtils.class.getName());
	
	/** The session factory. */
	private static SessionFactory sessionFactory = buildSessionFactory();

	/**
	 * Instantiates a new session factory utils.
	 */
	private SessionFactoryUtils() {
	}

	/**
	 * Builds the session factory.
	 *
	 * @return the session factory
	 */
	private static SessionFactory buildSessionFactory() {
		try {
			if (sessionFactory == null) {
				Configuration configuration = new Configuration()
						.configure(SessionFactoryUtils.class.getResource("/hibernate.cfg.xml"));
				StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
				serviceRegistryBuilder.applySettings(configuration.getProperties());
				ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			}
			return sessionFactory;
		} catch (Throwable ex) {
			log.error("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Gets the session factory.
	 *
	 * @return the session factory
	 */
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null || (sessionFactory != null && sessionFactory.isClosed())) {
			sessionFactory = buildSessionFactory();
		}
		return sessionFactory;
	}

	/**
	 * Close.
	 */
	public static void close() {
		sessionFactory.close();
	}
}