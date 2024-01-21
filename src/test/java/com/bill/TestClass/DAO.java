package com.bill.TestClass;

import java.util.*;

public class DAO<T> {

    private Map<String, T> map = new HashMap<>();

    public void save(String id, T entity) {
        if (!map.containsKey(id)) {
            this.map.put(id, entity);
        }
    }

    public T get(String id) {
        return this.map.get(id);
    }

    public void update(String id, T entity) {
        if (map.containsKey(id)) {
            this.map.put(id, entity);
        }
    }

    public List<T> list() {
        Collection<T> values = map.values();
        return new ArrayList<>(values);
    }

    public void delete(String id) {
        map.remove(id);
    }

}
