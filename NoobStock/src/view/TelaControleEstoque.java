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

	// Callbacks para o controller
	private Consumer<Produto> editarAcao;
	private Consumer<Produto> excluirAcao;

	public TelaControleEstoque() throws IOException {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[40px:n][135px:n][][20px:n][grow][grow 10][grow 4][grow 4][grow 4][grow 2]", "[40px:n][35px:n][35px:n][35px:n][35px:n][35px:n][grow][]"));

		lblPerfil = new JLabel("");
		lblPerfil.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/image8.png")));
		add(lblPerfil, "cell 0 0 2 1,alignx center,aligny center");

		JLabel lblNewLabel_1 = new JLabel("Descubra");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(lblNewLabel_1, "cell 0 1 2 1");

		LControleEstoq = new JLabel("Controle de Estoque");
		LControleEstoq.setFont(new Font("Tahoma", Font.BOLD, 16));
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

		Adicionar = new JButton("Adicionar");
		add(Adicionar, "cell 8 2,grow");

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		add(panel, "cell 7 2,grow");

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/calendar.png")));
		panel.add(lblNewLabel_6);

		JSeparator separator = new JSeparator();
		add(separator, "cell 2 0 1 9,gapx 2 2,growy");
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.BLACK);
		
		JLabel lblcontroleEstoq = new JLabel("");
		lblcontroleEstoq.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/caixa(1)1.png")));
		add(lblcontroleEstoq, "cell 0 3,alignx left");
		
		JLabel lblNewLabel = new JLabel("");
		add(lblNewLabel, "flowx,cell 4 3");
		
		// ── TABELA ────────────────────────────────────────────────────────────
		// ── TABELA ────────────────────────────────────────────────────────────
				table = new JTable();
				// --- Linha ~106: Atualize o modelo da tabela ---
				table.setModel(
				    new DefaultTableModel(
				        new Object[][] {},
				        new String[] { "ID", "Produto", "SKU", "Fornecedor", "Quantidade", "Data" } // Substituído "Disponibilidade" por "Fornecedor"
				    ) {
				        Class[] columnTypes = new Class[] {
				            String.class, Object.class, Object.class, Object.class, Object.class, Object.class
				        };
				        public Class getColumnClass(int columnIndex) {
				            return columnTypes[columnIndex];
				        }
				        @Override
				        public boolean isCellEditable(int row, int column) {
				            return false;
				        }
				    }
				);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.setRowHeight(28);

				// 👉 O SEGREDO AQUI: Esconde a coluna ID visualmente para alinhar os textos!
				table.getColumnModel().getColumn(0).setMinWidth(0);
				table.getColumnModel().getColumn(0).setMaxWidth(0);
				table.getColumnModel().getColumn(0).setWidth(0);
		        table.getColumnModel().getColumn(0).setPreferredWidth(0);
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, "cell 4 4 4 3,grow");

//				add(table, "cell 4 4 5 3,grow");
//		add(table, "cell 4 4 5 3,grow");

		// ── POPUP MENU (CLIQUE DIREITO NA TABELA) ─────────────────────────────
		configurarPopupMenu();

		JLabel lblEstatis = new JLabel("");
		lblEstatis.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/grafico.png")));
		add(lblEstatis, "cell 0 4,alignx center");

		imagemOriginal = ImageIO.read(getClass().getResource("/img/logopng.png"));

		JLabel lblEntraSai = new JLabel("");
		lblEntraSai.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/entradaesaida(1)1.png")));
		add(lblEntraSai, "cell 0 5,alignx center");
		
		

		JLabel lblLogo = new JLabel("");
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLogo.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/logopng.png")));
		add(lblLogo, "cell 1 7,growx");

		LInicio = new JLabel("Inicio");
		LInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		LInicio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(LInicio, "cell 1 2,alignx left,aligny center");

		LControleEstoq = new JLabel("Controle de estoque");
		LControleEstoq.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(LControleEstoq, "cell 1 3,alignx left,aligny center");

		LFor = new JLabel("Fornecedores");
		LFor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(LFor, "cell 1 4,alignx left,aligny center");

		LEntraSai = new JLabel("Entrada e saída");
		LEntraSai.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(LEntraSai, "cell 1 5,alignx left,aligny center");
		
		carregarTabelaProdutos();
	}

	// ── CONFIGURA O POPUP DE EDITAR / EXCLUIR ─────────────────────────────────
	private void configurarPopupMenu() {
		JPopupMenu popupMenu = new JPopupMenu();

		JMenuItem itemEditar = new JMenuItem("✏️  Editar produto");
		itemEditar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		itemEditar.addActionListener(e -> {
			Produto selecionado = getProdutoSelecionado();
			if (selecionado != null && editarAcao != null) {
				editarAcao.accept(selecionado);
			}
		});

		JMenuItem itemExcluir = new JMenuItem("🗑️  Excluir produto");
		itemExcluir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		itemExcluir.setForeground(new Color(180, 30, 30));
		itemExcluir.addActionListener(e -> {
			Produto selecionado = getProdutoSelecionado();
			if (selecionado != null && excluirAcao != null) {
				excluirAcao.accept(selecionado);
			}
		});

		popupMenu.add(itemEditar);
		popupMenu.addSeparator();
		popupMenu.add(itemExcluir);

		// Ao clicar com botão DIREITO: seleciona a linha e abre o menu
		// Ao clicar com botão ESQUERDO: seleção normal já funciona por padrão
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

	// ── RETORNA O PRODUTO DA LINHA SELECIONADA NA TABELA ─────────────────────
	private Produto getProdutoSelecionado() {
		int row = table.getSelectedRow();
		if (row < 0) return null;

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String id          = String.valueOf(model.getValueAt(row, 0));
		String nome        = String.valueOf(model.getValueAt(row, 1));
		String sku         = String.valueOf(model.getValueAt(row, 2));
		String quantidade  = String.valueOf(model.getValueAt(row, 4));

		// Busca dados completos do banco para ter localização, fornecedor e categoria
		ProdutoDAO dao = new ProdutoDAO();
		try {
			Produto completo = dao.buscarPorId(Integer.parseInt(id));
			if (completo != null) return completo;
		} catch (NumberFormatException ignored) {}

		// Fallback com os dados da tabela (sem localização/fornecedor/categoria)
		return new Produto(id, sku, nome, quantidade, 0, "", "", "", "");
	}

	// ── RECARREGA A TABELA DO BANCO ───────────────────────────────────────────
	public void recarregarTabela() {
		carregarTabelaProdutos();
	}

	// ── SETTERS DE CALLBACKS ──────────────────────────────────────────────────
	public void setEditarAcao(Consumer<Produto> acao) {
		this.editarAcao = acao;
	}

	public void setExcluirAcao(Consumer<Produto> acao) {
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
	
	// --- Final do arquivo: Método atualizado ---
	private void carregarTabelaProdutos() {
	    DefaultTableModel modelo = (DefaultTableModel) table.getModel();
	    modelo.setRowCount(0);

	    ProdutoDAO dao = new ProdutoDAO();
	    List<Produto> listaProdutos = dao.listarProdutos();

	    for (Produto p : listaProdutos) {
	        // Removemos a lógica de cálculo de disponibilidade
	        
	        modelo.addRow(new Object[]{
	            p.getId_produto(),       // Coluna 0 (ID - Oculta)
	            p.getNome(),             // Coluna 1 (Produto)
	            p.getSKU(),              // Coluna 2 (SKU)
	            p.getFornecedor(),       // Coluna 3 (Agora exibe o Fornecedor)
	            p.getQtd(),              // Coluna 4 (Quantidade)
	           p.getDataCriacao()                     // Coluna 5 (Data)
	        });
	    }
	}
}