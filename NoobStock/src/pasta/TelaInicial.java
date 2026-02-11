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

        contentPane.setLayout(new MigLayout(
                "fill, insets 0",
                "[grow][center][grow]",
                "[push][][40][]0[][push]"
        ));

        JLabel lblLogo = new JLabel("NoobStock");
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblLogo, "cell 1 1, align center");
        
                JButton btnEntrar = new JButton("Entrar");
                contentPane.add(btnEntrar, "cell 0 2");
                btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
                btnEntrar.setPreferredSize(new java.awt.Dimension(190, 30));

        JPanel painelBotoes = new JPanel(new MigLayout(
                "insets 0, gap 0",
                "[center]",
                "[]10[]"
        ));
        painelBotoes.setOpaque(false);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnCadastrar.setPreferredSize(new java.awt.Dimension(190, 30));
        painelBotoes.add(btnCadastrar);

        contentPane.add(painelBotoes, "cell 1 3, align center");

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent e) {
                int largura = getWidth();
                int tamanhoFonte = Math.max(20, largura / 15);
                lblLogo.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, tamanhoFonte));
            }
        });
    }
}