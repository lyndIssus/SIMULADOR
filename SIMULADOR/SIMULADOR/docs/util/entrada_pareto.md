# Documentação Técnica Extra

## 📚 Objetivo da Geração de Dados

A geração de dados utilizada neste simulador é baseada **exclusivamente** na **Lei de Pareto (80/20)**, conforme documentado em literatura clássica de Sistemas Operacionais.

- **80% dos acessos** à memória ocorrem em **apenas 20% das páginas**.
- Este comportamento é conhecido como **localidade de referência** e é amplamente aceito como um padrão real de acesso à memória em processos computacionais.

---

## 🎯 Justificativa Técnica

### Lei de Pareto (80/20)

> A Lei de Pareto, também conhecida como o Princípio de Pareto ou regra 80/20, afirma que 80% dos resultados são derivados de 20% das causas. Essa teoria foi desenvolvida por Vilfredo Pareto em 1906, observando que uma pequena porcentagem das causas é responsável pela maior parte dos efeitos. O princípio pode ser aplicado em diversas áreas, como gestão de tempo e priorização de tarefas, ajudando a focar nos esforços que trazem os melhores resultados

---

## 🛠️ Como foi implementado no projeto

- Dividimos o total de páginas em dois grupos:
    - **20%** das páginas são definidas como **páginas quentes** (acessadas frequentemente).
    - **80%** restantes são **páginas frias** (acessadas raramente).
- **Durante a geração**:
    - **80%** das referências são sorteadas entre as páginas quentes.
    - **20%** das referências são sorteadas entre as páginas frias.
- As ações (Leitura `L` ou Escrita `E`) são atribuídas aleatoriamente a cada acesso.

Essa abordagem proporciona um **cenário realista de uso de memória virtual**, respeitando o comportamento esperado em sistemas operacionais modernos.

---

## 📈 Consequências no Resultado dos Algoritmos

- Algoritmos que aproveitam **localidade de referência** (como LRU e Aging) tendem a apresentar melhor desempenho.
- Algoritmos mais simples (como FIFO) não conseguem explorar tão bem essa localidade, apresentando mais faltas de página.
- Em ambientes gerados com a Lei de Pareto:
    - Clock geralmente melhora levemente o desempenho em relação a FIFO.
    - LRU geralmente supera todos em eficiência.
    - Aging busca aproximar o comportamento do LRU de forma eficiente em software.

---

# 🚀 Conclusão

O simulador foi projetado para trabalhar com uma distribuição de acessos baseada na Lei de Pareto, proporcionando uma avaliação justa e realista do desempenho dos algoritmos de substituição de páginas.

