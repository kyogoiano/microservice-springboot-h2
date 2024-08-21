package org.leandro.beneficiary.ms.adapter.inbound.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * BeneficiaryDto
 */
@lombok.Builder @lombok.AllArgsConstructor

@JsonTypeName("beneficiary")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-20T21:22:15.452425452-03:00[America/Sao_Paulo]", comments = "Generator version: 7.7.0")
public class BeneficiaryDto {

  private Long beneficiaryId;

  private String name;

  private String phone;

  @Valid
  private Set<@Valid DocumentDto> documents = new LinkedHashSet<>();

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate birthDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate inclusionDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate updateDate;

  public BeneficiaryDto() {
    super();
  }

  public BeneficiaryDto beneficiaryId(Long beneficiaryId) {
    this.beneficiaryId = beneficiaryId;
    return this;
  }

  /**
   * Get beneficiaryId
   * @return beneficiaryId
   */
  
  @Schema(name = "beneficiaryId", example = "10", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("beneficiaryId")
  public Long getBeneficiaryId() {
    return beneficiaryId;
  }

  public void setBeneficiaryId(Long beneficiaryId) {
    this.beneficiaryId = beneficiaryId;
  }

  public BeneficiaryDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @NotNull 
  @Schema(name = "name", example = "Carlos", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BeneficiaryDto phone(String phone) {
    this.phone = phone;
    return this;
  }

  /**
   * Get phone
   * @return phone
   */
  
  @Schema(name = "phone", example = "551199991010", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("phone")
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public BeneficiaryDto documents(Set<@Valid DocumentDto> documents) {
    this.documents = documents;
    return this;
  }

  public BeneficiaryDto addDocumentsItem(DocumentDto documentsItem) {
    if (this.documents == null) {
      this.documents = new LinkedHashSet<>();
    }
    this.documents.add(documentsItem);
    return this;
  }

  /**
   * Get documents
   * @return documents
   */
  @Valid 
  @Schema(name = "documents", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("documents")
  public Set<@Valid DocumentDto> getDocuments() {
    return documents;
  }

  @JsonDeserialize(as = LinkedHashSet.class)
  public void setDocuments(Set<@Valid DocumentDto> documents) {
    this.documents = documents;
  }

  public BeneficiaryDto birthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  /**
   * beneficiary birth date
   * @return birthDate
   */
  @Valid 
  @Schema(name = "birthDate", description = "beneficiary birth date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("birthDate")
  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public BeneficiaryDto inclusionDate(LocalDate inclusionDate) {
    this.inclusionDate = inclusionDate;
    return this;
  }

  /**
   * beneficiary inclusion date
   * @return inclusionDate
   */
  @Valid 
  @Schema(name = "inclusionDate", description = "beneficiary inclusion date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("inclusionDate")
  public LocalDate getInclusionDate() {
    return inclusionDate;
  }

  public void setInclusionDate(LocalDate inclusionDate) {
    this.inclusionDate = inclusionDate;
  }

  public BeneficiaryDto updateDate(LocalDate updateDate) {
    this.updateDate = updateDate;
    return this;
  }

  /**
   * beneficiary update date
   * @return updateDate
   */
  @Valid 
  @Schema(name = "updateDate", description = "beneficiary update date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("updateDate")
  public LocalDate getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(LocalDate updateDate) {
    this.updateDate = updateDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BeneficiaryDto beneficiary = (BeneficiaryDto) o;
    return Objects.equals(this.beneficiaryId, beneficiary.beneficiaryId) &&
        Objects.equals(this.name, beneficiary.name) &&
        Objects.equals(this.phone, beneficiary.phone) &&
        Objects.equals(this.documents, beneficiary.documents) &&
        Objects.equals(this.birthDate, beneficiary.birthDate) &&
        Objects.equals(this.inclusionDate, beneficiary.inclusionDate) &&
        Objects.equals(this.updateDate, beneficiary.updateDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(beneficiaryId, name, phone, documents, birthDate, inclusionDate, updateDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BeneficiaryDto {\n");
    sb.append("    beneficiaryId: ").append(toIndentedString(beneficiaryId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    documents: ").append(toIndentedString(documents)).append("\n");
    sb.append("    birthDate: ").append(toIndentedString(birthDate)).append("\n");
    sb.append("    inclusionDate: ").append(toIndentedString(inclusionDate)).append("\n");
    sb.append("    updateDate: ").append(toIndentedString(updateDate)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

