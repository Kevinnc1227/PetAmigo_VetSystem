// Autor: Kevin
package model;

public interface OperacaoBD {
    /**
     * Executa a operação de atualização (INCLUSAO, ALTERACAO, EXCLUSAO).
     * @param operacao Tipo de operação a ser realizada.
     * @return Mensagem de sucesso ou erro.
     */
    String atualizar(TipoOperacaoBD operacao);

    /**
     * Procura o registro no banco de dados.
     * @return true se encontrado, false caso contrário.
     */
    boolean localizar();
}