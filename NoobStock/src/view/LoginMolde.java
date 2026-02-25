package view;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LoginMolde extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

		/**
	 * Create the panel.
	 */
	public LoginMolde() {
		setTitle("NOOB STOCK - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new MigLayout("flowy, insets 40", "[grow, center]", "[]20[]20[]20[]push[]"));

        getContentPane().setBackground(Color.WHITE);

        // =========================
        // PAINEL LOGO
        // =========================
        JPanel logoPanel = new JPanel(new MigLayout("wrap 1, align center"));
        logoPanel.setOpaque(false);

        JLabel lblNoob = new JLabel("NOOB");
        lblNoob.setFont(new Font("Arial", Font.BOLD, 48));
        lblNoob.setForeground(new Color(120, 130, 140));

        JLabel lblStock = new JLabel("STOCK");
        lblStock.setFont(new Font("Arial", Font.PLAIN, 32));
        lblStock.setForeground(new Color(40, 50, 70));

        logoPanel.add(lblNoob);
        logoPanel.add(lblStock);

        add(logoPanel, "growx");

        // =========================
        // TÍTULO
        // =========================
        JPanel tituloPanel = new JPanel(new MigLayout("wrap 1, align center"));
        tituloPanel.setOpaque(false);

        JLabel lblTitulo = new JLabel("Faça seu login");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblSub = new JLabel("Digite as credenciais para entrar no aplicativo");
        lblSub.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSub.setForeground(Color.GRAY);

        tituloPanel.add(lblTitulo);
        tituloPanel.add(lblSub);

        add(tituloPanel, "growx");

        // =========================
        // FORMULÁRIO
        // =========================
        JPanel formPanel = new JPanel(new MigLayout("wrap 2", "[right]10[grow, fill]", "[]15[]15[]"));
        formPanel.setOpaque(false);

        JLabel lblUsuario = new JLabel("Usuário:");
        JTextField txtUsuario = new JTextField(20);

        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField(20);

        JLabel lblEsqueci = new JLabel("Esqueci a senha");
        lblEsqueci.setForeground(Color.GRAY);

        formPanel.add(lblUsuario);
        formPanel.add(txtUsuario, "growx");

        formPanel.add(lblSenha);
        formPanel.add(txtSenha, "growx");

        formPanel.add(lblEsqueci, "span 2, align center");

        add(formPanel, "growx");

        // =========================
        // BOTÕES
        // =========================
        JPanel botoesPanel = new JPanel(new MigLayout("insets 0, gap 15"));
        botoesPanel.setOpaque(false);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBackground(Color.BLACK);
        btnEntrar.setForeground(Color.WHITE);

        JButton btnCadastrar = new JButton("Cadastrar");

        botoesPanel.add(btnEntrar, "width 100!");
        botoesPanel.add(btnCadastrar, "width 100!");

        add(botoesPanel, "align center");

        // =========================
        // RODAPÉ
        // =========================
        JLabel lblRodape = new JLabel("Ao continuar, você concorda com os nossos Termos de Serviço e Política de Privacidade");
        lblRodape.setFont(new Font("Arial", Font.PLAIN, 10));
        lblRodape.setForeground(Color.GRAY);

        add(lblRodape, "align center");

    }

    private void setDefaultCloseOperation(int exitOnClose) {
			// TODO Auto-generated method stub
			
		}

	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginMolde().setVisible(true);
        });
    }
}