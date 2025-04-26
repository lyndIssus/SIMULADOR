package util;

public class MensagemErro {
    public static void exibir(String contexto, Exception e) {
        System.err.println("[" + contexto + "] Erro: " + e.getMessage());
    }

    public static void faltaDeCampo(String campo) {
        System.err.println("Campo obrigatório ausente no JSON: " + campo);
    }

    public static void algoritmoNaoExecutado(String nome) {
        System.err.println("Algoritmo '" + nome + "' não pode ser executado.");
    }
}
