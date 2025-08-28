package org.example.core.mappers;

import org.example.annotations.MapField;
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
            var entityNewInstance = entityClass.getDeclaredConstructor().newInstance();
            for (Field fieldDTO: dto.getClass().getDeclaredFields()){
                var annotation = fieldDTO.getAnnotation(MapField.class);

                if(annotation != null && !annotation.nameField().isEmpty()){
                    Field fieldEntity = entityClass.getDeclaredField(annotation.nameField());
                    fieldEntity.setAccessible(true);
                    fieldDTO.setAccessible(true);
                    fieldEntity.set(entityNewInstance, fieldDTO.get(dto));
                    continue;
                }
                fieldDTO.setAccessible(true);
                Field fieldEntity = entityClass.getDeclaredField(fieldDTO.getName());
                fieldEntity.setAccessible(true);
                fieldEntity.set(entityNewInstance, fieldDTO.get(dto));
            }
            return entityNewInstance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <E, D> E toEntity(D dto, Class<E> entityClass, List<IgnoreParam> ignoreParams){
        try{
            var entityNewInstance = entityClass.getDeclaredConstructor().newInstance();

            for (Field fieldDTO: dto.getClass().getDeclaredFields()){
                var annotation = fieldDTO.getAnnotation(MapField.class);

                if(annotation != null && !annotation.nameField().isEmpty()){
                    Field fieldEntity = entityClass.getDeclaredField(annotation.nameField());
                    if(ignoreField(fieldEntity.getName(), ignoreParams)){
                        continue;
                    }
                    fieldEntity.setAccessible(true);
                    fieldDTO.setAccessible(true);
                    fieldEntity.set(entityNewInstance, fieldDTO.get(dto));
                    continue;
                }
                if(ignoreField(fieldDTO.getName(), ignoreParams)){
                    continue;
                }
                fieldDTO.setAccessible(true);
                Field fieldEntity = entityClass.getDeclaredField(fieldDTO.getName());
                fieldEntity.setAccessible(true);
                fieldEntity.set(entityNewInstance, fieldDTO.get(dto));
            }
            return entityNewInstance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean ignoreField(String fieldName, List<IgnoreParam> ignoreParams){
        return ignoreParams.stream().anyMatch(ignoreParam -> ignoreParam.getFieldName().equals(fieldName));
    }
}
