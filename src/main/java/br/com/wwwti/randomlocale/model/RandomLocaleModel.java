package br.com.wwwti.randomlocale.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import br.com.wwwti.randomlocale.controller.RandomLocaleController;
import br.com.wwwti.randomlocale.util.RandomLocaleUtil;

@Entity
@Table(name = "cities")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class RandomLocaleModel
{
	private final static Logger logger = Logger.getLogger(RandomLocaleController.class);
	@Id
	@Column(name="id", nullable=false)
	@SequenceGenerator(name="seqData", sequenceName="tcc_data_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seqData")
	private Long id;
	@Column(name="lat", nullable=false)
	private long latCoordinate;
	@Column(name="long", nullable=false)
	private long longCoordinate;
	@Column(name="name", nullable=false)
	private String name;
	@Column(name="state", nullable=false)
	private String state;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public long getLatCoordinate()
	{
		return latCoordinate;
	}

	public void setLatCoordinate(long latCoordinate)
	{
		this.latCoordinate = latCoordinate;
	}

	public long getLongCoordinate()
	{
		return longCoordinate;
	}

	public void setLongCoordinate(long longCoordinate)
	{
		this.longCoordinate = longCoordinate;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getStatus()
	{
		List<String> list = new ArrayList<String>();
			list.add("Ei, Adrian, sou eu Rocky - Rocky Balboa");
			list.add("Dane-se o orgulho. Orgulho só dói, ele nunca ajuda – Marsellus Wallace");
			list.add("Você é a doença e eu sou a cura - Stallone Cobra");
			list.add("Você já ouviu a filosodia de que uma vez que o homem admite que está errado, ele é imediatamente perdoado por tudo de errado que já fez? – Vincent Vega");
			list.add("Isso é o que somos: viver por nada ou morrer por alguma coisa - Rambo");
			list.add("Para sobreviver à guerra você precisa viver a guerra - Rambo");
			list.add("Você é um coco e eu vou matar você - Stallone Cobra");
			list.add("Se as minhas respostas assustarem você, então deixe de fazer perguntas assustadoras – Jules Winnfield");
			list.add("Ninguém deve nada a ninguém, você deve a você mesmo - Rocky");
			list.add("Só porque você é um personagem não significa que você tem caráter- The Wolf");
	    return list.get(RandomLocaleUtil.nextValue(0, list.size()-1));
	}

	public String toString()
	{
		String value = "{ \"id\": " + this.getId() + ", \"lat\": " + this.getLatCoordinate() + 
					   ", \"long\": "+ this.getLongCoordinate() + ", \"status\": \"" + this.getStatus() + "\"}";
		return value;
	}

	public JSONObject toJSON()
	{
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		try
		{
			json = (JSONObject) parser.parse(this.toString());
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return json;
	}
}