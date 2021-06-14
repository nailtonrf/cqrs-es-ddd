package com.banco.contacorrente.infrastructure;

import java.util.stream.Collectors;
import com.banco.contacorrente.model.contacorrente.ContaCorrenteState;
import com.banco.contacorrente.model.*;
import java.util.*;

@SuppressWarnings("all")
public class ContaCorrenteData {
  public final String id;
  public final NumeroContaData Numero;
  public final double LimiteCredito;
  public final boolean Bloqueada;

  public static ContaCorrenteData from(final ContaCorrenteState contaCorrenteState) {
    final NumeroContaData numero = contaCorrenteState.numero != null ? NumeroContaData.from(contaCorrenteState.numero) : null;
    return from(contaCorrenteState.id, Numero, contaCorrenteState.LimiteCredito, contaCorrenteState.Bloqueada);
  }

  public static ContaCorrenteData from(final String id, final NumeroContaData Numero, final double LimiteCredito, final boolean Bloqueada) {
    return new ContaCorrenteData(id, Numero, LimiteCredito, Bloqueada);
  }

  public static List<ContaCorrenteData> from(final List<ContaCorrenteState> states) {
    return states.stream().map(ContaCorrenteData::from).collect(Collectors.toList());
  }

  public static ContaCorrenteData empty() {
    return from(ContaCorrenteState.identifiedBy(""));
  }

  private ContaCorrenteData (final String id, final NumeroContaData Numero, final double LimiteCredito, final boolean Bloqueada) {
    this.id = id;
    this.Numero = Numero;
    this.LimiteCredito = LimiteCredito;
    this.Bloqueada = Bloqueada;
  }

  public ContaCorrenteState toContaCorrenteState() {
    final Agencia Agencia = Agencia.from(this.Numero.Agencia.Numero, this.Numero.Agencia.Nome);
    final NumeroConta Numero = NumeroConta.from(this.Numero.Numero, Agencia);
    return new ContaCorrenteState(id, Numero, LimiteCredito, Bloqueada);
  }

}
