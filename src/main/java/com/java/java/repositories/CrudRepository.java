package com.java.java.repositories;

import java.util.List;

/**
 * A generic interface that defines basic CRUD (Create, Read, Update, Delete) operations
 * for a repository managing entities of type {@code T}.
 *
 * @param <T> the type of the entity this repository manages
 */
public interface CrudRepository<T> {
    boolean insert(T obj);
    boolean update(T obj);
    boolean delete(Integer id);
    T get(Integer id);
    List<T> selectAll();
}
