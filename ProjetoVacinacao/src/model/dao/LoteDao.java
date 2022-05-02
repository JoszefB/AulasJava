package model.dao;

import java.util.List;

import model.entities.Lote;

public interface LoteDao {

	public void insert(Lote lote);
	public List<Lote> listAll();
	public Lote findByLote();
	
}
