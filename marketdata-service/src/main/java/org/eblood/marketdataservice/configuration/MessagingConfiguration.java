package org.eblood.marketdataservice.configuration;


import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.messaging.converter.ProtobufMessageConverter;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static java.util.Map.entry;
import static org.springframework.amqp.core.MessageProperties.CONTENT_TYPE_JSON;

@Configuration
@EnableRabbit
public class MessagingConfiguration {

  public static final String VERSION_HEADER = "version";

  public static final String EMPTY_ROUTING_KEY = "";

  public static final String CONTENT_TYPE_HEADER = "contentType";
  public static final String BUSINESS_KEY_HEADER = "business-key";
  public static final String CONTENT_TYPE_PB = "application/x-protobuf";
  public static final String PUBLISH_HEADER = "publish";
  public static final String DATASET_HEADER = "dataset";
  public static final String HASH_CODE = "hashcode";

  private static final String X_MAX_LENGTH = "x-max-length";
  private static final String X_QUEUE_MODE = "x-queue-mode";
  private static final String X_MESSAGE_TTL = "x-message-ttl";
  private static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
  private static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

/*  private final MessagingProperties messagingProperties;

  public MessagingConfiguration(MessagingProperties messagingProperties) {
    this.messagingProperties = messagingProperties;
  }*/

  @Bean
  public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory,
      ObjectMapper jsonObjectMapper) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(contentTypeDelegatingMessageConverter(jsonObjectMapper));
    return rabbitTemplate;
  }

   /* @Bean
    public Queue inputUniverseQueue() {
        return new Queue(
            "marketdata",
            true,
            false,
            false,
            Map.ofEntries(
                entry(X_MAX_LENGTH, 100000),
                entry(X_QUEUE_MODE, "lazy"),
                entry(X_MESSAGE_TTL, 60000)
            )
        );
    }*/

  @Bean
  public Queue myQueue() {
    return new Queue("myQueue", false);
  }

  @Bean
  public MessageConverter contentTypeDelegatingMessageConverter(ObjectMapper jsonObjectMapper) {
    ContentTypeDelegatingMessageConverter converter = new ContentTypeDelegatingMessageConverter();
    converter.addDelegate(MessageProperties.CONTENT_TYPE_JSON, new Jackson2JsonMessageConverter(jsonObjectMapper));
    //converter.addDelegate(CONTENT_TYPE_PB, new ProtobufMessageConverter());
    //converter.addDelegate(MessageProperties.CONTENT_TYPE_XML, new JaxbMessageConverter());
    return converter;
  }

/*
    @Bean
    public Binding bindingInputUniverseQueueToInputExchange() {
        return BindingBuilder
            .bind(inputUniverseQueue())
            .to(inputExchange())
            .whereAll(Map.of(
            CONTENT_TYPE_HEADER,
            messagingProperties.getInputExchange().getQueues().getUniverse().getContentType(),
            DATASET_HEADER,
            messagingProperties.getInputExchange().getQueues().getUniverse().getDataset()))
            .match();
    }
*/


}
