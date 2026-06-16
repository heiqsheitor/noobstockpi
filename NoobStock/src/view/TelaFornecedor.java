package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.function.Consumer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import controller.ComponentUtils;

import java.util.List;
import model.Fornecedor;
import model.FornecedorDAO;

import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;

public class TelaFornecedor extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage imagemOriginal;
	private JTable table;
	private JTextField txtPesquisar;
	private JLabel LInicio, LControleEstoq, LFor, lblPerfil, LEntraSai;
	private JButton Adicionar;

	// Callbacks para o controller
	private Consumer<Fornecedor> editarAcao;
	private Consumer<Fornecedor> excluirAcao;

	public TelaFornecedor() throws IOException {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[40px:n][150px:n][][20px:n][grow][grow 10][grow 4][grow 4][grow 4][grow 2]", "[40px:n][35px:n][35px:n][35px:n][35px:n][35px:n][grow][]"));

		lblPerfil = new JLabel("");
		lblPerfil.setIcon(new ImageIcon(TelaFornecedor.class.getResource("/img/image8.png")));
		add(lblPerfil, "cell 0 0 2 1,alignx center,aligny center");

		JLabel lblNewLabel_1 = new JLabel("Descubra");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(lblNewLabel_1, "cell 0 1 2 1");

		LControleEstoq = new JLabel("Fornecedores");
		LControleEstoq.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(LControleEstoq, "cell 4 1 2 1");

		JLabel lblInicio = new JLabel("");
		lblInicio.setIcon(new ImageIcon(TelaFornecedor.class.getResource("/img/home.png")));
		add(lblInicio, "cell 0 2,alignx center");

		txtPesquisar = new JTextField() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (getText().isEmpty()) {
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g2.setColor(new Color(192, 192, 192));
					g2.setFont(getFont());
					FontMetrics fm = g2.getFontMetrics();
					int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
					g2.drawString("Buscar Itens...", getInsets().left, y);
					g2.dispose();
				}
			}
		};
		add(txtPesquisar, "cell 4 2 3 1,grow");
		txtPesquisar.setColumns(10);

		Adicionar = new JButton("Adicionar");
		add(Adicionar, "cell 8 2,grow");

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		add(panel, "cell 7 2,grow");

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(TelaFornecedor.class.getResource("/img/calendar.png")));
		panel.add(lblNewLabel_6);

		JSeparator separator = new JSeparator();
		add(separator, "cell 2 0 1 9,gapx 2 2,growy");
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.BLACK);

		JLabel lblcontroleEstoq = new JLabel("");
		lblcontroleEstoq.setIcon(new ImageIcon(TelaFornecedor.class.getResource("/img/caixa(1)1.png")));
		add(lblcontroleEstoq, "cell 0 3,alignx left");

		JLabel lblNewLabel = new JLabel("");
		add(lblNewLabel, "flowx,cell 4 3");

		// ── TABELA ────────────────────────────────────────────────────────────
		table = new JTable();
		table.setModel(
			new DefaultTableModel(
				new Object[][] {},
				new String[] { "ID", "Nome", "CNPJ", "Contato", "Duração" }
			) {
				boolean[] columnEditables = new boolean[] { false, false, false, false, false };
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			}
		);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(28);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, "cell 4 4 5 3,grow");

		// ── POPUP MENU (CLIQUE DIREITO NA TABELA) ─────────────────────────────
		configurarPopupMenu();

		JLabel lblEstatis = new JLabel("");
		lblEstatis.setIcon(new ImageIcon(TelaFornecedor.class.getResource("/img/grafico.png")));
		add(lblEstatis, "cell 0 4,alignx center");

		imagemOriginal = ImageIO.read(getClass().getResource("/img/noobstocklogo.png"));
		
		

		JLabel lblEntraSai = new JLabel("");
		lblEntraSai.setIcon(new ImageIcon(TelaFornecedor.class.getResource("/img/entradaesaida(1)1.png")));
		add(lblEntraSai, "cell 0 5,alignx center");

		ImageIcon icon = new ImageIcon(TelaSaida.class.getResource("/img/noobstocklogo.png"));
        Image imgRedimensionada = icon.getImage().getScaledInstance(90, 47, Image.SCALE_SMOOTH);
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(imgRedimensionada));
		add(lblLogo, "cell 0 7 2 1,alignx center,aligny bottom");

		LInicio = new JLabel("Início");
		LInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		LInicio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(LInicio, "cell 1 2,alignx left,aligny center");

		LControleEstoq = new JLabel("Entrada");
		LControleEstoq.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(LControleEstoq, "cell 1 3,alignx left,aligny center");

		LFor = new JLabel("Fornecedores");
		LFor.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(LFor, "cell 1 4,alignx left,aligny center");

		LEntraSai = new JLabel("Saída de Estoque");
		LEntraSai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(LEntraSai, "cell 1 5,alignx left,aligny center");

		carregarTabelaFornecedores();
	}

	// ── CONFIGURA O POPUP DE EDITAR / EXCLUIR ─────────────────────────────────
	private void configurarPopupMenu() {
		JPopupMenu popupMenu = new JPopupMenu();

		JMenuItem itemEditar = new JMenuItem("✏️  Editar fornecedor");
		itemEditar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		itemEditar.addActionListener(e -> {
			Fornecedor selecionado = getFornecedorSelecionado();
			if (selecionado != null && editarAcao != null) {
				editarAcao.accept(selecionado);
			}
		});

		JMenuItem itemExcluir = new JMenuItem("🗑️  Excluir fornecedor");
		itemExcluir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		itemExcluir.setForeground(new Color(180, 30, 30));
		itemExcluir.addActionListener(e -> {
			Fornecedor selecionado = getFornecedorSelecionado();
			if (selecionado != null && excluirAcao != null) {
				excluirAcao.accept(selecionado);
			}
		});

		popupMenu.add(itemEditar);
		popupMenu.addSeparator();
		popupMenu.add(itemExcluir);

		// Ao clicar com botão DIREITO: seleciona a linha e abre o menu
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				if (row >= 0) {
					table.setRowSelectionInterval(row, row);
				}
				if (SwingUtilities.isRightMouseButton(e) && row >= 0) {
					popupMenu.show(table, e.getX(), e.getY());
				}
			}
		});
	}

	// ── RETORNA O FORNECEDOR DA LINHA SELECIONADA NA TABELA ───────────────────
	private Fornecedor getFornecedorSelecionado() {
		int row = table.getSelectedRow();
		if (row < 0) return null;

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		Fornecedor f = new Fornecedor();
		f.setIdfornecedor((int) model.getValueAt(row, 0));
		f.setNome(String.valueOf(model.getValueAt(row, 1)));
		f.setCnpj(String.valueOf(model.getValueAt(row, 2)));
		f.setContato(String.valueOf(model.getValueAt(row, 3)));
		f.setDuracaoContrato(String.valueOf(model.getValueAt(row, 4)));
		return f;
	}

	// ── RECARREGA A TABELA DO BANCO ───────────────────────────────────────────
	public void recarregarTabela() {
		carregarTabelaFornecedores();
	}

	// ── SETTERS DE CALLBACKS ──────────────────────────────────────────────────
	public void setEditarAcao(Consumer<Fornecedor> acao) {
		this.editarAcao = acao;
	}

	public void setExcluirAcao(Consumer<Fornecedor> acao) {
		this.excluirAcao = acao;
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

	public void setFornecedorAcao(Runnable acao) {
		ComponentUtils.transformarEmLink(this.LFor, acao);
	}
	
	public void setSaida(Runnable acao) {
		ComponentUtils.transformarEmLink(this.LEntraSai, acao);
	}

	public void setAdicionar(Runnable acao) {
		Adicionar.addActionListener(e -> acao.run());
	}

	private void redimensionarImagem(int largura, int altura) {
		largura /= 4;
		altura /= 4;
		if (largura <= 0 || altura <= 0) return;
		imagemOriginal.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
	}

	public void ajustarFonte(int largura, int altura) {
		// TODO Auto-generated method stub
	}

	public void carregarTabelaFornecedores() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);

		FornecedorDAO dao = new FornecedorDAO();
		List<Fornecedor> listaFornecedores = dao.listar();

		for (Fornecedor f : listaFornecedores) {
			modelo.addRow(new Object[]{
				f.getIdfornecedor(),
				f.getNome(),
				f.getCnpj(),
				f.getContato(),
				f.getDuracaoContrato()
			});
		}
	}
}