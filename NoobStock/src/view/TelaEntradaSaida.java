package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextField;

public class TelaEntradaSaida extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtPesquisar;

	/**
	 * Create the panel.
	 */
	public TelaEntradaSaida() {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[grow 0][grow 2][grow 0][grow 2][grow 3][grow 2][grow 11][grow 2][grow 11][grow 2][grow 11][grow 4]", "[::40px,grow 0][::35px,grow 1][::35px,grow 1][::35px,grow 1][::35px,grow 1][grow 1][grow 11][grow 11][grow 11][grow 11]"));
		
		JLabel lblPerfil = new JLabel("");
		lblPerfil.setIcon(new ImageIcon(TelaEntradaSaida.class.getResource("/img/image8.png")));
		add(lblPerfil, "cell 0 0 2 1,alignx center,aligny center");
		
		JLabel lblNewLabel_4 = new JLabel("Descubra");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(lblNewLabel_4, "cell 0 1 2 1");
		
		JLabel lblNewLabel_5 = new JLabel("Entrada e Saída");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(lblNewLabel_5, "cell 4 1");
		
		JLabel lblInicio = new JLabel("");
		lblInicio.setForeground(new Color(255, 255, 255));
		lblInicio.setIcon(new ImageIcon(TelaEntradaSaida.class.getResource("/img/home.png")));
		add(lblInicio, "cell 0 2,alignx center");
		
		JLabel lblNewLabel = new JLabel("Início");
		add(lblNewLabel, "cell 1 2");
		
		txtPesquisar = new JTextField(){
		    @Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        
		        // Se o campo estiver vazio, desenha o texto de fundo
		        if (getText().isEmpty()) {
		            Graphics2D g2 = (Graphics2D) g.create();
		            // Suaviza as bordas da fonte
		            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		            
		            // Define a cor e a fonte do placeholder
		            g2.setColor(new Color(192, 192, 192));
		            g2.setFont(getFont());
		            
		            // Calcula o alinhamento vertical para centralizar o texto
		            FontMetrics fm = g2.getFontMetrics();
		            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
		            
		            // Desenha o texto respeitando as margens internas (insets)
		            g2.drawString("Buscar Itens...", getInsets().left, y);
		            g2.dispose();
		        }
		    }
		};
		add(txtPesquisar, "cell 4 2 6 1,grow");
		txtPesquisar.setColumns(10);
		
		JLabel lblControleEstoq = new JLabel("");
		lblControleEstoq.setIcon(new ImageIcon(TelaEntradaSaida.class.getResource("/img/caixa(1)1.png")));
		add(lblControleEstoq, "cell 0 3,alignx left");
		
		JLabel lblNewLabel_1 = new JLabel("Controle de Estoque");
		add(lblNewLabel_1, "cell 1 3");
		
		JLabel lblEstatis = new JLabel("");
		lblEstatis.setIcon(new ImageIcon(TelaEntradaSaida.class.getResource("/img/grafico.png")));
		add(lblEstatis, "cell 0 4,alignx center");
		
		JLabel lblNewLabel_2 = new JLabel("Estatísticas");
		add(lblNewLabel_2, "cell 1 4");
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(new Color(0, 0, 0));
		add(separator, "cell 2 0 1 10,gapx 2 2,growy");
		
		JLabel lblEstraSai = new JLabel("");
		lblEstraSai.setIcon(new ImageIcon(TelaEntradaSaida.class.getResource("/img/entradaesaida(1)1.png")));
		add(lblEstraSai, "cell 0 5,alignx center");
		
		JLabel lblNewLabel_3 = new JLabel("Entrada e Saída");
		add(lblNewLabel_3, "cell 1 5");
		

	}

}
