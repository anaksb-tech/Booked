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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

           Map<String, Object> body = new HashMap<>();
        body.put("selector", selector);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

         ResponseEntity<CouchDbFindResponse<Pesquisa>> response = restTemplate.exchange(
                url, HttpMethod.POST, request,
                new ParameterizedTypeReference<CouchDbFindResponse<Pesquisa>>() {}
        );

        CouchDbFindResponse<Pesquisa> result = response.getBody();

        // Se não encontrar nada, devolve lista vazia
        if (result == null || result.getDocs() == null) {
            return List.of();
        }

          List<Pesquisa> pesquisas = new java.util.ArrayList<>(result.getDocs());

        pesquisas.sort((p1, p2) -> {
            if (p1.getDataHora() == null) return 1;
            if (p2.getDataHora() == null) return -1;
            return p2.getDataHora().compareTo(p1.getDataHora());
        });

        return pesquisas;
    }
}