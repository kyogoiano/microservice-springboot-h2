/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.7.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.leandro.beneficiary.ms.adapter.inbound.resources;

import org.leandro.beneficiary.ms.adapter.inbound.model.BeneficiaryDto;
import org.leandro.beneficiary.ms.adapter.inbound.model.DocumentDto;
import java.util.Set;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-20T21:22:15.452425452-03:00[America/Sao_Paulo]", comments = "Generator version: 7.7.0")
@Validated
@Tag(name = "beneficiary", description = "Beneficiary object")
public interface BeneficiaryApi {

    /**
     * POST /beneficiary : Add a new beneficiary to the database
     * Add a new beneficiary to the database
     *
     * @param beneficiaryDto Create a new beneficiary in the database (required)
     * @return Successful operation (status code 200)
     *         or Invalid input (status code 405)
     */
    @Operation(
        operationId = "addBeneficiary",
        summary = "Add a new beneficiary to the database",
        description = "Add a new beneficiary to the database",
        tags = { "beneficiary" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiaryDto.class)),
                @Content(mediaType = "application/xml", schema = @Schema(implementation = BeneficiaryDto.class))
            }),
            @ApiResponse(responseCode = "405", description = "Invalid input")
        },
        security = {
            @SecurityRequirement(name = "beneficiary_database_auth", scopes={ "write:beneficiaries", "read:beneficiaries" })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/beneficiary",
        produces = { "application/json", "application/xml" },
        consumes = { "application/json", "application/xml", "application/x-www-form-urlencoded" }
    )
    
    ResponseEntity<BeneficiaryDto> addBeneficiary(
        @Parameter(name = "BeneficiaryDto", description = "Create a new beneficiary in the database", required = true) @Valid @RequestBody BeneficiaryDto beneficiaryDto
    );


    /**
     * DELETE /beneficiary/{beneficiaryId} : Deletes a beneficiary
     * delete a beneficiary
     *
     * @param beneficiaryId beneficiary id to delete (required)
     * @param apiKey  (optional)
     * @return successful enacted operation (status code 204)
     *         or Invalid beneficiary value (status code 400)
     */
    @Operation(
        operationId = "deleteBeneficiary",
        summary = "Deletes a beneficiary",
        description = "delete a beneficiary",
        tags = { "beneficiary" },
        responses = {
            @ApiResponse(responseCode = "204", description = "successful enacted operation"),
            @ApiResponse(responseCode = "400", description = "Invalid beneficiary value")
        },
        security = {
            @SecurityRequirement(name = "beneficiary_database_auth", scopes={ "write:beneficiaries", "read:beneficiaries" })
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/beneficiary/{beneficiaryId}"
    )
    
    ResponseEntity<Void> deleteBeneficiary(
        @Parameter(name = "beneficiaryId", description = "beneficiary id to delete", required = true, in = ParameterIn.PATH) @PathVariable("beneficiaryId") Long beneficiaryId,
        @Parameter(name = "api_key", description = "", in = ParameterIn.HEADER) @RequestHeader(value = "api_key", required = false) String apiKey
    );


    /**
     * GET /beneficiary : List beneficiaries
     * List all beneficiaries
     *
     * @return successful operation (status code 200)
     */
    @Operation(
        operationId = "getAllBeneficiaries",
        summary = "List beneficiaries",
        description = "List all beneficiaries",
        tags = { "beneficiary" },
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BeneficiaryDto.class))),
                @Content(mediaType = "application/xml", array = @ArraySchema(schema = @Schema(implementation = BeneficiaryDto.class)))
            })
        },
        security = {
            @SecurityRequirement(name = "beneficiary_database_auth", scopes={ "read:beneficiaries" })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/beneficiary",
        produces = { "application/json", "application/xml" }
    )
    
    ResponseEntity<List<BeneficiaryDto>> getAllBeneficiaries(
        
    );


    /**
     * GET /beneficiary/{beneficiaryId} : Find beneficiary by ID
     * Returns a single beneficiary
     *
     * @param beneficiaryId ID of beneficiary to return (required)
     * @return successful operation (status code 200)
     *         or Invalid ID supplied (status code 400)
     *         or beneficiary not found (status code 404)
     */
    @Operation(
        operationId = "getBeneficiaryById",
        summary = "Find beneficiary by ID",
        description = "Returns a single beneficiary",
        tags = { "beneficiary" },
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiaryDto.class)),
                @Content(mediaType = "application/xml", schema = @Schema(implementation = BeneficiaryDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "beneficiary not found")
        },
        security = {
            @SecurityRequirement(name = "api_key"),
            @SecurityRequirement(name = "beneficiary_database_auth", scopes={ "read:beneficiaries" })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/beneficiary/{beneficiaryId}",
        produces = { "application/json", "application/xml" }
    )
    
    ResponseEntity<BeneficiaryDto> getBeneficiaryById(
        @Parameter(name = "beneficiaryId", description = "ID of beneficiary to return", required = true, in = ParameterIn.PATH) @PathVariable("beneficiaryId") Long beneficiaryId
    );


    /**
     * GET /beneficiary/{beneficiaryId}/documents : Finds beneficiaries documents by beneficiary id
     * retrieve all beneficiary documents
     *
     * @param beneficiaryId ID of beneficiary to return (required)
     * @return successful operation (status code 200)
     *         or Invalid ID supplied (status code 400)
     */
    @Operation(
        operationId = "getDocuments",
        summary = "Finds beneficiaries documents by beneficiary id",
        description = "retrieve all beneficiary documents",
        tags = { "document" },
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DocumentDto.class))),
                @Content(mediaType = "application/xml", array = @ArraySchema(schema = @Schema(implementation = DocumentDto.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied")
        },
        security = {
            @SecurityRequirement(name = "beneficiary_database_auth", scopes={ "read:beneficiaries" })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/beneficiary/{beneficiaryId}/documents",
        produces = { "application/json", "application/xml" }
    )
    
    ResponseEntity<Set<DocumentDto>> getDocuments(
        @Parameter(name = "beneficiaryId", description = "ID of beneficiary to return", required = true, in = ParameterIn.PATH) @PathVariable("beneficiaryId") Long beneficiaryId
    );


    /**
     * PUT /beneficiary : Update an existing beneficiary
     * Update an existing beneficiary by Id
     *
     * @param beneficiaryDto Update an existent beneficiary in the database (required)
     * @return Successful operation (status code 200)
     *         or Invalid ID supplied (status code 400)
     *         or beneficiary not found (status code 404)
     *         or Validation exception (status code 405)
     */
    @Operation(
        operationId = "updateBeneficiary",
        summary = "Update an existing beneficiary",
        description = "Update an existing beneficiary by Id",
        tags = { "beneficiary" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = BeneficiaryDto.class)),
                @Content(mediaType = "application/xml", schema = @Schema(implementation = BeneficiaryDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "beneficiary not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")
        },
        security = {
            @SecurityRequirement(name = "beneficiary_database_auth", scopes={ "write:beneficiaries", "read:beneficiaries" })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/beneficiary",
        produces = { "application/json", "application/xml" },
        consumes = { "application/json", "application/xml", "application/x-www-form-urlencoded" }
    )
    
    ResponseEntity<BeneficiaryDto> updateBeneficiary(
        @Parameter(name = "BeneficiaryDto", description = "Update an existent beneficiary in the database", required = true) @Valid @RequestBody BeneficiaryDto beneficiaryDto
    );


    /**
     * POST /beneficiary/{beneficiaryId} : Updates a beneficiary in the database with form data
     * 
     *
     * @param beneficiaryId ID of beneficiary that needs to be updated (required)
     * @param name Name of beneficiary that needs to be updated (optional)
     * @param phone Phone of beneficiary that needs to be updated (optional)
     * @return Invalid input (status code 405)
     */
    @Operation(
        operationId = "updateBeneficiaryWithForm",
        summary = "Updates a beneficiary in the database with form data",
        description = "",
        tags = { "beneficiary" },
        responses = {
            @ApiResponse(responseCode = "405", description = "Invalid input")
        },
        security = {
            @SecurityRequirement(name = "beneficiary_database_auth", scopes={ "write:beneficiaries", "read:beneficiaries" })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/beneficiary/{beneficiaryId}"
    )
    
    ResponseEntity<Void> updateBeneficiaryWithForm(
        @Parameter(name = "beneficiaryId", description = "ID of beneficiary that needs to be updated", required = true, in = ParameterIn.PATH) @PathVariable("beneficiaryId") Long beneficiaryId,
        @Parameter(name = "name", description = "Name of beneficiary that needs to be updated", in = ParameterIn.QUERY) @Valid @RequestParam(value = "name", required = false) String name,
        @Parameter(name = "phone", description = "Phone of beneficiary that needs to be updated", in = ParameterIn.QUERY) @Valid @RequestParam(value = "phone", required = false) String phone
    );

}
