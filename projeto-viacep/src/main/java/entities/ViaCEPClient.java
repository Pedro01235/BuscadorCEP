package entities;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class ViaCEPClient {

    public static Endereco buscaEnderecoPeloCEP(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return parseEndereco(response.body());
            } else {
                System.err.println("Erro ao buscar o CEP: " + response.statusCode());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao enviar requisição: " + e.getMessage());
            return null;
        }
    }

    private static Endereco parseEndereco(String responseBody) {
        JSONObject jsonObject = new JSONObject(responseBody);
        Endereco endereco = new Endereco();
        endereco.setCep(jsonObject.getString("cep"));
        endereco.setLogradouro(jsonObject.getString("logradouro"));
        endereco.setComplemento(jsonObject.optString("complemento"));
        endereco.setBairro(jsonObject.getString("bairro"));
        endereco.setLocalidade(jsonObject.getString("localidade"));
        endereco.setUf(jsonObject.getString("uf"));
        endereco.setIbge(jsonObject.optString("ibge"));
        endereco.setGia(jsonObject.optString("gia"));
        endereco.setDdd(jsonObject.optString("ddd"));
        endereco.setSiafi(jsonObject.optString("siafi"));
        return endereco;
    }
}