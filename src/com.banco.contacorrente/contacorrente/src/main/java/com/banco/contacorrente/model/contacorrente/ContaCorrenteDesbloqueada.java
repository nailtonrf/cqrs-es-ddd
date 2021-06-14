package com.banco.contacorrente.model.contacorrente;

import io.vlingo.xoom.common.version.SemanticVersion;
import io.vlingo.xoom.lattice.model.IdentifiedDomainEvent;

import com.banco.contacorrente.model.*;

/**
 * See
 * <a href="https://docs.vlingo.io/xoom-lattice/entity-cqrs#commands-domain-events-and-identified-domain-events">
 *   Commands, Domain Events, and Identified Domain Events
 * </a>
 */
public final class ContaCorrenteDesbloqueada extends IdentifiedDomainEvent {

  public final String id;
  public final NumeroConta Numero;

  public ContaCorrenteDesbloqueada(final ContaCorrenteState state) {
    super(SemanticVersion.from("1.0.0").toValue());
    this.id = state.id;
    this.Numero = state.Numero;
  }

  @Override
  public String identity() {
    return id;
  }
}
