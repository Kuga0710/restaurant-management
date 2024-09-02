package com.example.Restaurant.management.controllers;

import com.example.Restaurant.management.dtos.QueryDto;
import com.example.Restaurant.management.dtos.ReviewDto;
import com.example.Restaurant.management.dtos.UserDto;
import com.example.Restaurant.management.entities.Query;
import com.example.Restaurant.management.entities.User;
import com.example.Restaurant.management.enums.RestApiResponseStatusCodes;
import com.example.Restaurant.management.services.QueryService;
import com.example.Restaurant.management.utilities.ResponseWrapper;
import com.example.Restaurant.management.utilities.ValidationMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/query")
@CrossOrigin(origins = "http://localhost:3000")
public class QueryController {

    @Autowired
    private QueryService queryService;

    @PostMapping
    public ResponseEntity<ResponseWrapper<Query>> createQuery(@RequestBody QueryDto queryDto) {
        Query createdQuery=queryService.createQuery(queryDto);
        if (createdQuery != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.CREATED.getCode(),
                    ValidationMessages.SAVED_SUCCESSFULL,
                    null
            ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.BAD_REQUEST.getCode(),
                    ValidationMessages.SAVE_FAILED,
                    null
            ));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<QueryDto>>> getAllReviews() {
        List<QueryDto> queryDtos = queryService.getAllQuery();
        if (queryDtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.BAD_REQUEST.getCode(),
                    ValidationMessages.RETRIEVED_FAILED,
                    null
            ));
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.SUCCESS.getCode(),
                    ValidationMessages.RETRIEVED,
                    queryDtos
            ));
        }
    }
}
