package com.ajwalker.utility;

import java.util.List;
import java.util.Optional;
//TODO : Soft delete ekle
public interface ICRUD<T> {
    boolean save(T t);
    boolean update(T t);
    boolean delete(Long id);
    List<T> findAll();
    Optional<T> findById(Long id);
}
