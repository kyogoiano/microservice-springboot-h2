package org.leandro.beneficiary.ms.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

/**
 * Beneficiary POJO model class
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuppressWarnings("unused")
public class Beneficiary {
    /**
     * Unique Identifier for the beneficiary
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long beneficiaryId;
    /**
     * Name for the Beneficiary
     */
    String name;
    /**
     * Phone for the Beneficiary
     */
    String phone;
    /**
     * Birthday date for the Beneficiary
     */
    LocalDate birthDate;
    /**
     * Inclusion date- System generated
     */
    LocalDate inclusionDate;
    /**
     * Update date- System generated
     */
    LocalDate updateDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "beneficiary", fetch = FetchType.EAGER)
    Set<Document> documents;

}
