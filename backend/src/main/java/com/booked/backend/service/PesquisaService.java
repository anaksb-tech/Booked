package com.booked.backend.service;

import com.booked.backend.couch.CouchDbFindResponse;
import com.booked.backend.couch.CouchDbWriteResponse;
import com.booked.backend.entity.Pesquisa;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.Instant;

import java.time.*;
import java.time.format.*;
import java.util.*;

@Service
public class PesquisaService {

    private final RestTemplate restTemplate;

    @Value("${couchdb.url}")
    private String couchDbUrl;

    @Value("${couchdb.database.pesquisas}")
    private String database;

    public PesquisaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Pesquisa registrarPesquisa(Pesquisa pesquisa) {
        if (pesquisa.getTexto() == null || pesquisa.getTexto().trim().isEmpty()) {
            throw new RuntimeException("O texto da pesquisa não pode ser vazio.");
        }
        if (pesquisa.getIdUsuario() == null) {
            throw new RuntimeException("id_usuario é obrigatório.");
        }

        pesquisa.setTipo("pesquisa");
        pesquisa.setDataHora(Instant.now().toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Pesquisa> request = new HttpEntity<>(pesquisa, headers);

        String url = couchDbUrl + "/" + database;
        ResponseEntity<CouchDbWriteResponse> response =
                restTemplate.postForEntity(url, request, CouchDbWriteResponse.class);

        CouchDbWriteResponse body = response.getBody();
        if (body == null || !body.isOk()) {
            throw new RuntimeException("Erro ao salvar pesquisa no CouchDB.");
        }

        pesquisa.setId(body.getId());
        pesquisa.setRev(body.getRev());
        return pesquisa;
    }

    public List<Pesquisa> historicoPorUsuario(Integer idUsuario) {
        String url = couchDbUrl + "/" + database + "/_find";

        Map<String, Object> selector = new HashMap<>();
        selector.put("id_usuario", idUsuario);

        Map<String, Object> sort = new HashMap<>();
        sort.put("data_hora", "desc");

        Map<String, Object> body = new HashMap<>();
        body.put("selector", selector);
        body.put("sort", List.of(sort));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<CouchDbFindResponse<Pesquisa>> response = restTemplate.exchange(
                url, HttpMethod.POST, request,
                new ParameterizedTypeReference<CouchDbFindResponse<Pesquisa>>() {}
        );

        CouchDbFindResponse<Pesquisa> result = response.getBody();
        return result != null ? result.getDocs() : List.of();
    }
}