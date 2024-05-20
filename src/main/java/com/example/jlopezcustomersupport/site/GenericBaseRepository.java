package com.example.jlopezcustomersupport.site;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class GenericBaseRepository <I extends Serializable, E extends Serializable> implements GenericRepository<I,E> {

    protected final Class<I> idClass;
    protected final Class<E> entityClass;

    public GenericBaseRepository() {
        Type genericSuperClass = this.getClass().getGenericSuperclass();
        while (!(genericSuperClass instanceof ParameterizedType)) {
            if (!(genericSuperClass instanceof ParameterizedType)) {
                throw new IllegalStateException("Unable to determine type arguments because generic superclass neighter parameterized type nor class.");
            }
            if (genericSuperClass == GenericBaseRepository.class) {
                throw new IllegalStateException("Unable to determine type arguments because generic no parameterized generic superclass found.");
            }

            genericSuperClass = ((Class) genericSuperClass).getGenericSuperclass();

        }

        ParameterizedType type = (ParameterizedType) genericSuperClass;
        Type[] aruguments = type.getActualTypeArguments();
        idClass = (Class<I>) aruguments[0];
        entityClass = (Class<E>) aruguments[1];
    }


}
