# Documento Técnico — Gráficos Gerados

## 📊 Visão Geral

Este projeto gera gráficos para **visualizar e comparar o desempenho** dos algoritmos de substituição de páginas simulados.

A visualização ajuda a interpretar rapidamente:
- Quantidade de faltas de página.
- Eficiência relativa dos algoritmos.
- Percentual de falhas em relação aos acessos.

---

## 🛠️ Biblioteca Utilizada

- **JFreeChart 1.5.3**
- Complementar: **JCommon 1.0.24**

Bibliotecas populares em Java para construção de gráficos de alto nível, compatíveis com aplicações desktop.

---

## 📈 Tipos de Gráficos Gerados

### 1. Gráfico Comparativo de Faltas de Página
**Classe:** `grafico.GraficoComparativo`

- **Eixo X:** Nome dos algoritmos.
- **Eixo Y:** Número total de faltas de página.
- **Objetivo:** Comparar diretamente o volume de faltas entre algoritmos.

**Características:**
- Gráfico de barras vertical.
- Cada barra representa um algoritmo.
- Cores diferentes para cada algoritmo.

---

### 2. Gráfico Comparativo da Taxa de Faltas (%)
**Classe:** `grafico.GraficoTaxaFalta`

- **Eixo X:** Nome dos algoritmos.
- **Eixo Y:** Taxa de faltas (%) relativa ao total de acessos.
- **Objetivo:** Mostrar eficiência em termos percentuais (mais justo para diferentes cargas de trabalho).

**Características:**
- Gráfico de barras vertical.
- Exibe o percentual com 2 casas decimais.
- Uso de cores suaves para melhor legibilidade.

---

### 3. Gráficos Individuais por Algoritmo
**Classe:** `grafico.GraficoIndividual`

- Para cada algoritmo:
    - **Gera um gráfico exclusivo** com seus próprios dados.
    - Mostra as suas faltas de página e taxa de faltas.

**Objetivo:**
- Permitir análise isolada de cada algoritmo.
- Visualizar performance absoluta sem interferência dos demais.

---

## 🎨 Detalhes Visuais

- **Cores personalizadas** para melhor diferenciação.
- **Fonte Arial Bold** para números e rótulos.
- **Tamanho 900x600px** (largura x altura) nos frames de gráficos.
- **Grade de fundo leve** para facilitar a leitura dos eixos.

---

## 🧠 Interpretação dos Gráficos

| Cenário | Interpretação |
|---------|---------------|
| Barra menor em "Faltas" | Algoritmo teve menos faltas → melhor eficiência |
| Taxa de falta menor (%) | Algoritmo aproveitou melhor a memória |
| Gráfico individual alto | Algoritmo foi menos eficiente |
| Gráfico individual baixo | Algoritmo foi mais eficiente |

---

## 📍 Localização no Projeto

Os gráficos são gerados dinamicamente após a execução da `Main`:
- Não são salvos em arquivo (apenas exibidos em tela).
- Podem ser fechados individualmente.

---

## 🔥 Exemplos Visuais

**Comparativo de Faltas:**
```
| FIFO    | ██████████████ 683 faltas
| Clock   | █████████████  682 faltas
```

**Comparativo de Taxas:**
```
| FIFO    | 68.30%
| Clock   | 68.20%
```

**Gráfico Individual FIFO:**
```
"FIFO: 683 faltas de página, 31.7% de acertos."
```

---

## 📝 Observações

- Em execuções maiores (ex: 10000 referências), a diferença entre algoritmos fica mais visível.
- A leitura gráfica complementa a análise numérica exibida no console.

---

