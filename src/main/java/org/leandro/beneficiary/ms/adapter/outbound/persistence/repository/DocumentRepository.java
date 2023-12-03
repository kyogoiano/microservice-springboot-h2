package org.leandro.beneficiary.ms.adapter.outbound.persistence.repository;

import org.leandro.beneficiary.ms.domain.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

}
