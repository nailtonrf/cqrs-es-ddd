package com.banco.contacorrente.infrastructure;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.banco.contacorrente.model.*;

public class AgenciaData {

  public final int Numero;
  public final String Nome;

  public static AgenciaData from(final Agencia agencia) {
    return from(agencia.Numero, agencia.Nome);
  }

  public static AgenciaData from(final int Numero, final String Nome) {
    return new AgenciaData(Numero, Nome);
  }

  public static Set<AgenciaData> from(final Set<Agencia> correspondingObjects) {
    return correspondingObjects == null ? Collections.emptySet() : correspondingObjects.stream().map(AgenciaData::from).collect(Collectors.toSet());
  }

  public static List<AgenciaData> from(final List<Agencia> correspondingObjects) {
    return correspondingObjects == null ? Collections.emptyList() : correspondingObjects.stream().map(AgenciaData::from).collect(Collectors.toList());
  }

  private AgenciaData (final int Numero, final String Nome) {
    this.Numero = Numero;
    this.Nome = Nome;
  }

  public Agencia toAgencia() {
    return Agencia.from(Numero, Nome);
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
