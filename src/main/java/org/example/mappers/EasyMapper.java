package org.example.mappers;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

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

}
