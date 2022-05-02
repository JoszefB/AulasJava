package model.entities;


public class Unidade {
	
	private Integer idUnidade;
	private String nome;
	private Endereco endereco;
	private Centro centro;
	
	public Unidade () {}
	
	public Unidade(String nome, Endereco endereco, Centro centro) {
		setNome(nome);
		setEndereco(endereco);
		setCentro(centro);
	}

	public Integer getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Integer idUnidade) {
		this.idUnidade = idUnidade;
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Unidade: "+
				"\nNome:"+getNome()+
				"\nCentro:"+getCentro()+
				"\n"+getEndereco();
	}
}