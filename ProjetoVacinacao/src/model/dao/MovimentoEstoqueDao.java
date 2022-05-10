package model.dao;

import java.util.List;

import model.entities.MovimentoEstoque;

public interface MovimentoEstoqueDao {

	public void insertMovimentoComPessoa(MovimentoEstoque movimentoEstoque);
	public void insert(MovimentoEstoque movimentoEstoque);
	public void insertTransasao(MovimentoEstoque movimentoEstoque);
	public void transacao(MovimentoEstoque origem, MovimentoEstoque destino);
	public List<MovimentoEstoque> relatorioApliacao(int id);
	public List<MovimentoEstoque> relatorioResumoAplicacao(int id);
	public List<MovimentoEstoque> relatorioEsquemaIncompleto();
	public List<MovimentoEstoque> listAll();
	
}