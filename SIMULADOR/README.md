# Simulador de Algoritmos de Substituição de Páginas

Este projeto simula e compara o desempenho de diferentes algoritmos de substituição de páginas utilizados em sistemas de gerenciamento de memória virtual.

---

## 🎯 Objetivo

Avaliar algoritmos clássicos de substituição de páginas com base em sequências realistas de acessos à memória.  
O foco da análise é o número de **faltas de página**, e opcionalmente o **tempo de execução**.

---

## 🛠️ Funcionalidades

- Simulação de múltiplos algoritmos de substituição.
- Geração de dados de entrada **realistas** baseados em comportamento de sistemas reais:
  - **Localidade Temporal**: páginas acessadas recentemente são mais prováveis de serem acessadas novamente.
  - **Localidade Espacial**: páginas próximas tendem a ser acessadas juntas.
  - **Saltos Aleatórios**: simula mudanças de contexto.
- Suporte a entrada manual ou geração automática.

---

## 📚 Algoritmos Implementados

| Algoritmo | Descrição |
|:---------|:----------|
| **FIFO** (First In, First Out) | Remove a página mais antiga da memória. |
| **LRU** (Least Recently Used) | Remove a página que não foi usada há mais tempo. |
| **Clock** (Segunda Chance) | FIFO otimizado, dá uma "segunda chance" a páginas usadas recentemente. |
| **Aging** (Envelhecimento) | Aproximação de LRU usando contadores de envelhecimento. |

---

## 📈 Critérios de Comparação

| Métrica             | Descrição |
|---------------------|-----------|
| **Faltas de página** | Quantas vezes uma página precisou ser carregada. Principal métrica avaliada. |
| **Tempo de execução** | (Opcional) Tempo que cada algoritmo levou para processar a fila. |
| **Gráficos de desempenho** | Comparação visual dos resultados. |

---

## 🛂 Dados de Entrada

O simulador recebe as seguintes informações:

| Parâmetro                  | Descrição |
|----------------------------|-----------|
| **Tamanho da memória**     | Quantidade de quadros disponíveis na memória. |
| **Fila de páginas**        | Sequência de páginas a serem acessadas. |
| **Fila de ações**          | Tipo de operação em cada página (`L` = leitura, `E` = escrita). |
| **Estado inicial da memória** | Páginas já carregadas inicialmente (opcional). |
| **Interrupção do relógio** | Intervalo de interrupção do Clock para renovação de bits de uso. |
| **Tau (t)**                | Parâmetro de idade máxima para o algoritmo Aging. |
| **Páginas únicas**         | Lista de páginas distintas presentes. |
| **Quantidade de páginas únicas** | Total de páginas distintas. |

---

## ✨ Geração Realista de Entrada (`GeradorEntrada.java`)

- **Comportamento Simulado**:
  - 70% dos acessos reutilizam páginas próximas (localidade espacial).
  - 30% dos acessos pulam para outra região aleatoriamente (mudança de contexto).
- **Parâmetros Configuráveis**:
  - Número de referências.
  - Range de páginas disponíveis.

A entrada gerada é salva automaticamente em `entrada.json` e pode ser usada para simular os algoritmos.

---

## 🗂️ Exemplo de `entrada.json`

```json
{
  "referencias": [1, 2, 3, 2, 1, 4, 5, 2, 1, 2, 3, 4],
  "tamanhoMemoria": 3,
  "acoes": ["L", "E", "L", "L", "E", "L", "L", "E", "L", "L", "E", "E"],
  "memoriaInicial": [2, 3, 1],
  "interrupcaoRelogio": 4,
  "tau": 3,
  "paginasUnicas": [1, 2, 3, 4, 5],
  "quantidadePaginas": 5,
  "configuracoes": {
    "usarEstadoInicial": false,
    "considerarAcoes": false,
    "usarClockInterrupcao": true,
    "usarTauAging": true
  }
}
```

---

## 📋 Notas Adicionais

- O arquivo de entrada pode ser gerado automaticamente usando o gerador realista ou escrito manualmente.
- Cada algoritmo implementa a interface `AlgoritmoSubstituicao`, facilitando a adição de novos métodos de substituição.
- A estrutura do projeto é modular, permitindo fácil extensão e comparação entre algoritmos.

---

# 🚀 Como Rodar

```bash
// Gerar entrada realista
java util.GeradorEntrada

// Rodar simulador
java principal.Main
```

---

# 🔥 Projeto feito para avaliações acadêmicas e simulações reais de gerenciamento de memória!

