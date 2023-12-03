package org.leandro.beneficiary.ms.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Document {
    /**
     * Unique Identifier for the Document
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long documentId;

    DocumentType documentType;

    String description;

    LocalDate inclusionDate;

    LocalDate updateDate;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="beneficiaryId", nullable=false)
    Beneficiary beneficiary;
}
