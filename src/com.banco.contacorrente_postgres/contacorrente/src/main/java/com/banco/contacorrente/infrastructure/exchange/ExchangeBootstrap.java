package com.banco.contacorrente.infrastructure.exchange;

import io.vlingo.xoom.turbo.actors.Settings;
import io.vlingo.xoom.lattice.exchange.Exchange;
import io.vlingo.xoom.turbo.exchange.ExchangeSettings;
import io.vlingo.xoom.turbo.exchange.ExchangeInitializer;
import io.vlingo.xoom.lattice.exchange.rabbitmq.ExchangeFactory;
import io.vlingo.xoom.lattice.exchange.ConnectionSettings;
import io.vlingo.xoom.lattice.exchange.rabbitmq.Message;
import io.vlingo.xoom.lattice.exchange.rabbitmq.MessageSender;
import io.vlingo.xoom.lattice.exchange.Covey;
import io.vlingo.xoom.lattice.grid.Grid;
import io.vlingo.xoom.symbio.store.dispatch.Dispatcher;

import io.vlingo.xoom.lattice.model.IdentifiedDomainEvent;
import com.banco.contacorrente.infrastructure.ContaCorrenteData;

public class ExchangeBootstrap implements ExchangeInitializer {

  private Dispatcher<?> dispatcher;

  @Override
  public void init(final Grid stage) {
    ExchangeSettings.load(Settings.properties());

    final ConnectionSettings contacorrenteTopicSettings =
                ExchangeSettings.of("contacorrente-topic").mapToConnection();

    final Exchange contacorrenteTopic =
                ExchangeFactory.fanOutInstance(contacorrenteTopicSettings, "contacorrente-topic", true);

    contacorrenteTopic.register(Covey.of(
        new MessageSender(contacorrenteTopic.connection()),
        new ContaCorrenteExchangeReceivers.LimiteCreditoAlterado(stage),
        new ContaCorrenteConsumerAdapter("banco:contacorrente:creditorotativo:LimiteCreditoAlterado:1.0.0"),
        ContaCorrenteData.class,
        String.class,
        Message.class));

    final ConnectionSettings limitealteradoTopicSettings =
                ExchangeSettings.of("limitealterado-topic").mapToConnection();

    final Exchange limitealteradoTopic =
                ExchangeFactory.fanOutInstance(limitealteradoTopicSettings, "limitealterado-topic", true);

    limitealteradoTopic.register(Covey.of(
        new MessageSender(limitealteradoTopic.connection()),
        received -> {},
        new CreditoRotativoProducerAdapter(),
        IdentifiedDomainEvent.class,
        IdentifiedDomainEvent.class,
        Message.class));


    this.dispatcher = new ExchangeDispatcher(limitealteradoTopic);

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        contacorrenteTopic.close();
        limitealteradoTopic.close();

        System.out.println("\n");
        System.out.println("==================");
        System.out.println("Stopping exchange.");
        System.out.println("==================");
    }));
  }

  @Override
  public Dispatcher<?> dispatcher() {
    return dispatcher;
  }
}