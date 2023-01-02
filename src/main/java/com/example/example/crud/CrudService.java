package com.example.example.crud;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CrudService {
    private final CrudRepository crudRepository;

    public CrudService(CrudRepository crudRepository){
        this.crudRepository = crudRepository;
    }
    public CrudEntity createEntity(CreateDto dto) {
        CrudEntity entity = new CrudEntity();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setNickname(dto.getNickname());
        return crudRepository.save(entity);
    }

    public CrudEntity updateEntity(UpdateDto dto) {
        CrudEntity entity = findEntity(dto.getId());
        Optional.ofNullable(dto.getTitle()).ifPresent(entity::setTitle);
        Optional.ofNullable(dto.getContent()).ifPresent(entity::setContent);
        Optional.ofNullable(dto.getNickname()).ifPresent(entity::setNickname);
        return crudRepository.save(entity);
    }

    public CrudEntity findEntity(Long id){
        return crudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));
    }

    public void deleteEntity(long id) {
        CrudEntity entity = findEntity(id);
        crudRepository.delete(entity);
    }
}
