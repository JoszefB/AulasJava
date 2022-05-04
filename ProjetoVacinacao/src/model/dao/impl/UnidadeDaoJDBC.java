package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.UnidadeDao;
import model.entities.Centro;
import model.entities.Endereco;
import model.entities.Unidade;

public class UnidadeDaoJDBC implements UnidadeDao{
	
	private Connection conn;
	
	public UnidadeDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Unidade unidade) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO unidade"+
					"(nome, centro, fk_idEndereco)"+
					"Values"+
					"(?,?,?)");
			st.setString(1, unidade.getNome());
			if(unidade.getCentro() == Centro.SIM) {
				st.setString(2, "sim");
			}
			else {
				st.setString(2, "não");
			}
			st.setInt(3, unidade.getEndereco().getIdEndereco());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					unidade.setIdUnidade(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
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
		if(rs.getString("centro") == "sim") {
			uni.setCentro(Centro.SIM);
		}
		else {
			uni.setCentro(Centro.NAO);
		}
		uni.setEndereco(endereco);
		return uni;
	}

	@Override
	public List<Unidade> listAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT unidade.*,endereco.* "+
					"FROM unidade INNER JOIN endereco "+
					"ON unidade.fk_idEndereco = endereco.idEndereco ");
			rs = st.executeQuery();
			
			List<Unidade> uni = new ArrayList<>();
			
			while(rs.next()) {
				Endereco end = instantiateEndereco(rs);
				Unidade u = instantiateUnidade(rs, end);
				uni.add(u);
			}
			
			return uni;
		}
		catch(SQLException e) {
			throw new DbException("Unexpected error! No rows affected!");
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Unidade findById(int id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT unidade.*,endereco.* "+
					"FROM unidade INNER JOIN endereco "+
					"ON unidade.fk_idEndereco = endereco.idEndereco "+
					"WHERE unidade.idUnidade = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			Endereco end = instantiateEndereco(rs);
			Unidade u = instantiateUnidade(rs, end);
			
			return u;
		}
		catch(SQLException e) {
			throw new DbException("Unexpected error! No rows affected!");
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Unidade> findByEndereco(Endereco endereco) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT unidade.*,endereco.* "+
					"FROM unidade INNER JOIN endereco "+
					"ON unidade.fk_idEndereco = endereco.idEndereco "+
					"WHERE "+
					"endereco.cep = ?, "+
					"endereco.pais = ?, "+
					"endereco.estado = ?, "+
					"endereco.cidade = ?, "+
					"endereco.bairro = ? , "+ 
					"endereco.rua = ?, "+
					"endereco.numero = ? , "+
					"endereco.complemento = ?");
			st.setString(1, endereco.getCep());
			st.setString(2, endereco.getPais());
			st.setString(3, endereco.getEstado());
			st.setString(4, endereco.getCidade());
			st.setString(5, endereco.getBairro());
			st.setString(6, endereco.getRua());
			st.setInt(7, endereco.getNumero());
			st.setString(8, endereco.getComplemento());
			rs = st.executeQuery();

			List<Unidade> uni = new ArrayList<>();
			
			while(rs.next()) {
				Endereco end = instantiateEndereco(rs);
				Unidade u = instantiateUnidade(rs, end);
				uni.add(u);
			}
			
			return uni;
		}
		catch(SQLException e) {
			throw new DbException("Unexpected error! No rows affected!");
		}
		finally {
			DB.closeStatement(st);
		}
	}
}