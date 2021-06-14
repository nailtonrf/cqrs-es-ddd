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

public class NumeroContaData {

  public final int Numero;
  public final AgenciaData Agencia;

  public static NumeroContaData from(final NumeroConta numeroConta) {
    final AgenciaData agencia = numeroConta.agencia != null ? AgenciaData.from(numeroConta.agencia) : null;
    return from(numeroConta.Numero, Agencia);
  }

  public static NumeroContaData from(final int Numero, final AgenciaData Agencia) {
    return new NumeroContaData(Numero, Agencia);
  }

  public static Set<NumeroContaData> from(final Set<NumeroConta> correspondingObjects) {
    return correspondingObjects == null ? Collections.emptySet() : correspondingObjects.stream().map(NumeroContaData::from).collect(Collectors.toSet());
  }

  public static List<NumeroContaData> from(final List<NumeroConta> correspondingObjects) {
    return correspondingObjects == null ? Collections.emptyList() : correspondingObjects.stream().map(NumeroContaData::from).collect(Collectors.toList());
  }

  private NumeroContaData (final int Numero, final AgenciaData Agencia) {
    this.Numero = Numero;
    this.Agencia = Agencia;
  }

  public NumeroConta toNumeroConta() {
    final Agencia Agencia = Agencia.from(this.Agencia.Numero, this.Agencia.Nome);
    return NumeroConta.from(Numero, Agencia);
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
