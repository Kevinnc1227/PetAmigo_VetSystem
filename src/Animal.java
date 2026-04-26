public class Animal {
    private int id;
    private String nome;
    private TipoAnimal especie;
    private float peso;
    private Prontuario prontuario;

    public Animal(int id, String nome, TipoAnimal especie, float peso) {
        this.setId(id);
        this.setNome(nome);
        this.setEspecie(especie);
        this.setPeso(peso);
        this.prontuario = new Prontuario();
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoAnimal getEspecie() {
		return this.especie;
	}

	public void setEspecie(TipoAnimal especie) {
		this.especie = especie;
	}

	public float getPeso() {
		return this.peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public Prontuario getProntuario() {
		return this.prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}
	@Override
	public String toString() {
	    return "Animal:\n" +
	           "Id = " + id + "\n" +
	           "Nome = " + nome + "\n" +
	           "Especie = " + especie + "\n" +
	           "Peso = " + peso;
	}
}
