package com.banco.contacorrente.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public final class NumeroConta {

  public final int Numero;
  public final Agencia Agencia;

  public static NumeroConta from(final int Numero, final Agencia Agencia) {
    return new NumeroConta(Numero, Agencia);
  }

  private NumeroConta (final int Numero, final Agencia Agencia) {
    this.Numero = Numero;
    this.Agencia = Agencia;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(31, 17)
              .append(Numero)
              .append(Agencia)
              .toHashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    NumeroConta another = (NumeroConta) other;
    return new EqualsBuilder()
              .append(this.Numero, another.Numero)
              .append(this.Agencia, another.Agencia)
              .isEquals();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
              .append("Numero", Numero)
              .append("Agencia", Agencia)
              .toString();
  }
}
