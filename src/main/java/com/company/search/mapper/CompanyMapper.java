package com.company.search.mapper;

import com.company.search.client.dto.CompanyDto;
import com.company.search.model.entity.Company;
import com.company.search.model.entity.Officer;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    @Mapping(source = "officers", target = "officers")
    Company companyClientDtoToEntity(CompanyDto companyDto, List<Officer> officers);

    List<com.company.search.model.dto.CompanyDto> companyEntityListtoDtoList(List<Company> companies);

    @AfterMapping
    default void setOfficers(@MappingTarget Company company) {
        Optional.ofNullable(company.getOfficers()).ifPresent(item -> item.forEach(it -> it.setCompany(company)));
    }
}
