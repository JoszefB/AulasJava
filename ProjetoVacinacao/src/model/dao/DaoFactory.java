package model.dao;

import db.DB;
import model.dao.impl.EnderecoDaoJDBC;

public class DaoFactory {

	public static EnderecoDao createEnderecoDao() {
		return new EnderecoDaoJDBC(DB.getConnection());
	}
	
}
