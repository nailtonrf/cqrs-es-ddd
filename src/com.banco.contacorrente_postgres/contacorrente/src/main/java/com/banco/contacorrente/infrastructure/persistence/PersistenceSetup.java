package com.banco.contacorrente.infrastructure.persistence;

import io.vlingo.xoom.turbo.annotation.persistence.Persistence;
import io.vlingo.xoom.turbo.annotation.persistence.Persistence.StorageType;
import io.vlingo.xoom.turbo.annotation.persistence.Adapters;
import io.vlingo.xoom.turbo.annotation.persistence.DataObjects;
import com.banco.contacorrente.model.contacorrente.ContaCorrenteState;
import com.banco.contacorrente.infrastructure.NumeroContaData;
import com.banco.contacorrente.infrastructure.ContaCorrenteData;
import com.banco.contacorrente.infrastructure.CreditoRotativoData;
import com.banco.contacorrente.model.creditorotativo.CreditoRotativoState;
import com.banco.contacorrente.infrastructure.AgenciaData;

@SuppressWarnings("unused")
@Persistence(basePackage = "com.banco.contacorrente", storageType = StorageType.STATE_STORE, cqrs = false)
@Adapters({
  CreditoRotativoState.class,
  ContaCorrenteState.class
})
@DataObjects({ContaCorrenteData.class, NumeroContaData.class, CreditoRotativoData.class, AgenciaData.class})
public class PersistenceSetup {


}