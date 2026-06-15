package com.booked.backend.service;

import com.booked.backend.couch.CouchDbFindResponse;
import com.booked.backend.couch.CouchDbWriteResponse;
import com.booked.backend.entity.Mensagem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MensagemService {

    private final RestTemplate restTemplate;

    @Value("${couchdb.url}")
    private String couchDbUrl;

    @Value("${couchdb.database.mensagens}")
    private String database;

    public MensagemService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Mensagem enviarMensagem(Mensagem mensagem) {
        if (mensagem.getIdUsuario1() == null || mensagem.getIdUsuario2() == null) {
            throw new RuntimeException("id_usuario1 e id_usuario2 são obrigatórios.");
        }
        if (mensagem.getIdUsuario1().equals(mensagem.getIdUsuario2())) {
            throw new RuntimeException("Um usuário não pode enviar mensagem para si mesmo.");
        }

        if (mensagem.getTexto() == null || mensagem.getTexto().trim().isEmpty()) {
            throw new RuntimeException("A mensagem não pode estar vazia.");
        }

        mensagem.setTipo("mensagem");
        mensagem.setDataHora(Instant.now().toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Mensagem> request = new HttpEntity<>(mensagem, headers);

        String url = couchDbUrl + "/" + database;
        ResponseEntity<CouchDbWriteResponse> response =
                restTemplate.postForEntity(url, request, CouchDbWriteResponse.class);

        CouchDbWriteResponse body = response.getBody();
        if (body == null || !body.isOk()) {
            throw new RuntimeException("Erro ao salvar mensagem no CouchDB.");
        }

        mensagem.setId(body.getId());
        mensagem.setRev(body.getRev());
        return mensagem;
    }

    public List<Mensagem> buscarConversa(String idUsuario1, String idUsuario2) {
        String url = couchDbUrl + "/" + database + "/_find";

        Map<String, Object> par1 = new HashMap<>();
        par1.put("id_usuario1", idUsuario1);
        par1.put("id_usuario2", idUsuario2);

        Map<String, Object> par2 = new HashMap<>();
        par2.put("id_usuario1", idUsuario2);
        par2.put("id_usuario2", idUsuario1);

        List<Object> orConditions = new ArrayList<>();
        orConditions.add(par1);
        orConditions.add(par2);

        Map<String, Object> selector = new HashMap<>();
        selector.put("$or", orConditions);

        Map<String, Object> body = new HashMap<>();
        body.put("selector", selector);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<CouchDbFindResponse<Mensagem>> response = restTemplate.exchange(
                url, HttpMethod.POST, request,
                new ParameterizedTypeReference<CouchDbFindResponse<Mensagem>>() {}
        );

        CouchDbFindResponse<Mensagem> result = response.getBody();
        return result != null ? result.getDocs() : List.of();
    }
}