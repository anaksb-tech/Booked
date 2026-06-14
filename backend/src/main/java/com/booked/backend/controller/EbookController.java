package com.booked.backend.controller;

import com.booked.backend.entity.Ebook;
import com.booked.backend.entity.EbookDTO;
import com.booked.backend.repository.EbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Autowired
    private EbookRepository repository;

    @GetMapping("/carregar-ebooks")
    public List<EbookDTO> carregarEbooks() {
        List<Ebook> todosEbooks = repository.findAll();
        int tamanho = todosEbooks.size();
        List<EbookDTO> ebooksDTO = new ArrayList<>();

        for(int i = 0; i < tamanho; i++) {
            int id = todosEbooks.get(i).getId_ebook();
            String titulo = todosEbooks.get(i).getTitulo();
            ebooksDTO.add(new EbookDTO(id, titulo));
        }

        return ebooksDTO;
    }

    @GetMapping("/{id}")
    public Ebook buscarEbook(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping("/publicar-ebook")
    public Ebook publicar(@RequestBody Ebook ebook) {

        LocalDateTime dataHora = LocalDateTime.now();
        ebook.setData_hora(dataHora);
        return repository.save(ebook);

    }

}
