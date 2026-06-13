
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
import javax.swing.JOptionPane;

public class TelaAgendamentoConsulta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JComboBox<String> cbxCliente;
	private JComboBox<String> cbxAnimal;
	private JComboBox<String> cbxVeterinario;
	private JTextField txtData;
	private JTextField txtHora;
	private JTextField txtValor;
	private JButton btnAgendar;

	private ConsultaDAO consultaDAO;
	private ClienteDAO clienteDAO;
	private model.VeterinarioDAO veterinarioDAO;
	private model.AnimalDAO animalDAO;

	private java.util.List<model.Cliente> listaClientes;
	private java.util.List<model.Animal> listaAnimais;
	private java.util.List<model.Veterinario> listaVeterinarios;

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
		veterinarioDAO = new model.VeterinarioDAO();
		animalDAO = new model.AnimalDAO();

		setTitle("PetAmigo - Agendamento de Consultas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 420);
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

		JLabel lblVeterinario = new JLabel("Veterinário:");
		lblVeterinario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVeterinario.setBounds(30, 140, 150, 20);
		contentPane.add(lblVeterinario);

		cbxVeterinario = new JComboBox<String>();
		cbxVeterinario.setBounds(180, 140, 200, 22);
		contentPane.add(cbxVeterinario);

		JLabel lblData = new JLabel("Data (DD/MM/AAAA):");
		lblData.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblData.setBounds(30, 180, 150, 20);
		contentPane.add(lblData);

		txtData = new JTextField();
		txtData.setBounds(180, 180, 100, 20);
		contentPane.add(txtData);
		txtData.setColumns(10);

		JLabel lblHora = new JLabel("Horário (HH:MM):");
		lblHora.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHora.setBounds(30, 220, 150, 20);
		contentPane.add(lblHora);

		txtHora = new JTextField();
		txtHora.setBounds(180, 220, 100, 20);
		contentPane.add(txtHora);
		txtHora.setColumns(10);

		JLabel lblValor = new JLabel("Valor (R$):");
		lblValor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblValor.setBounds(30, 260, 150, 20);
		contentPane.add(lblValor);

		txtValor = new JTextField();
		txtValor.setBounds(180, 260, 100, 20);
		contentPane.add(txtValor);
		txtValor.setColumns(10);

		btnAgendar = new JButton("Agendar");
		btnAgendar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAgendar.setBounds(150, 310, 120, 30);
		contentPane.add(btnAgendar);

		btnAgendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agendarAtendimento();
			}
		});

		preencherClientes();
		preencherAnimais();
		preencherVeterinarios();

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
		try {
			listaClientes = clienteDAO.listarClientes();
			for (model.Cliente c : listaClientes) {
				cbxCliente.addItem(c.getNome() + " (" + c.getCpf() + ")");
			}
		} catch (Exception e) {
			System.out.println("Erro ao carregar clientes: " + e.getMessage());
		}
	}

	private void preencherAnimais() {
		cbxAnimal.removeAllItems();
		cbxAnimal.addItem("Selecione um Animal...");
		try {
			listaAnimais = animalDAO.listarAnimais();
			for (model.Animal a : listaAnimais) {
				cbxAnimal.addItem(a.getNome() + " (" + a.getEspecie() + ")");
			}
		} catch (Exception e) {
			System.out.println("Erro ao carregar animais: " + e.getMessage());
		}
	}

	private void preencherVeterinarios() {
		cbxVeterinario.removeAllItems();
		cbxVeterinario.addItem("Selecione um Veterinário...");
		try {
			listaVeterinarios = veterinarioDAO.listarVeterinarios();
			for (model.Veterinario v : listaVeterinarios) {
				cbxVeterinario.addItem(v.getNome() + " (CRMV: " + v.getCrmv() + ")");
			}
		} catch (Exception e) {
			System.out.println("Erro ao carregar veterinarios: " + e.getMessage());
		}
	}

	public void agendarAtendimento() {
		if (cbxCliente.getSelectedIndex() <= 0) {
			JOptionPane.showMessageDialog(this, "Selecione um cliente válido.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (cbxAnimal.getSelectedIndex() <= 0) {
			JOptionPane.showMessageDialog(this, "Selecione um animal válido.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (cbxVeterinario.getSelectedIndex() <= 0) {
			JOptionPane.showMessageDialog(this, "Selecione um veterinário válido.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		String dataStr = txtData.getText().trim();
		String[] dataPartes = dataStr.split("/");
		if (dataPartes.length != 3) {
			JOptionPane.showMessageDialog(this, "Data deve estar no formato DD/MM/AAAA.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		String horaStr = txtHora.getText().trim();
		String[] horaPartes = horaStr.split(":");
		if (horaPartes.length != 2) {
			JOptionPane.showMessageDialog(this, "Horário deve estar no formato HH:MM.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		float valor = 0.0f;
		try {
			valor = Float.parseFloat(txtValor.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Valor inválido. Insira um número decimal (ex: 150.00).", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			int dia = Integer.parseInt(dataPartes[0]);
			int mes = Integer.parseInt(dataPartes[1]);
			int ano = Integer.parseInt(dataPartes[2]);
			model.Data data = new model.Data(dia, mes, ano);

			int horaVal = Integer.parseInt(horaPartes[0]);
			int minVal = Integer.parseInt(horaPartes[1]);
			model.Hora hora = new model.Hora(horaVal, minVal);

			model.Animal selectedAnimal = listaAnimais.get(cbxAnimal.getSelectedIndex() - 1);
			model.Veterinario selectedVet = listaVeterinarios.get(cbxVeterinario.getSelectedIndex() - 1);

			model.Consulta consulta = new model.Consulta();
			consulta.setData(data);
			consulta.setHora(hora);
			consulta.setAnimal(selectedAnimal);
			consulta.setVet(selectedVet);
			consulta.setValor(valor);

			consultaDAO.setConsulta(consulta);
			String res = consultaDAO.atualizar(model.TipoOperacaoBD.INCLUSAO);

			JOptionPane.showMessageDialog(this, res, "Resultado", JOptionPane.INFORMATION_MESSAGE);

			if (res.contains("sucesso")) {
				cbxCliente.setSelectedIndex(0);
				cbxAnimal.setSelectedIndex(0);
				cbxVeterinario.setSelectedIndex(0);
				txtData.setText("");
				txtHora.setText("");
				txtValor.setText("");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao agendar consulta: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}