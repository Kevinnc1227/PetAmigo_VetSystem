public class Consulta extends Animal {
	private Data data;
	private Hora hora;
	private Animal animal;
	private Veterinario vet;
	private float valor;
	private Pagamento pagamento;

	public Consulta() {

	}

	public Data getData() {
		return data;
	}

	public void setData(Data) {
		this.data = data;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float v) {
		this.valor = v;
	}

	public void realizarAtendimento() {
	
		System.out.println("Atendimento realizado para o animal: " + this.animal);
	}
