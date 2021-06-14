package com.banco.contacorrente.model.contacorrente;

import com.banco.contacorrente.model.*;

import io.vlingo.xoom.symbio.store.object.StateObject;

/**
 * See <a href="https://docs.vlingo.io/xoom-symbio/object-storage">Object Storage</a>
 */
public final class ContaCorrenteState extends StateObject {

  public final String id;
  public final NumeroConta Numero;
  public final double LimiteCredito;
  public final boolean Bloqueada;

  public static ContaCorrenteState identifiedBy(final String id) {
    return new ContaCorrenteState(id, null, 0, false);
  }

  public ContaCorrenteState (final String id, final NumeroConta Numero, final double LimiteCredito, final boolean Bloqueada) {
    this.id = id;
    this.Numero = Numero;
    this.LimiteCredito = LimiteCredito;
    this.Bloqueada = Bloqueada;
  }

  public ContaCorrenteState CriarContaCorrente(final NumeroConta Numero, final double LimiteCredito) {
    //TODO: Implement command logic.
    return new ContaCorrenteState(this.id, Numero, LimiteCredito, this.Bloqueada);
  }

  public ContaCorrenteState BloquearContaCorrente(final NumeroConta Numero) {
    //TODO: Implement command logic.
    return new ContaCorrenteState(this.id, Numero, this.LimiteCredito, this.Bloqueada);
  }

  public ContaCorrenteState DesbloquearContaCorrente(final NumeroConta Numero) {
    //TODO: Implement command logic.
    return new ContaCorrenteState(this.id, Numero, this.LimiteCredito, this.Bloqueada);
  }

  public ContaCorrenteState AtualizarLimiteCreditoRotativo(final NumeroConta Numero, final double LimiteCredito) {
    //TODO: Implement command logic.
    return new ContaCorrenteState(this.id, Numero, LimiteCredito, this.Bloqueada);
  }

}
