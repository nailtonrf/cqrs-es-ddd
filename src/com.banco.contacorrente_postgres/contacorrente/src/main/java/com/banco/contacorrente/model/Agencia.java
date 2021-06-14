package com.banco.contacorrente.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public final class Agencia {

  public final int Numero;
  public final String Nome;

  public static Agencia from(final int Numero, final String Nome) {
    return new Agencia(Numero, Nome);
  }

  private Agencia (final int Numero, final String Nome) {
    this.Numero = Numero;
    this.Nome = Nome;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(31, 17)
              .append(Numero)
              .append(Nome)
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
    Agencia another = (Agencia) other;
    return new EqualsBuilder()
              .append(this.Numero, another.Numero)
              .append(this.Nome, another.Nome)
              .isEquals();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
              .append("Numero", Numero)
              .append("Nome", Nome)
              .toString();
  }
}
