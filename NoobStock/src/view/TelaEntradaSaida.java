package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class TelaEntradaSaida extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TelaEntradaSaida() {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[grow 0][grow 4][grow 11][grow 11][grow 11][grow 11][grow 11][grow 11][grow 11][grow 11][grow 11]", "[][][][][][][][]"));
		
		JLabel lblPerfil = new JLabel("");
		lblPerfil.setIcon(new ImageIcon(TelaEntradaSaida.class.getResource("/img/image8.png")));
		add(lblPerfil, "cell 0 0 2 1,alignx center,aligny center");
		
		JLabel lblInicio = new JLabel("");
		lblInicio.setForeground(new Color(255, 255, 255));
		lblInicio.setIcon(new ImageIcon(TelaEntradaSaida.class.getResource("/img/home.png")));
		add(lblInicio, "cell 0 1,alignx center");
		
		JLabel lblNewLabel = new JLabel("Início");
		add(lblNewLabel, "cell 1 1");
		
		JLabel lblControleEstoq = new JLabel("");
		lblControleEstoq.setIcon(new ImageIcon(TelaEntradaSaida.class.getResource("/img/caixa(1)1.png")));
		add(lblControleEstoq, "cell 0 2,alignx left");
		
		JLabel lblNewLabel_1 = new JLabel("Controle de Estoque");
		add(lblNewLabel_1, "cell 1 2");
		
		JLabel lblEstatis = new JLabel("");
		lblEstatis.setIcon(new ImageIcon(TelaEntradaSaida.class.getResource("/img/grafico.png")));
		add(lblEstatis, "cell 0 3,alignx center");
		
		JLabel lblNewLabel_2 = new JLabel("Estatísticas");
		add(lblNewLabel_2, "cell 1 3");
		
		JLabel lblEstraSai = new JLabel("");
		lblEstraSai.setIcon(new ImageIcon(TelaEntradaSaida.class.getResource("/img/entradaesaida(1)1.png")));
		add(lblEstraSai, "cell 0 4,alignx center");
		
		JLabel lblNewLabel_3 = new JLabel("Entrada e Saída");
		add(lblNewLabel_3, "cell 1 4");
		

	}

}
