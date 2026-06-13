package view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import model.Consulta;
import model.ConsultaDAO;

public class TelaConsultaAgendamentos extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTable tblConsultas;
	private JScrollPane scrollPane;
	private JButton btnAtualizar;

	private ConsultaDAO consultaDAO;

	public TelaConsultaAgendamentos() {

		setTitle("Consulta de Agendamentos");
		setSize(800, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		consultaDAO = new ConsultaDAO();

		inicializarComponentes();

		carregarConsultas();
		// É só trocar o final pelo nome da nova foto (ex: "foto_cliente.png")
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

		btnAtualizar.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        carregarConsultas();
    }
});

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

				model.addRow(new Object[] { consulta.getData(), consulta.getHora(), consulta.getAnimal().getNome(),
						consulta.getVeterinario().getNome(), consulta.getValor() });
			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(this, "Erro ao carregar consultas: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		TelaConsultaAgendamentos tela = new TelaConsultaAgendamentos();
		tela.setVisible(true);
	}

}
