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
    private TelaDeInicio inicio; // Adicionada Tela de Início

    // Constantes de navegação
    public static final String LOGIN = "LOGIN";
    public static final String CADASTRO = "CADASTRO";
    public static final String PERFIL = "PERFIL";
    public static final String ESTOQUE = "ESTOQUE";
    public static final String REDEFINIR = "REDEFINIR";
    public static final String INICIO = "INICIO"; // Adicionada constante INICIO

    public Principal() {
        setTitle("NoobStock");
        
        URL iconUrl = Principal.class.getResource("/img/logopng.png");
        if (iconUrl != null) {
            setIconImage(Toolkit.getDefaultToolkit().getImage(iconUrl));
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        // CardLayout
        cardLayout = new CardLayout();
        contentPane = new JPanel(cardLayout);
        contentPane.setPreferredSize(new Dimension(816, 522));

        setContentPane(contentPane);

        // Inicializa telas
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
            inicio = new TelaDeInicio(); // Inicializa Tela de Início
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Adiciona telas
        if (login != null) contentPane.add(login, LOGIN);
        if (cadastro != null) contentPane.add(cadastro, CADASTRO);
        if (perfil != null) contentPane.add(perfil, PERFIL);
        if (redefinirSenha != null) contentPane.add(redefinirSenha, REDEFINIR);
        if (controle != null) contentPane.add(controle, ESTOQUE);
        if (inicio != null) contentPane.add(inicio, INICIO); // Adiciona ao layout

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
    public TelaLogin getLogin() { return login; }
    public TelaCadastro getCadastro() { return cadastro; }
    public TelaPerfil getPerfil() { return perfil; }
    public TelaRedefinirSenha getRedefinirSenha() { return redefinirSenha; }
    public TelaControleEstoque getControle() { return controle; }
    public TelaDeInicio getInicio() { return inicio; }
}
