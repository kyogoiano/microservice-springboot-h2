package org.leandro.beneficiary.ms.adapter.outbound.mapper;


import org.leandro.beneficiary.ms.adapter.inbound.model.BeneficiaryDto;
import org.leandro.beneficiary.ms.adapter.inbound.model.DocumentDto;
import org.leandro.beneficiary.ms.domain.model.Beneficiary;
import org.leandro.beneficiary.ms.domain.model.Document;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface BeneficiaryMapper {

    List<BeneficiaryDto> toBeneficiaryDtoList(List<Beneficiary> beneficiaryList);

    BeneficiaryDto toBeneficiaryDto(Beneficiary beneficiary);

    Document toDocument(DocumentDto document);

    Beneficiary toBeneficiary(BeneficiaryDto beneficiaryDto);

    Set<DocumentDto> toDocumentDtoSet(Set<Document> documentSet);

}
