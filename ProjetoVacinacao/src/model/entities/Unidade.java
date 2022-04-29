package model.entities;

import java.util.ArrayList;
import java.util.List;

public class Unidade {
	
	private String nome;
	private Endereco endereco;
	private List<Vacina> vacinas = new ArrayList<>();
	
	public Unidade () {}
	
	public Unidade(String nome, Endereco endereco) {
		setNome(nome);
		setEndereco(endereco);
	}
	
	public Unidade(String nome, Endereco endereco, Vacina vacinas) {
		setNome(nome);
		setEndereco(endereco);
		addVacina(vacinas);
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

	public List<Vacina> getVacinas() {
		return vacinas;
	}
	
	public void addVacina(Vacina vac) {
		vacinas.add(vac);
	}
	
	public void removeVacina(Vacina vac) {
		vacinas.remove(vac);
	}

	@Override
	public String toString() {
		return "Unidade: "+
				"\nNome:"+getNome()+
				"\n"+getEndereco()+
				"\n"+getVacinas();
	}
}