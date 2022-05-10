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
		Endereco end;
		try {
			System.out.print("Informe a cidade: ");
			String cidade = kb.nextLine();
			if(cidade.equalsIgnoreCase("São Leopoldo")) {
				System.out.print("Informe o CEP: ");
				String cep = kb.nextLine();
				System.out.print("Informe o Bairro: ");
				String bairro = kb.nextLine();
				System.out.print("Informe a rua: ");
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
				String cep = kb.nextLine();
				System.out.print("Informe o pais: ");
				String pais = kb.nextLine();
				System.out.print("Informe o estado: ");
				String estado = kb.nextLine();
				System.out.print("Informe o Bairro: ");
				String bairro = kb.nextLine();
				System.out.print("Informe a rua: ");
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
			String nome = kb.nextLine();
			kb.nextLine();
			System.out.print("Informe o cpf: ");
			String cpf = kb.nextLine();
			kb.nextLine();
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
			System.out.print("Deseja criar um centro de distribuição: (S - sim /N - Não) ");
			char resposta = kb.next().charAt(0);
			if(resposta == 'S') {
				List<Unidade> all = unidade.listAll();
				boolean existCentro = false;
				for(Unidade unidade : all) {
					if (unidade.getCentro() == Centro.SIM) {
						existCentro = true;
					}
				}
				if(existCentro == true) {
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
			kb.nextLine();
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
	
	public void criarMovimento(String resp) {
		try {
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
			MovimentoEstoque me = new MovimentoEstoque();
			me.setSequencia(1);
			me.setUnidade(centro);
			me.setLote(l);
			me.setTipoTransacao(TipoTransacao.REC);
			me.setQuantidade(est.getQuantidade());
			me.setDataMovimentacao(new Date());
			mestoque.insert(me);
			estoque.insert(est);
			System.out.println("Digite qualquer coisa para continuar.");
		}
		else {
			System.out.println("Unico que pode receber um novo lote é o centro de distribuição");
		}
	}
	
	private void transferencia() {
		try {
			System.out.print("Informe a unidade de origem da transferencia: ");
			String unidadeOrigem = kb.nextLine();
			kb.nextLine();
			Unidade unidadeDeOrigem = null;
			List<Unidade> unidades = unidade.listAll();
			for(Unidade unidade : unidades) {
				if(unidade.getNome().equals(unidadeOrigem)) {
					unidadeDeOrigem = unidade;
				}
			}
			if(unidadeDeOrigem != null) {
				System.out.print("Informe a unidade de destino da transferencia: ");
				String unidadeDstino = kb.nextLine();
				kb.nextLine();
				Unidade unidadeDeDestino = null;
				for(Unidade unidade : unidades) {
					if(unidade.getNome().equals(unidadeDstino)) {
						unidadeDeDestino  = unidade;
					}
				}
				if(unidadeDeDestino != null) {
					System.out.print("Informe o lote: ");
					int idLote = kb.nextInt();
					List<Lote> lotes = lote.listAll();
					Lote l = null;
					for(Lote lot : lotes) {
						if(lot.getLote() == idLote) {
							l = lot;
						}
					}
					if(l != null) {
						System.out.println("Informe a quantidade a ser movida: ");
						int quantidade =kb.nextInt();
						if((unidadeDeOrigem.getCentro() == Centro.SIM && unidadeDeDestino.getCentro() == Centro.NAO) || (unidadeDeOrigem.getCentro() == Centro.NAO && unidadeDeDestino.getCentro() == Centro.SIM)) {
							List<Estoque> estoques = estoque.listAll();
							Estoque est = null;
							for(Estoque e : estoques) {
								if(e.getLote().getLote() == l.getLote()) {
									if(unidadeDeOrigem.getIdUnidade() == e.getUnidade().getIdUnidade()) {
										est = e;
									}
								}
							}
							
							if(est != null || est.getQuantidade() >= 0) {
								List<MovimentoEstoque> lista = mestoque.listAll();
								MovimentoEstoque movimentoDeOrigem = new MovimentoEstoque(1, unidadeDeOrigem, l, null, TipoTransacao.TRA, est.getQuantidade()-quantidade, new Date());
								MovimentoEstoque movimentoDeDestino = new MovimentoEstoque(1, unidadeDeDestino, l, null, TipoTransacao.REC, quantidade, new Date());
								for (MovimentoEstoque move : lista) {
									if(move.getSequencia() == movimentoDeOrigem.getSequencia()) {
										if(move.getLote().getLote() == movimentoDeOrigem.getLote().getLote()) {
											if(move.getUnidade().getIdUnidade() == movimentoDeOrigem.getUnidade().getIdUnidade()) {
												movimentoDeOrigem.setSequencia(movimentoDeOrigem.getSequencia()+1);
											}
										}
									}
									else if(move.getSequencia() == movimentoDeDestino.getSequencia()) {
										if(move.getLote().getLote() == movimentoDeDestino.getLote().getLote()) {
											if(move.getUnidade().getIdUnidade() == movimentoDeDestino.getUnidade().getIdUnidade()) {
												movimentoDeDestino.setSequencia(movimentoDeDestino.getSequencia()+1);
											}
										}
									}
								}
								mestoque.transacao(movimentoDeOrigem, movimentoDeDestino);
							}
							else {
								System.out.println("Operação impossivel de ser realizada.");
							}
						}
						else {
							System.out.println("Operação impossivel de ser realizada.");
						}
					}
					else {
						System.out.println("Operação impossivel de ser realizada.");
					}
				}
				else {
					System.out.println("Operação impossivel de ser realizada.");
				}
			}
			else {
				System.out.println("Operação impossivel de ser realizada.");
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
			if(e == null || e.getQuantidade() <= 0) {
				System.out.println("Não há estoque para aplicacão");
			}
			else {
				e.setQuantidade(e.getQuantidade()-1);
				MovimentoEstoque me = new MovimentoEstoque(1, e.getUnidade(), e.getLote(), p, TipoTransacao.APL, 1, new Date());
				List<MovimentoEstoque> list = mestoque.listAll();
				MovimentoEstoque me2 = null;
				int sequencia = 0;
				for(MovimentoEstoque movimento : list) {
					if(me.getLote().getLote() == movimento.getLote().getLote()) {
						if(me.getUnidade().getIdUnidade() == movimento.getUnidade().getIdUnidade()) {
							me2 = movimento;
							sequencia = movimento.getSequencia() + 1;
						}
					}
				}
				if(me2 == null) {
					estoque.update(e);
					mestoque.insert(me);
				}
				else {
					me2 = new MovimentoEstoque(sequencia, e.getUnidade(), e.getLote(), p, TipoTransacao.APL, 1, new Date());
					estoque.update(e);
					mestoque.insert(me2);
				}
			}
		}
		catch(RuntimeException e) {
			throw new ExceptionCustomized(e.getMessage());
		} 
	}
	
	public void pessoaInsert() {
		Pessoa pes = criarPessoa();
		endereco.insert(pes.getEndereco());
		pessoa.insert(pes);
	}
	
	public void pessoaList() {
		List<Pessoa> pessoas = pessoa.listAll();
		for(Pessoa p : pessoas) {
			System.out.println(p);
		}
	}
	
	public void unidadeInsert() {
		Unidade uni = criarUnidade();
		endereco.insert(uni.getEndereco());
		unidade.insert(uni);
	}
	
	public void unidadeList() {
		List<Unidade> unidades = unidade.listAll();
		for(Unidade u : unidades) {
			System.out.println(u);
		}
	}
	
	public void loteInsert() {
		lote.insert(criarLote());
	}
	
	public void loteList() {
		List<Lote> lotes = lote.listAll();
		for(Lote l : lotes) {
			System.out.println(l);
		}
	}
	
	public void relatorioEstoque() {
		System.out.print("Informe a unidade: ");
		String nomeUnidade = kb.nextLine();
		kb.nextLine();
		Unidade unidadeRelatorio = null;
		List<Unidade> unidades = unidade.listAll();
		for(Unidade unidade : unidades) {
			if(unidade.getNome().equals(nomeUnidade)) {
				unidadeRelatorio = unidade;
			}
		}
		if(unidadeRelatorio != null) {
			List<Estoque> estoques = estoque.relatorioEstoque(unidadeRelatorio.getIdUnidade());
			for(Estoque estoque : estoques) {
				System.out.println(estoque);
			}
		}
		else {
			System.out.println("Unidade informada não existe");
		}
	}
	
	public void relatorioApliacao() {
		System.out.print("Informe a unidade: ");
		String nomeUnidade = kb.nextLine();
		kb.nextLine();
		Unidade unidadeRelatorio = null;
		List<Unidade> unidades = unidade.listAll();
		for(Unidade unidade : unidades) {
			if(unidade.getNome().equals(nomeUnidade)) {
				unidadeRelatorio = unidade;
			}
		}
		if(unidadeRelatorio != null) {
			List<MovimentoEstoque> relatorios = mestoque.relatorioApliacao(unidadeRelatorio.getIdUnidade());
			for(MovimentoEstoque relatorio : relatorios) {
				System.out.println(relatorio);
			}
		}
		else {
			System.out.println("Unidade informada não existe");
		}
	}
	
	public void relatorioResumoAplicacao() {
		
	}
	
	public void relatorioEsquemaIncompleto() {
		
	}
}