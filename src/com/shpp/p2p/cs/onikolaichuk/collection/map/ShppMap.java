package com.shpp.p2p.cs.onikolaichuk.collection.map;

import com.shpp.p2p.cs.onikolaichuk.collection.chainlist.ShppLinkedList;

public class ShppMap<K, V> implements Map<K, V> {
    /* the number MapEntry of which are stored in the structure */
    private int count = 0;
    /* internal array size */
    private int capacity = 16;
    /**
     * the load factor
     * is the ratio of the size
     * of the array to the number of elements present in the table
     */
    private final float loadFactor = 0.75f;
    /**
     * the threshold, when exceeded, the internal array must be increased
     */
    int threshold;
    /* */
    private ShppLinkedList<MapEntry<K, V>>[] buckets = new ShppLinkedList[capacity];

    /**
     * constructor in which we calculate the threshold value
     */
    public ShppMap() {
        this.threshold = (int) (capacity * loadFactor);
    }

    /**
     * calculate the index
     * using one of the variations of calculating the hash value of the object
     * to get in range
     * we take the remainder of the division by the size of the internal array
     *
     * @param key object
     * @return index array
     */
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    /**
     * first we check the threshold value with the number of available elements
     * <p>
     * we insert into the linked list
     * which is placed according to the calculated index through the key
     * if there is already a note in the letter, we attach it to it
     *
     * @return old value or null
     */
    @Override
    public V put(K key, V value) {
        if (count > threshold) {
            resize();
        }
        V oldValue = null;
        int index = hash(key);
        if (buckets[index] == null) {
            buckets[index] = new ShppLinkedList<>();
        }
        ShppLinkedList<MapEntry<K, V>> bucket = buckets[index];
        boolean present = false;
        for (MapEntry<K, V> pair : bucket) {
            if (pair.getKey().equals(key)) {
                oldValue = pair.getValue();
                pair.setValue(value); // if such a key is contained, then we update the value
                present = true;
                break;
            }
        }
        if (!present) {
            bucket.add(new MapEntry<>(key, value));
            count++;
        }
        return oldValue;
    }

    /**
     * if the number of elements in the map exceeds the threshold,
     * then we create a new array and reinsert it again
     * that is, we are hashing
     */
    private void resize() {
        capacity *= 2;
        ShppLinkedList<MapEntry<K, V>>[] oldMap = buckets;
        buckets = new ShppLinkedList[capacity];
        count = 0;
        for (ShppLinkedList<MapEntry<K, V>> bucket : oldMap) {
            if (bucket != null) {
                for (MapEntry<K, V> pair : bucket) {
                    put(pair.getKey(), pair.getValue());
                }
            }
        }
        threshold = (int) (capacity * loadFactor);
    }

    /**
     * we go through all the buckets and the nodes
     * in the bucket in turn and compare the keys
     *
     * @return value
     */
    @Override
    public V get(K key) {
        for (MapEntry<K, V> pair : buckets[hash(key)]) {
            if (pair.getKey().equals(key)) {
                return pair.getValue();
            }
        }
        return null;
    }

    /**
     * we delete the element that corresponds to the key
     * or null
     *
     * @return value or null
     */
    public V remove(K key) {
        V result = null;
        if (count > 0) {
            int index = hash(key);
            for (int i = 0; i < buckets[index].size(); i++) {
                if (buckets[index].get(i).getKey().equals(key)) {
                    result = buckets[index].get(i).getValue();
                    buckets[index].remove(i);
                    count--;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * number MapEntry into map
     *
     * @return count
     */
    public int size() {
        return count;
    }

    /**
     * view all map entry
     * @return string
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            if (buckets[i] != null) {
                builder.append(i).append(" ");
                buckets[i].forEach(pair -> builder.append(pair.toString()));
            }
        }
        return builder.toString();
    }
}
