package com.banco.contacorrente.model.contacorrente;

import io.vlingo.xoom.actors.World;
import io.vlingo.xoom.actors.testkit.AccessSafely;
import io.vlingo.xoom.symbio.BaseEntry;
import com.banco.contacorrente.infrastructure.persistence.MockDispatcher;
import com.banco.contacorrente.model.*;
import com.banco.contacorrente.infrastructure.persistence.ContaCorrenteStateAdapter;
import io.vlingo.xoom.lattice.model.stateful.StatefulTypeRegistry;
import io.vlingo.xoom.lattice.model.stateful.StatefulTypeRegistry.Info;
import io.vlingo.xoom.symbio.store.state.StateStore;
import io.vlingo.xoom.symbio.store.state.inmemory.InMemoryStateStoreActor;
import io.vlingo.xoom.symbio.StateAdapterProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class ContaCorrenteEntityTest {

  private World world;
  private StateStore store;
  private MockDispatcher dispatcher;
  private ContaCorrente contaCorrente;

  @BeforeEach
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void setUp(){
    dispatcher = new MockDispatcher();
    world = World.startWithDefaults("test-stateful");
    new StateAdapterProvider(world).registerAdapter(ContaCorrenteState.class, new ContaCorrenteStateAdapter());
    store = world.actorFor(StateStore.class, InMemoryStateStoreActor.class, Collections.singletonList(dispatcher));
    new StatefulTypeRegistry(world).register(new Info(store, ContaCorrenteState.class, ContaCorrenteState.class.getSimpleName()));
    contaCorrente = world.actorFor(ContaCorrente.class, ContaCorrenteEntity.class, "#1");
  }

  private static final NumeroConta NUMERO_FOR_CRIAR_CONTA_CORRENTE_TEST = NumeroConta.from(1, Agencia.from(2, "contacorrente-Numero-Agencia-Nome"));
  private static final double LIMITE_CREDITO_FOR_CRIAR_CONTA_CORRENTE_TEST = 3;

  @Test
  public void CriarContaCorrente() {
    final AccessSafely dispatcherAccess = dispatcher.afterCompleting(1);
    final ContaCorrenteState state = contaCorrente.CriarContaCorrente(NUMERO_FOR_CRIAR_CONTA_CORRENTE_TEST, LIMITE_CREDITO_FOR_CRIAR_CONTA_CORRENTE_TEST).await();

    assertEquals(state.Numero.Numero, 1);
    assertEquals(state.Numero.Agencia.Numero, 2);
    assertEquals(state.Numero.Agencia.Nome, "contacorrente-Numero-Agencia-Nome");
    assertEquals(state.LimiteCredito, 3);
    assertEquals(1, (int) dispatcherAccess.readFrom("entriesCount"));
    assertEquals(ContaCorrenteCriada.class.getName(), ((BaseEntry<String>) dispatcherAccess.readFrom("appendedAt", 0)).typeName());
  }

  private static final NumeroConta NUMERO_FOR_BLOQUEAR_CONTA_CORRENTE_TEST = NumeroConta.from(2, Agencia.from(3, "updated-contacorrente-Numero-Agencia-Nome"));

  @Test
  public void BloquearContaCorrente() {
    _createEntity();
    final AccessSafely dispatcherAccess = dispatcher.afterCompleting(1);
    final ContaCorrenteState state = contaCorrente.BloquearContaCorrente(NUMERO_FOR_BLOQUEAR_CONTA_CORRENTE_TEST).await();

    assertEquals(state.LimiteCredito, 3);
    assertEquals(state.Numero.Numero, 2);
    assertEquals(state.Numero.Agencia.Numero, 3);
    assertEquals(state.Numero.Agencia.Nome, "updated-contacorrente-Numero-Agencia-Nome");
    assertEquals(2, (int) dispatcherAccess.readFrom("entriesCount"));
    assertEquals(ContaCorrenteBloqueada.class.getName(), ((BaseEntry<String>) dispatcherAccess.readFrom("appendedAt", 1)).typeName());
  }

  private static final NumeroConta NUMERO_FOR_DESBLOQUEAR_CONTA_CORRENTE_TEST = NumeroConta.from(2, Agencia.from(3, "updated-contacorrente-Numero-Agencia-Nome"));

  @Test
  public void DesbloquearContaCorrente() {
    _createEntity();
    final AccessSafely dispatcherAccess = dispatcher.afterCompleting(1);
    final ContaCorrenteState state = contaCorrente.DesbloquearContaCorrente(NUMERO_FOR_DESBLOQUEAR_CONTA_CORRENTE_TEST).await();

    assertEquals(state.LimiteCredito, 3);
    assertEquals(state.Numero.Numero, 2);
    assertEquals(state.Numero.Agencia.Numero, 3);
    assertEquals(state.Numero.Agencia.Nome, "updated-contacorrente-Numero-Agencia-Nome");
    assertEquals(2, (int) dispatcherAccess.readFrom("entriesCount"));
    assertEquals(ContaCorrenteDesbloqueada.class.getName(), ((BaseEntry<String>) dispatcherAccess.readFrom("appendedAt", 1)).typeName());
  }

  private static final NumeroConta NUMERO_FOR_ATUALIZAR_LIMITE_CREDITO_ROTATIVO_TEST = NumeroConta.from(2, Agencia.from(3, "updated-contacorrente-Numero-Agencia-Nome"));
  private static final double LIMITE_CREDITO_FOR_ATUALIZAR_LIMITE_CREDITO_ROTATIVO_TEST = 4;

  @Test
  public void AtualizarLimiteCreditoRotativo() {
    final ContaCorrenteState state = contaCorrente.AtualizarLimiteCreditoRotativo(NUMERO_FOR_ATUALIZAR_LIMITE_CREDITO_ROTATIVO_TEST, LIMITE_CREDITO_FOR_ATUALIZAR_LIMITE_CREDITO_ROTATIVO_TEST).await();

    assertEquals(state.Numero.Numero, 2);
    assertEquals(state.Numero.Agencia.Numero, 3);
    assertEquals(state.Numero.Agencia.Nome, "updated-contacorrente-Numero-Agencia-Nome");
    assertEquals(state.LimiteCredito, 4);
  }

  private static final NumeroConta NUMERO_FOR_ENTITY_CREATION = NumeroConta.from(1, Agencia.from(2, "contacorrente-Numero-Agencia-Nome"));
  private static final double LIMITE_CREDITO_FOR_ENTITY_CREATION = 3;

  private void _createEntity() {
    contaCorrente.CriarContaCorrente(NUMERO_FOR_ENTITY_CREATION, LIMITE_CREDITO_FOR_ENTITY_CREATION).await();
  }
}
