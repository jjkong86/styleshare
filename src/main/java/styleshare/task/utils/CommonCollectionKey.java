package styleshare.task.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.lang.reflect.Field;

public interface CommonCollectionKey<T> {

    @JsonIgnore
    default String getCollectionKey() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(CollectionKey.class)) { // @CollectionKey 있는지 체크
                return field.getName();
            }
        }
        return "";
    }

    @JsonIgnore
    default T getCollectionKeyValue() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(CollectionKey.class)) { // @CollectionKey 있는지 체크
                field.setAccessible(true);
                try {
                    return (T) field.get(this);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    default void setKeyNameByReflection(T value) {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(CollectionKey.class)) {
                field.setAccessible(true);
                try {
                    field.set(this, value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
