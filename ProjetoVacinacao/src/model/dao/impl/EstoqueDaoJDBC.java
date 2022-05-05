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
import model.dao.EstoqueDao;
import model.entities.Centro;
import model.entities.Endereco;
import model.entities.Estoque;
import model.entities.Lote;
import model.entities.Unidade;

public class EstoqueDaoJDBC implements EstoqueDao{

	private Connection conn;
	
	public EstoqueDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Estoque estoque) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO estoque"+
					"(fk_idUnidade, fk_lote, quantidade)" +
					"VALUES " +
					"(?,?,?)", 
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, estoque.getUnidade().getIdUnidade());
			st.setInt(2, estoque.getLote().getLote());
			st.setInt(3, estoque.getQuantidade());
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
		Lote lote = new Lote();
		lote.setLote(rs.getInt("lote"));
		lote.setVencimento(rs.getDate("data_validade"));
		return lote;
	}
	
	private Endereco instantiateEndereco(ResultSet rs) throws SQLException {
		Endereco end = new Endereco();
		end.setIdEndereco(rs.getInt("idEndereco"));
		end.setCep(rs.getString("cep"));
		end.setPais(rs.getString("pais"));
		end.setEstado(rs.getString("estado"));
		end.setCidade(rs.getString("cidade"));
		end.setBairro(rs.getString("bairro"));
		end.setRua(rs.getString("rua"));
		end.setNumero(rs.getInt("numero"));
		end.setComplemento(rs.getString("complemento"));
		return end;
 	}
	
	private Unidade instantiateUnidade(ResultSet rs, Endereco endereco) throws SQLException{
		Unidade uni = new Unidade();
		uni.setIdUnidade(rs.getInt("idUnidade"));
		uni.setNome(rs.getString("nome"));
		if(rs.getString("centro") == "sim") {
			uni.setCentro(Centro.SIM);
		}
		else {
			uni.setCentro(Centro.NAO);
		}
		uni.setEndereco(endereco);
		return uni;
	}
	
	private Estoque instantiateEstoque(ResultSet rs, Unidade uni, Lote lote) throws SQLException{
		Estoque estoque = new Estoque();
		estoque.setLote(lote);
		estoque.setUnidade(uni);
		estoque.setQuantidade(rs.getInt("quantidade"));
		return estoque;
	}
	
	@Override
	public List<Estoque> fyndByLote(int lote) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT estoque.*,lote.*,unidade*,endereco.* "+
					"FROM"+
					"estoque INNER JOIN lote "+
					"ON estoque.fk_lote = unidade.lote AND"+
					"estoque INNER JOIN lote "+
					"ON estoque.fk_idUnidade = unidade.idUnidade AND"+
					"unidade INNER JOIN endereco "+
					"ON unidade.fk_idEndereco = endereco.idEndereco"+
					"WHERE lote.lote = ?");
			st.setInt(1, lote);
			rs = st.executeQuery();
			
			List<Estoque> estoque = new ArrayList<>();
			while (rs.next()) {
				Lote l = instantiateLote(rs);
				Endereco endereco = instantiateEndereco(rs);
				Unidade uni = instantiateUnidade(rs, endereco);
				Estoque est = instantiateEstoque(rs, uni, l);
				estoque.add(est);
			}
			
			return estoque;
		}
		catch(SQLException e) {
			throw new DbException("Unexpected error! No rows affected!");
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Estoque> fyndByUnidade(int idUnidade) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT estoque.*,lote.*,unidade*,endereco.* "+
					"FROM"+
					"estoque INNER JOIN lote "+
					"ON estoque.fk_lote = unidade.lote AND"+
					"estoque INNER JOIN lote "+
					"ON estoque.fk_idUnidade = unidade.idUnidade AND"+
					"unidade INNER JOIN endereco "+
					"ON unidade.fk_idEndereco = endereco.idEndereco"+
					"WHERE unidade.idUnidade = ?");
			st.setInt(1, idUnidade);
			rs = st.executeQuery();
			
			List<Estoque> estoque = new ArrayList<>();
			while (rs.next()) {
				Lote l = instantiateLote(rs);
				Endereco endereco = instantiateEndereco(rs);
				Unidade uni = instantiateUnidade(rs, endereco);
				Estoque est = instantiateEstoque(rs, uni, l);
				estoque.add(est);
			}
			
			return estoque;
		}
		catch(SQLException e) {
			throw new DbException("Unexpected error! No rows affected!");
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Estoque> listAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT estoque.*,lote.*,unidade*,endereco.* "+
					"FROM"+
					"estoque INNER JOIN lote "+
					"ON estoque.fk_lote = unidade.lote AND"+
					"estoque INNER JOIN lote "+
					"ON estoque.fk_idUnidade = unidade.idUnidade AND"+
					"unidade INNER JOIN endereco "+
					"ON unidade.fk_idEndereco = endereco.idEndereco");
			rs = st.executeQuery();
			
			List<Estoque> estoque = new ArrayList<>();
			while (rs.next()) {
				Lote l = instantiateLote(rs);
				Endereco endereco = instantiateEndereco(rs);
				Unidade uni = instantiateUnidade(rs, endereco);
				Estoque est = instantiateEstoque(rs, uni, l);
				estoque.add(est);
			}
			
			return estoque;
		}
		catch(SQLException e) {
			throw new DbException("Unexpected error! No rows affected!");
		}
		finally {
			DB.closeStatement(st);
		}		
	}

	@Override
	public void update(Estoque estoque) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE estoque "
					+ "SET fk_idUnidade = ?, fk_lote = ?, quantidade = ?"
					+ "WHERE fk_idUnidade = ? and fk_lote = ?");
			st.setInt(1, estoque.getUnidade().getIdUnidade());
			st.setInt(2, estoque.getLote().getLote());
			st.setInt(3, estoque.getQuantidade());
			st.setInt(4, estoque.getUnidade().getIdUnidade());
			st.setInt(5, estoque.getLote().getLote());
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException("Unexpected error! No rows affected!");
		}
		finally {
			DB.closeStatement(st);
		}
	}
}
