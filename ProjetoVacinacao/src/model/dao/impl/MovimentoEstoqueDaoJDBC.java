package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.MovimentoEstoqueDao;
import model.entities.Centro;
import model.entities.Endereco;
import model.entities.Estoque;
import model.entities.Lote;
import model.entities.MovimentoEstoque;
import model.entities.Pessoa;
import model.entities.TipoTransacao;
import model.entities.Unidade;

public class MovimentoEstoqueDaoJDBC implements MovimentoEstoqueDao{

	private Connection conn;
	
	public MovimentoEstoqueDaoJDBC (Connection conn) {
		this.conn = conn;
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
	
	private Pessoa instantiatePessoa(ResultSet rs, Endereco endereco)  throws SQLException{
		Pessoa pessoa = new Pessoa();
		pessoa.setIdPessoa(rs.getInt("idPessoa"));
		pessoa.setNome(rs.getString("nome"));
		pessoa.setCpf(rs.getString("cpf"));
		pessoa.setEndereco(endereco);
		return pessoa;
	}
	
	private Estoque instantiateEstoque(ResultSet rs, Unidade uni, Lote lote) throws SQLException{
		Estoque estoque = new Estoque();
		estoque.setLote(lote);
		estoque.setUnidade(uni);
		estoque.setQuantidade(rs.getInt("quantidade"));
		return estoque;
	}
	
	private MovimentoEstoque instantiateMovimentoEstoque(ResultSet rs, Unidade uni, Lote lote, Pessoa pessoa, Estoque estoque, Endereco endereco) throws SQLException {
		MovimentoEstoque movimento = new MovimentoEstoque();
		movimento.setSequencia(rs.getInt("sequencia"));
		movimento.setDataMovimentacao(rs.getDate("data_movimento"));
		movimento.setLote(lote);
		movimento.setPessoa(pessoa);
		movimento.setUnidade(uni);
		movimento.setQuantidade(rs.getInt("quantidade"));
		if(rs.getString("tipo_transacao") == "rec") {
			movimento.setTipoTransacao(TipoTransacao.REC);
		}
		else if(rs.getString("tipo_transacao") == "tra") {
			movimento.setTipoTransacao(TipoTransacao.TRA);
		}
		else {
			movimento.setTipoTransacao(TipoTransacao.APL);
		}
		return movimento;
	}
	
	@Override
	public void insert(MovimentoEstoque movimentoEstoque) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MovimentoEstoque> listAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT pessoa*, endereco.*, unidade.*, estoque.*, lote*, movimento_estoque.*"
					+ "FROM"
					+ "movimento_estoque INNER JOIN pessoa"
					+ "ON movimento_estoque.fk_idPessoa = pessoa.idPessoa AND"
					+ "pessoa INNER JOIN endereco"
					+ "ON pessoa.fk_idEndereco = endereco.idEndereco AND"
					+ "movimento_estoque INNER JOIN unidade"
					+ "ON movimento_estoque.fk_idUnidade = unidade.idUnidade AND"
					+ "unidade INNER JOIN endereco"
					+ "ON unidade.fk_idEndereco = endereco.idEndereco AND"
					+ "estoque INNER JOIN unidade"
					+ "ON estoque.fk_idUnidade = unidade.idUnidade AND"
					+ "estoque INNER JOIN lote"
					+ "ON estoque.fk_idUnidade = lote.idUnidade AND"
					+ "lote INNER JOIN movimento_estoque"
					+ "ON lote.lote = movimento_estoque.fk_lote"
					);
			rs = st.executeQuery();
			
			List<MovimentoEstoque> movimentoEstoque = new ArrayList<>();
			
			while (rs.next()) {
				Lote lote = instantiateLote(rs);
				Endereco enderco = instantiateEndereco(rs);
				Unidade unidade = instantiateUnidade(rs, enderco);
				Pessoa pessoa = instantiatePessoa(rs, enderco);
				Estoque estoque = instantiateEstoque(rs, unidade, lote);
				MovimentoEstoque movimento = instantiateMovimentoEstoque(rs, unidade, lote, pessoa, estoque, enderco);
				movimentoEstoque.add(movimento);
			}
			
			return movimentoEstoque;
		}
		catch(SQLException e) {
			throw new DbException("Unexpected error! No rows affected!");
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public List<MovimentoEstoque> listByDate() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT pessoa*, endereco.*, unidade.*, estoque.*, lote*, movimento_estoque.*"
					+ "FROM"
					+ "movimento_estoque INNER JOIN pessoa"
					+ "ON movimento_estoque.fk_idPessoa = pessoa.idPessoa AND"
					+ "pessoa INNER JOIN endereco"
					+ "ON pessoa.fk_idEndereco = endereco.idEndereco AND"
					+ "movimento_estoque INNER JOIN unidade"
					+ "ON movimento_estoque.fk_idUnidade = unidade.idUnidade AND"
					+ "unidade INNER JOIN endereco"
					+ "ON unidade.fk_idEndereco = endereco.idEndereco AND"
					+ "estoque INNER JOIN unidade"
					+ "ON estoque.fk_idUnidade = unidade.idUnidade AND"
					+ "estoque INNER JOIN lote"
					+ "ON estoque.fk_idUnidade = lote.idUnidade AND"
					+ "lote INNER JOIN movimento_estoque"
					+ "ON lote.lote = movimento_estoque.fk_lote ORDER BY data_movimento");
			rs = st.executeQuery();
			
			List<MovimentoEstoque> movimentoEstoque = new ArrayList<>();
			
			while (rs.next()) {
				Lote lote = instantiateLote(rs);
				Endereco enderco = instantiateEndereco(rs);
				Unidade unidade = instantiateUnidade(rs, enderco);
				Pessoa pessoa = instantiatePessoa(rs, enderco);
				Estoque estoque = instantiateEstoque(rs, unidade, lote);
				MovimentoEstoque movimento = instantiateMovimentoEstoque(rs, unidade, lote, pessoa, estoque, enderco);
				movimentoEstoque.add(movimento);
			}
			
			return movimentoEstoque;
		}
		catch(SQLException e) {
			throw new DbException("Unexpected error! No rows affected!");
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public List<MovimentoEstoque> fyndByDate(Date data) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT pessoa*, endereco.*, unidade.*, estoque.*, lote*, movimento_estoque.*"
					+ "FROM"
					+ "movimento_estoque INNER JOIN pessoa"
					+ "ON movimento_estoque.fk_idPessoa = pessoa.idPessoa AND"
					+ "pessoa INNER JOIN endereco"
					+ "ON pessoa.fk_idEndereco = endereco.idEndereco AND"
					+ "movimento_estoque INNER JOIN unidade"
					+ "ON movimento_estoque.fk_idUnidade = unidade.idUnidade AND"
					+ "unidade INNER JOIN endereco"
					+ "ON unidade.fk_idEndereco = endereco.idEndereco AND"
					+ "estoque INNER JOIN unidade"
					+ "ON estoque.fk_idUnidade = unidade.idUnidade AND"
					+ "estoque INNER JOIN lote"
					+ "ON estoque.fk_idUnidade = lote.idUnidade AND"
					+ "lote INNER JOIN movimento_estoque"
					+ "ON lote.lote = movimento_estoque.fk_lote"
					+ " WHERE data_movimento = ?");
			st.setDate(1, new java.sql.Date(data.getTime()));
			rs = st.executeQuery();
			
			List<MovimentoEstoque> movimentoEstoque = new ArrayList<>();
			
			while (rs.next()) {
				Lote lote = instantiateLote(rs);
				Endereco enderco = instantiateEndereco(rs);
				Unidade unidade = instantiateUnidade(rs, enderco);
				Pessoa pessoa = instantiatePessoa(rs, enderco);
				Estoque estoque = instantiateEstoque(rs, unidade, lote);
				MovimentoEstoque movimento = instantiateMovimentoEstoque(rs, unidade, lote, pessoa, estoque, enderco);
				movimentoEstoque.add(movimento);
			}
			
			return movimentoEstoque;
		}
		catch(SQLException e) {
			throw new DbException("Unexpected error! No rows affected!");
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public MovimentoEstoque fyndByKey(MovimentoEstoque movimentoEsto) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT pessoa*, endereco.*, unidade.*, estoque.*, lote*, movimento_estoque.*"
					+ "FROM"
					+ "movimento_estoque INNER JOIN pessoa"
					+ "ON movimento_estoque.fk_idPessoa = pessoa.idPessoa AND"
					+ "pessoa INNER JOIN endereco"
					+ "ON pessoa.fk_idEndereco = endereco.idEndereco AND"
					+ "movimento_estoque INNER JOIN unidade"
					+ "ON movimento_estoque.fk_idUnidade = unidade.idUnidade AND"
					+ "unidade INNER JOIN endereco"
					+ "ON unidade.fk_idEndereco = endereco.idEndereco AND"
					+ "estoque INNER JOIN unidade"
					+ "ON estoque.fk_idUnidade = unidade.idUnidade AND"
					+ "estoque INNER JOIN lote"
					+ "ON estoque.fk_idUnidade = lote.idUnidade AND"
					+ "lote INNER JOIN movimento_estoque"
					+ "ON lote.lote = movimento_estoque.fk_lote"
					+ " WHERE movimento_estoque.sequencia = ? AND"
					+ "movimento_estoque.lote = ? AND"
					+ "movimento_estoque.fk_idUnidade = ?");
			st.setInt(1, movimentoEsto.getSequencia());
			st.setInt(2, movimentoEsto.getLote().getLote());
			st.setInt(3, movimentoEsto.getUnidade().getIdUnidade());
			rs = st.executeQuery();
			Lote lote = instantiateLote(rs);
			Endereco enderco = instantiateEndereco(rs);
			Unidade unidade = instantiateUnidade(rs, enderco);
			Pessoa pessoa = instantiatePessoa(rs, enderco);
			Estoque estoque = instantiateEstoque(rs, unidade, lote);
			MovimentoEstoque movimento = instantiateMovimentoEstoque(rs, unidade, lote, pessoa, estoque, enderco);
			
			return movimento;
		}
		catch(SQLException e) {
			throw new DbException("Unexpected error! No rows affected!");
		}
		finally {
			DB.closeStatement(st);
		}
	}
	
}
