// Autor: Gabriel Lucas
package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Consulta;
import model.ConsultaDAO;

public class TelaConsultaAgendamentos extends JDialog {
	private static final long serialVersionUID = 1L;

	private JTable tblConsultas;
	private JScrollPane scrollPane;
	private JButton btnAtualizar;

	private ConsultaDAO consultaDAO;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				TelaConsultaAgendamentos dialog = new TelaConsultaAgendamentos();
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public TelaConsultaAgendamentos() {
		super((Frame) null, true);
		initGUI();
	}

	public TelaConsultaAgendamentos(Frame parent, boolean modal) {
		super(parent, modal);
		initGUI();
	}

	private void initGUI() {
		setTitle("Consulta de Agendamentos");
		setSize(800, 400);
		setLocationRelativeTo(getOwner());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		consultaDAO = new ConsultaDAO();

		inicializarComponentes();

		carregarConsultas();

		java.net.URL url = getClass().getResource("/Pictures/foto de perfil do Pixel dog.jpg");

		if (url != null) {
			java.awt.Image icone = java.awt.Toolkit.getDefaultToolkit().getImage(url);
			this.setIconImage(icone);
		} else {
			System.out.println("Erro: Não encontrei a nova imagem!");
		}
	}

	private void inicializarComponentes() {
		setLayout(new BorderLayout());

		String[] colunas = { "Data", "Hora", "Animal", "Veterinário", "Valor" };

		DefaultTableModel model = new DefaultTableModel(colunas, 0);

		tblConsultas = new JTable(model);

		scrollPane = new JScrollPane(tblConsultas);

		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(e -> carregarConsultas());

		JPanel painelInferior = new JPanel();
		painelInferior.add(btnAtualizar);

		add(scrollPane, BorderLayout.CENTER);
		add(painelInferior, BorderLayout.SOUTH);
	}

	private void carregarConsultas() {
		DefaultTableModel model = (DefaultTableModel) tblConsultas.getModel();
		model.setRowCount(0);

		try {
			List<Consulta> consultas = consultaDAO.listarConsultas();

			for (Consulta consulta : consultas) {
				model.addRow(new Object[] { 
					consulta.getData(), 
					consulta.getHora(), 
					consulta.getAnimal().getNome(),
					consulta.getVeterinario().getNome(), 
					consulta.getValor() 
				});
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao carregar consultas: " + e.getMessage());
		}
	}
}
