package org.leandro.beneficiary.ms.adapter.outbound.service;

import org.leandro.beneficiary.ms.adapter.inbound.model.BeneficiaryDto;
import org.leandro.beneficiary.ms.adapter.inbound.model.DocumentDto;
import org.leandro.beneficiary.ms.adapter.outbound.mapper.BeneficiaryMapper;
import org.leandro.beneficiary.ms.domain.port.input.BeneficiaryService;
import org.leandro.beneficiary.ms.exception.BeneficiaryDoesNotExistException;
import org.leandro.beneficiary.ms.domain.model.Beneficiary;
import org.leandro.beneficiary.ms.domain.model.Document;
import org.leandro.beneficiary.ms.adapter.outbound.persistence.repository.BeneficiaryRepository;
import org.leandro.beneficiary.ms.adapter.outbound.persistence.repository.DocumentRepository;
import org.leandro.beneficiary.ms.util.BeneficiaryApplicationConstants;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.text.MessageFormat;

import java.time.LocalDate;
import java.util.*;

/**
 * In this service class data is fetched from database
 */
@Service
@Slf4j
public class BeneficiaryServiceImpl implements BeneficiaryApplicationConstants, BeneficiaryService {

    private final String className = this.getClass().getSimpleName();

    private final BeneficiaryRepository beneficiaryRepository;

    private final DocumentRepository documentRepository;

    private final BeneficiaryMapper beneficiaryMapper;

    public BeneficiaryServiceImpl(@Autowired BeneficiaryRepository beneficiaryRepository,
                                  @Autowired DocumentRepository documentRepository,
                                  @Autowired BeneficiaryMapper beneficiaryMapper) {
        this.beneficiaryRepository = beneficiaryRepository;
        this.documentRepository = documentRepository;
        this.beneficiaryMapper = beneficiaryMapper;
    }

    /**
     * Method implementation for listing all the beneficiaries
     * @return List of all Beneficiary objects
     */
    @Override
    public List<BeneficiaryDto> findAll() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        List<Beneficiary> beneficiaryList = beneficiaryRepository.findAll();
        log.debug("Beneficiary list=="+ beneficiaryList);
        log.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return beneficiaryMapper.toBeneficiaryDtoList(beneficiaryList);
    }

    /**
     * Method implementation for fetching the single beneficiary object, based on id
     * @param id Unique identifier of the beneficiary
     * @return Single Beneficiary object, based on id
     */
    @Override
    public BeneficiaryDto findById(long id) {
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        final Optional<Beneficiary> optionalBeneficiary = beneficiaryRepository.findById(id);
        log.debug("Beneficiary=="+optionalBeneficiary);
        log.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        final Beneficiary beneficiary = optionalBeneficiary.orElseThrow();
        return  beneficiaryMapper.toBeneficiaryDto(beneficiary);
    }

    /**
     * Method implementation for deleting a beneficiary based on id
     * @param id Unique identifier of the beneficiary to be deleted
     * @return True or False
     */
    @Override
    public boolean deleteById(long id) {
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        boolean isDeleted = false;
        try {
            //Delete the Documents before deleting the beneficiary.
            final Optional<Beneficiary> deletionBeneficiary =  beneficiaryRepository.findById(id);
            if(deletionBeneficiary.isPresent()) {
                final Set<Document> documentSet = deletionBeneficiary.get().getDocuments();
                if (documentSet != null) {
                    documentRepository.deleteAll(documentSet);
                    beneficiaryRepository.delete(deletionBeneficiary.get());
                    isDeleted = true;
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        log.debug("isDeleted=="+isDeleted);
        log.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return isDeleted;
    }

    /**
     * Method implementation for creating or updating a beneficiaryDto
     * @param beneficiaryDto beneficiaryDto object to be created/updated
     * @return Newly created or updated beneficiaryDto object
     */
    @Override
    public BeneficiaryDto createOrUpdate(BeneficiaryDto beneficiaryDto) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));

        //Get the list of documents from the Request Body
        final Set<DocumentDto> documentSet = beneficiaryDto.getDocuments();

        //Save the Documents before beneficiaryDto can be saved
        if(documentSet != null) {
            for (DocumentDto documentDto : documentSet) {
                if (documentDto != null && !documentDto.getDescription().isEmpty()) {
                    documentDto.setInclusionDate(LocalDate.now());
                    documentRepository.save(beneficiaryMapper.toDocument(documentDto));
                }
            }
        }

        //Set the Creation Date only during initial creation of the beneficiaryDto
        if(beneficiaryDto.getInclusionDate() == null)
            beneficiaryDto.setInclusionDate(LocalDate.now());

        final Beneficiary beneficiary = beneficiaryRepository.save(beneficiaryMapper.toBeneficiary(beneficiaryDto));
        log.debug("Beneficiary=="+beneficiary);
        log.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return beneficiaryMapper.toBeneficiaryDto(beneficiary);
    }

    /**
     * Method implementation for listing all the documents of a beneficiary
     * @return Set of all documents
     */
    @Override
    public Set<DocumentDto> getBeneficiaryDocuments(long id) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        final Optional<Beneficiary> optionalBeneficiary = beneficiaryRepository.findById(id);
        final Set<Document> documentSet = optionalBeneficiary.map(Beneficiary::getDocuments).orElseThrow(BeneficiaryDoesNotExistException::new);


        log.debug("documentSet=="+documentSet);
        log.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return beneficiaryMapper.toDocumentDtoSet(documentSet);
    }
}
