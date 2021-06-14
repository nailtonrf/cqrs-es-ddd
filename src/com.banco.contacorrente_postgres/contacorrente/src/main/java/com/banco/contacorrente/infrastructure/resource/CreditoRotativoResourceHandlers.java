package com.banco.contacorrente.infrastructure.resource;

import io.vlingo.xoom.actors.Stage;
import io.vlingo.xoom.common.Completes;
import io.vlingo.xoom.turbo.annotation.autodispatch.Handler.Three;
import io.vlingo.xoom.turbo.annotation.autodispatch.Handler.Two;
import io.vlingo.xoom.turbo.annotation.autodispatch.HandlerEntry;

import com.banco.contacorrente.model.creditorotativo.CreditoRotativo;
import com.banco.contacorrente.infrastructure.*;
import com.banco.contacorrente.model.creditorotativo.CreditoRotativoState;

public class CreditoRotativoResourceHandlers {

  public static final int ADAPT_STATE = 0;

  public static final HandlerEntry<Two<CreditoRotativoData, CreditoRotativoState>> ADAPT_STATE_HANDLER =
          HandlerEntry.of(ADAPT_STATE, CreditoRotativoData::from);


}