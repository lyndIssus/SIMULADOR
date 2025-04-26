package algoritmos;

import util.ResultadoSimulacao;
import java.util.*;

public class FIFO implements AlgoritmoSubstituicao {

    @Override
    public ResultadoSimulacao executar(List<Integer> referencias, int tamanhoMemoria) {
        Set<Integer> memoria = new HashSet<>();
        Queue<Integer> fila = new LinkedList<>();
        int faltas = 0;

        for (int pagina : referencias) {
            if (!memoria.contains(pagina)) {
                faltas++;

                if (memoria.size() == tamanhoMemoria) {
                    int removida = fila.poll();
                    memoria.remove(removida);
                }

                fila.add(pagina);
                memoria.add(pagina);
            }
        }

        return new ResultadoSimulacao("FIFO", faltas, referencias.size());
    }

    @Override
    public ResultadoSimulacao executarComEstadoInicial(List<Integer> referencias, int tamanhoMemoria, List<Integer> memoriaInicial) {
        Set<Integer> memoria = new HashSet<>(memoriaInicial);
        Queue<Integer> fila = new LinkedList<>(memoriaInicial);
        int faltas = 0;

        for (int pagina : referencias) {
            if (!memoria.contains(pagina)) {
                faltas++;

                if (memoria.size() == tamanhoMemoria) {
                    int removida = fila.poll();
                    memoria.remove(removida);
                }

                fila.add(pagina);
                memoria.add(pagina);
            }
        }

        return new ResultadoSimulacao("FIFO", faltas, referencias.size());
    }
}
