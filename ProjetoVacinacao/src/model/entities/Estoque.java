package model.entities;

public class Estoque {

	private Unidade unidade;
	private Lote lote;
	private Integer quantidade;
	
	public Estoque() {}
	
	public Estoque(Unidade unidade, Lote lote, Integer quantidade) {
		setUnidade(unidade);
		setLote(lote);
		setQuantidade(quantidade);
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
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	@Override
	public String toString() {
		return "Estoque: "+
				"\nUnidade: "+getUnidade()+
				"\nLote: "+getLote()+
				"\nQuantidade: "+getQuantidade();
	}
}