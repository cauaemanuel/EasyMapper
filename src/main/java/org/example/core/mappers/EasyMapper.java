package org.example.core.mappers;

import org.example.core.ignores.IgnoreParam;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class EasyMapper {

    public <D, E> D toDto(E entity, Class<D> dtoClass) {
        try {
            D dtoInstance = dtoClass.getDeclaredConstructor().newInstance();
            for (Field fieldDto : dtoClass.getDeclaredFields()) {
                fieldDto.setAccessible(true);
                Field fieldEntity = entity.getClass().getDeclaredField(fieldDto.getName());
                fieldEntity.setAccessible(true);
                fieldDto.set(dtoInstance, fieldEntity.get(entity));
            }
            return dtoInstance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <D, E> D toDto(E entity, Class<D> dtoClass, List<IgnoreParam> ignoreParams) {
        try {
            D dtoInstance = dtoClass.getDeclaredConstructor().newInstance();
            for (Field fieldDto : dtoClass.getDeclaredFields()) {
                if(ignoreParams.stream().anyMatch(ignoreParam -> ignoreParam.getFieldName().equals(fieldDto.getName()))){
                    continue;
                }
                fieldDto.setAccessible(true);
                Field fieldEntity = entity.getClass().getDeclaredField(fieldDto.getName());
                fieldEntity.setAccessible(true);
                fieldDto.set(dtoInstance, fieldEntity.get(entity));
            }
            return dtoInstance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <E, D> E toEntity(D dto, Class<E> entityClass){
        try{
            var entityInstance = entityClass.getDeclaredConstructor().newInstance();
            for (Field fieldEntity: entityClass.getDeclaredFields()){
                fieldEntity.setAccessible(true);
                Field fieldDto = dto.getClass().getDeclaredField(fieldEntity.getName());
                fieldDto.setAccessible(true);
                fieldEntity.set(entityInstance, fieldDto.get(dto));
            }
            return entityInstance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <E, D> E toEntity(D dto, Class<E> entityClass, List<IgnoreParam> ignoreParams){
        try{
            var entityInstance = entityClass.getDeclaredConstructor().newInstance();
            for (Field fieldEntity: entityClass.getDeclaredFields()){
                if(ignoreParams.stream().anyMatch(ignoreParam -> ignoreParam.getFieldName().equals(fieldEntity.getName()))){
                    continue;
                }
                fieldEntity.setAccessible(true);
                Field fieldDto = dto.getClass().getDeclaredField(fieldEntity.getName());
                fieldDto.setAccessible(true);
                fieldEntity.set(entityInstance, fieldDto.get(dto));
            }
            return entityInstance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
