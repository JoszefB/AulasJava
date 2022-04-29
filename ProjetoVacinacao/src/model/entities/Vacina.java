package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Vacina {

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private Integer lote;
	private Date vencimento;
	private Integer quantidade;
	
	public Vacina() {
	}

	public Vacina(Integer lote, Date vencimento, Integer quantidade) {
		setLote(lote);
		setVencimento(vencimento);
		setQuantidade(quantidade);
	}

	public Integer getLote() {
		return lote;
	}

	public void setLote(Integer lote) {
		this.lote = lote;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "Vacina :"+
				"\nLote:"+getLote()+
				"\nValido até: "+sdf.format(getVencimento())+
				"\nQuantidade: "+getQuantidade();
	}
}