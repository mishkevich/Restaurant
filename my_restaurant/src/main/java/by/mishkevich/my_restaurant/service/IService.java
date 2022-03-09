package by.mishkevich.my_restaurant.service;

import java.util.List;

public interface IService<T, K> {
    List<T> findAll();

    T findById(K id);

    void create(T entity);

    void delete(K id);
}
