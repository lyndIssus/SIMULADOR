# Documento Técnico — FIFO.java

## Nome da Classe
FIFO

## Pacote
```java
package algoritmos;
```

## Descrição Geral
Esta classe implementa o algoritmo de substituição de páginas **FIFO (First In, First Out)**.

O algoritmo FIFO remove sempre a **página mais antiga** da memória quando precisa dar espaço a uma nova página.

## Responsabilidades
- Controlar o conjunto de páginas em memória.
- Contar o número de faltas de página.
- Aplicar a política FIFO de substituição de páginas.
- Permitir execução com ou sem memória inicial carregada.

## Estruturas Usadas
| Estrutura | Função |
|-----------|--------|
| `Set<Integer> memoria` | Guarda as páginas atualmente na memória. |
| `Queue<Integer> fila` | Guarda a ordem de inserção das páginas, garantindo FIFO. |

## Métodos Públicos
| Método | Descrição |
|--------|-----------|
| `ResultadoSimulacao executar(List<Integer> referencias, int tamanhoMemoria)` | Executa o algoritmo FIFO iniciando memória vazia. |
| `ResultadoSimulacao executarComEstadoInicial(List<Integer> referencias, int tamanhoMemoria, List<Integer> memoriaInicial)` | Executa o algoritmo FIFO começando com páginas já carregadas. |

## Fluxo de Execução
1. Para cada página da sequência:
    - Se já estiver na memória → acerto (não faz nada).
    - Se não estiver na memória → falta:
        - Se a memória estiver cheia:
            - Remove a página mais antiga (fila.poll()).
            - Remove também da memória (memoria.remove()).
        - Adiciona a nova página à fila e à memória.

## Exemplo de Funcionamento
Referências: `1 2 3 4`
Memória com 3 quadros.

| Passo | Memória antes | Ação | Memória depois |
|------|---------------|------|----------------|
| 1    | []             | Falta → adiciona 1 | [1] |
| 2    | [1]            | Falta → adiciona 2 | [1,2] |
| 3    | [1,2]          | Falta → adiciona 3 | [1,2,3] |
| 4    | [1,2,3]        | Falta → remove 1 e adiciona 4 | [2,3,4] |

## Código Base
```java
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
```

## Pontos Fortes
- Simplicidade de implementação.
- Baixo custo computacional.

## Limitações
- Sofre com a **anomalia de Belady**.
- Não leva em conta padrões de acesso (somente ordem de chegada).

## Observação
Este algoritmo é adequado para ambientes onde a simplicidade e a previsibilidade são mais importantes do que a eficiência máxima em cache.
