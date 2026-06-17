package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import controller.ComponentUtils;

public class TelaMensagem extends JDialog {
	private boolean confirmado = false;

	public TelaMensagem(String titulo, String mensagem, String tipo) {
		configurarTela(titulo, mensagem, tipo, false);
	}

	public TelaMensagem(String titulo, String mensagem) {
		configurarTela(titulo, mensagem, "CONFIRMACAO", true);
	}

	private void configurarTela(String titulo, String mensagem, String tipo, boolean isConfirmacao) {
		setTitle(titulo);
		setModal(true);
		setSize(400, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setUndecorated(true);

		JPanel painelPrincipal = new JPanel(new MigLayout("insets 20, fill", "[grow]", "[][grow][]"));
		painelPrincipal.setBackground(Color.WHITE);
		painelPrincipal.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));

		Color corDestaque = new Color(30, 30, 30);
		if (tipo.equals("SUCESSO"))
			corDestaque = new Color(40, 167, 69);
		else if (tipo.equals("ERRO"))
			corDestaque = new Color(220, 53, 69);
		else if (tipo.equals("AVISO"))
			corDestaque = new Color(255, 193, 7);

		JLabel lblTitulo = new JLabel(titulo);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblTitulo.setForeground(corDestaque);

		JTextPane txtMensagem = new JTextPane();
		txtMensagem.setText(mensagem);
		txtMensagem.setEditable(false);
		txtMensagem.setFocusable(false);
		txtMensagem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtMensagem.setOpaque(false);

		StyledDocument doc = txtMensagem.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);

		painelPrincipal.add(lblTitulo, "cell 0 0, alignx center");
		painelPrincipal.add(txtMensagem, "cell 0 1, grow, gapy 10");

		JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
		painelBotoes.setBackground(Color.WHITE);

		if (isConfirmacao) {
			JButton btnSim = criarBotao("Sim", new Color(220, 53, 69));
			btnSim.addActionListener(e -> {
				confirmado = true;
				dispose();
			});
			ComponentUtils.associarTeclaEnter(painelBotoes, btnSim);


			JButton btnNao = criarBotao("Não", new Color(150, 150, 150));
			btnNao.addActionListener(e -> {
				confirmado = false;
				dispose();
			});
			ComponentUtils.associarTeclaEnter(painelBotoes, btnNao);

			painelBotoes.add(btnSim);
			painelBotoes.add(btnNao);
		} else {
			JButton btnOk = criarBotao("OK", corDestaque);
			btnOk.addActionListener(e -> dispose());
			painelBotoes.add(btnOk);
			ComponentUtils.associarTeclaEnter(painelBotoes, btnOk);

		}

		painelPrincipal.add(painelBotoes, "cell 0 2, alignx center");
		add(painelPrincipal);
	}

	private JButton criarBotao(String texto, Color cor) {
		JButton btn = new JButton(texto);
		btn.setBackground(cor);
		btn.setForeground(Color.WHITE);
		btn.setFocusPainted(false);
		btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		return btn;
		
	}

	public boolean isConfirmado() {
		return confirmado;
	}
}