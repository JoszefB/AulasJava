package model.dao;

import java.util.List;

import model.entities.Lote;

public interface LoteDao {

	public void insert(Lote lote);
	public Lote fyndById(int id);
	public List<Lote> listAll();
	public List<Lote> listByVencimento();
	
}