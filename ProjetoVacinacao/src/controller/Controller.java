package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.EnderecoDao;
import model.dao.EstoqueDao;
import model.dao.LoteDao;
import model.dao.MovimentoEstoqueDao;
import model.dao.PessoaDao;
import model.dao.UnidadeDao;
import model.entities.Centro;
import model.entities.Endereco;
import model.entities.Estoque;
import model.entities.Lote;
import model.entities.MovimentoEstoque;
import model.entities.Pessoa;
import model.entities.TipoTransacao;
import model.entities.Unidade;


public class Controller {

	private EnderecoDao endereco;
	private EstoqueDao estoque;
	private LoteDao lote;
	private MovimentoEstoqueDao mestoque;
	private PessoaDao pessoa;
	private UnidadeDao unidade;
	private SimpleDateFormat sdf;
	private Scanner kb;
	
	public Controller() {
		endereco = DaoFactory.createEnderecoDao();
		estoque = DaoFactory.createEstoqueDao();
		lote = DaoFactory.createLoteDao();
		mestoque = DaoFactory.createMovimentoEstoqueDao();
		pessoa = DaoFactory.createPessoaDao();
		unidade = DaoFactory.createUnidadeDaoDao();
		kb = new Scanner(System.in);
		sdf = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	private Endereco criarEndereco() {
		Endereco end = new Endereco();
		try {
			System.out.print("Informe a cidade: ");
			kb.nextLine();
			String cidade = kb.nextLine();
			if(cidade.equals("São Leopoldo")) {
				System.out.print("Informe o CEP: ");
				kb.nextLine();
				String cep = kb.nextLine();
				System.out.print("Informe o Bairro: ");
				kb.nextLine();
				String bairro = kb.nextLine();
				System.out.print("Informe a rua: ");
				kb.nextLine();
				String rua = kb.nextLine();
				System.out.print("Informe o numero: ");
				int numero = kb.nextInt();
				System.out.print("Informe o complemento: ");
				kb.nextLine();
				String complemento = kb.nextLine();
				end = new Endereco(cep, bairro, rua, numero, complemento);
			}
			else {
				System.out.print("Informe o CEP: ");
				kb.nextLine();
				String cep = kb.nextLine();
				System.out.print("Informe o pais: ");
				kb.nextLine();
				String pais = kb.nextLine();
				System.out.print("Informe o estado: ");
				kb.nextLine();
				String estado = kb.nextLine();
				System.out.print("Informe o Bairro: ");
				kb.nextLine();
				String bairro = kb.nextLine();
				System.out.print("Informe a rua: ");
				kb.nextLine();
				String rua = kb.nextLine();
				System.out.print("Informe o numero: ");
				int numero = kb.nextInt();
				System.out.print("Informe o complemento: ");
				kb.nextLine();
				String complemento = kb.nextLine();
				end = new Endereco(cep, pais, estado, cidade, bairro, rua, numero, complemento);
			}
			return end;
		}
		catch(RuntimeException e) {
			throw new ExceptionCustomized(e.getMessage());
		}
	}
	
	private Pessoa criarPessoa() {
		Pessoa pessoa;
		try {
			System.out.print("Informe o nome: ");
			kb.nextLine();
			String nome = kb.nextLine();
			System.out.print("Informe o cpf: ");
			String cpf = kb.next();
			Endereco end = criarEndereco();
			pessoa = new Pessoa(nome, cpf, end);
			return pessoa;
		}
		catch(RuntimeException e) {
			throw new ExceptionCustomized(e.getMessage());
		}
	}
	
	private Unidade criarUnidade() {
		Unidade uni = new Unidade();
		try {
			System.out.print("Deseja criar um centro de distribuição: (Sim/Não) ");
			String resposta = kb.next();
			if(resposta.equals("Sim")) {
				List<Unidade> all = unidade.listAll();
				for(Unidade unidade : all) {
					if (unidade.getCentro() == Centro.SIM) {
						System.out.println("Só pode haver um centro de distribuição");
					}
					else {
						System.out.print("Informe o nome: ");
						kb.nextLine();
						String nome = kb.nextLine();
						Endereco end = criarEndereco();
						uni = new Unidade(nome, end, Centro.SIM);
					}
				}
			}
			else {
				System.out.print("Informe o nome: ");
				kb.nextLine();
				String nome = kb.nextLine();
				Endereco end = criarEndereco();
				uni = new Unidade(nome, end, Centro.NAO);
			}
			return uni;
		}
		catch(RuntimeException e) {
			throw new ExceptionCustomized(e.getMessage());
		}
	}
	
	private Lote criarLote() {
		Lote lote;
		try {
			System.out.print("Informe o lote: ");
			int l = kb.nextInt();
			System.out.print("Informe a data de validade (dd/MM/yyyy): ");
			Date validade = sdf.parse(kb.next());
			lote = new Lote(l, validade);
			return lote;
		}
		catch(RuntimeException e) {
			throw new ExceptionCustomized(e.getMessage());
		} 
		catch (ParseException e) {
			throw new ExceptionCustomized(e.getMessage());
		}
	}
	
	private Estoque criarEstoque() {
		Estoque est = null;
		try {
			System.out.print("Informe a unidade: ");
			kb.nextLine();
			String nome = kb.nextLine();
			List<Unidade> all = unidade.listAll();
			Unidade un = null;
			Lote lo = null;
			for(Unidade u : all) {
				if(u.getNome().equals(nome)) {
					un = u;
					System.out.print("Informe o lote: ");
					int idlote = kb.nextInt();
					List<Lote> l = lote.listAll();
					for(Lote lot : l) {
						if(lot.getLote() == idlote) {
							lo = lot;
						}
					}
					if (lo != null) {
						System.out.print("Informe a quantidade de doses desse lote: ");
						int quantidade = kb.nextInt();
						est = new Estoque(u, lo, quantidade);
					}
					else {
						lo = criarLote();
						System.out.print("Informe a quantidade de doses desse lote: ");
						int quantidade = kb.nextInt();
						est = new Estoque(u, lo, quantidade);
					}
				}
			}
			if(un == null) {
				un = criarUnidade();
				lo = criarLote();
				System.out.print("Informe a quantidade de doses desse lote: ");
				int quantidade = kb.nextInt();
				est = new Estoque(un, lo, quantidade);
			}
			return est;
		}
		catch(RuntimeException e) {
			throw new ExceptionCustomized(e.getMessage());
		} 
	}
	
	public void criarMovimento() {
		try {
			System.out.print("Informe o tipo de movimentoção (rec - recebimento / tra - transdefrencia / apl - aplicação):");
			String resp = kb.next();
			if(resp.equals("rec")) {
				recebimento();
			}
			else if(resp.equals("tra")) {
				transferencia();
			}
			else {
				System.out.print("Informe o CPF da pessoa a ser vacinada: ");
				String CPF = kb.next();
				if(pessoa.findByCPF(CPF) !=  null) {
					apicacao(pessoa.findByCPF(CPF));
				}
				else {
					apicacao(criarPessoa());
				}
			}
		}
		catch(RuntimeException e) {
			throw new ExceptionCustomized(e.getMessage());
		} 
	}
	
	private void recebimento() {
		System.out.print("Informe qual o lote: ");
		int idLote = kb.nextInt();
		Lote l;
		if(lote.fyndById(idLote) != null) {
			l = lote.fyndById(idLote);
		}
		else {
			l = criarLote();
		}
		System.out.print("Informe a quantidade de doses: ");
		int quantidade = kb.nextInt();
		List<Unidade> unidades = unidade.listAll();
		Unidade centro = null;
		for (Unidade unidade : unidades) {
			if(unidade.getCentro() == Centro.SIM) {
				centro = unidade;
			}
		}
		if(centro != null) {
			Estoque est = new Estoque(centro, l, quantidade);
			estoque.insert(est);
		}
		else {
			System.out.println("Unico que pode receber um novo lote é o centro de distribuição");
		}
	}
	
	private void transferencia() {
		try {
			System.out.print("Informe a unidade de origem da transferencia: ");
			String unidadeOrigem = kb.next();
			List<Unidade> unidades = unidade.listAll();
			boolean origem = false;
			boolean destino = false;
			Unidade unidadeDeDestino = null;
			for(Unidade unidade : unidades) {
				if(unidade.getNome().equals(unidadeOrigem) && unidade.getCentro() == Centro.SIM) {
					origem = true;
				}
			}
			if(origem == true) {
				System.out.print("Informe a unidade de destino da transferencia: ");
				String unidadeDestino = kb.next();
				for(Unidade unidade : unidades) {
					if(unidade.getNome().equals(unidadeDestino)) {
						destino = true;
						unidadeDeDestino = unidade;
					}
				}
				if(destino != true) {
					unidadeDeDestino = criarUnidade();
				}
				else {
					System.out.print("Informe o lote a ser transferido");
					int idLote = kb.nextInt();
					List<Lote> lotes = lote.listAll();
					Lote l;
					boolean loteExist = false;
					for(Lote lote : lotes) {
						if(lote.getLote() == idLote) {
							loteExist = true;
						}
					}
					if(loteExist == true) {
						l = lote.fyndById(idLote);
						System.out.print("Informe a quantide a ser transferida");
						int quantidade = kb.nextInt();
						List<Estoque> estoques = estoque.fyndByLote(idLote);
						Estoque est = null;
						for (Estoque e : estoques) {
							if(e.getUnidade().getIdUnidade() == unidadeDeDestino.getIdUnidade()) {
								est = e;
							}
						}
						if(est != null) {
							if((quantidade > 0)&&(quantidade <= est.getQuantidade())) {
								Estoque upEst = new Estoque(unidadeDeDestino, l, quantidade);
								estoque.update(upEst);
								MovimentoEstoque me = new MovimentoEstoque(1, upEst.getUnidade(), upEst.getLote(), null, TipoTransacao.TRA, 1, new Date());
								if(mestoque.fyndByKey(me) == null) {
									mestoque.insert(me);
								}
								else {
									me = mestoque.fyndByKey(me);
									me = new MovimentoEstoque(me.getSequencia()+1, upEst.getUnidade(), upEst.getLote(), null, TipoTransacao.TRA, 1, new Date());
									mestoque.insert(me);
								}
							}
						}
					}
					else {
						l = criarLote();
					}
				}
			}
			else {
				System.out.println("Somente o centro de distribuição pode enviar para outras unidades");
			}
		}
		catch(RuntimeException e) {
			throw new ExceptionCustomized(e.getMessage());
		} 
	}
	
	private void apicacao(Pessoa p) {
		try {
			System.out.print("Informe qual a unidade: ");
			String unidadeNome = kb.next();
			int lote;
			Estoque e = null;
			List<Estoque> estoques = estoque.listAll();
			for(Estoque est : estoques) {
				if(est.getUnidade().getNome().equals(unidadeNome)) {
					System.out.print("Informe qual o lote: ");
					lote = kb.nextInt();
					if(est.getLote().getLote() == lote) {
						e = est;
					}
				}
				else if(est.getUnidade().getCentro() == Centro.SIM){
					System.out.println("Centro de distribuição não pode realizar aplicação");
				}
			}
			if(e == null) {
				e = criarEstoque();
			}
			else {
				e.setQuantidade(e.getQuantidade()-1);
				MovimentoEstoque me = new MovimentoEstoque(1, e.getUnidade(), e.getLote(), p, TipoTransacao.APL, 1, new Date());
				if(mestoque.fyndByKey(me) == null) {
					estoque.update(e);
					mestoque.insert(me);
				}
				else {
					me = mestoque.fyndByKey(me);
					me = new MovimentoEstoque(me.getSequencia()+1, e.getUnidade(), e.getLote(), p, TipoTransacao.APL, 1, new Date());
					estoque.update(e);
					mestoque.insert(me);
				}
			}
		}
		catch(RuntimeException e) {
			throw new ExceptionCustomized(e.getMessage());
		} 
	}
}