package algoritmos;

import util.ResultadoSimulacao;
import java.util.List;

public interface AlgoritmoSubstituicao {
    ResultadoSimulacao executar(List<Integer> referencias, int tamanhoMemoria);
    ResultadoSimulacao executarComEstadoInicial(List<Integer> referencias, int tamanhoMemoria, List<Integer> memoriaInicial);
}
