package model.dao;

import java.util.List;

import model.entities.Endereco;
import model.entities.Unidade;

public interface UnidadeDao {

	public void insert(Unidade unidade);
	public List<Unidade> listAll();
	public Unidade findById(int id);
	public List<Unidade> findByEndereco(Endereco endereco);
	
}