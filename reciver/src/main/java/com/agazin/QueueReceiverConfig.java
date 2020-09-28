package com.agazin;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueReceiverConfig {

	private static final String EX_AGAZIN_FANOUT = "agazin-fanout-exchange";
	private static final String Q_AGAZIN_FANOUT = "agazin-fanout";
	private static final String AGAZIN = "agazin";
	private static final String AGAZIN_EX = "agazin-exchange";
	static final String topicExchangeName = "spring-boot-exchange";
	static final String queueName = "spring-boot";
	
	
	
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	@Bean
	public SimpleRabbitListenerContainerFactory jsaFactory(ConnectionFactory connectionFactory,
			SimpleRabbitListenerContainerFactoryConfigurer configurer) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		factory.setMessageConverter(jsonMessageConverter());
		return factory;
	}

	@Bean
	@Qualifier("spring-boot")
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	@Qualifier("spring-boot")
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}

	@Bean
	@Qualifier("spring-boot")
	Binding binding(@Qualifier("spring-boot") Queue queue, @Qualifier("spring-boot") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
	}
	
	@Bean
	@Qualifier(AGAZIN)
	Queue queueAgazin() {
		return new Queue(AGAZIN, false);
	}

	@Bean
	@Qualifier(AGAZIN_EX)
	TopicExchange exchangeAgazin() {
		return new TopicExchange(AGAZIN_EX);
	}

	@Bean
	@Qualifier(AGAZIN)
	Binding bindingAgazin(@Qualifier(AGAZIN) Queue queue, @Qualifier(AGAZIN_EX) TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("com.agazin.#");
	}
	
	
	
	@Bean
	@Qualifier(Q_AGAZIN_FANOUT)
	Queue queueFanout() {
		return new Queue(Q_AGAZIN_FANOUT, false);
	}
	@Bean
	@Qualifier(EX_AGAZIN_FANOUT)
	FanoutExchange exchangeFanout() {
		return new FanoutExchange(EX_AGAZIN_FANOUT);
	}
	@Bean
	@Qualifier(Q_AGAZIN_FANOUT)
	Binding bindingFanout(@Qualifier(Q_AGAZIN_FANOUT) Queue queue, @Qualifier(EX_AGAZIN_FANOUT) FanoutExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange);
	}
	
	@Bean
	@Qualifier("agazin-direct")
	Queue queueDirect() {
		return new Queue("agazin-direct", false);
	}
	@Bean
	@Qualifier("agazin-direct-exchange")
	DirectExchange exchangeDirect() {
		return new DirectExchange("agazin-direct-exchange");
	}
	@Bean
	@Qualifier("agazin-direct")
	Binding bindingDirect(@Qualifier("agazin-direct") Queue queue, @Qualifier("agazin-direct-exchange") DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("com.agazin.#");
	}
}
