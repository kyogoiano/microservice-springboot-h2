package org.leandro.beneficiary.ms.adapter.outbound.persistence.repository;

import org.leandro.beneficiary.ms.domain.model.Beneficiary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

}
