package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MovimentoEstoque {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private Integer sequencia;
	private Unidade unidade;
	private Lote lote;
	private Pessoa pessoa;
	private TipoTransacao tipoTransacao;
	private Integer quantidade;
	private Date dataMovimentacao;

	public MovimentoEstoque(SimpleDateFormat sdf, Integer sequencia, Unidade unidade, Lote lote, Pessoa pessoa,
			TipoTransacao tipoTransacao, Integer quantidade, Date dataMovimentacao) {
		this.sdf = sdf;
		this.sequencia = sequencia;
		this.unidade = unidade;
		this.lote = lote;
		this.pessoa = pessoa;
		this.tipoTransacao = tipoTransacao;
		this.quantidade = quantidade;
		this.dataMovimentacao = dataMovimentacao;
	}

	public Integer getSequencia() {
		return sequencia;
	}
	
	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}
	
	public Unidade getUnidade() {
		return unidade;
	}
	
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	
	public Lote getLote() {
		return lote;
	}
	
	public void setLote(Lote lote) {
		this.lote = lote;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}
	
	public void setTipoTransacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Date getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(Date dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	@Override
	public String toString() {
		return "Movimento :"+
				"\nUnidade: "+getUnidade()+
				"\nLote:"+getLote()+
				"\nSequencia: "+getSequencia()+
				"\nTipo: "+getTipoTransacao()+
				"\nPessoa: "+getPessoa()+
				"\nQuantidade"+getQuantidade()+
				"\nData: "+sdf.format(getDataMovimentacao());
	}
}
