package com.banco.contacorrente.model.contacorrente;

import com.banco.contacorrente.model.*;
import io.vlingo.xoom.common.Completes;

import io.vlingo.xoom.lattice.model.stateful.StatefulEntity;

/**
 * See <a href="https://docs.vlingo.io/xoom-lattice/entity-cqrs#stateful">StatefulEntity</a>
 */
public final class ContaCorrenteEntity extends StatefulEntity<ContaCorrenteState> implements ContaCorrente {
  private ContaCorrenteState state;

  public ContaCorrenteEntity(final String id) {
    super(id);
    this.state = ContaCorrenteState.identifiedBy(id);
  }

  /*
   * Returns my current state.
   *
   * @return {@code Completes<ContaCorrenteState>}
   */
  public Completes<ContaCorrenteState> currentState() {
    return Completes.withSuccess(state);
  }

  @Override
  public Completes<ContaCorrenteState> CriarContaCorrente(final NumeroConta Numero, final double LimiteCredito) {
    /**
     * TODO: Implement command logic. See {@link ContaCorrenteState#CriarContaCorrente()}
     */
    final ContaCorrenteState stateArg = state.CriarContaCorrente(Numero, LimiteCredito);
    return apply(stateArg, new ContaCorrenteCriada(stateArg), () -> state);
  }

  @Override
  public Completes<ContaCorrenteState> BloquearContaCorrente(final NumeroConta Numero) {
    /**
     * TODO: Implement command logic. See {@link ContaCorrenteState#BloquearContaCorrente()}
     */
    final ContaCorrenteState stateArg = state.BloquearContaCorrente(Numero);
    return apply(stateArg, new ContaCorrenteBloqueada(stateArg), () -> state);
  }

  @Override
  public Completes<ContaCorrenteState> DesbloquearContaCorrente(final NumeroConta Numero) {
    /**
     * TODO: Implement command logic. See {@link ContaCorrenteState#DesbloquearContaCorrente()}
     */
    final ContaCorrenteState stateArg = state.DesbloquearContaCorrente(Numero);
    return apply(stateArg, new ContaCorrenteDesbloqueada(stateArg), () -> state);
  }

  @Override
  public Completes<ContaCorrenteState> AtualizarLimiteCreditoRotativo(final NumeroConta Numero, final double LimiteCredito) {
    /**
     * TODO: Implement command logic. See {@link ContaCorrenteState#AtualizarLimiteCreditoRotativo()}
     */
    final ContaCorrenteState stateArg = state.AtualizarLimiteCreditoRotativo(Numero, LimiteCredito);
    return apply(stateArg, () -> state);
  }

  /*
   * Received when my current state has been applied and restored.
   *
   * @param state the ContaCorrenteState
   */
  @Override
  protected void state(final ContaCorrenteState state) {
    this.state = state;
  }

  /*
   * Received when I must provide my state type.
   *
   * @return {@code Class<ContaCorrenteState>}
   */
  @Override
  protected Class<ContaCorrenteState> stateType() {
    return ContaCorrenteState.class;
  }
}
