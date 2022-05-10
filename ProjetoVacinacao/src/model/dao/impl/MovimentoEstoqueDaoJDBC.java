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
import model.dao.DaoFactory;
import model.dao.EstoqueDao;
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
		if(rs.getString("tipo_transacao").equals("rec")) {
			movimento.setTipoTransacao(TipoTransacao.REC);
		}
		else if(rs.getString("tipo_transacao").equals("tra")) {
			movimento.setTipoTransacao(TipoTransacao.TRA);
		}
		else {
			movimento.setTipoTransacao(TipoTransacao.APL);
		}
		return movimento;
	}
	
	@Override
	public void insertMovimentoComPessoa(MovimentoEstoque movimentoEstoque) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO movimento_estoque "
					+" (sequencia, data_movimento, tipo_transacao, quantidade, fk_idUnidade, fk_lote, fk_idPessoa) "
					+" VALUES "
					+" (?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, movimentoEstoque.getSequencia());
			st.setDate(2, new java.sql.Date(movimentoEstoque.getDataMovimentacao().getTime()));
			st.setString(3, "apl");
			st.setInt(4, movimentoEstoque.getQuantidade());
			st.setInt(5, movimentoEstoque.getUnidade().getIdUnidade());
			st.setInt(6, movimentoEstoque.getLote().getLote());
			if(movimentoEstoque.getPessoa() != null)
				st.setInt(6, movimentoEstoque.getPessoa().getIdPessoa());
			else
				st.setInt(6, 0);
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
	public void insert(MovimentoEstoque movimentoEstoque) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO movimento_estoque "
					+" (sequencia, data_movimento, tipo_transacao, quantidade, fk_idUnidade, fk_lote) "
					+" VALUES "
					+" (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, movimentoEstoque.getSequencia());
			st.setDate(2, new java.sql.Date(movimentoEstoque.getDataMovimentacao().getTime()));
			st.setString(3, "rec");
			st.setInt(4, movimentoEstoque.getQuantidade());
			st.setInt(5, movimentoEstoque.getUnidade().getIdUnidade());
			st.setInt(6, movimentoEstoque.getLote().getLote());
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
	public void insertTransasao(MovimentoEstoque movimentoEstoque) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO movimento_estoque "
					+" (sequencia, data_movimento, tipo_transacao, quantidade, fk_idUnidade, fk_lote) "
					+" VALUES "
					+" (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, movimentoEstoque.getSequencia());
			st.setDate(2, new java.sql.Date(movimentoEstoque.getDataMovimentacao().getTime()));
			st.setString(3, "tra");
			st.setInt(4, movimentoEstoque.getQuantidade());
			st.setInt(5, movimentoEstoque.getUnidade().getIdUnidade());
			st.setInt(6, movimentoEstoque.getLote().getLote());
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
	public void transacao(MovimentoEstoque origem, MovimentoEstoque destino) {
		Statement st = null;
		try {
			conn = DB.getConnection();
	
			conn.setAutoCommit(false);

			st = conn.createStatement();
			insertTransasao(origem);
			EstoqueDao estoque = DaoFactory.createEstoqueDao();
			Estoque estoqueDeOrigem = new Estoque(origem.getUnidade(), origem.getLote(), origem.getQuantidade());
			estoque.update(estoqueDeOrigem);
			
			insert (destino);
			Estoque estoqueDeDestino = new Estoque(destino.getUnidade(), destino.getLote(), destino.getQuantidade());
			estoque.insert(estoqueDeDestino);
			conn.commit();
			
		}
		catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
			} 
			catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
			}
		} 
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

	@Override
	public List<MovimentoEstoque> relatorioApliacao(int id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * "
					+ " FROM estoque, lote, unidade, endereco, movimento_estoque, pessoa "
					+ " WHERE "
					+ " estoque.fk_lote = lote.lote AND "
					+ " estoque.fk_idUnidade = unidade.idUnidade AND "
					+ " endereco.idEndereco = unidade.fk_idEndereco AND "
					+ " movimento_estoque.fk_idPessoa = pessoa.idPessoa AND "
					+ " movimento_estoque.fk_idUnidade = unidade.idUnidade AND "
					+ " movimento_estoque.fk_lote = lote.lote AND "
					+ " estoque.fk_idUnidade = ? AND "
					+ " movimento_estoque.tipo_transacao = 'apl'; ");
			rs = st.executeQuery();
			
			List<MovimentoEstoque> movimentoEstoques = new ArrayList<MovimentoEstoque>();
			
			while(rs.next()) {
				Endereco endereco = instantiateEndereco(rs);
				Lote lote = instantiateLote(rs);
				Unidade unidade = instantiateUnidade(rs, endereco);
				Pessoa pessoa = instantiatePessoa(rs, endereco);
				Estoque estoque = instantiateEstoque(rs, unidade, lote);
				MovimentoEstoque movimentoEstoque = instantiateMovimentoEstoque(rs, unidade, lote, pessoa, estoque, endereco);
				movimentoEstoques.add(movimentoEstoque);
			}
			
			return movimentoEstoques;
		}
		catch(SQLException e) {
			throw new DbException("Unexpected error! No rows affected!");
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<MovimentoEstoque> relatorioResumoAplicacao(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MovimentoEstoque> relatorioEsquemaIncompleto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MovimentoEstoque> listAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * "
					+ " FROM estoque, lote, unidade, endereco, movimento_estoque, pessoa "
					+ " WHERE "
					+ " estoque.fk_lote = lote.lote AND "
					+ " estoque.fk_idUnidade = unidade.idUnidade AND "
					+ " endereco.idEndereco = unidade.fk_idEndereco AND "
					+ " movimento_estoque.fk_idUnidade = unidade.idUnidade AND "
					+ " movimento_estoque.fk_lote = lote.lote;");
			rs = st.executeQuery();
			
			List<MovimentoEstoque> movimentoEstoques = new ArrayList<MovimentoEstoque>();
			
			while(rs.next()) {
				Lote lot = instantiateLote(rs);
				Endereco end = instantiateEndereco(rs);
				Unidade uni = instantiateUnidade(rs, end);
				Estoque est = instantiateEstoque(rs, uni, lot);
				MovimentoEstoque mv = instantiateMovimentoEstoque(rs, uni, lot, null, est, end);
				movimentoEstoques.add(mv);
			}
			
			return movimentoEstoques;
		}
		catch(SQLException e) {
			throw new DbException("Unexpected error! No rows affected!");
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
}