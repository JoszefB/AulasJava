package model.entities;

public class Pessoa {

	
	private Integer idPessoa;
	private String nome;
	private String cpf;
	private Endereco endereco;
	
	public Pessoa() {}

	public Pessoa(String nome, String cpf, Endereco endereco) {
		setNome(nome);
		setCpf(cpf);
		setEndereco(endereco);
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
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

	@Override
	public String toString() {
		return "Pessoa:"+
				"\nNome: "+getNome()+
				"\nCPF: "+getCpf()+
				"\nEnreco: "+getEndereco();
	}
}
