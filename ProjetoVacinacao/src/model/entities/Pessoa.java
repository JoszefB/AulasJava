package model.entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pessoa {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private String nome;
	private String cpf;
	private Endereco endereco;
	private List<Unidade> unidade = new ArrayList<>();
	private Integer dose;
	private Date dataUltimaDose;
	
	public Pessoa() {}

	public Pessoa(String nome, String cpf, Endereco endereco, Unidade unidade, Date dataUltimaDose) {
		setNome(nome);
		setCpf(cpf);
		setEndereco(endereco);
		addUnidade(unidade);
		setDataUltimaDose(dataUltimaDose);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getUnidade() {
		String list = "";
		for(Unidade uni: unidade) {
			list = "\nUnidade: "+uni.getNome()+
					"Endereco: "+uni.getEndereco();
		}
		return list;
	}

	public void addUnidade(Unidade unidade) {
		this.unidade.add(unidade);
	}

	public Integer getDose() {
		return dose;
	}

	public void setDose() {
		dose++;
	}

	public Date getDataUltimaDose() {
		return dataUltimaDose;
	}

	public void setDataUltimaDose(Date dataUltimaDose) {
		this.dataUltimaDose = dataUltimaDose;
	}

	public boolean isEsquemaCompleto() {
		if(dose == 3)
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "Pessoa:"+
				"\nNome: "+getNome()+
				"\nCPF: "+getCpf()+
				"\nEnreco: "+getEndereco()+
				"\nUnidade(s) onde foi vacinado: "+getUnidade()+
				"\nDose: "+getDose()+
				"\nData da ultima dose: "+sdf.format(getDataUltimaDose());
	}
}
