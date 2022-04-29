package model.entities;

public class Endereco {
	
	private String cep;
	private String pais;
	private String estado;
	private String cidade;
	private String bairro;
	private String rua;;
	private Integer numero;
	private String complemento;
	
	public Endereco() {}

	public Endereco(String cep, String pais, String estado, String cidade, String bairro, String rua, Integer numero,
			String complemento) {
		setCep(cep);
		setPais(pais);
		setEstado(estado);
		setCidade(cidade);
		setBairro(bairro);
		setRua(rua);
		setNumero(numero);
		setComplemento(complemento);
	}
	
	public Endereco(String cep, String bairro, String rua, Integer numero, String complemento) {
		setCep(cep);
		setPais("Brasil");
		setEstado("Rio Grande do Sul");
		setCidade("São Leopoldo");
		setBairro(bairro);
		setRua(rua);
		setNumero(numero);
		setComplemento(complemento);
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Override
	public String toString() {
		return "Endereco: "+
				"\nCEP: "+getCep()+
				"\nPais: "+getPais()+
				"\nEstado: "+getEstado()+
				"\nCidade: "+getCidade()+
				"\nBairro: "+getBairro()+
				"\nRua: "+getRua()+
				"\nNumero: "+getNumero()+
				"\nComplemento "+getComplemento();
	}
	
}