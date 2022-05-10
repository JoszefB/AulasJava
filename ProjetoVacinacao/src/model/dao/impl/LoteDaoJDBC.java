package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.LoteDao;
import model.entities.Lote;

public class LoteDaoJDBC implements LoteDao {

	private Connection conn;
	
	public LoteDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Lote lote) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO lote"+
					"(lote, data_validade)" +
					"VALUES " +
					"(?,?)", 
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, lote.getLote());
			st.setDate(2, new java.sql.Date(lote.getVencimento().getTime()));
			
			st.executeUpdate();
	
		}
		catch(SQLException e) {
			throw new DbException("Unexpected error! No rows affected!");
		}
		finally {
			DB.closeStatement(st);
		}
		
	}
	
	private Lote instantiateLote(ResultSet rs) throws SQLException {
		//Lote lote = new Lote();
		//lote.setLote(rs.getInt("lote"));
		//lote.setVencimento(rs.getDate("data_validade"));
		Lote lote = new Lote(rs.getInt("lote"), rs.getDate("data_validade"));
		return lote;
	}

	@Override
	public Lote fyndById(int id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM lote where lote.lote = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Lote lote = instantiateLote(rs);
				return lote;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Lote> listAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM lote");
			rs = st.executeQuery();
			
			List<Lote> list = new ArrayList<>();
			
			while(rs.next()) {
				Lote lote = instantiateLote(rs);
				list.add(lote);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public List<Lote> listByVencimento() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM lote ORDER BY data_validade");
			rs = st.executeQuery();
			
			List<Lote> list = new ArrayList<>();
			
			while(rs.next()) {
				Lote lote = instantiateLote(rs);
				list.add(lote);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}