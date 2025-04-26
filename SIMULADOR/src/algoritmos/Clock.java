package algoritmos;

import util.ResultadoSimulacao;
import java.util.*;

public class Clock implements AlgoritmoSubstituicao {

    @Override
    public ResultadoSimulacao executar(List<Integer> referencias, int tamanhoMemoria) {
        List<Integer> memoria = new ArrayList<>(Collections.nCopies(tamanhoMemoria, -1));
        Map<Integer, Boolean> bitsDeUso = new HashMap<>();
        int ponteiro = 0;
        int faltas = 0;

        for (int pagina : referencias) {
            if (memoria.contains(pagina)) {
                bitsDeUso.put(pagina, true);
            } else {
                faltas++;

                while (true) {
                    int paginaAtual = memoria.get(ponteiro);

                    if (paginaAtual == -1 || !bitsDeUso.getOrDefault(paginaAtual, false)) {
                        if (paginaAtual != -1) {
                            bitsDeUso.remove(paginaAtual);
                        }
                        memoria.set(ponteiro, pagina);
                        bitsDeUso.put(pagina, true);
                        ponteiro = (ponteiro + 1) % tamanhoMemoria;
                        break;
                    } else {
                        bitsDeUso.put(paginaAtual, false);
                        ponteiro = (ponteiro + 1) % tamanhoMemoria;
                    }
                }
            }
        }

        return new ResultadoSimulacao("Clock", faltas, referencias.size());
    }

    @Override
    public ResultadoSimulacao executarComEstadoInicial(List<Integer> referencias, int tamanhoMemoria, List<Integer> memoriaInicial) {
        List<Integer> memoria = new ArrayList<>(Collections.nCopies(tamanhoMemoria, -1));
        Map<Integer, Boolean> bitsDeUso = new HashMap<>();
        int ponteiro = 0;
        int faltas = 0;

        // Preenche memória inicial
        for (int i = 0; i < memoriaInicial.size() && i < tamanhoMemoria; i++) {
            memoria.set(i, memoriaInicial.get(i));
            bitsDeUso.put(memoriaInicial.get(i), true);
        }

        for (int pagina : referencias) {
            if (memoria.contains(pagina)) {
                bitsDeUso.put(pagina, true);
            } else {
                faltas++;

                while (true) {
                    int paginaAtual = memoria.get(ponteiro);

                    if (paginaAtual == -1 || !bitsDeUso.getOrDefault(paginaAtual, false)) {
                        if (paginaAtual != -1) {
                            bitsDeUso.remove(paginaAtual);
                        }
                        memoria.set(ponteiro, pagina);
                        bitsDeUso.put(pagina, true);
                        ponteiro = (ponteiro + 1) % tamanhoMemoria;
                        break;
                    } else {
                        bitsDeUso.put(paginaAtual, false);
                        ponteiro = (ponteiro + 1) % tamanhoMemoria;
                    }
                }
            }
        }

        return new ResultadoSimulacao("Clock", faltas, referencias.size());
    }
}
