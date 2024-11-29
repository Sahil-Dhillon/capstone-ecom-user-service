package com.ecom.service.user;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.user.ICounterRepo;
import com.ecom.model.user.Counter;

@Service
public class CounterService {

    @Autowired
    private ICounterRepo counterRepository;

    // Initialize counter if not present
    @Transactional
    public Counter getCounter() {
        return counterRepository.findById(1).orElseGet(() -> {
            Counter counter = new Counter();
            counterRepository.save(counter);
            return counter;
        });
    }

    @Transactional
    public void incrementCategoryCount() {
        Counter counter = getCounter();
        counter.setCategoryCount(counter.getCategoryCount() + 1);
        counterRepository.save(counter);
    }
    

    @Transactional
    public void incrementProductCount() {
        Counter counter = getCounter();
        counter.setProductCount(counter.getProductCount() + 1);
        counterRepository.save(counter);
    }

    @Transactional
    public void incrementUserCount() {
        Counter counter = getCounter();
        counter.setUserCount(counter.getUserCount() + 1);
        counterRepository.save(counter);
    }

    @Transactional
    public void incrementOrderCount() {
        Counter counter = getCounter();
        counter.setOrderCount(counter.getOrderCount() + 1);
        counterRepository.save(counter);
    }
    
 // Decrement methods
    @Transactional
    public void decrementCategoryCount() {
        Counter counter = getCounter();
        if (counter.getCategoryCount() > 0) {
            counter.setCategoryCount(counter.getCategoryCount() - 1);
            counterRepository.save(counter);
        }
    }

    @Transactional
    public void decrementProductCount() {
        Counter counter = getCounter();
        if (counter.getProductCount() > 0) {
            counter.setProductCount(counter.getProductCount() - 1);
            counterRepository.save(counter);
        }
    }

    @Transactional
    public void decrementUserCount() {
        Counter counter = getCounter();
        if (counter.getUserCount() > 0) {
            counter.setUserCount(counter.getUserCount() - 1);
            counterRepository.save(counter);
        }
    }

    @Transactional
    public void decrementOrderCount() {
        Counter counter = getCounter();
        if (counter.getOrderCount() > 0) {
            counter.setOrderCount(counter.getOrderCount() - 1);
            counterRepository.save(counter);
        }
    }

}

