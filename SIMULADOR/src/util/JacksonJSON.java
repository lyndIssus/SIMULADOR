package util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JacksonJSON {
    public static ConfiguracaoSimulacao carregar(String caminho) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(caminho), ConfiguracaoSimulacao.class);
    }
}
