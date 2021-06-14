package com.banco.contacorrente.model.creditorotativo;

import com.banco.contacorrente.model.*;
import io.vlingo.xoom.common.Completes;

import io.vlingo.xoom.lattice.model.stateful.StatefulEntity;

/**
 * See <a href="https://docs.vlingo.io/xoom-lattice/entity-cqrs#stateful">StatefulEntity</a>
 */
public final class CreditoRotativoEntity extends StatefulEntity<CreditoRotativoState> implements CreditoRotativo {
  private CreditoRotativoState state;

  public CreditoRotativoEntity(final String id) {
    super(id);
    this.state = CreditoRotativoState.identifiedBy(id);
  }

  /*
   * Returns my current state.
   *
   * @return {@code Completes<CreditoRotativoState>}
   */
  public Completes<CreditoRotativoState> currentState() {
    return Completes.withSuccess(state);
  }

  @Override
  public Completes<CreditoRotativoState> AumentarLimiteCredito(final NumeroConta ContaCorrente, final double Valor) {
    /**
     * TODO: Implement command logic. See {@link CreditoRotativoState#AumentarLimiteCredito()}
     */
    final CreditoRotativoState stateArg = state.AumentarLimiteCredito(ContaCorrente, Valor);
    return apply(stateArg, new LimiteCreditoAlterado(stateArg), () -> state);
  }

  /*
   * Received when my current state has been applied and restored.
   *
   * @param state the CreditoRotativoState
   */
  @Override
  protected void state(final CreditoRotativoState state) {
    this.state = state;
  }

  /*
   * Received when I must provide my state type.
   *
   * @return {@code Class<CreditoRotativoState>}
   */
  @Override
  protected Class<CreditoRotativoState> stateType() {
    return CreditoRotativoState.class;
  }
}
