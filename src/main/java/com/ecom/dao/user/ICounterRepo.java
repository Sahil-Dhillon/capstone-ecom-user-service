package com.ecom.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.user.Counter;

public interface ICounterRepo extends JpaRepository<Counter, Integer> {
}

