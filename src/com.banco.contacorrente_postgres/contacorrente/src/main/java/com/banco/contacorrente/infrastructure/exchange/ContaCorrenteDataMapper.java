package com.banco.contacorrente.infrastructure.exchange;

import io.vlingo.xoom.lattice.exchange.ExchangeMapper;
import io.vlingo.xoom.common.serialization.JsonSerialization;

import com.banco.contacorrente.infrastructure.ContaCorrenteData;

/**
 * See <a href="https://docs.vlingo.io/xoom-lattice/exchange#exchangemapper">ExchangeMapper</a>
 */
public class ContaCorrenteDataMapper implements ExchangeMapper<ContaCorrenteData,String> {

  @Override
  public String localToExternal(final ContaCorrenteData local) {
    return JsonSerialization.serialized(local);
  }

  @Override
  public ContaCorrenteData externalToLocal(final String external) {
    return JsonSerialization.deserialized(external, ContaCorrenteData.class);
  }
}
