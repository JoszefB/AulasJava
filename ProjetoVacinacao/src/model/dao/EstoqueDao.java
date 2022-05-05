package model.dao;

import java.util.List;

import model.entities.Estoque;

public interface EstoqueDao {

	public void insert(Estoque estoque);
	public List<Estoque> fyndByLote (int lote);
	public List<Estoque> fyndByUnidade (int idUnidade);
	public List<Estoque> listAll();
	public void update(Estoque estoque);
	
}