package com.example.example.crud;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CrudMapper {
    ResponseDto crudEntityToResponseDto(CrudEntity entity);
}
