package model.dao;

import java.util.List;

import model.entities.Endereco;

public interface EnderecoDao {

	public void insert(Endereco obj);
	public Endereco fyndById(int id);
	public List<Endereco> listAll();
	public List<Endereco> listByCep(String cep);
	public List<Endereco> listByBairro(String bairro);
	
}