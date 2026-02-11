package pasta;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import java.awt.Toolkit;

public class TelaInicial extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaInicial frame = new TelaInicial();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaInicial() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/img/logoNoobstock.png")));
        setTitle("Tela Inicial");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        contentPane = new JPanel();
        contentPane.setBorder(null);
        setContentPane(contentPane);
        setResizable(false);

        contentPane.setLayout(new MigLayout("fill, insets 0", "[grow][grow 0,center][grow]", "[push][][]20[][]15[][][]20[][][push][][][]"));

        JLabel lblLogo = new JLabel("NoobStock");
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblLogo, "cell 1 1, align center");

        JLabel lblTitulo = new JLabel("Faça seu login");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(lblTitulo, "cell 1 2, align center");

        JLabel lblSub = new JLabel("Digite as credenciais para entrar no aplicativo");
        lblSub.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblSub.setForeground(java.awt.Color.GRAY);
        contentPane.add(lblSub, "cell 1 3, align center");

        JPanel painelForm = new JPanel(new MigLayout(
                "insets 0, gapy 10",
                "[][grow,fill]",
                "[][]"
        ));
        painelForm.setOpaque(false);

        JLabel lblUsuario = new JLabel("Usuário:");
        JTextField txtUsuario = new JTextField();
        txtUsuario.setPreferredSize(new java.awt.Dimension(220, 28));

        painelForm.add(lblUsuario);
        painelForm.add(txtUsuario, "wrap");


        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField();
        txtSenha.setPreferredSize(new java.awt.Dimension(220, 28));

        painelForm.add(lblSenha);
        painelForm.add(txtSenha, "alignx center");

        contentPane.add(painelForm, "cell 1 4, align center");
        
        JButton btnEsquecerSenha = new JButton("Esqueci a senha");
        contentPane.add(btnEsquecerSenha, "cell 1 6");

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setPreferredSize(new java.awt.Dimension(190, 35));
        btnEntrar.setBackground(java.awt.Color.BLACK);
        btnEntrar.setForeground(java.awt.Color.WHITE);
        btnEntrar.setFocusPainted(false);
        contentPane.add(btnEntrar, "cell 1 7,alignx center");

        JLabel lblRodape = new JLabel(
            "Ao continuar, você concorda com os nossos Termos de Serviço e Política de Privacidade"
        );
        lblRodape.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblRodape.setForeground(java.awt.Color.GRAY);
        contentPane.add(lblRodape, "cell 1 13,alignx center");

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent e) {
                int largura = getWidth();
                int tamanhoFonte = Math.max(20, largura / 15);
                lblLogo.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, tamanhoFonte));
            }
        });
    }
}