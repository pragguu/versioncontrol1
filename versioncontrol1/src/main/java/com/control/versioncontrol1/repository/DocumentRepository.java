package com.control.versioncontrol1.repository;
import com.control.versioncontrol1.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<com.control.versioncontrol1.model.Document, Integer> {
}
