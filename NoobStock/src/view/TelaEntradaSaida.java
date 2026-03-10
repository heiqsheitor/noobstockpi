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
		setForeground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[grow 11][grow 11][grow 11][grow 11][grow 11][grow 11][grow 11][grow 11][grow 11][grow 11][grow 11]", "[][][][][][][][]"));
		
		JLabel lblInicio = new JLabel("");
		lblInicio.setForeground(new Color(255, 255, 255));
		lblInicio.setIcon(new ImageIcon(TelaEntradaSaida.class.getResource("/img/image8.png")));
		add(lblInicio, "cell 1 0");
		
		JLabel lblControleEstoq = new JLabel("New label");
		add(lblControleEstoq, "cell 0 1");
		
		JLabel lblEstatis = new JLabel("New label");
		add(lblEstatis, "cell 0 2");
		
		JLabel lblEstraSai = new JLabel("New label");
		add(lblEstraSai, "cell 0 3");
		

	}

}
