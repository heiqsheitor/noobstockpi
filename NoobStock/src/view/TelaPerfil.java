package view;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaPerfil extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tFPrimeiroNome;
	private JTextField tFSegundoNome;
	private JTextField tFEmail;
	private JPasswordField pFSenha;

	/**
	 * Create the panel.
	 */
	public TelaPerfil() {
		JPanel painelEsquerdo = new JPanel();
		painelEsquerdo.setLayout(new MigLayout("flowy, filly", "[grow]", "[]"));
		painelEsquerdo.setBorder(new MatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY));
		
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[grow 15][][grow 40][grow][grow][grow][grow]", "[][][][][][][][][][][grow]"));
		
		// Barra Vertical
		JSeparator separatorVer = new JSeparator();
		separatorVer.setOrientation(SwingConstants.VERTICAL);
		separatorVer.setForeground(Color.BLACK);
		add(separatorVer, "cell 1 0 1 11, gapx 10 10, growy");

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaPerfil.class.getResource("/img/image8.png")));
		add(lblNewLabel, "cell 0 0, alignx center");
		
		JLabel lblEditarPerfil = new JLabel("Editar Perfil");
		lblEditarPerfil.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblEditarPerfil, "cell 2 0");

		// Barra Horizontal
		JSeparator separatorHor = new JSeparator();
		separatorHor.setOrientation(SwingConstants.HORIZONTAL);
		separatorHor.setForeground(Color.BLACK);
		add(separatorHor, "cell 2 1 5 1,growx,gapy 5 10");
		
		JLabel lblNewLabel_1 = new JLabel("Descubra");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblNewLabel_1, "cell 0 1, alignx left");
		
		JLabel lblInfoBasicas = new JLabel("Informações Básicas");
		lblInfoBasicas.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblInfoBasicas, "cell 2 2, gaptop 10"); 
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(TelaPerfil.class.getResource("/img/home.png")));
		add(lblNewLabel_2, "flowx, cell 0 2");
		
		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		add(lblInicio, "cell 0 2,aligny center");
		
		JLabel lblIconAvatar = new JLabel("");
		lblIconAvatar.setIcon(new ImageIcon(TelaPerfil.class.getResource("/img/icone avatar.png")));
		add(lblIconAvatar, "cell 2 3 1 5,growx,aligny top");
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(TelaPerfil.class.getResource("/img/caixa(1)1.png")));
		add(lblNewLabel_4, "flowx, cell 0 3, alignx left");
		
		JLabel lblControleEstoque = new JLabel("Controle de Estoque");
		lblControleEstoque.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		add(lblControleEstoque, "cell 0 3");
		
		// --- CAMPOS DE FORMULÁRIO ---
		JLabel lblPrimeiroNome = new JLabel("Primeiro Nome:");
		add(lblPrimeiroNome, "cell 3 3,alignx right");
		
		tFPrimeiroNome = new JTextField();
		add(tFPrimeiroNome, "cell 4 3 2 1,grow");
		
		JLabel lblSegundoNome = new JLabel("Segundo Nome:");
		add(lblSegundoNome, "cell 3 4,alignx trailing");
		
		tFSegundoNome = new JTextField();
		add(tFSegundoNome, "cell 4 4 2 1,grow");
		
		JLabel lblEmail = new JLabel("Email:");
		add(lblEmail, "cell 3 5,alignx trailing");
		// ----------------------------

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(TelaPerfil.class.getResource("/img/grafico.png")));
		add(lblNewLabel_6, "flowx, cell 0 4");
		
		JLabel lblEstatisticas = new JLabel("Estatísticas");
		lblEstatisticas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		add(lblEstatisticas, "cell 0 4");
		
		JLabel lblEntradaSaida = new JLabel("Entrada e Saída");
		lblEntradaSaida.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblEntradaSaida.setIcon(new ImageIcon(TelaPerfil.class.getResource("/img/entradaesaida(1)1.png")));
		add(lblEntradaSaida, "cell 0 5");
		
		tFEmail = new JTextField();
		add(tFEmail, "cell 4 5 2 1,grow");
		
		JLabel lblSenha = new JLabel("Senha:");
		add(lblSenha, "cell 3 6,alignx trailing");
		
		pFSenha = new JPasswordField();
		add(pFSenha, "cell 4 6 2 1,grow");
		
		JLabel lblAvatar = new JLabel("Avatar");
		add(lblAvatar, "cell 2 8,growx"); 
		
		JButton btnImportarAvatar = new JButton("Importar Arquivo");
		btnImportarAvatar.setBackground(new Color(255, 255, 255));
		JLabel lblStatusArquivo = new JLabel("Nenhum arquivo selecionado");
		lblStatusArquivo.setForeground(Color.GRAY);

		btnImportarAvatar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser selector = new JFileChooser();
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagens (JPG, PNG)", "jpg", "png", "jpeg");
				selector.setFileFilter(filtro);
				int retorno = selector.showOpenDialog(null);
				if (retorno == JFileChooser.APPROVE_OPTION) {
					File arquivo = selector.getSelectedFile();
					lblStatusArquivo.setText(arquivo.getName());
					ImageIcon novaImagem = new ImageIcon(arquivo.getAbsolutePath());
					Image img = novaImagem.getImage();
					Image imgRedimensionada = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
					lblIconAvatar.setIcon(new ImageIcon(imgRedimensionada));
				}
			}
		});

		add(btnImportarAvatar, "flowx,cell 2 9,growx"); 
		add(lblStatusArquivo, "cell 2 9,growx,gapx 10");
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setForeground(new Color(255, 255, 255));
		btnAtualizar.setBackground(new Color(0, 0, 0));
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
			}
		});
		add(btnAtualizar, "cell 4 9,alignx right");
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(255, 255, 255));
		add(btnCancelar, "cell 5 9,width 100!,alignx left");
		// ----------------------
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(TelaPerfil.class.getResource("/img/LOGO1.png")));
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblLogo, "cell 0 10, alignx center, aligny bottom");
	}
}