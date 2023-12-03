package org.leandro.beneficiary.ms.adapter.inbound.resources;

import org.leandro.beneficiary.ms.adapter.inbound.model.BeneficiaryDto;
import org.leandro.beneficiary.ms.adapter.inbound.model.DocumentDto;
import org.leandro.beneficiary.ms.domain.port.input.BeneficiaryService;
import org.leandro.beneficiary.ms.util.BeneficiaryApplicationConstants;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;

import java.util.List;
import java.util.Set;

/**
 * BeneficiaryResource is the Controller class for the To-do Tracker Application.
 * This class is responsible for exposing the REST APIs.
 * CrossOrigin: This is used to accept the requests from cross domain URLs
 *         '<a href="http://localhost:3000">http://localhost:3000</a>' = Frontend Domain URL
 */
@CrossOrigin(origins = { "http://localhost:3000" })
@RequestMapping("/beneficiary")
@RestController
@Slf4j
@SuppressWarnings("unused")
public class BeneficiaryResource implements BeneficiaryApplicationConstants, BeneficiaryApi {

    //Variable declarations for logging
    private final String className = this.getClass().getSimpleName();

    /**
     * The Autowired Service Interface
     */
    private final BeneficiaryService beneficiaryService;

    public BeneficiaryResource(@Autowired BeneficiaryService beneficiaryService) {
        this.beneficiaryService = beneficiaryService;
    }

    /**
     * Method for listing all the beneficiaries
     * This method accepts HTTP_REQUEST_METHOD:GET
     *
     * @return Response Entity with Http Status Code and
     *         List of all Beneficiary objects
     */
    @GetMapping
    @Override
    public ResponseEntity<List<BeneficiaryDto>> getAllBeneficiaries() {
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        try {
            final List<BeneficiaryDto> beneficiaryList = beneficiaryService.findAll();
            log.debug("BeneficiaryList=="+ beneficiaryList.toString());
            return new ResponseEntity<>(beneficiaryList, HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            log.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        }
    }

    /**
     * Method for listing a beneficiary based on id
     * This method accepts HTTP_REQUEST_METHOD:GET
     *
     * @param beneficiaryId Unique identifier of the beneficiary
     * @return Response Entity with Http Status Code and
     *         Single Beneficiary object, based on id
     */
    @Override
    public ResponseEntity<BeneficiaryDto> getBeneficiaryById(Long beneficiaryId) {
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        final BeneficiaryDto beneficiaryDto = beneficiaryService.findById(beneficiaryId);
        log.debug("Beneficiary=="+beneficiaryDto);
        log.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return new ResponseEntity<>(beneficiaryDto, HttpStatus.OK);
        //} else {
        //    log.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        //    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        //}
    }

    /**
     * Method for deleting a beneficiary based on beneficiaryId
     * This method accepts HTTP_REQUEST_METHOD:DELETE
     *
     * @param beneficiaryId Unique identifier of the beneficiary to be deleted
     * @return Response Entity with Http Status Code
     */
    @Override
    public ResponseEntity<Void> deleteBeneficiary(Long beneficiaryId, String apiKey) {
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        try {
            if(beneficiaryService.deleteById(beneficiaryId))
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            log.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        }
    }

    /**
     * Method for updating an item of beneficiary
     * This method accepts HTTP_REQUEST_METHOD:PUT
     *
     * @param beneficiary Beneficiary object to be updated
     * @return Response Entity with Http Status Code and
     *         Update beneficiary object
     */
    @Override
    public ResponseEntity<BeneficiaryDto> updateBeneficiary(BeneficiaryDto beneficiary) {
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        beneficiary = beneficiaryService.createOrUpdate(beneficiary);
        log.debug("Beneficiary=="+beneficiary.toString());
        log.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return new ResponseEntity<>(beneficiary, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateBeneficiaryWithForm(Long beneficiaryId, String name, String phone) {
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        BeneficiaryDto beneficiaryDto = BeneficiaryDto.builder().beneficiaryId(beneficiaryId).name(name).phone(phone).build();
        return null;
    }

    /**
     * Method for creating a beneficiary
     * This method accepts HTTP_REQUEST_METHOD:POST
     *
     * @param beneficiary beneficiary object to be created
     * @return Response Entity with Http Status Code and
     *         Newly created beneficiary object
     */
    @Override
    public ResponseEntity<BeneficiaryDto> addBeneficiary(BeneficiaryDto beneficiary) {
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        beneficiary = beneficiaryService.createOrUpdate(beneficiary);
        log.debug("Beneficiary=="+beneficiary.toString());
        log.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return new ResponseEntity<>(beneficiary, HttpStatus.CREATED);
    }

    /**
     * Method for listing all the documents of the beneficiary
     * This method accepts HTTP_REQUEST_METHOD:GET
     *
     * @return Response Entity with Http Status Code and
     *         List of all documents of the beneficiary
     */
    @Override
    public ResponseEntity<Set<DocumentDto>> getDocuments(Long beneficiaryId) {
        final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        final Set<DocumentDto> documentSet = beneficiaryService.getBeneficiaryDocuments(beneficiaryId);
        log.debug("documentSet=="+documentSet);
        log.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return new ResponseEntity<>(documentSet, HttpStatus.OK);
    }

}
