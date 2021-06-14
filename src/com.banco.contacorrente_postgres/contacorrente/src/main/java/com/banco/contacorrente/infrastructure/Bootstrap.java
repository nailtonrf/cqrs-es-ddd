package com.banco.contacorrente.infrastructure;


import io.vlingo.xoom.http.resource.SinglePageApplicationConfiguration;
import io.vlingo.xoom.lattice.grid.Grid;
import io.vlingo.xoom.turbo.XoomInitializationAware;
import io.vlingo.xoom.turbo.annotation.initializer.Xoom;

@Xoom(name = "contacorrente")
public class Bootstrap implements XoomInitializationAware {

  @Override
  public void onInit(final Grid grid) {
  }

  @Override
  public SinglePageApplicationConfiguration singlePageApplicationResource() {
    return SinglePageApplicationConfiguration.defineWith("/frontend", "/app");
  }

}
