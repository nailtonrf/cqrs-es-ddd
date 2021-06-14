package com.banco.contacorrente.model.contacorrente;

import io.vlingo.xoom.actors.Definition;
import com.banco.contacorrente.model.*;
import io.vlingo.xoom.common.Completes;
import io.vlingo.xoom.actors.Stage;

public interface ContaCorrente {

  /*
   * Returns my current state.
   *
   * @return {@code Completes<ContaCorrenteState>}
   */
  Completes<ContaCorrenteState> currentState();

  Completes<ContaCorrenteState> CriarContaCorrente(final NumeroConta Numero, final double LimiteCredito);

  static Completes<ContaCorrenteState> CriarContaCorrente(final Stage stage, final NumeroConta Numero, final double LimiteCredito) {
    final io.vlingo.xoom.actors.Address _address = stage.addressFactory().uniquePrefixedWith("g-");
    final ContaCorrente _contaCorrente = stage.actorFor(ContaCorrente.class, Definition.has(ContaCorrenteEntity.class, Definition.parameters(_address.idString())), _address);
    return _contaCorrente.CriarContaCorrente(Numero, LimiteCredito);
  }

  Completes<ContaCorrenteState> BloquearContaCorrente(final NumeroConta Numero);

  Completes<ContaCorrenteState> DesbloquearContaCorrente(final NumeroConta Numero);

  Completes<ContaCorrenteState> AtualizarLimiteCreditoRotativo(final NumeroConta Numero, final double LimiteCredito);

}