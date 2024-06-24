package com.company.search.mapper;

import com.company.search.client.dto.OfficerItemDto;
import com.company.search.model.dto.OfficerDto;
import com.company.search.model.entity.Officer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OfficerMapper {

    OfficerMapper INSTANCE = Mappers.getMapper(OfficerMapper.class);

    Officer officerClientDtoToEntity(OfficerItemDto officerItemDto);

    OfficerDto officerEntityToDto(Officer officer);
}
