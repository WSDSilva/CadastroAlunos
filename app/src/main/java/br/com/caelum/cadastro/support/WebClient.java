package br.com.caelum.cadastro.support;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by wanderson on 09/06/16.
 */
public class WebClient {

    public String post(String json){

        try {
//            CONFIGURANDO A CONEXÃO
            URL url = new URL("https://www.caelum.com.br/mobile");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
//            CONFIGURANDO A FORMA DE ENVIO E RETORNO
            connection.setRequestProperty("Accept","application/json");
            connection.setRequestProperty("Content-type","application/json");
//            INFORMANDO QUE ENVIAREMOS E RECEBEREMOS ALGO NO POST
            connection.setDoInput(true);
            connection.setDoOutput(true);
//            INSERINDO O CONTEUDO(JSON) NO POST
            PrintStream saida = new PrintStream(connection.getOutputStream());
            saida.println(json);
//            INICIANDO A CONEXÃO
            connection.connect();
            String jsonResposta = new Scanner(connection.getInputStream()).next();

            return jsonResposta;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
