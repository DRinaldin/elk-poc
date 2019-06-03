package br.com.wwwti.randomlocale.wrapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

public class ProducerRandomLocale
{
	final static Logger logger = Logger.getLogger(ProducerRandomLocale.class);
	private Producer<String, JSONObject> producer;

	private Properties loadConfig() throws IOException
	{
		logger.info("Loading configuration file");
		InputStream input = ProducerRandomLocale.class.getResourceAsStream("/config.properties");
		Properties properties = new Properties();
		properties.load(input);
		return properties;
	}

	public ProducerRandomLocale()
	{
		Properties props = null;
		try
		{
			props = loadConfig();
		    props.put(ProducerConfig.ACKS_CONFIG, "all");
		    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaJsonSerializer");
		}
		catch (Exception e)
		{
			logger.error("Could not load the configuration file");
			System.exit(1);
		}
		this.producer = new KafkaProducer<String, JSONObject>(props);
	}

	public void sendMessage(String topic, JSONObject message)
	{
		logger.info("Producing... Topic: " + topic + " - Message: " + message);
		producer.send(new ProducerRecord<String, JSONObject>(topic, message), new Callback()
						{
							@Override
							public void onCompletion(RecordMetadata m, Exception e)
							{
								if (e != null)
								{
									logger.error("Error: " + e.getMessage());
					            }
								else
								{
									logger.info("Produced record to topic " + m.topic() + " partition [" + m.partition() + "] @ offset "+ m.offset());
					            }
					          }
						});
	}
	
	public void close()
	{
		logger.info("Finalizing producer");
		this.producer.flush();
		this.producer.close();
	}
}