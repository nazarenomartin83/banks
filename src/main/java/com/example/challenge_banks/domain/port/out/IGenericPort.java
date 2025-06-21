package com.example.challenge_banks.domain.port.out;

import java.util.List;
import java.util.Optional;

public interface IGenericPort <T> {
    T create(T entity);
    Optional<T> getById(Long id);
    Optional<T> getByPK(String pk);
    List<T> getAll();
    void delete(Long id);
    T update(T entity);
}
