package org.leandro.beneficiary.ms.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public enum DocumentType {
    COMMON("common"),

    LEGAL("legal");

    DocumentType(String value) {
        this.value = value;
    }

    final String value;

}
