public class Main {
	public static void main(String[] args) {
		System.out.println("Olá mundo");
		Animal an = new Animal(12 , "susu" , TipoAnimal.CACHORRO , 3);
		System.out.println(an.toString());
		an.getProntuario().setHistorico("Saudável");
		an.getProntuario().setUltimaVacina("Antirrábica");
		an.getProntuario().setObservacoes("Nenhuma");
		System.out.println(an.getProntuario());
	}

}
