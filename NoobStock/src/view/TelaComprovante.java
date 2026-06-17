package view;

import model.ItemSaida;
import model.SaidaEstoque;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.ComponentUtils;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TelaComprovante extends JDialog {

	private static final long serialVersionUID = 1L;

	private final SaidaEstoque saida;
	private final String htmlContent;
	private final String dataHoraRegistro;

	public TelaComprovante(JFrame parent, SaidaEstoque saida) {
		super(parent, "Comprovante de Saída de Estoque", true);
		this.saida = saida;

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		this.dataHoraRegistro = dtf.format(LocalDateTime.now());
		this.htmlContent = gerarHtml();

		construirInterface();
	}

	private void construirInterface() {
		setSize(620, 660);
		setMinimumSize(new Dimension(520, 500));
		setLocationRelativeTo(getOwner());
		setResizable(true);
		getContentPane().setLayout(new BorderLayout());
		JPanel panelHeader = new JPanel(new BorderLayout());
		panelHeader.setBackground(new Color(20, 20, 20));
		panelHeader.setBorder(new EmptyBorder(12, 18, 12, 18));

		JLabel lblTitulo = new JLabel("Saída Registrada com Sucesso!");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTitulo.setForeground(Color.WHITE);
		panelHeader.add(lblTitulo, BorderLayout.WEST);

		JLabel lblNum = new JLabel("Nº " + saida.getId());
		lblNum.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNum.setForeground(new Color(180, 180, 180));
		panelHeader.add(lblNum, BorderLayout.EAST);

		getContentPane().add(panelHeader, BorderLayout.NORTH);

		JEditorPane editorPane = new JEditorPane("text/html", htmlContent);
		editorPane.setEditable(false);
		editorPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		editorPane.setBackground(Color.WHITE);

		JScrollPane scroll = new JScrollPane(editorPane);
		scroll.setBorder(null);
		getContentPane().add(scroll, BorderLayout.CENTER);
		SwingUtilities.invokeLater(() -> editorPane.setCaretPosition(0));

		JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 10));
		panelBotoes.setBackground(new Color(245, 245, 245));
		panelBotoes.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(220, 220, 220)));

		JButton btnBaixar = new JButton("Baixar Comprovante");
		btnBaixar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnBaixar.setBackground(new Color(20, 130, 60));
		btnBaixar.setForeground(Color.WHITE);
		btnBaixar.setFocusPainted(false);
		btnBaixar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBaixar.addActionListener(e -> baixarComprovante());
		panelBotoes.add(btnBaixar);

		JButton btnFechar = new JButton("  Fechar  ");
		btnFechar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnFechar.setBackground(new Color(60, 60, 60));
		btnFechar.setForeground(Color.WHITE);
		btnFechar.setFocusPainted(false);
		btnFechar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFechar.addActionListener(e -> dispose());
		panelBotoes.add(btnFechar);

		getContentPane().add(panelBotoes, BorderLayout.SOUTH);
		
	

	}

	private String gerarHtml() {
		StringBuilder sb = new StringBuilder();

		sb.append("<!DOCTYPE html><html><head>").append("<meta charset='UTF-8'>").append("<style>")
				.append("  body { font-family: Arial, sans-serif; margin: 20px; color: #222; background: #fff; }")
				.append("  .header { border-bottom: 3px solid #111; padding-bottom: 12px; margin-bottom: 18px; }")
				.append("  .logo { font-size: 28px; font-weight: bold; letter-spacing: 3px; color: #111; }")
				.append("  .titulo-doc { font-size: 15px; color: #555; margin-top: 4px; }")
				.append("  .info-bloco { margin-bottom: 16px; }")
				.append("  .info-row { display: flex; margin-bottom: 5px; font-size: 13px; }")
				.append("  .info-label { font-weight: bold; min-width: 140px; color: #333; }")
				.append("  .badge { background: #111; color: #fff; padding: 2px 10px;")
				.append("           border-radius: 10px; font-size: 12px; }")
				.append("  table { width: 100%; border-collapse: collapse; margin-top: 6px; }")
				.append("  th { background: #111; color: #fff; padding: 9px 12px;")
				.append("       text-align: left; font-size: 13px; }")
				.append("  td { padding: 8px 12px; border-bottom: 1px solid #e0e0e0; font-size: 13px; }")
				.append("  tr:nth-child(even) td { background: #f7f7f7; }")
				.append("  .total-box { margin-top: 14px; padding: 10px 14px;")
				.append("               background: #f0f0f0; border-radius: 6px; text-align: right; }")
				.append("  .total-label { font-size: 14px; color: #333; }")
				.append("  .total-valor { font-size: 18px; font-weight: bold; color: #111; }")
				.append("  .footer { margin-top: 22px; border-top: 1px solid #ddd;")
				.append("            padding-top: 10px; font-size: 11px; color: #999; text-align: center; }")
				.append("</style></head><body>");

		sb.append("<div class='header'>").append("<div class='logo'>NoobStock</div>")
				.append("<div class='titulo-doc'>Comprovante de Saída de Estoque</div>").append("</div>");
		sb.append("<div class='info-bloco'>");
		infoRow(sb, "Nº da Saída:", "#" + saida.getId());
		infoRow(sb, "Data / Hora:", dataHoraRegistro);
		infoRow(sb, "Responsável:", saida.getResponsavel());

		String obs = saida.getObservacao();
		if (obs != null && !obs.trim().isEmpty()) {
			infoRow(sb, "Observação:", obs);
		}

		sb.append("<div class='info-row'>").append("<span class='info-label'>Status:</span>")
				.append("<span class='badge'>Conclu&#237;da</span>").append("</div>");
		sb.append("</div>");

		sb.append("<table>").append("<thead><tr>").append("<th>Produto</th><th>SKU</th><th>Quantidade</th>")
				.append("</tr></thead><tbody>");

		int totalUnidades = 0;
		for (ItemSaida item : saida.getItens()) {
			sb.append("<tr>").append("<td>").append(esc(item.getProduto().getNome())).append("</td>").append("<td>")
					.append(esc(item.getProduto().getSKU())).append("</td>").append("<td>").append(item.getQuantidade())
					.append(" un.</td>").append("</tr>");
			totalUnidades += item.getQuantidade();
		}

		sb.append("</tbody></table>");

		// ── Total ─────────────────────────────────────────────────────────────
		sb.append("<div class='total-box'>").append("<span class='total-label'>Total de unidades enviadas: </span>")
				.append("<span class='total-valor'>").append(totalUnidades).append("</span>").append("</div>");
		sb.append("<div class='footer'>").append("Documento gerado automaticamente pelo sistema NoobStock &bull; ")
				.append(dataHoraRegistro).append("</div>");

		sb.append("</body></html>");
		return sb.toString();
	}

	private void infoRow(StringBuilder sb, String label, String valor) {
		sb.append("<div class='info-row'>").append("<span class='info-label'>").append(label).append("</span>")
				.append("<span>").append(esc(valor)).append("</span>").append("</div>");
	}

	private String esc(String texto) {
		if (texto == null)
			return "";
		return texto.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
	}

	private void baixarComprovante() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Salvar Comprovante de Saída");
		String nomeArquivo = "comprovante_saida_" + saida.getId() + ".html";
		fc.setSelectedFile(new File(nomeArquivo));

		fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivo HTML (*.html)", "html"));

		int resultado = fc.showSaveDialog(this);
		if (resultado != JFileChooser.APPROVE_OPTION)
			return;

		File arquivo = fc.getSelectedFile();
		if (!arquivo.getName().toLowerCase().endsWith(".html")) {
			arquivo = new File(arquivo.getAbsolutePath() + ".html");
		}

		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter(arquivo, java.nio.charset.StandardCharsets.UTF_8))) {
			writer.write(htmlContent);

			JOptionPane.showMessageDialog(this, "Comprovante salvo com sucesso!\n\n" + arquivo.getAbsolutePath(),
					"Download concluído", JOptionPane.INFORMATION_MESSAGE);
			try {
				Desktop.getDesktop().browse(arquivo.toURI());
			} catch (Exception ignored) {
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Erro ao salvar o arquivo:\n" + ex.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
