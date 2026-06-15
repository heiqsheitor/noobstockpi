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
	private JLabel LInicio, LControleEstoq, LFor, LEntraSai, lblPerfil, lblBemVindo;

	public TelaDeInicio() throws IOException {
		setBackground(new Color(255, 255, 255));
	
		setLayout(new MigLayout("", "[40px:n][150px:n][][20px:n][][grow]", "[40px:n][35px:n][35px:n][35px:n][35px:n][35px:n][grow][]"));

		lblPerfil = new JLabel("");
		lblPerfil.setIcon(new ImageIcon(TelaDeInicio.class.getResource("/img/image8.png")));
		add(lblPerfil, "cell 0 0 2 1,alignx center");

		JLabel lblNewLabel_11 = new JLabel("Início");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 22));
		add(lblNewLabel_11, "cell 4 0 2 1"); // Movido para alinhar melhor o topo

		JSeparator separator = new JSeparator();
		add(separator, "cell 2 0 1 8,gapx 2 2,growy");
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.BLACK);
		
		JLabel lblDescubra = new JLabel("Descubra");
		lblDescubra.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(lblDescubra, "cell 0 1 2 1");

		// ITENS DO MENU LATERAL
		JLabel lblInicio = new JLabel("");
		lblInicio.setIcon(new ImageIcon(TelaDeInicio.class.getResource("/img/home.png")));
		add(lblInicio, "cell 0 2,alignx center");
		LInicio = new JLabel("Início");
		LInicio.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(LInicio, "cell 1 2,alignx left,aligny center");

		JLabel lblcontroleEstoq = new JLabel("");
		lblcontroleEstoq.setIcon(new ImageIcon(TelaDeInicio.class.getResource("/img/caixa(1)1.png")));
		add(lblcontroleEstoq, "cell 0 3,alignx center");
		LControleEstoq = new JLabel("Controle de Estoque");
		LControleEstoq.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(LControleEstoq, "cell 1 3,alignx left,aligny center");

		JLabel lblEstatis = new JLabel("");
		lblEstatis.setIcon(new ImageIcon(TelaDeInicio.class.getResource("/img/grafico.png")));
		add(lblEstatis, "cell 0 4,alignx center");
		LFor = new JLabel("Fornecedores");
		LFor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(LFor, "cell 1 4,alignx left,aligny center");

		JLabel lblEntraSai = new JLabel("");
		lblEntraSai.setIcon(new ImageIcon(TelaDeInicio.class.getResource("/img/entradaesaida(1)1.png")));
		add(lblEntraSai, "cell 0 5,alignx center");
		LEntraSai = new JLabel("Saída de Estoque");
		LEntraSai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(LEntraSai, "cell 1 5,alignx left,aligny center");

		// CONTEÚDO PRINCIPAL
		lblBemVindo = new JLabel("Bem vindo(a), (Nome de usuário)");
		lblBemVindo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblBemVindo, "cell 4 1 2 1");

		// LOGO INFERIOR
		ImageIcon icon = new ImageIcon(TelaSaida.class.getResource("/img/noobstocklogo.png"));
        Image imgRedimensionada = icon.getImage().getScaledInstance(90, 47, Image.SCALE_SMOOTH);
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(imgRedimensionada));
		add(lblLogo, "cell 0 7 2 1,alignx center,aligny bottom");
	}

	public void setNomeUsuario(String nome) { lblBemVindo.setText("Bem vindo(a), " + nome); }
	public void setPerfilAcao(Runnable acao) { ComponentUtils.transformarEmLink(this.lblPerfil, acao); }
	public void setInicioAcao(Runnable acao) { ComponentUtils.transformarEmLink(this.LInicio, acao); }
	public void setControleEstoqueAcao(Runnable acao) { ComponentUtils.transformarEmLink(this.LControleEstoq, acao); }
	public void setFornecedorAcao(Runnable acao) { ComponentUtils.transformarEmLink(this.LFor, acao); }
	public void setEntradaSaidaAcao(Runnable acao) { ComponentUtils.transformarEmLink(this.LEntraSai, acao); }
	public void ajustarFonte(int largura, int altura) {}
}