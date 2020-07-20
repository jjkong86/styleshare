package styleshare.task.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.lang.reflect.Field;

@Getter
@Setter
@ToString
@NoArgsConstructor
public abstract class CommonVo<T> {

    @JsonIgnore
    public abstract String getCollectionKey();

    @JsonIgnore
    public abstract T getCollectionKeyValue();

    @JsonIgnore
    public abstract <I> void setCollectionKeyValue(I instance, Number keyValue) throws IllegalAccessException;

    public String getKeyNameByReflection(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(CollectionKey.class)) { // @CollectionKey 있는지 체크
                return field.getName();
            }
        }
        return "";
    }

    public <I> void setKeyNameByReflection(I instance, T value) throws IllegalAccessException {
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(CollectionKey.class)) {
                field.setAccessible(true);
                field.set(instance, value);
            }
        }
    }
}
