package com.banco.contacorrente.infrastructure.exchange;

import io.vlingo.xoom.lattice.exchange.ExchangeAdapter;
import io.vlingo.xoom.lattice.exchange.MessageParameters;
import io.vlingo.xoom.lattice.exchange.MessageParameters.DeliveryMode;
import io.vlingo.xoom.lattice.exchange.rabbitmq.Message;

import com.banco.contacorrente.infrastructure.ContaCorrenteData;

/**
 * See <a href="https://docs.vlingo.io/xoom-lattice/exchange#exchangeadapter">ExchangeAdapter</a>
 */
public class ContaCorrenteConsumerAdapter implements ExchangeAdapter<ContaCorrenteData, String, Message> {

  private final String supportedSchemaName;

  public ContaCorrenteConsumerAdapter(final String supportedSchemaName) {
    this.supportedSchemaName = supportedSchemaName;
  }

  @Override
  public ContaCorrenteData fromExchange(final Message exchangeMessage) {
    return new ContaCorrenteDataMapper().externalToLocal(exchangeMessage.payloadAsText());
  }

  @Override
  public Message toExchange(final ContaCorrenteData local) {
    final String messagePayload = new ContaCorrenteDataMapper().localToExternal(local);
    return new Message(messagePayload, MessageParameters.bare().deliveryMode(DeliveryMode.Durable));
  }

  @Override
  public boolean supports(final Object exchangeMessage) {
    if(!exchangeMessage.getClass().equals(Message.class)) {
      return false;
    }
    final String schemaName = ((Message) exchangeMessage).messageParameters.typeName();
    return supportedSchemaName.equalsIgnoreCase(schemaName);
  }

}
