package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Lote {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private Integer lote;
	private Date vencimento;
	
	public Lote() {
	}

	public Lote(Integer lote, Date vencimento) {
		setLote(lote);
		setVencimento(vencimento);
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
	
	@Override
	public String toString() {
		return "Vacina :"+
				"\nLote:"+getLote()+
				"\nValido até: "+sdf.format(getVencimento());
	}
}