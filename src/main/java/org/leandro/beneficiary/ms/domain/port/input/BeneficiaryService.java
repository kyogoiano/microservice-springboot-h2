package org.leandro.beneficiary.ms.domain.port.input;

import org.leandro.beneficiary.ms.adapter.inbound.model.BeneficiaryDto;
import org.leandro.beneficiary.ms.adapter.inbound.model.DocumentDto;
import java.util.List;
import java.util.Set;

public interface BeneficiaryService {
    List<BeneficiaryDto> findAll();

    BeneficiaryDto findById(long id);

    boolean deleteById(long id);

    BeneficiaryDto createOrUpdate(BeneficiaryDto beneficiary);

    Set<DocumentDto> getBeneficiaryDocuments(long id);
}
