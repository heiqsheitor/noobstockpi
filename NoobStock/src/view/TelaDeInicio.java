package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.SwingConstants;

import controller.ComponentUtils;

public class TelaDeInicio extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage imagemOriginal;
	private JLabel LInicio, LControleEstoq, LEstatis, LEntraSai, lblPerfil;
	
	// NOVO: Variável para a mensagem de boas-vindas
	private JLabel lblBemVindo; 

	/**
	 * Create the panel.
	 * * @throws IOException
	 */
	public TelaDeInicio() throws IOException {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[40px:n][135px:n][][20px:n][][grow 10]", "[40px:n][35px:n][35px:n][35px:n][35px:n][35px:n][grow 30][][][][]"));

		lblPerfil = new JLabel("");
		lblPerfil.setIcon(new ImageIcon(TelaDeInicio.class.getResource("/img/image8.png")));
		add(lblPerfil, "cell 1 0");

		JLabel lblNewLabel_11 = new JLabel("Início");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblNewLabel_11, "cell 4 1");

		JLabel lblInicio = new JLabel("");
		lblInicio.setIcon(new ImageIcon(TelaDeInicio.class.getResource("/img/home.png")));
		add(lblInicio, "cell 0 2,alignx center");

		LInicio = new JLabel("Inicio");
		LInicio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(LInicio, "cell 1 2");

		// ALTERADO: Agora usamos a variável global lblBemVindo
		lblBemVindo = new JLabel("Bem vindo(a), (Nome de usuário)");
		lblBemVindo.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblBemVindo, "cell 4 2");

		JLabel lblcontroleEstoq = new JLabel("");
		lblcontroleEstoq.setIcon(new ImageIcon(TelaDeInicio.class.getResource("/img/caixa(1)1.png")));
		add(lblcontroleEstoq, "cell 0 3,alignx center");

		LControleEstoq = new JLabel("Controle de estoque");
		LControleEstoq.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(LControleEstoq, "cell 1 3");

		JLabel lblEstatis = new JLabel("");
		lblEstatis.setIcon(new ImageIcon(TelaDeInicio.class.getResource("/img/grafico.png")));
		add(lblEstatis, "cell 0 4,alignx center");

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setOrientation(SwingConstants.VERTICAL);
		add(separator, "cell 2 0 1 11,gapx 2 2,growy");
		separator.setBackground(new Color(255, 255, 255));

		LEstatis = new JLabel("Estatísticas");
		LEstatis.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(LEstatis, "cell 1 4");

		JLabel lblEntraSai = new JLabel("");
		lblEntraSai.setIcon(new ImageIcon(TelaDeInicio.class.getResource("/img/entradaesaida(1)1.png")));
		add(lblEntraSai, "cell 0 5,alignx center");

		LEntraSai = new JLabel("Entrada e saída");
		LEntraSai.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(LEntraSai, "cell 1 5");
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(TelaDeInicio.class.getResource("/img/logopng.png")));
		add(lblLogo, "cell 1 7");
		ImageIcon icon = new ImageIcon(TelaPerfil.class.getResource("/img/logopng.png"));
		Image img = icon.getImage();
		Image imgRedimensionada = img.getScaledInstance(120, 50, Image.SCALE_SMOOTH);
		lblLogo.setIcon(new ImageIcon(imgRedimensionada));
		add(lblLogo, "cell 0 10 2 1,alignx center,aligny bottom");
	}

	// NOVO: Método para o controller mudar o texto
	public void setNomeUsuario(String nome) {
		lblBemVindo.setText("Bem vindo(a), " + nome);
	}

	public void setPerfilAcao(Runnable acao) {
		ComponentUtils.transformarEmLink(this.lblPerfil, acao);
	}

	public void setInicioAcao(Runnable acao) {
		ComponentUtils.transformarEmLink(this.LInicio, acao);
	}

	public void setControleEstoqueAcao(Runnable acao) {
		ComponentUtils.transformarEmLink(this.LControleEstoq, acao);
	}

	public void setEstatisticasAcao(Runnable acao) {
		ComponentUtils.transformarEmLink(this.LEstatis, acao);
	}

	public void setEntradaSaidaAcao(Runnable acao) {
		ComponentUtils.transformarEmLink(this.LEntraSai, acao);
	}

	public void ajustarFonte(int largura, int altura) {
		// Implementação opcional
	}
}