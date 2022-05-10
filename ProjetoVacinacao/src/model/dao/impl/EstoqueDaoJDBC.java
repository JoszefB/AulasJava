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


	@Override
	public List<Estoque> listAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT unidade.idUnidade, unidade.nome, unidade.centro, unidade.fk_idEndereco,"
					+ " endereco.idEndereco, endereco.cidade, endereco.bairro, endereco.numero, endereco.rua, endereco.cep, endereco.pais, endereco.estado, endereco.complemento, "
					+ " lote.lote, lote.data_validade, "
					+ " estoque.quantidade, estoque.fk_idUnidade, estoque.fk_lote "
					+ " FROM estoque, lote, unidade, endereco "
					+ " WHERE "
					+ " estoque.fk_lote = lote.lote AND "
					+ " estoque.fk_idUnidade = unidade.idUnidade AND "
					+ " endereco.idEndereco = unidade.fk_idEndereco;");
			rs = st.executeQuery();
			
			List<Estoque> estoques = new ArrayList<Estoque>();
			
			while(rs.next()) {
				Endereco end = instantiateEndereco(rs);
				Unidade uni = instantiateUnidade(rs, end);
				Lote lote = instantiateLote(rs);
				Estoque es = new Estoque(uni, lote, rs.getInt("quantidade"));
				estoques.add(es);				
			}
			
			return estoques;
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
	public List<Estoque> relatorioEstoque(int id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT"
					+ "unidade.idUnidade, unidade.nome, unidade.centro, unidade.fk_idEndereco,"
					+ "endereco.idEndereco, endereco.cidade, endereco.bairro, endereco.numero, endereco.rua, endereco.cep, endereco.pais, endereco.estado, endereco.complemento,"
					+ "lote.lote, lote.data_validade,"
					+ "estoque.quantidade, estoque.fk_idUnidade, estoque.fk_lote"
					+ "FROM estoque, lote, unidade, endereco"
					+ "WHERE"
					+ "estoque.fk_lote = lote.lote AND"
					+ "estoque.fk_idUnidade = unidade.idUnidade AND"
					+ "endereco.idEndereco = unidade.fk_idEndereco AND"
					+ "estoque.fk_idUnidade = ?;");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			List<Estoque> estoques = new ArrayList<Estoque>();
			
			while(rs.next()) {
				Endereco endereco = instantiateEndereco(rs);
				Unidade unidade = instantiateUnidade(rs, endereco);
				Lote lote = instantiateLote(rs);
				Estoque estoque = new Estoque(unidade, lote, rs.getInt("quantidade"));
				estoques.add(estoque);
			}
			
			return estoques;
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
	public void update(Estoque estoque) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					" UPDATE estoque "
					+ " SET quantidade = ? "
					+ " WHERE fk_idUnidade = ? and fk_lote = ? ");
			st.setInt(1, estoque.getQuantidade());
			st.setInt(2, estoque.getUnidade().getIdUnidade());
			st.setInt(3, estoque.getLote().getLote());
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException("Unexpected error! No rows affected!");
		}
		finally {
			DB.closeStatement(st);
		}
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
		if(rs.getString("centro").equals("sim")) {
			uni.setCentro(Centro.SIM);
		}
		else {
			uni.setCentro(Centro.NAO);
		}
		uni.setEndereco(endereco);
		return uni;
	}
	
	private Lote instantiateLote(ResultSet rs) throws SQLException {
		Lote lote = new Lote(rs.getInt("lote"), rs.getDate("data_validade"));
		return lote;
	}

}