package com.ecom.service.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.model.user.Counter;

@Service
public class DashboardService {

    @Autowired
    private CounterService counterService;

    public Map<String, Integer> getDashboardCounts() {
        Counter counter = counterService.getCounter();
        Map<String, Integer> counts = new HashMap<>();
        counts.put("categories", counter.getCategoryCount());
        counts.put("products", counter.getProductCount());
        counts.put("users", counter.getUserCount());
        counts.put("orders", counter.getOrderCount());
        return counts;
    }
}
