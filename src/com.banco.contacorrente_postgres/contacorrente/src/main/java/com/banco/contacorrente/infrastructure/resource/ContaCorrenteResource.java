package com.banco.contacorrente.infrastructure.resource;

import io.vlingo.xoom.common.Completes;
import io.vlingo.xoom.turbo.annotation.autodispatch.*;
import io.vlingo.xoom.http.Response;

import com.banco.contacorrente.model.contacorrente.ContaCorrenteEntity;
import com.banco.contacorrente.infrastructure.ContaCorrenteData;
import com.banco.contacorrente.model.contacorrente.ContaCorrente;

import static io.vlingo.xoom.http.Method.*;

@AutoDispatch(path="/", handlers=ContaCorrenteResourceHandlers.class)
@Model(protocol = ContaCorrente.class, actor = ContaCorrenteEntity.class, data = ContaCorrenteData.class)
public interface ContaCorrenteResource {

}