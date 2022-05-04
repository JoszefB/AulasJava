package model.dao;

import java.util.Date;
import java.util.List;

import model.entities.MovimentoEstoque;

public interface MovimentoEstoqueDao {

	public void insert(MovimentoEstoque movimentoEstoque);
	public List<MovimentoEstoque> listAll();
	public List<MovimentoEstoque> listByDate();
	public List<MovimentoEstoque> fyndByDate(Date data);
	public MovimentoEstoque fyndByKey(MovimentoEstoque movimento);
	
}
