package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private CardLayout cardLayout;

	// Telas
	private TelaLogin login;
	private TelaCadastro cadastro;
	private TelaPerfil perfil;
	private TelaRedefinirSenha redefinirSenha;
	private TelaControleEstoque controle;
	private TelaDeInicio inicio;
	private TelaAdicionarProduto adicionar;
	private TelaFornecedor fornecedor;
	private TelaAdicionarFornecedor adicionarfor;
	private TelaDetalhesProduto telaDetalhes;
	private TelaSaida telaSaida;

	// Constantes de navegação
	public static final String LOGIN = "LOGIN";
	public static final String CADASTRO = "CADASTRO";
	public static final String PERFIL = "PERFIL";
	public static final String ESTOQUE = "ESTOQUE";
	public static final String REDEFINIR = "REDEFINIR";
	public static final String INICIO = "INICIO";
	public static final String ADICIONAR = "ADICIONAR";
	public static final String FORNECEDOR = "FORNECEDOR";
	public static final String ADICIONARFOR = "ADICIONARFOR";
	public static final String DETALHES = "DETALHES";
	public static final String SAIDA = "SAIDA";

	public Principal() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/noobstocklogo.png")));
		setTitle("NoobStock");

		URL iconUrl = Principal.class.getResource("/img/logopng.png");
		if (iconUrl != null) {
			setIconImage(Toolkit.getDefaultToolkit().getImage(iconUrl));
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		// CardLayout
		cardLayout = new CardLayout();
		contentPane = new JPanel(cardLayout);
		contentPane.setPreferredSize(new Dimension(816, 522));

		setContentPane(contentPane);

		// Inicializa telas

		adicionarfor = new TelaAdicionarFornecedor();

		try {
			fornecedor = new TelaFornecedor();
		} catch (IOException e) {
			e.printStackTrace();
		}
		telaSaida = new TelaSaida();

		telaDetalhes = new TelaDetalhesProduto();

		try {
			login = new TelaLogin();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			cadastro = new TelaCadastro();
		} catch (IOException e) {
			e.printStackTrace();
		}

		perfil = new TelaPerfil();
		redefinirSenha = new TelaRedefinirSenha();
		try {
			controle = new TelaControleEstoque();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			inicio = new TelaDeInicio();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			adicionar = new TelaAdicionarProduto();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Adiciona telas
		if (login != null)
			contentPane.add(login, LOGIN);
		if (cadastro != null)
			contentPane.add(cadastro, CADASTRO);
		if (perfil != null)
			contentPane.add(perfil, PERFIL);
		if (redefinirSenha != null)
			contentPane.add(redefinirSenha, REDEFINIR);
		if (controle != null)
			contentPane.add(controle, ESTOQUE);
		if (inicio != null)
			contentPane.add(inicio, INICIO);
		if (adicionar != null)
			contentPane.add(adicionar, ADICIONAR);
		if (fornecedor != null)
			contentPane.add(fornecedor, FORNECEDOR);
		if (adicionarfor != null)
			contentPane.add(adicionarfor, ADICIONARFOR);
		if (telaDetalhes != null)
			contentPane.add(telaDetalhes, DETALHES);
		if (telaSaida != null)
			contentPane.add(telaSaida, SAIDA);

		// Configuração das ações das telas
		if (redefinirSenha != null) {
			redefinirSenha.adicionarListenerCancelar(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					redefinirSenha.limparCampos();
					mostrarTela(Principal.LOGIN);
				}
			});
		}

		// Responsividade
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				ajustarFonte();
			}
		});
	}

	// Navegação
	public void mostrarTela(String nome) {
		cardLayout.show(contentPane, nome);
	}

	// Responsividade
	private void ajustarFonte() {
		int largura = getWidth();
		int altura = getHeight();

		if (controle != null) {
			controle.ajustarFonte(largura, altura);
		}
		if (inicio != null) {
			inicio.ajustarFonte(largura, altura);
		}

		repaint();
		revalidate();
	}

	// Getters
	public TelaLogin getLogin() {
		return login;
	}

	public TelaCadastro getCadastro() {
		return cadastro;
	}

	public TelaPerfil getPerfil() {
		return perfil;
	}

	public TelaRedefinirSenha getRedefinirSenha() {
		return redefinirSenha;
	}

	public TelaControleEstoque getControle() {
		return controle;
	}

	public TelaDeInicio getInicio() {
		return inicio;
	}

	public TelaAdicionarProduto getAdicionar() {
		return adicionar;
	}

	public TelaFornecedor getFornecedor() {
		return fornecedor;
	}

	public TelaAdicionarFornecedor getAdicionarFor() {
		return adicionarfor;
	}

	public TelaDetalhesProduto getTelaDetalhesProduto() {
		return telaDetalhes;
	}

	public TelaSaida getTelaSaida() {
		return telaSaida;
	}
}
