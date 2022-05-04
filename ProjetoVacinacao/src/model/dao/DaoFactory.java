package model.dao;

import db.DB;
import model.dao.impl.EnderecoDaoJDBC;
import model.dao.impl.EstoqueDaoJDBC;
import model.dao.impl.LoteDaoJDBC;
import model.dao.impl.MovimentoEstoqueDaoJDBC;
import model.dao.impl.PessoaDaoJDBC;
import model.dao.impl.UnidadeDaoJDBC;

public class DaoFactory {

	public static EnderecoDao createEnderecoDao() {
		return new EnderecoDaoJDBC(DB.getConnection());
	}
	
	public static LoteDao createLoteDao() {
		return new LoteDaoJDBC(DB.getConnection());
	}
	
	public static PessoaDao createPessoaDao() {
		return new PessoaDaoJDBC(DB.getConnection());
	}
	
	public static UnidadeDao createUnidadeDaoDao() {
		return new UnidadeDaoJDBC(DB.getConnection());
	}
	
	public static EstoqueDao createEstoqueDao() {
		return new EstoqueDaoJDBC(DB.getConnection());
	}
	
	public static MovimentoEstoqueDao createMovimentoEstoqueDao() {
		return new MovimentoEstoqueDaoJDBC(DB.getConnection());
	}
}