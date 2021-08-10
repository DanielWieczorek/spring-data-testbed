package de.wieczorek.dataloader.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MappingContext<K> {

    private Map<Class<?>, Map<K, Object>> mappedObjects = new HashMap<>();

    public <T> Map<K, T> getAll(Class<T> clazz) {
        return (Map<K, T>) mappedObjects.getOrDefault(clazz, new HashMap<>());
    }

    public <T> T get(Class<T> clazz, K id) {
        return (T) mappedObjects.getOrDefault(clazz, new HashMap<>()).get(id);
    }

    public <T> void addAll(Class<T> clazz, Function<T, K> keyExtractor, Set<T> bos) {
        Map<K, T> mapped = bos.stream().collect(Collectors.toMap(keyExtractor, Function.identity()));
        var targetMap = mappedObjects.getOrDefault(clazz, new HashMap<>());
        targetMap.putAll(mapped);
        mappedObjects.put(clazz, targetMap);
    }

    public <T> void add(Class<T> clazz, Function<T, K> keyExtractor, T bo) {
        addAll(clazz, keyExtractor, Set.of(bo));
    }
}
