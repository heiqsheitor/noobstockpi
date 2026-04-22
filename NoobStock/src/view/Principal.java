package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Principal extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private CardLayout cardLayout;

    // Telas
    private TelaLogin login;
    private TelaCadastro2 cadastro;
    private TelaPerfil perfil;
    private TelaRedefinirSenha redefinirSenha;
    private TelaControleEstoque controle;

    // Constantes de navegação
    public static final String LOGIN = "LOGIN";
    public static final String CADASTRO = "CADASTRO";
    public static final String PERFIL = "PERFIL";
    public static final String ESTOQUE = "ESTOQUE";
    public static final String REDEFINIR = "REDEFINIR";

    public Principal() {
        setTitle("NoobStock");
        setIconImage(Toolkit.getDefaultToolkit().getImage(
                Principal.class.getResource("/img/logoNoobstock.png")));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        // 🔥 CardLayout
        cardLayout = new CardLayout();
        contentPane = new JPanel(cardLayout);
        contentPane.setPreferredSize(new Dimension(816, 522));

        setContentPane(contentPane);

        // 🔥 Inicializa telas
        try {
			login = new TelaLogin();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			cadastro = new TelaCadastro2();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        perfil = new TelaPerfil();
        redefinirSenha = new TelaRedefinirSenha();
        try {
			controle = new TelaControleEstoque();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // 🔥 Adiciona telas
        contentPane.add(login, LOGIN);
        contentPane.add(cadastro, CADASTRO);
        contentPane.add(perfil, PERFIL);
        contentPane.add(redefinirSenha, REDEFINIR);
        contentPane.add(controle, ESTOQUE);

        // 🔥 Responsividade (da TelaJFrame)
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ajustarFonte();
            }
        });
    }

    // 🔁 Navegação
    public void mostrarTela(String nome) {
        cardLayout.show(contentPane, nome);
    }

    // 🔧 Responsividade
    private void ajustarFonte() {
        int largura = getWidth();
        int altura = getHeight();

        if (controle != null) {
            controle.ajustarFonte(largura, altura);
        }

        repaint();
        revalidate();
    }

    // 🔓 Getters (IMPORTANTE pro controller)
    public TelaLogin getLogin() { return login; }
    public TelaCadastro2 getCadastro() { return cadastro; }
    public TelaPerfil getPerfil() { return perfil; }
    public TelaControleEstoque getControle() { return controle; }

	public void adicionarTela(String nome, JPanel tela) {
		contentPane.add(login, LOGIN);
        contentPane.add(cadastro, CADASTRO);
        contentPane.add(perfil, PERFIL);
        contentPane.add(redefinirSenha, REDEFINIR);
        contentPane.add(controle, ESTOQUE);
		// TODO Auto-generated method stub
		
	}
}