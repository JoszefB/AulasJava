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
import model.dao.PessoaDao;
import model.entities.Endereco;
import model.entities.Pessoa;

public class PessoaDaoJDBC implements PessoaDao{

	private Connection conn;
	
	public PessoaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Pessoa pessoa) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO pessoa "+
					"(nome, cpf, fk_idEndereco)" +
					"VALUES " +
					"(?,?, ?)", 
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, pessoa.getNome());
			st.setString(2, pessoa.getCpf());
			st.setInt(3, pessoa.getEndereco().getIdEndereco());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					pessoa.setIdPessoa(id);
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
	
	private Pessoa instantiatePessoa(ResultSet rs, Endereco  endereco)throws SQLException {
		Pessoa pessoa = new Pessoa();
		pessoa.setIdPessoa(rs.getInt("idPessoa"));
		pessoa.setNome(rs.getString("nome"));
		pessoa.setCpf(rs.getString("cpf"));
		pessoa.setEndereco(endereco);
		return pessoa;
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

	@Override
	public List<Pessoa> listAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT pessoa.*,endereco.* "+
					"FROM pessoa INNER JOIN endereco "+
					"ON pessoa.fk_idEndereco = endereco.idEndereco ");
			rs = st.executeQuery();
			
			List<Pessoa> list = new ArrayList<Pessoa>();
			
			while (rs.next()) {
				Endereco end = instantiateEndereco(rs);
				Pessoa pes = instantiatePessoa(rs, end);
				list.add(pes);
			}
			return list;
		}
		catch(SQLException e) {
			throw new DbException("Unexpected error! No rows affected!");
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Pessoa findById(int id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT pessoa.*,endereco.* "+
					" FROM pessoa INNER JOIN endereco "+
					" ON pessoa.fk_idEndereco = endereco.idEndereco "+
					" WHERE pessoa.idPessoa = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			Endereco end = instantiateEndereco(rs);
			Pessoa pes = instantiatePessoa(rs, end);
			
			return pes;
		}
		catch(SQLException e) {
			throw new DbException("Unexpected error! No rows affected!");
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Pessoa findByCPF(String cpf) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					" SELECT pessoa.*,  endereco.* "+
					" FROM pessoa, endereco "+
					" WHERE pessoa.fk_idEndereco = endereco.idEndereco AND pessoa.cpf =  ?");
			st.setString(1, cpf);
			rs = st.executeQuery();
			
			Endereco end = instantiateEndereco(rs);
			Pessoa pes = instantiatePessoa(rs, end);
			
			return pes;
		}
		catch(SQLException e) {
			throw new DbException("Unexpected error! No rows affected!");
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Pessoa> findByEndereco(Endereco endereco) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT pessoa.*,endereco.* "+
					"FROM pessoa INNER JOIN endereco "+
					"ON pessoa.fk_idEndereco = endereco.idEndereco "+
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
			
			List<Pessoa> list = new ArrayList<Pessoa>();
			
			while (rs.next()) {
				Endereco end = instantiateEndereco(rs);
				Pessoa pes = instantiatePessoa(rs, end);
				list.add(pes);
			}
			return list;
		}
		catch(SQLException e) {
			throw new DbException("Unexpected error! No rows affected!");
		}
		finally {
			DB.closeStatement(st);
		}
	}

}