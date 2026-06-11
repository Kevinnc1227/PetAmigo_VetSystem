package view;

import java.awt.Font;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.ConsultaDAO;
import model.ClienteDAO;

public class TelaAgendamentoConsulta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JComboBox<String> cbxCliente;
	private JComboBox<String> cbxAnimal;
	private JTextField txtData;
	private JTextField txtHora;
	private JButton btnAgendar;

	private ConsultaDAO consultaDAO;
	private ClienteDAO clienteDAO;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAgendamentoConsulta frame = new TelaAgendamentoConsulta();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaAgendamentoConsulta() {

		consultaDAO = new ConsultaDAO();
		clienteDAO = new ClienteDAO();

		setTitle("PetAmigo - Agendamento de Consultas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Agendar Nova Consulta");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(110, 11, 220, 25);
		contentPane.add(lblTitulo);

		JLabel lblCliente = new JLabel("Selecione o Cliente:");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCliente.setBounds(30, 60, 150, 20);
		contentPane.add(lblCliente);

		cbxCliente = new JComboBox<String>();
		cbxCliente.setBounds(180, 60, 200, 22);
		contentPane.add(cbxCliente);

		JLabel lblAnimal = new JLabel("Selecione o Animal:");
		lblAnimal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAnimal.setBounds(30, 100, 150, 20);
		contentPane.add(lblAnimal);

		cbxAnimal = new JComboBox<String>();
		cbxAnimal.setBounds(180, 100, 200, 22);
		contentPane.add(cbxAnimal);

		JLabel lblData = new JLabel("Data (DD/MM/AAAA):");
		lblData.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblData.setBounds(30, 140, 150, 20);
		contentPane.add(lblData);

		txtData = new JTextField();
		txtData.setBounds(180, 140, 100, 20);
		contentPane.add(txtData);
		txtData.setColumns(10);

		JLabel lblHora = new JLabel("Horário (HH:MM):");
		lblHora.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHora.setBounds(30, 180, 150, 20);
		contentPane.add(lblHora);

		txtHora = new JTextField();
		txtHora.setBounds(180, 180, 100, 20);
		contentPane.add(txtHora);
		txtHora.setColumns(10);

		btnAgendar = new JButton("Agendar");
		btnAgendar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAgendar.setBounds(150, 240, 120, 30);
		contentPane.add(btnAgendar);

		btnAgendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agendarAtendimento();
			}
		});

		preencherClientes();
		java.net.URL url = getClass().getResource("/Pictures/Popcat Cartoon.jpg");

		if (url != null) {
			java.awt.Image icone = java.awt.Toolkit.getDefaultToolkit().getImage(url);
			this.setIconImage(icone);
		} else {
			System.out.println("Erro: Não encontrei a nova imagem!");
		}
	}

	private void preencherClientes() {
		cbxCliente.removeAllItems();
		cbxCliente.addItem("Selecione um Cliente...");

		cbxAnimal.removeAllItems();
		cbxAnimal.addItem("Selecione um Animal...");
	}

	public void agendarAtendimento() {
		System.out.println("Botão agendar clicado! Data: " + txtData.getText() + " Hora: " + txtHora.getText());

	}
}