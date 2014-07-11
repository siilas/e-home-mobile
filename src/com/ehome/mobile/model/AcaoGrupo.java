package com.ehome.mobile.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Classe que recebe os status da casa
 * 
 * @author Silas M. Ferreira
 *
 */
public class AcaoGrupo implements Serializable {

	private static final long serialVersionUID = 973106656972195399L;
	
	private long agrCodigo;
    private String agrNome;
    private Boolean agrAtivo;
    private Byte agrTipo;
    private String agrIcone;
    private List<Acao> acoes;
    
    public AcaoGrupo() {
    }

	public long getAgrCodigo() {
		return agrCodigo;
	}

	public void setAgrCodigo(long agrCodigo) {
		this.agrCodigo = agrCodigo;
	}

	public String getAgrNome() {
		return agrNome;
	}

	public void setAgrNome(String agrNome) {
		this.agrNome = agrNome;
	}

	public Boolean getAgrAtivo() {
		return agrAtivo;
	}

	public void setAgrAtivo(Boolean agrAtivo) {
		this.agrAtivo = agrAtivo;
	}

	public Byte getAgrTipo() {
		return agrTipo;
	}

	public void setAgrTipo(Byte agrTipo) {
		this.agrTipo = agrTipo;
	}

	public String getAgrIcone() {
		return agrIcone;
	}

	public void setAgrIcone(String agrIcone) {
		this.agrIcone = agrIcone;
	}

	public List<Acao> getAcoes() {
		return acoes;
	}

	public void setAcoes(List<Acao> acoes) {
		this.acoes = acoes;
	}
    
	@Override
    public int hashCode() {
    	return new HashCodeBuilder()
    			.append(agrCodigo)
    			.append(agrNome)
    			.append(agrAtivo)
    			.append(agrTipo)
    			.append(agrIcone)
    			.append(acoes)
    			.toHashCode();
    }
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AcaoGrupo)) {
			return false;
		}
		
		AcaoGrupo other = (AcaoGrupo) object;
		
		return new EqualsBuilder()
				.append(agrCodigo, other.agrCodigo)
				.append(agrNome, other.agrNome)
				.append(agrAtivo, other.agrAtivo)
				.append(agrTipo, other.agrTipo)
				.append(agrIcone, other.agrIcone)
				.append(acoes, other.acoes)
				.isEquals();
	}
	
	@Override
	public String toString() {
		return agrNome;
	}
}