package com.preoday.nasaapi.repository;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.preoday.nasaapi.domain.Mars;

@Service
public class MarsRepository {

    public Mars returnTemperature(Integer SOL) {

        // https://api.nasa.gov/insight_weather/?api_key=DEMO_KEY&feedtype=json&ver=1.0
        JsonParser parser = new JsonParser();
        Mars mars = new Mars();

        /*
         * Para o calculo de temperatura quando o parametro SOL não for passado
         */
        Float averageTemperature = 0F;
        Integer countElements = 0;

        RestTemplate template = new RestTemplate();

        UriComponents uri = UriComponentsBuilder.newInstance().scheme("https").host("api.nasa.gov")
                .path("insight_weather/").queryParam("api_key", "DEMO_KEY").queryParam("feedtype", "json")
                .queryParam("ver", "1.0").build();

        ResponseEntity<String> entity = template.getForEntity(uri.toUriString(), String.class);

        /*
         * Faz um parser para pegar todos os objetos em forma de metadados
         */
        JsonObject rootObj = parser.parse(entity.getBody()).getAsJsonObject();

        /*
         * Pega cada atributo relacionado a um SOL
         */
        Set<Entry<String, JsonElement>> elements = rootObj.entrySet();

        Iterator<Entry<String, JsonElement>> it = elements.iterator();

        while (it.hasNext()) {
            Entry<String, JsonElement> next = it.next();

            /*
             * Verifica se foi passado o parâmetro. Se sim: - Retorna o próprio valor de
             * média de temperatura fornecida para o parâmetro
             */
            if (SOL != null) {
                try {
                    String key = next.getKey();
                    if (SOL.equals(Integer.parseInt(key))) {
                        Float temperature = next.getValue().getAsJsonObject().getAsJsonObject("AT").get("av")
                                .getAsFloat();
                        mars.setAverageTemperature(temperature);
                    }
                } catch (NumberFormatException e) {
                }
            } else {
                /*
                 * Se não foi passado o parâmetro: - Soma todas as temperaturas para calcular as
                 * médias depois
                 */
                try {
                    Integer.parseInt(next.getKey());
                    countElements++;
                    Float temperature = next.getValue().getAsJsonObject().getAsJsonObject("AT").get("av").getAsFloat();
                    averageTemperature += temperature;
                } catch (NumberFormatException e) {
                }
            }
        }

        /*
         * Se a consulta foi sem parâmetros, calcular a média e devolver o valor
         */
        if (countElements > 0) {
            averageTemperature /= countElements;
            mars.setAverageTemperature(averageTemperature);
        }

        return mars;
    }

}
