package com.control.versioncontrol1.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.control.versioncontrol1.model.Document; // ✅ make sure this line is correct
import com.control.versioncontrol1.repository.DocumentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = "*")
public class DocumentController {

    private final DocumentRepository repository;

    public DocumentController(DocumentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Document> getAllDocuments() {
        return repository.findAll();
    }

    @PostMapping
    public Document createDocument(@RequestBody Document document) {
        return repository.save(document);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDocument(@PathVariable int id, @RequestBody Document doc) {
        Optional<Document> optional = repository.findById(id);
        if (optional.isPresent()) {
            Document existing = optional.get();
            existing.setTitle(doc.getTitle());
            existing.setContent(doc.getContent());
            existing.setVersion(doc.getVersion());
            existing.setStatus(doc.getStatus());
            return ResponseEntity.ok(repository.save(existing));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Document with ID " + id + " not found.");
        }
    }


    @DeleteMapping("/{id}")
    public void deleteDocument(@PathVariable int id) {
        repository.deleteById(id);
    }
}
