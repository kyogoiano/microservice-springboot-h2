package org.leandro.beneficiary.ms;

import org.leandro.beneficiary.ms.adapter.inbound.model.BeneficiaryDto;
import org.leandro.beneficiary.ms.adapter.inbound.model.DocumentDto;
import org.leandro.beneficiary.ms.adapter.outbound.mapper.BeneficiaryMapper;
import org.leandro.beneficiary.ms.adapter.outbound.mapper.BeneficiaryMapperImpl;
import org.leandro.beneficiary.ms.domain.model.Beneficiary;
import org.leandro.beneficiary.ms.domain.model.Document;
import org.leandro.beneficiary.ms.adapter.outbound.persistence.repository.BeneficiaryRepository;
import org.leandro.beneficiary.ms.adapter.outbound.persistence.repository.DocumentRepository;
import org.leandro.beneficiary.ms.adapter.outbound.service.BeneficiaryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.leandro.beneficiary.ms.domain.model.DocumentType;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

class BeneficiaryServiceImplTests {

    @InjectMocks
    private BeneficiaryServiceImpl beneficiaryServiceImpl;

    @Mock
    private BeneficiaryRepository beneficiaryRepository;

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private BeneficiaryMapper beneficiaryMapper;

    private BeneficiaryMapperImpl realBeneficiaryMapper;

    private final List<Beneficiary> beneficiaryList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        final Set<Document> documentSet = createDocuments();
        final Beneficiary beneficiary = new Beneficiary(1L,"Beneficiary for test 1", "+551199991010", LocalDate.now(),
                LocalDate.parse("1990-10-15", DateTimeFormatter.ISO_DATE), LocalDate.now(), documentSet);
        final Beneficiary beneficiary1 = new Beneficiary(2L,"Beneficiary for test 2", "+551299991010", LocalDate.now(),
                LocalDate.parse("1980-10-15", DateTimeFormatter.ISO_DATE), LocalDate.now(), documentSet);
        final Beneficiary beneficiary2 = new Beneficiary(3L,"Beneficiary for test 3", "+551399991010", LocalDate.now(),
                LocalDate.parse("1970-10-15", DateTimeFormatter.ISO_DATE), LocalDate.now(), documentSet);


        beneficiaryList.add(beneficiary);
        beneficiaryList.add(beneficiary1);
        beneficiaryList.add(beneficiary2);

        realBeneficiaryMapper = new BeneficiaryMapperImpl();
    }

    private static Set<Document> createDocuments() {
        final Document document = new Document(1L, DocumentType.COMMON, "Test Comment", LocalDate.now(), LocalDate.now(), null);
        final Set<Document> documentSet = new HashSet<>();
        documentSet.add(document);
        return documentSet;
    }

    @Test
    public void findAllTest() {

        Mockito.when(beneficiaryRepository.findAll()).thenReturn(beneficiaryList);

        List<BeneficiaryDto> beneficiaryDtoList =  realBeneficiaryMapper.toBeneficiaryDtoList(beneficiaryList);

        Mockito.when(beneficiaryMapper.toBeneficiaryDtoList(Mockito.anyList())).thenReturn(beneficiaryDtoList);
        final List<BeneficiaryDto> resultList = beneficiaryServiceImpl.findAll();
        Assertions.assertEquals(beneficiaryDtoList.size(), resultList.size());
    }

    @Test
    public void findByIdTest() {

        final Set<Document> documentSet = createDocuments();
        final Beneficiary beneficiary = new Beneficiary(1L,"Beneficiary for test 1", "+551199991010", LocalDate.now(),
                LocalDate.parse("1990-10-15", DateTimeFormatter.ISO_DATE), LocalDate.now(), documentSet);

        Mockito.when(beneficiaryRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(beneficiary));
        Assertions.assertDoesNotThrow(() -> beneficiaryServiceImpl.findById(1L));
    }

    @Test
    public void deleteByIdTest() {
        final Set<Document> documentSet = createDocuments();
        final Beneficiary beneficiary = new Beneficiary(1L,"Beneficiary for test 1", "+551199991010", LocalDate.now(),
                LocalDate.parse("1990-10-15", DateTimeFormatter.ISO_DATE), LocalDate.now(), documentSet);

        Mockito.when(beneficiaryRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(beneficiary));
        Mockito.doNothing().when(documentRepository).deleteAll(Mockito.any());
        Mockito.doNothing().when(beneficiaryRepository).delete(Mockito.any(Beneficiary.class));

        boolean deleteResult = beneficiaryServiceImpl.deleteById(1L);
        Assertions.assertTrue(deleteResult);
    }

    @Test
    public void getBeneficiaryDocumentsTest() {

        final Set<Document> documentSet = createDocuments();
        final Beneficiary beneficiary = new Beneficiary(1L,"Beneficiary for test 1", "+551199991010", LocalDate.now(),
                LocalDate.parse("1990-10-15", DateTimeFormatter.ISO_DATE), LocalDate.now(), documentSet);

        Mockito.when(beneficiaryRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(beneficiary));

        Set<DocumentDto> expectedDocuments = realBeneficiaryMapper.toDocumentDtoSet(documentSet);

        Mockito.when(beneficiaryMapper.toDocumentDtoSet(Mockito.anySet())).thenReturn(expectedDocuments);
        Set<DocumentDto> documents = beneficiaryServiceImpl.getBeneficiaryDocuments(1L);
        Assertions.assertFalse(documents.isEmpty());
        Assertions.assertEquals(expectedDocuments, documents);
    }

    @Test
    public void createOrUpdateBeneficiaryTest() {
        final Set<Document> documentSet = createDocuments();
        final Beneficiary beneficiary = new Beneficiary(1L,"Beneficiary for test 1", "+551199991010", LocalDate.now(),
                LocalDate.parse("1990-10-15", DateTimeFormatter.ISO_DATE), LocalDate.now(), documentSet);

        documentSet.forEach( document -> {
            Mockito.when(beneficiaryMapper.toDocument(Mockito.any(DocumentDto.class))).thenReturn(document);
            Mockito.when(documentRepository.save(Mockito.any(Document.class))).thenReturn(document);
        });
        Mockito.when(beneficiaryMapper.toBeneficiary(Mockito.any(BeneficiaryDto.class))).thenReturn(beneficiary);
        Mockito.when(beneficiaryRepository.save(Mockito.any(Beneficiary.class))).thenReturn(beneficiary);


        BeneficiaryDto input = realBeneficiaryMapper.toBeneficiaryDto(beneficiary);

        Mockito.when(beneficiaryMapper.toBeneficiaryDto(Mockito.any(Beneficiary.class))).thenReturn(input);

        BeneficiaryDto result = beneficiaryServiceImpl.createOrUpdate(input);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(input, result);
    }
}
