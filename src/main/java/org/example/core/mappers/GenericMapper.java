package org.example.core.mappers;

import org.example.annotations.MapField;
import org.example.core.ignores.IgnoreParam;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class GenericMapper {


    public <E, D> E mapper(D current, Class<E> targetClass){
        try{
            var entityNewInstance = targetClass.getDeclaredConstructor().newInstance();
            var currentFields = current.getClass().getDeclaredFields();
            var targetFields = targetClass.getDeclaredFields();

            for (Field fieldCurrent: currentFields){
                var annotation = fieldCurrent.getAnnotation(MapField.class);
                fieldCurrent.setAccessible(true);

                // Verifica se tem anotacao na classe atual e se o nome do campo esta vazio
                if(annotation != null && !annotation.nameField().isEmpty()){
                    Field fieldTarget = targetClass.getDeclaredField(annotation.nameField());
                    fieldTarget.setAccessible(true);
                    fieldTarget.set(entityNewInstance, fieldCurrent.get(current));
                    continue;
                }
                //---------------------------------------------------------
                //se caso tenha o campo na classe alvo mapeado pela annotation MapField



                //---------------------------------------------------------
                //se caso nao tenha o campo na classe alvo e nem anotacao
                Field fieldTarget = targetClass.getDeclaredField(fieldCurrent.getName());
                fieldTarget.setAccessible(true);
                fieldTarget.set(entityNewInstance, fieldCurrent.get(current));
            }
            return entityNewInstance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }





    private boolean valideCurrent(Field fieldCurrent, Field[] currentFields){
        var result = Arrays.stream(currentFields).anyMatch(p -> {
            if (!p.isAnnotationPresent(MapField.class)){
                return false;
            }
            return fieldCurrent.equals(p.getAnnotation(MapField.class).nameField());
        });

        return result;
    }

    private boolean ignoreField(String fieldName, List<IgnoreParam> ignoreParams){
        return ignoreParams.stream().anyMatch(ignoreParam -> ignoreParam.getFieldName().equals(fieldName));
    }

}
