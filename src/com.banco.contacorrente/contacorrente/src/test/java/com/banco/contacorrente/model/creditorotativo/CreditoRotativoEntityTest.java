package com.banco.contacorrente.model.creditorotativo;

import io.vlingo.xoom.actors.World;
import io.vlingo.xoom.actors.testkit.AccessSafely;
import io.vlingo.xoom.symbio.BaseEntry;
import com.banco.contacorrente.infrastructure.persistence.MockDispatcher;
import com.banco.contacorrente.model.*;
import com.banco.contacorrente.infrastructure.persistence.CreditoRotativoStateAdapter;
import io.vlingo.xoom.lattice.model.stateful.StatefulTypeRegistry;
import io.vlingo.xoom.lattice.model.stateful.StatefulTypeRegistry.Info;
import io.vlingo.xoom.symbio.store.state.StateStore;
import io.vlingo.xoom.symbio.store.state.inmemory.InMemoryStateStoreActor;
import io.vlingo.xoom.symbio.StateAdapterProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class CreditoRotativoEntityTest {

  private World world;
  private StateStore store;
  private MockDispatcher dispatcher;
  private CreditoRotativo creditoRotativo;

  @BeforeEach
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void setUp(){
    dispatcher = new MockDispatcher();
    world = World.startWithDefaults("test-stateful");
    new StateAdapterProvider(world).registerAdapter(CreditoRotativoState.class, new CreditoRotativoStateAdapter());
    store = world.actorFor(StateStore.class, InMemoryStateStoreActor.class, Collections.singletonList(dispatcher));
    new StatefulTypeRegistry(world).register(new Info(store, CreditoRotativoState.class, CreditoRotativoState.class.getSimpleName()));
    creditoRotativo = world.actorFor(CreditoRotativo.class, CreditoRotativoEntity.class, "#1");
  }

  private static final NumeroConta CONTA_CORRENTE_FOR_AUMENTAR_LIMITE_CREDITO_TEST = NumeroConta.from(2, Agencia.from(3, "updated-creditorotativo-ContaCorrente-Agencia-Nome"));
  private static final double VALOR_FOR_AUMENTAR_LIMITE_CREDITO_TEST = 4;

  @Test
  public void AumentarLimiteCredito() {
    final AccessSafely dispatcherAccess = dispatcher.afterCompleting(1);
    final CreditoRotativoState state = creditoRotativo.AumentarLimiteCredito(CONTA_CORRENTE_FOR_AUMENTAR_LIMITE_CREDITO_TEST, VALOR_FOR_AUMENTAR_LIMITE_CREDITO_TEST).await();

    assertEquals(state.ContaCorrente.Numero, 1);
    assertEquals(state.ContaCorrente.Agencia.Numero, 2);
    assertEquals(state.ContaCorrente.Agencia.Nome, "creditorotativo-ContaCorrente-Agencia-Nome");
    assertEquals(state.Valor, 3);
    assertEquals(1, (int) dispatcherAccess.readFrom("entriesCount"));
    assertEquals(LimiteCreditoAlterado.class.getName(), ((BaseEntry<String>) dispatcherAccess.readFrom("appendedAt", 0)).typeName());
  }

}
