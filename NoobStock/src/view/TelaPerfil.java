package view;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

public class TelaPerfil extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tFNome;
	private JTextField tFEmail;
	private JPasswordField pFSenha;
	private JButton btnDeslogar;
	private JButton btnAtualizar;
	private JButton btnCancelar;
	private JButton btnImportarAvatar;
	private JButton btnExcluirConta;

	public TelaPerfil() {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[40px:n,grow 0][135px:n,grow 0][][20px:n][grow 40][grow][grow][grow][grow 40]",
				"[40px:n,grow 0][35px:n][35px:n][35px:n][35px:n][35px:n][35px:n][][][][grow]"));

		JLabel lblPerfil = new JLabel("");
		lblPerfil.setIcon(new ImageIcon(TelaPerfil.class.getResource("/img/image8.png")));
		add(lblPerfil, "cell 0 0 2 1,alignx center");

		JLabel lblEditarPerfil = new JLabel("Editar Perfil");
		lblEditarPerfil.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblEditarPerfil, "cell 4 0,aligny bottom");

		JLabel lblNewLabel_1 = new JLabel("Descubra");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(lblNewLabel_1, "cell 0 1 2 1");

		JSeparator separatorHor = new JSeparator();
		separatorHor.setOrientation(SwingConstants.HORIZONTAL);
		separatorHor.setForeground(Color.BLACK);
		add(separatorHor, "cell 3 1 6 1,growx,gapy 5 10");

		JSeparator separatorVer = new JSeparator();
		separatorVer.setOrientation(SwingConstants.VERTICAL);
		separatorVer.setForeground(Color.BLACK);
		add(separatorVer, "cell 2 0 1 11,gapx 2 2,growy");

		JLabel LInicio = new JLabel("Inicio");
		LInicio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(LInicio, "cell 1 2,alignx left,aligny center");

		JLabel lblInfoBasicas = new JLabel("Informações Básicas");
		lblInfoBasicas.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblInfoBasicas, "cell 4 2,gapy 10");

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(TelaPerfil.class.getResource("/img/home.png")));
		add(lblNewLabel_2, "flowx,cell 0 2,alignx center");

		JLabel LControleEstoq = new JLabel("Controle de estoque");
		LControleEstoq.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(LControleEstoq, "cell 1 3,alignx left,aligny center");

		JLabel lblIconAvatar = new JLabel("");
		lblIconAvatar.setIcon(new ImageIcon(TelaPerfil.class.getResource("/img/icone avatar.png")));
		add(lblIconAvatar, "cell 4 3 1 5,growx,aligny top");

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(TelaPerfil.class.getResource("/img/caixa(1)1.png")));
		add(lblNewLabel_4, "flowx, cell 0 3, alignx left");

		JLabel lblNome = new JLabel("Nome:");
		add(lblNome, "cell 5 3,alignx right");

		tFNome = new JTextField();
		add(tFNome, "cell 6 3 2 1,grow");

		JLabel LEstatis = new JLabel("Estatísticas");
		LEstatis.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(LEstatis, "cell 1 4");

		JLabel lblEmail = new JLabel("Email:");
		add(lblEmail, "cell 5 4,alignx trailing");

		tFEmail = new JTextField();
		add(tFEmail, "cell 6 4 2 1,grow");

		JLabel lblEntraSai = new JLabel("");
		lblEntraSai.setIcon(new ImageIcon(TelaPerfil.class.getResource("/img/entradaesaida(1)1.png")));
		add(lblEntraSai, "cell 0 5,alignx center");

		JLabel LEntraSai = new JLabel("Entrada e saída");
		LEntraSai.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(LEntraSai, "cell 1 5");

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(TelaPerfil.class.getResource("/img/grafico.png")));
		add(lblNewLabel_6, "flowx,cell 0 4,alignx center");

		JLabel lblSenha = new JLabel("Senha:");
		add(lblSenha, "cell 5 5,alignx trailing");

		pFSenha = new JPasswordField();
		add(pFSenha, "cell 6 5 2 1,grow");

		JLabel lblAvatar = new JLabel("Avatar");
		add(lblAvatar, "cell 4 8,growx");

		btnImportarAvatar = new JButton("Importar Arquivo");
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

		add(btnImportarAvatar, "flowx,cell 4 9,growx");
		add(lblStatusArquivo, "cell 4 9,growx,gapx 10");

		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setForeground(new Color(0, 0, 0));
		btnAtualizar.setBackground(new Color(255, 255, 255));
		// A lógica do botão foi movida para o PerfilController para seguir o MVC
		add(btnAtualizar, "cell 6 9,alignx right");

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(255, 255, 255));
		add(btnCancelar, "flowx,cell 7 9,width 100!,alignx left");

		JLabel lblLogo = new JLabel("");
		ImageIcon icon = new ImageIcon(TelaPerfil.class.getResource("/img/logopng.png"));
		Image img = icon.getImage();
		Image imgRedimensionada = img.getScaledInstance(70, 35, Image.SCALE_SMOOTH);
		lblLogo.setIcon(new ImageIcon(imgRedimensionada));
		add(lblLogo, "cell 0 10 2 1,alignx center,aligny bottom");

		btnDeslogar = new JButton("Deslogar");
		add(btnDeslogar, "cell 7 9,alignx right,aligny bottom");

		btnExcluirConta = new JButton("Excluir Conta");
		add(btnExcluirConta, "cell 7 9");
	}

	// MÉTODOS PARA O CONTROLLER
	public void adicionarAtualizarListener(ActionListener listener) {
		btnAtualizar.addActionListener(listener);
	}

	public void adicionarCancelarListener(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}

	public void adicionarDeslogar(ActionListener listener) {
		btnDeslogar.addActionListener(listener);
	}

	public void adicionarExcluirContaListener(ActionListener listener) {
		btnExcluirConta.addActionListener(listener);
	}

	public String getNome() { return tFNome.getText().trim(); }
	public String getEmail() { return tFEmail.getText().trim(); }
	public String getSenha() { return new String(pFSenha.getPassword()); }

	public void limparCampos() {
		tFNome.setText("");
		tFEmail.setText("");
		pFSenha.setText("");
	}
}