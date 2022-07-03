package com.shpp.p2p.cs.onikolaichuk.collection.map;

/**
 * a class in which an object containing
 * a key and value pair is implemented
 * @param <K> key
 * @param <V> value
 */
public class MapEntry<K, V> {
    private K key;
    private V value;

    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public V setValue(V value) {
        this.value = value;
        return value;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) obj;
        if (key != null ? !key.equals(mapEntry.key) : mapEntry.key != null) {
            return false;
        }
        return value != null ? value.equals(mapEntry.value) : mapEntry.value == null;
    }

    @Override
    public String toString() {
        return "key= " + key + ", value= " + value;
    }
}
