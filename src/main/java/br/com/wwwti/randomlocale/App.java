package br.com.wwwti.randomlocale;

import java.io.IOException;

import org.apache.log4j.Logger;

import br.com.wwwti.randomlocale.controller.RandomLocaleController;

public class App
{
	final static Logger logger = Logger.getLogger(App.class);

	public static void main(final String[] args) throws IOException
	{
		logger.info("Starting application");
		RandomLocaleController controller = new RandomLocaleController();
		controller.run();
		logger.info("Application finalized");
	}
}