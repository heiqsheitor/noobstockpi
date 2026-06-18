package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
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
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Cursor;
import java.util.ArrayList;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

import controller.ComponentUtils;

import java.util.List;
import model.Produto;
import model.ProdutoDAO;

import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;

public class TelaControleEstoque extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage imagemOriginal;
	private JTable table;
	private JTextField txtPesquisar;
	private JLabel LInicio, LControleEstoq, LFor, lblPerfil, LEntraSai;
	private JButton Adicionar;
	private JMenuItem mntmDetalhes, mntmEditar, mntmExcluir;

	private Consumer<Produto> editarAcao;
	private Consumer<Produto> excluirAcao;
	private TableRowSorter<DefaultTableModel> sorter;

	public TelaControleEstoque() throws IOException {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[40px:n][150px:n][][20px:n][grow][grow 10][grow 4][grow 4][grow 4][grow 2]",
				"[40px:n][35px:n][35px:n][35px:n][35px:n][35px:n][grow][]"));

		lblPerfil = new JLabel("");
		lblPerfil.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/image8.png")));
		add(lblPerfil, "cell 0 0 2 1,alignx center,aligny center");

		JLabel lblNewLabel_1 = new JLabel("Descubra");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(lblNewLabel_1, "cell 0 1 2 1");

		LControleEstoq = new JLabel("Entradas de Estoque");
		LControleEstoq.setFont(new Font("Tahoma", Font.BOLD, 17));
		add(LControleEstoq, "cell 4 1 2 1");

		JLabel lblInicio = new JLabel("");
		lblInicio.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/home.png")));
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
		txtPesquisar.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				filtrarTabela();
			}

			public void removeUpdate(DocumentEvent e) {
				filtrarTabela();
			}

			public void changedUpdate(DocumentEvent e) {
				filtrarTabela();
			}
		});

		Adicionar = new JButton("Adicionar");
		Adicionar.setBackground(Color.BLACK);
		Adicionar.setForeground(new Color(255, 255, 255));
		add(Adicionar, "cell 8 2,grow");
		Adicionar.setOpaque(true);
		Adicionar.setBorderPainted(false);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		add(panel, "cell 7 2,grow");

		JSeparator separator = new JSeparator();
		add(separator, "cell 2 0 1 9,gapx 2 2,growy");
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.BLACK);

		JLabel lblcontroleEstoq = new JLabel("");
		lblcontroleEstoq.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/caixa(1)1.png")));
		add(lblcontroleEstoq, "cell 0 3,alignx left");

		JLabel lblNewLabel = new JLabel("");
		add(lblNewLabel, "flowx,cell 4 3");

		table = new JTable();

		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nome do Produto", "SKU", "Fornecedor", "Quantidade", "Preço", "Data Cadastro" }) {

			Class[] columnTypes = new Class[] { String.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(28);

		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);

		sorter = new TableRowSorter<>((DefaultTableModel) table.getModel());
		table.setRowSorter(sorter);

		sorter.setComparator(6, new java.util.Comparator<String>() {
			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("dd/MM/yyyy");

			@Override
			public int compare(String s1, String s2) {
				try {
					java.util.Date d1 = format.parse(s1);
					java.util.Date d2 = format.parse(s2);
					return d1.compareTo(d2);
				} catch (Exception e) {
					return s1.compareTo(s2);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, "cell 4 4 4 3,grow");

		configurarPopupMenu();

		JLabel lblEstatis = new JLabel("");
		lblEstatis.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/Fornecedores_resized (2).png")));
		add(lblEstatis, "cell 0 4,alignx center");

		imagemOriginal = ImageIO.read(getClass().getResource("/img/noobstocklogo.png"));

		JLabel lblEntraSai = new JLabel("");
		lblEntraSai.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/entradaesaida(1)1.png")));
		add(lblEntraSai, "cell 0 5,alignx center");

		ImageIcon icon = new ImageIcon(TelaSaida.class.getResource("/img/noobstocklogo.png"));
		Image imgRedimensionada = icon.getImage().getScaledInstance(90, 47, Image.SCALE_SMOOTH);
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(imgRedimensionada));
		add(lblLogo, "cell 0 7 2 1,alignx center,aligny bottom");

		LInicio = new JLabel("Início");
		LInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		LInicio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(LInicio, "cell 1 2,alignx left,aligny center");

		LControleEstoq = new JLabel("Entrada");
		LControleEstoq.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(LControleEstoq, "cell 1 3,alignx left,aligny center");

		LFor = new JLabel("Fornecedores");
		LFor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(LFor, "cell 1 4,alignx left,aligny center");

		LEntraSai = new JLabel("Saída de Estoque");
		LEntraSai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(LEntraSai, "cell 1 5,alignx left,aligny center");

		carregarTabelaProdutos();
	}

	private void configurarPopupMenu() {
		JPopupMenu popupMenu = new JPopupMenu();

		mntmDetalhes = new JMenuItem("👁️  Ver Detalhes do Produto");
		mntmDetalhes.setFont(new Font("Tahoma", Font.PLAIN, 13));

		mntmEditar = new JMenuItem("✏️  Editar produto");
		mntmEditar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		mntmEditar.addActionListener(e -> {
			Produto selecionado = getProdutoSelecionado();
			if (selecionado != null && editarAcao != null) {
				editarAcao.accept(selecionado);
			}
		});

		mntmExcluir = new JMenuItem("🗑️  Excluir produto");
		mntmExcluir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		mntmExcluir.setForeground(new Color(180, 30, 30));
		mntmExcluir.addActionListener(e -> {
			Produto selecionado = getProdutoSelecionado();
			if (selecionado != null && excluirAcao != null) {
				excluirAcao.accept(selecionado);
			}
		});

		popupMenu.add(mntmDetalhes);
		popupMenu.addSeparator();
		popupMenu.add(mntmEditar);
		popupMenu.add(mntmExcluir);

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

	public Produto getProdutoSelecionado() {
		int row = table.getSelectedRow();
		if (row < 0)
			return null;

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String id = String.valueOf(model.getValueAt(row, 0));
		String nome = String.valueOf(model.getValueAt(row, 1));
		String sku = String.valueOf(model.getValueAt(row, 2));
		String quantidade = String.valueOf(model.getValueAt(row, 4));

		ProdutoDAO dao = new ProdutoDAO();
		try {
			Produto completo = dao.buscarPorId(Integer.parseInt(id));
			if (completo != null)
				return completo;
		} catch (NumberFormatException ignored) {
		}

		return new Produto(id, sku, nome, quantidade, 0, "", "", "", "", 0.0);
	}

	public void recarregarTabela() {
		carregarTabelaProdutos();
	}

	public void setEditarAcao(Consumer<Produto> acao) {
		this.editarAcao = acao;
	}

	public void setExcluirAcao(Consumer<Produto> acao) {
		this.excluirAcao = acao;
	}

	public void setDetalhesAcao(ActionListener acao) {
		mntmDetalhes.addActionListener(acao);
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
		if (largura <= 0 || altura <= 0)
			return;
		imagemOriginal.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
	}

	public void ajustarFonte(int largura, int altura) {
		// TODO Auto-generated method stub
	}

	private void filtrarTabela() {
		String texto = txtPesquisar.getText().trim();
		if (texto.isEmpty()) {
			sorter.setRowFilter(null);
		} else {
			try {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(texto), 1, 2, 3));
			} catch (java.util.regex.PatternSyntaxException ex) {

			}
		}
	}

	private void carregarTabelaProdutos() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);

		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> listaProdutos = dao.listarProdutos();
		java.text.DecimalFormat df = new java.text.DecimalFormat("R$ #,##0.00");

		for (Produto p : listaProdutos) {
			modelo.addRow(new Object[] { p.getId_produto(), p.getNome(), p.getSKU(), p.getFornecedor(), p.getQtd(),
					df.format(p.getPreco()), p.getDataCriacao() });
		}
	}
	
	public void setIconePerfil(ImageIcon miniatura) {
	    if (lblPerfil != null) {
	        lblPerfil.setIcon(miniatura);
	    }
	}
	
	
}