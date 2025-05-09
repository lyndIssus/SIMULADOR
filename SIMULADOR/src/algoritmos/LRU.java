package algoritmos;

import util.ResultadoSimulacao;
import java.util.*;

public class LRU implements AlgoritmoSubstituicao {
    @Override
    public ResultadoSimulacao executar(List<Integer> referencias, int tamanhoMemoria) {
        Set<Integer> memoria = new LinkedHashSet<>();
        int faltas = 0;
        for (int pagina : referencias) {
            if (!memoria.contains(pagina)) {
                faltas++;
                if (memoria.size() == tamanhoMemoria) {
                    Iterator<Integer> iterator = memoria.iterator();
                    iterator.next();
                    iterator.remove();
                }
                memoria.add(pagina);
            } else {
                memoria.remove(pagina);
                memoria.add(pagina);
            }
        }
        return new ResultadoSimulacao("LRU", faltas, referencias.size());
    }

    @Override
    public ResultadoSimulacao executarComEstadoInicial(List<Integer> referencias, int tamanhoMemoria, List<Integer> memoriaInicial) {

        LinkedHashSet<Integer> memoria = new LinkedHashSet<>(memoriaInicial);
        int faltas = 0;
        for (int pagina : referencias) {
            if (!memoria.contains(pagina)) {
                faltas++;
                if (memoria.size() == tamanhoMemoria) {
                    Iterator<Integer> iterator = memoria.iterator();
                    iterator.next();
                    iterator.remove();
                }
                memoria.add(pagina);
            } else {
                memoria.remove(pagina);
                memoria.add(pagina);
            }
        }

        return new ResultadoSimulacao("LRU", faltas, referencias.size());
    }
}