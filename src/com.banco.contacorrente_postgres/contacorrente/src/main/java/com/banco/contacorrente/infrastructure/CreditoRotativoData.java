package com.banco.contacorrente.infrastructure;

import java.util.stream.Collectors;
import com.banco.contacorrente.model.*;
import java.util.*;
import com.banco.contacorrente.model.creditorotativo.CreditoRotativoState;

@SuppressWarnings("all")
public class CreditoRotativoData {
  public final String id;
  public final NumeroContaData ContaCorrente;
  public final double Valor;

  public static CreditoRotativoData from(final CreditoRotativoState creditoRotativoState) {
    final NumeroContaData contaCorrente = creditoRotativoState.contaCorrente != null ? NumeroContaData.from(creditoRotativoState.contaCorrente) : null;
    return from(creditoRotativoState.id, ContaCorrente, creditoRotativoState.Valor);
  }

  public static CreditoRotativoData from(final String id, final NumeroContaData ContaCorrente, final double Valor) {
    return new CreditoRotativoData(id, ContaCorrente, Valor);
  }

  public static List<CreditoRotativoData> from(final List<CreditoRotativoState> states) {
    return states.stream().map(CreditoRotativoData::from).collect(Collectors.toList());
  }

  public static CreditoRotativoData empty() {
    return from(CreditoRotativoState.identifiedBy(""));
  }

  private CreditoRotativoData (final String id, final NumeroContaData ContaCorrente, final double Valor) {
    this.id = id;
    this.ContaCorrente = ContaCorrente;
    this.Valor = Valor;
  }

  public CreditoRotativoState toCreditoRotativoState() {
    final Agencia Agencia = Agencia.from(this.ContaCorrente.Agencia.Numero, this.ContaCorrente.Agencia.Nome);
    final NumeroConta ContaCorrente = NumeroConta.from(this.ContaCorrente.Numero, Agencia);
    return new CreditoRotativoState(id, ContaCorrente, Valor);
  }

}
