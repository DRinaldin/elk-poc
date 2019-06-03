package br.com.wwwti.randomlocale.util;

import java.util.Random;

public class RandomLocaleUtil
{
	public static int nextValue(int min, int max)
	{
		if (min >= max)
		{
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}