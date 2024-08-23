package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.QueryDto;
import com.example.Restaurant.management.entities.Query;

public interface QueryService {
    Query createQuery(QueryDto queryDto);
}
