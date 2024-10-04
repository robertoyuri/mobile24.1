package br.eti.roberto.teste;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class HttpService extends AsyncTask<Void, Void, String> {
    private final String cep;

    public HttpService(String cep){
        this.cep = cep;
    }

    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();
        if(cep != null && cep.length() == 8){
            try{
                URL url = new URL("https://viacep.com.br/ws/"+cep.toString()+"/json/");
                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                c.setRequestMethod("GET");
                c.setRequestProperty("Content-type", "application/json");
                c.setRequestProperty("Accept", "application/json");
                c.setDoOutput(true);
                c.setConnectTimeout(50000);
                c.connect();

                Scanner s = new Scanner(url.openStream());
                while(s.hasNext()){
                    resposta.append(s.nextLine());
                }

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return resposta.toString();
    }
}
