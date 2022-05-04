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
	
	private MovimentoEstoque criarMovimento() {
		MovimentoEstoque me =null;
		try {
			return me;
		}
		catch(RuntimeException e) {
			throw new ExceptionCustomized(e.getMessage());
		} 
	}
}