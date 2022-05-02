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
import model.dao.EnderecoDao;
import model.entities.Endereco;

public class EnderecoDaoJDBC implements EnderecoDao {

	private Connection conn;
	
	public EnderecoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Endereco obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO endereco " +
					"(cep, pais, estado, cidade, bairro, rua, numero, complemento)" +
					"VALUES " +
					"(?,?,?,?,?,?,?,?)", 
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getCep());
			st.setString(2, obj.getPais());
			st.setString(3, obj.getEstado());
			st.setString(4, obj.getCidade());
			st.setString(5, obj.getBairro());
			st.setString(6, obj.getRua());
			st.setInt(7, obj.getNumero());
			st.setString(8, obj.getComplemento());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setIdEndereco(id);
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

	@Override
	public List<Endereco> listAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM endereco ORDER BY cep");
			rs = st.executeQuery();
			
			List<Endereco> list = new ArrayList<Endereco>();
			
			while (rs.next()) {
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
				list.add(end);
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
	public List<Endereco> listByCep(String cep) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM endereco WHERE cep = ?");
			
			st.setString(1, cep);
			rs = st.executeQuery();
			
			List<Endereco> list = new ArrayList<Endereco>();
			
			while (rs.next()) {
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
				list.add(end);
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
	public List<Endereco> listByBairro(String bairro) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM endereco WHERE bairro = ?");
			
			st.setString(1, bairro);
			rs = st.executeQuery();
			
			List<Endereco> list = new ArrayList<Endereco>();
			
			while (rs.next()) {
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
				list.add(end);
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
