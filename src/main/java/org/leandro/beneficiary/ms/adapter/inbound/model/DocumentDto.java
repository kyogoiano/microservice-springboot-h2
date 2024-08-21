package org.leandro.beneficiary.ms.adapter.inbound.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * DocumentDto
 */
@lombok.Builder @lombok.AllArgsConstructor

@JsonTypeName("document")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-20T21:22:15.452425452-03:00[America/Sao_Paulo]", comments = "Generator version: 7.7.0")
public class DocumentDto {

  private Long documentId;

  /**
   * Gets or Sets documentType
   */
  public enum DocumentTypeEnum {
    COMMON("common"),
    
    LEGAL("legal");

    private String value;

    DocumentTypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static DocumentTypeEnum fromValue(String value) {
      for (DocumentTypeEnum b : DocumentTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private DocumentTypeEnum documentType;

  private String description;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate inclusionDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate updateDate;

  public DocumentDto documentId(Long documentId) {
    this.documentId = documentId;
    return this;
  }

  /**
   * Get documentId
   * @return documentId
   */
  
  @Schema(name = "documentId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("documentId")
  public Long getDocumentId() {
    return documentId;
  }

  public void setDocumentId(Long documentId) {
    this.documentId = documentId;
  }

  public DocumentDto documentType(DocumentTypeEnum documentType) {
    this.documentType = documentType;
    return this;
  }

  /**
   * Get documentType
   * @return documentType
   */
  
  @Schema(name = "documentType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("documentType")
  public DocumentTypeEnum getDocumentType() {
    return documentType;
  }

  public void setDocumentType(DocumentTypeEnum documentType) {
    this.documentType = documentType;
  }

  public DocumentDto description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   */
  
  @Schema(name = "description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public DocumentDto inclusionDate(LocalDate inclusionDate) {
    this.inclusionDate = inclusionDate;
    return this;
  }

  /**
   * document inclusion date
   * @return inclusionDate
   */
  @Valid 
  @Schema(name = "inclusionDate", description = "document inclusion date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("inclusionDate")
  public LocalDate getInclusionDate() {
    return inclusionDate;
  }

  public void setInclusionDate(LocalDate inclusionDate) {
    this.inclusionDate = inclusionDate;
  }

  public DocumentDto updateDate(LocalDate updateDate) {
    this.updateDate = updateDate;
    return this;
  }

  /**
   * document update date
   * @return updateDate
   */
  @Valid 
  @Schema(name = "updateDate", description = "document update date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
    DocumentDto document = (DocumentDto) o;
    return Objects.equals(this.documentId, document.documentId) &&
        Objects.equals(this.documentType, document.documentType) &&
        Objects.equals(this.description, document.description) &&
        Objects.equals(this.inclusionDate, document.inclusionDate) &&
        Objects.equals(this.updateDate, document.updateDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(documentId, documentType, description, inclusionDate, updateDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DocumentDto {\n");
    sb.append("    documentId: ").append(toIndentedString(documentId)).append("\n");
    sb.append("    documentType: ").append(toIndentedString(documentType)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

