package br.com.wwwti.randomlocale.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class RandomLocaleHibernateUtil
{
	final StandardServiceRegistry REGISTRY = new StandardServiceRegistryBuilder().configure().build();
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory()
	{
		if(sessionFactory == null)
		{
	        try
	        {
	        	sessionFactory = new MetadataSources(REGISTRY).buildMetadata().buildSessionFactory();
	        }
	        catch (Exception e)
	        {
	            StandardServiceRegistryBuilder.destroy(REGISTRY);
	        }
		}
		return sessionFactory;
	}
}