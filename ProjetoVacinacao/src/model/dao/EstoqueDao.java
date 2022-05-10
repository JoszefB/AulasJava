package model.dao;

import java.util.List;

import model.entities.Estoque;

public interface EstoqueDao {

	public void insert(Estoque estoque);
	public List<Estoque> relatorioEstoque(int id);
	public List<Estoque> listAll();
	public void update(Estoque estoque);

}