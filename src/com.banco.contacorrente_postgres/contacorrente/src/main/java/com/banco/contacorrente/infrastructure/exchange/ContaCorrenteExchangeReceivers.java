package com.banco.contacorrente.infrastructure.exchange;

import io.vlingo.xoom.lattice.exchange.ExchangeReceiver;
import io.vlingo.xoom.lattice.grid.Grid;

import com.banco.contacorrente.model.contacorrente.ContaCorrenteEntity;
import io.vlingo.xoom.actors.Definition;
import com.banco.contacorrente.model.*;
import com.banco.contacorrente.model.contacorrente.ContaCorrente;
import com.banco.contacorrente.infrastructure.*;

public class ContaCorrenteExchangeReceivers {

  /**
   * See <a href="https://docs.vlingo.io/xoom-lattice/exchange#exchangereceiver">ExchangeReceiver</a>
   */
  static class LimiteCreditoAlterado implements ExchangeReceiver<ContaCorrenteData> {

    private final Grid stage;

    public LimiteCreditoAlterado(final Grid stage) {
      this.stage = stage;
    }

    @Override
    public void receive(final ContaCorrenteData data) {
      final Agencia Agencia = Agencia.from(data.Numero.Agencia.Numero, data.Numero.Agencia.Nome);
      final NumeroConta Numero = NumeroConta.from(data.Numero.Numero, Agencia);
      stage.actorOf(ContaCorrente.class, stage.addressFactory().from(data.id), Definition.has(ContaCorrenteEntity.class, Definition.parameters(data.id)))
              .andFinallyConsume(contaCorrente -> contaCorrente.AtualizarLimiteCreditoRotativo(Numero, data.LimiteCredito));
    }
  }

}
