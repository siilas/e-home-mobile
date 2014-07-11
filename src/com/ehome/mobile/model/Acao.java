package com.ehome.mobile.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Acao  implements Serializable {

	private static final long serialVersionUID = -7106476315251597462L;
	
	private long acaoCodigo;
    private String acaoDescricao;
    private Boolean acaoAtivo;
    private String acaoComando;
    private String acaoIcone;
    private Boolean acaoMaster;
    
    public Acao() {
    }

	public Long getAcaoCodigo() {
		return acaoCodigo;
	}

	public void setAcaoCodigo(long acaoCodigo) {
		this.acaoCodigo = acaoCodigo;
	}

	public String getAcaoDescricao() {
		return acaoDescricao;
	}

	public void setAcaoDescricao(String acaoDescricao) {
		this.acaoDescricao = acaoDescricao;
	}

	public Boolean getAcaoAtivo() {
		return acaoAtivo;
	}

	public void setAcaoAtivo(Boolean acaoAtivo) {
		this.acaoAtivo = acaoAtivo;
	}

	public String getAcaoComando() {
		return acaoComando;
	}

	public void setAcaoComando(String acaoComando) {
		this.acaoComando = acaoComando;
	}

	public String getAcaoIcone() {
		return acaoIcone;
	}

	public void setAcaoIcone(String acaoIcone) {
		this.acaoIcone = acaoIcone;
	}

	public Boolean getAcaoMaster() {
		return acaoMaster;
	}

	public void setAcaoMaster(Boolean acaoMaster) {
		this.acaoMaster = acaoMaster;
	}
    
	@Override
    public int hashCode() {
    	return new HashCodeBuilder()
    			.append(acaoCodigo)
    			.append(acaoDescricao)
    			.append(acaoAtivo)
    			.append(acaoComando)
    			.append(acaoIcone)
    			.append(acaoMaster)
    			.toHashCode();
    }
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Acao)) {
			return false;
		}
		
		Acao other = (Acao) object;
		
		return new EqualsBuilder()
				.append(acaoCodigo, other.acaoCodigo)
				.append(acaoDescricao, other.acaoDescricao)
				.append(acaoAtivo, other.acaoAtivo)
				.append(acaoComando, other.acaoComando)
				.append(acaoIcone, other.acaoIcone)
				.append(acaoMaster, other.acaoMaster)
				.isEquals();
	}
	
	@Override
	public String toString() {
		return acaoDescricao;
	}
}