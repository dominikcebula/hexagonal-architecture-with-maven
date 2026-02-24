package com.dominikcebula.samples.loans.application.port.in.dto.mapper;

import com.dominikcebula.samples.loans.application.domain.model.identifier.Identifier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IdentifierMapper {
    default Long mapIdentifier(Identifier identifier) {
        return identifier.getValue()
                .orElse(null);
    }
}
