package br.com.wwwti.randomlocale.controller;

import org.apache.log4j.Logger;

import br.com.wwwti.randomlocale.dao.RandomLocaleDAO;
import br.com.wwwti.randomlocale.model.RandomLocaleModel;
import br.com.wwwti.randomlocale.util.RandomLocaleUtil;
import br.com.wwwti.randomlocale.wrapper.ProducerRandomLocale;

public class RandomLocaleController implements Runnable
{
	private final static Logger logger = Logger.getLogger(RandomLocaleController.class);

	private final Integer MIN_TIME = 500;
	private final Integer MAX_TIME = 3000;

	private final Integer MIN_ID = 1;
	private final Integer MAX_ID = 5509;

	private boolean stop = false;
	private ProducerRandomLocale producer;
	private final String TOPIC = "poc_elk";

	@Override
	public void run()
	{
		this.producer = new ProducerRandomLocale();
		Integer sleepTime;
		Long cityId;
		RandomLocaleModel model;
		RandomLocaleDAO dao = new RandomLocaleDAO();
		try
		{
			while (!this.stop)
			{
				sleepTime = RandomLocaleUtil.nextValue(MIN_TIME, MAX_TIME);
				cityId = new Long(RandomLocaleUtil.nextValue(MIN_ID, MAX_ID));
				model = dao.getById(cityId);
				this.producer.sendMessage(TOPIC, model.toJSON());
				Thread.sleep(sleepTime);
			}
		} catch (Exception e)
		{
			logger.error(e);
			this.shutdown();
			System.exit(1);
		}
	}

	public void shutdown()
	{
		this.stop = true;
	}
}