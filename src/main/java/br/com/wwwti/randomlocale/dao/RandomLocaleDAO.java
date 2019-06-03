package br.com.wwwti.randomlocale.dao;

import org.hibernate.Session;

import br.com.wwwti.randomlocale.model.RandomLocaleModel;
import br.com.wwwti.randomlocale.util.RandomLocaleHibernateUtil;

public class RandomLocaleDAO
{
	private final RandomLocaleHibernateUtil util = new RandomLocaleHibernateUtil();

	public RandomLocaleModel getById(Long id) throws Exception
	{
		Session session = this.util.getSessionFactory().openSession();
		RandomLocaleModel randomLocaleModel = (RandomLocaleModel) session.get(RandomLocaleModel.class, id);
		return randomLocaleModel;
	}
}