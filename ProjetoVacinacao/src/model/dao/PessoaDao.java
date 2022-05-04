package model.dao;

import java.util.List;

import model.entities.Endereco;
import model.entities.Pessoa;

public interface PessoaDao {

	public void insert(Pessoa pessoa);
	public List<Pessoa> listAll();
	public Pessoa findById(int id);
	public Pessoa findByCPF(String cpf);
	public List<Pessoa> findByEndereco(Endereco endereco);
	
}