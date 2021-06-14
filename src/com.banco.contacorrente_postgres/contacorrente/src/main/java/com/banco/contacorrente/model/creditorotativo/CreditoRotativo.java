package com.banco.contacorrente.model.creditorotativo;

import com.banco.contacorrente.model.*;
import io.vlingo.xoom.common.Completes;

public interface CreditoRotativo {

  /*
   * Returns my current state.
   *
   * @return {@code Completes<CreditoRotativoState>}
   */
  Completes<CreditoRotativoState> currentState();

  Completes<CreditoRotativoState> AumentarLimiteCredito(final NumeroConta ContaCorrente, final double Valor);

}