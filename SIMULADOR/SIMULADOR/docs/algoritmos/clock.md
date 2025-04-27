# Documento Técnico — Clock.java

## Nome da Classe
Clock

## Pacote
```java
package algoritmos;
```

## Descrição Geral
Esta classe implementa o algoritmo de substituição de páginas **Clock (Segunda Chance)**.

O algoritmo Clock é uma variação do FIFO:
- Mantém a ordem de chegada como o FIFO.
- Dá a cada página uma **segunda chance** antes de removê-la, usando um **bit de uso** (flag).

É uma solução intermediária entre FIFO e LRU, equilibrando simplicidade e eficiência.

## Responsabilidades
- Controlar o conjunto de páginas em memória.
- Gerenciar bits de uso associados a cada página.
- Substituir páginas considerando se foram recentemente acessadas.
- Permitir execução com ou sem estado inicial.

## Estruturas Usadas
| Estrutura | Função |
|-----------|--------|
| `List<Integer> memoria` | Guarda as páginas carregadas em posições fixas (simulando a volta do ponteiro). |
| `Map<Integer, Boolean> bitsDeUso` | Guarda o bit de uso (true ou false) de cada página. |
| `int ponteiro` | Aponta para a página da memória a ser analisada/substituída. |

## Métodos Públicos
| Método | Descrição |
|--------|-----------|
| `ResultadoSimulacao executar(List<Integer> referencias, int tamanhoMemoria)` | Executa o algoritmo Clock com memória vazia inicialmente. |
| `ResultadoSimulacao executarComEstadoInicial(List<Integer> referencias, int tamanhoMemoria, List<Integer> memoriaInicial)` | Executa o Clock partindo de um estado inicial carregado. |

## Fluxo de Execução
1. Para cada página referenciada:
    - Se já está na memória → atualiza o **bit de uso** para `true`.
    - Se não está na memória (falta):
        - Procura na memória:
            - Se encontrar uma página com **bit de uso = false** → substitui esta página.
            - Se bit de uso = true → zera o bit e avança o ponteiro.
        - Quando substitui:
            - Remove o antigo, coloca o novo e seta bit de uso `true`.

## Exemplo de Funcionamento
Referências: `1 2 3 1 4`
Memória com 3 quadros.

| Passo | Memória antes | Bit de Uso | Ação | Memória depois | Ponteiro |
|------|----------------|------------|------|----------------|----------|
| 1    | [ -1, -1, -1 ]  | (todos 0)  | Falta → carrega 1 | [1, -1, -1] | 1 |
| 2    | [1, -1, -1]     | (1:1)      | Falta → carrega 2 | [1,2,-1] | 2 |
| 3    | [1,2,-1]        | (1:1, 2:1) | Falta → carrega 3 | [1,2,3] | 0 |
| 4    | [1,2,3]         | (1:1,2:1,3:1) | Acerto → bit 1 já 1 | [1,2,3] | 0 |
| 5    | [1,2,3]         | (1:1,2:1,3:1) | Falta → roda o relógio removendo quem tiver bit=0 | [4,2,3] | 1 |

## Código Base
```java
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
                if (paginaAtual != -1) bitsDeUso.remove(paginaAtual);
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
```

## Pontos Fortes
- Reduz o número de substituições desnecessárias em relação ao FIFO.
- Mantém boa performance com custo computacional baixo.

## Limitações
- Mais complexo do que FIFO.
- Ainda pode tomar decisões subótimas, pois não armazena informações completas de uso recente.

## Observação
O algoritmo Clock é amplamente usado em sistemas reais por oferecer um bom compromisso entre simplicidade e desempenho, sendo uma alternativa prática ao LRU completo.

