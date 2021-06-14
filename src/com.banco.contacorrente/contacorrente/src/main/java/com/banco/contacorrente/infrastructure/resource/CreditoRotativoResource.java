package com.banco.contacorrente.infrastructure.resource;

import io.vlingo.xoom.common.Completes;
import io.vlingo.xoom.turbo.annotation.autodispatch.*;
import io.vlingo.xoom.http.Response;

import com.banco.contacorrente.model.creditorotativo.CreditoRotativo;
import com.banco.contacorrente.infrastructure.CreditoRotativoData;
import com.banco.contacorrente.model.creditorotativo.CreditoRotativoEntity;

import static io.vlingo.xoom.http.Method.*;

@AutoDispatch(path="/", handlers=CreditoRotativoResourceHandlers.class)
@Model(protocol = CreditoRotativo.class, actor = CreditoRotativoEntity.class, data = CreditoRotativoData.class)
public interface CreditoRotativoResource {

}