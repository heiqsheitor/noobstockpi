package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TelaJFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    
    // Variáveis das telas
    private TelaLogin login;
    private TelaCadastro cadastro;
    private TelaRedefinirSenha RSenha;
    private TelaDeInicio gestor;
    private TelaControleEstoque Controle;
    private TelaEntradaSaida entsai;

    // Constantes para o CardLayout
    public static final String LOGIN_PANEL = "Login";
    public static final String CADASTRO_PANEL = "Cadastro";
    public static final String CONTROLE_PANEL = "Controle";

    // O gerenciador de layout e o painel principal precisam ser globais
    private CardLayout cardLayout;
    private JPanel painelPrincipal;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaJFrame frame = new TelaJFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaJFrame() throws IOException {
        setTitle("NoobStock");
        
        // CORREÇÃO: Alterado de logoNoobstock.png para logopng.png
        URL iconUrl = TelaJFrame.class.getResource("/img/logopng.png");
        if (iconUrl != null) {
            setIconImage(Toolkit.getDefaultToolkit().getImage(iconUrl));
        }
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 703, 407);

        // 1. INICIALIZANDO O CARDLAYOUT E O PAINEL PRINCIPAL
        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);
        painelPrincipal.setPreferredSize(new Dimension(816, 522));

        // 2. INICIALIZANDO AS TELAS
        login = new TelaLogin(); 
        cadastro = new TelaCadastro();
        Controle = new TelaControleEstoque();
        gestor = new TelaDeInicio();

        // 3. ADICIONANDO AS TELAS AO PAINEL PRINCIPAL
        painelPrincipal.add(login, LOGIN_PANEL);
        painelPrincipal.add(cadastro, CADASTRO_PANEL);
        painelPrincipal.add(Controle, CONTROLE_PANEL);

        // 4. DEFININDO O PAINEL PRINCIPAL COMO O CONTEÚDO DA JANELA
        setContentPane(painelPrincipal);

        // Listener para redimensionamento
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ajustarFonte();
            }
        });
        
        mostrarTela(LOGIN_PANEL);
    }

    public void mostrarTela(String panelName) {
        cardLayout.show(painelPrincipal, panelName);
    }

    private void ajustarFonte() {
        int largura = getWidth();
        int altura = getHeight();

        if (gestor != null) {
            gestor.ajustarFonte(largura, altura);
        }
        
        repaint();
        revalidate();
    }
}
