package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import controller.ComponentUtils;
import controller.Navegador;
import model.Produto;

public class TelaDetalhesProduto extends JPanel {
	private static final long serialVersionUID = 1L;
	
	// Componentes dinâmicos
	private JLabel lblValorNome, lblValorSKU, lblValorQtd, lblValorLocal, lblValorFornecedor, lblValorCategoria;
	private JLabel Voltar;

	public TelaDetalhesProduto() {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("fillx", "[grow][grow][grow]", "[][][][][][][][][grow]"));
		
		Voltar = new JLabel("");
		Voltar.setIcon(new ImageIcon(TelaAdicionarFornecedor.class.getResource("/img/button→svg.png")));
		add(Voltar, "flowx,cell 0 0,alignx left");
		
				JLabel lblTitulo = new JLabel("Detalhes Completos do Produto");
				lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
				add(lblTitulo, "cell 1 0,alignx left,gapy 20 20");

		JSeparator separator = new JSeparator();
		add(separator, "cell 0 1 3 1, growx");

		// Configuração dos Labels fixos e dinâmicos
		Font fonteTitulo = new Font("Tahoma", Font.BOLD, 14);
		Font fonteValor = new Font("Tahoma", Font.PLAIN, 16);

		add(new JLabel("Nome do Produto:"), "cell 0 2");
		lblValorNome = new JLabel("-");
		lblValorNome.setFont(fonteValor);
		add(lblValorNome, "cell 0 3");

		add(new JLabel("SKU:"), "cell 1 2");
		lblValorSKU = new JLabel("-");
		lblValorSKU.setFont(fonteValor);
		add(lblValorSKU, "cell 1 3");

		add(new JLabel("Fornecedor:"), "cell 2 2");
		lblValorFornecedor = new JLabel("-");
		lblValorFornecedor.setFont(fonteValor);
		add(lblValorFornecedor, "cell 2 3");

		add(new JLabel("Quantidade em Estoque:"), "cell 0 4, gapy 15 0");
		lblValorQtd = new JLabel("-");
		lblValorQtd.setFont(fonteValor);
		add(lblValorQtd, "cell 0 5");

		add(new JLabel("Localização (Prateleira):"), "cell 1 4, gapy 15 0");
		lblValorLocal = new JLabel("-");
		lblValorLocal.setFont(fonteValor);
		add(lblValorLocal, "cell 1 5");

		add(new JLabel("Categoria:"), "cell 2 4, gapy 15 0");
		lblValorCategoria = new JLabel("-");
		lblValorCategoria.setFont(fonteValor);
		add(lblValorCategoria, "cell 2 5");
	}
	
	public void acaoVoltar(Runnable acao) {
		ComponentUtils.transformarEmLink(this.Voltar, acao);
	}

	// ── MÉTODO QUE RECEBE O PRODUTO E ATUALIZA A TELA ──
	public void preencherDados(Produto p) {
		lblValorNome.setText(p.getNome() != null ? p.getNome() : "Não informado");
		lblValorSKU.setText(p.getSKU() != null ? p.getSKU() : "Não informado");
		lblValorQtd.setText(p.getQtd() != null ? p.getQtd() : "0");
		
		lblValorLocal.setText((p.getLocalização() != null && !p.getLocalização().isEmpty()) ? p.getLocalização() : "Não alocado");
		lblValorFornecedor.setText((p.getFornecedor() != null && !p.getFornecedor().isEmpty()) ? p.getFornecedor() : "Sem fornecedor");
		lblValorCategoria.setText((p.getCategoria() != null && !p.getCategoria().isEmpty()) ? p.getCategoria() : "Não categorizado");
	}

	public void acaoVoltar(Object acao) {
		// TODO Auto-generated method stub
		
	}
}