package model;

public interface OperacaoBD {
	/**
	 * @param operacao O tipo de operação a ser realizada (INCLUSAO, ALTERACAO,
	 *                 EXCLUSAO)
	 * @return Uma String com a mensagem de sucesso ou erro da operação
	 */
	String atualizar(TipoOperacaoBD operacao);
}