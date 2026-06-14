package com.booked.backend.controller;

import com.booked.backend.entity.Ebook;
import com.booked.backend.repository.EbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Autowired
    private EbookRepository repository;

    @GetMapping("/carregar-ebooks")
    public List<Ebook> carregarEbooks() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Ebook buscarEbook(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }

}
