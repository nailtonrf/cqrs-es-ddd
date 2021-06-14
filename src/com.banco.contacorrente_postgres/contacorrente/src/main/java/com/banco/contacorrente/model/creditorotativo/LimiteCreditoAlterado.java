package com.banco.contacorrente.model.creditorotativo;

import io.vlingo.xoom.common.version.SemanticVersion;
import io.vlingo.xoom.lattice.model.IdentifiedDomainEvent;

import com.banco.contacorrente.model.*;

/**
 * See
 * <a href="https://docs.vlingo.io/xoom-lattice/entity-cqrs#commands-domain-events-and-identified-domain-events">
 *   Commands, Domain Events, and Identified Domain Events
 * </a>
 */
public final class LimiteCreditoAlterado extends IdentifiedDomainEvent {

  public final String id;
  public final NumeroConta ContaCorrente;
  public final double Valor;

  public LimiteCreditoAlterado(final CreditoRotativoState state) {
    super(SemanticVersion.from("1.0.0").toValue());
    this.id = state.id;
    this.ContaCorrente = state.ContaCorrente;
    this.Valor = state.Valor;
  }

  @Override
  public String identity() {
    return id;
  }
}
