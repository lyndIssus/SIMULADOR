package algoritmos;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import util.ResultadoSimulacao;
import java.util.*;

public class Aging implements AlgoritmoSubstituicao {
    private static class Pagina {
        int numero;
        int contador;
        Pagina(int numero) {
            this.numero = numero;
            this.contador = 0;
        }
    }

    @Override
    public ResultadoSimulacao executar(List<Integer> referencias, int tamanhoMemoria) {
        Map<Integer, Pagina> memoria = new HashMap<>();
        Queue<Pagina> fila = new LinkedList<>();
        int faltas = 0;
        for (int numeroPagina : referencias) {
            for (Pagina p : fila) {
                p.contador >>= 1;
            }

            if (memoria.containsKey(numeroPagina)) {

                Pagina p = memoria.get(numeroPagina);
                p.contador |= 1 << 7;
            } else {
                faltas++;

                if (memoria.size() == tamanhoMemoria) {

                    Pagina menor = Collections.min(fila, Comparator.comparingInt(p -> p.contador));
                    fila.remove(menor);
                    memoria.remove(menor.numero);
                }

                Pagina nova = new Pagina(numeroPagina);
                nova.contador = 1 << 7;
                fila.add(nova);
                memoria.put(numeroPagina, nova);
            }
        }

        return new ResultadoSimulacao("Aging", faltas, referencias.size());
    }

    @Override
    public ResultadoSimulacao executarComEstadoInicial(List<Integer> referencias, int tamanhoMemoria, List<Integer> memoriaInicial) {
        Set<Integer> memoria = new HashSet<>(memoriaInicial);
        Queue<Pagina> fila = new LinkedList<>();
        Map<Integer, Pagina> paginaMap = new HashMap<>();
        int faltas = 0;
        for (int numero : memoriaInicial) {
            Pagina p = new Pagina(numero);
            paginaMap.put(numero, p);
            fila.add(p);
        }

        for (int numeroPagina : referencias) {
            for (Pagina p : fila) {
                p.contador >>= 1;
            }

            if (memoria.contains(numeroPagina)) {

                Pagina p = paginaMap.get(numeroPagina);
                p.contador |= 1 << 7;
            } else {
                faltas++;
                if (memoria.size() == tamanhoMemoria) {
                    Pagina menor = Collections.min(fila, Comparator.comparingInt(p -> p.contador));
                    fila.remove(menor);
                    memoria.remove(menor.numero);
                    paginaMap.remove(menor.numero);
                }

                Pagina nova = new Pagina(numeroPagina);
                nova.contador = 1 << 7;
                fila.add(nova);
                memoria.add(numeroPagina);
                paginaMap.put(numeroPagina, nova);
            }
        }

        return new ResultadoSimulacao("Aging", faltas, referencias.size());
    }

}
