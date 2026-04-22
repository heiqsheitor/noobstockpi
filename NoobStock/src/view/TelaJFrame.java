package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TelaJFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    
    // Variáveis das telas
    private TelaLogin login;
    private TelaCadastro cadastro;
    private TelaRedefinirSenha RSenha;
    private TelaIniGestor gestor;
    private TelaControleEstoque Controle;
    private TelaEntradaSaida entsai;

    // Constantes para o CardLayout
    public static final String LOGIN_PANEL = "Login";
    public static final String CADASTRO_PANEL = "Cadastro";
    public static final String CONTROLE_PANEL = "Controle";

    // O gerenciador de layout e o painel principal precisam ser globais
    // para que o método mostrarTela() consiga acessá-los depois.
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
        // Se a imagem não for encontrada, evite o NullPointerException colocando a imagem num try-catch depois, 
        // mas mantive conforme seu código original.
        setIconImage(Toolkit.getDefaultToolkit().getImage(TelaJFrame.class.getResource("/img/logoNoobstock.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 703, 407);

        // 1. INICIALIZANDO O CARDLAYOUT E O PAINEL PRINCIPAL
        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);
        painelPrincipal.setPreferredSize(new Dimension(816, 522));

        // 2. INICIALIZANDO AS TELAS (Dando "new" nelas)
        // Atenção: Se alguma dessas telas lançar exceções no construtor delas, 
        // certifique-se de tratá-las ou colocar "throws IOException" na assinatura como está aqui.
        login = new TelaLogin(); 
        cadastro = new TelaCadastro();
        Controle = new TelaControleEstoque();
        gestor = new TelaIniGestor(); // Inicializando para não dar erro no ajustarFonte()

        // 3. ADICIONANDO AS TELAS AO PAINEL PRINCIPAL (Respeitando letras minúsculas/maiúsculas)
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
        
        // Opcional: Define a tela inicial assim que o JFrame for aberto
        mostrarTela(LOGIN_PANEL);
    }

    // Método movido para FORA do construtor e corrigido!
    // Você vai usar esse método para navegar entre as telas.
    public void mostrarTela(String panelName) {
        // cardLayout.show é o comando correto para mudar a aba ativa
        cardLayout.show(painelPrincipal, panelName);
    }

    private void ajustarFonte() {
        // Pega tamanho atual da janela
        int largura = getWidth();
        int altura = getHeight();

        // Se o gestor não tivesse sido inicializado lá em cima, essa linha daria erro fatal (NPE)
        if (gestor != null) {
            gestor.ajustarFonte(largura, altura);
        }
        
        repaint();
        revalidate();
    }
}