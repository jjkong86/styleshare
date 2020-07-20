package styleshare.task.utils;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CollectionUtil {

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    // group by key to list
    public <E extends CommonCollectionKey<? extends Number>> List<E> distinctToList(List<E> list) {
        return list.stream().filter(distinctByKey(E::getCollectionKey)).collect(Collectors.toList());
    }
}
