package util;

import java.util.List;

public class ConfiguracaoSimulacao {
    public int tamanhoMemoria;
    public List<Integer> referencias;
    public List<String> acoes;
    public List<Integer> memoriaInicial;
    public int interrupcaoRelogio;
    public int tau;
    public List<Integer> paginasUnicas;
    public int quantidadePaginas;
    public Configuracoes configuracoes;

    public static class Configuracoes {
        public boolean usarEstadoInicial;
        public boolean considerarAcoes;
        public boolean usarClockInterrupcao;
        public boolean usarTauAging;
    }

    @Override
    public String toString() {
        return "Referências: " + referencias +
                "\nTamanho da memória: " + tamanhoMemoria +
                "\nAções: " + acoes +
                "\nMemória inicial: " + memoriaInicial +
                "\nInterrupção do relógio: " + interrupcaoRelogio +
                "\nt (tau): " + tau +
                "\nPáginas únicas: " + paginasUnicas +
                "\nQuantidade de páginas: " + quantidadePaginas +
                "\nConfigurações: " +
                "\n  - Usar estado inicial: " + configuracoes.usarEstadoInicial +
                "\n  - Considerar ações: " + configuracoes.considerarAcoes +
                "\n  - Usar interrupção no Clock: " + configuracoes.usarClockInterrupcao +
                "\n  - Usar tau no Aging: " + configuracoes.usarTauAging;
    }
}
