package com.banco.contacorrente.model.creditorotativo;

import com.banco.contacorrente.model.*;

import io.vlingo.xoom.symbio.store.object.StateObject;

/**
 * See <a href="https://docs.vlingo.io/xoom-symbio/object-storage">Object Storage</a>
 */
public final class CreditoRotativoState extends StateObject {

  public final String id;
  public final NumeroConta ContaCorrente;
  public final double Valor;

  public static CreditoRotativoState identifiedBy(final String id) {
    return new CreditoRotativoState(id, null, 0);
  }

  public CreditoRotativoState (final String id, final NumeroConta ContaCorrente, final double Valor) {
    this.id = id;
    this.ContaCorrente = ContaCorrente;
    this.Valor = Valor;
  }

  public CreditoRotativoState AumentarLimiteCredito(final NumeroConta ContaCorrente, final double Valor) {
    //TODO: Implement command logic.
    return new CreditoRotativoState(this.id, ContaCorrente, Valor);
  }

}
