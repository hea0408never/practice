package com.example.example.crud;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@Validated
@RequestMapping("/")
public class CrudController {
    private final CrudService crudService;
    private final CrudMapper crudMapper;

    public CrudController(CrudService crudService, CrudMapper crudMapper){
        this.crudService = crudService;
        this.crudMapper = crudMapper;
    }

    @PostMapping("create")
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody CreateDto dto){
        CrudEntity entity = crudService.createEntity(dto);
        return ResponseEntity.ok(crudMapper.crudEntityToResponseDto(entity));
    }

    @GetMapping("read")
    public ResponseEntity<ResponseDto> read(@Positive @RequestParam long id){
        CrudEntity entity = crudService.findEntity(id);
        return ResponseEntity.ok(crudMapper.crudEntityToResponseDto(entity));
    }

    @PatchMapping("update")
    public ResponseEntity<ResponseDto> update(@Valid @RequestBody UpdateDto dto){
        CrudEntity entity = crudService.updateEntity(dto);
        return ResponseEntity.ok(crudMapper.crudEntityToResponseDto(entity));
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> delete(@Positive @RequestParam long id){
        crudService.deleteEntity(id);
        return ResponseEntity.ok("삭제됨");
    }
}
