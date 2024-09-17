package com.xoff.demospringhtmx.controller;

import com.xoff.demospringhtmx.jpa.TodoEntity;
import com.xoff.demospringhtmx.jpa.TodoItemRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")

public class RestController {

  @Autowired
  private TodoItemRepository todoItemRepository;

  @GetMapping(path = "/items",produces= MediaType.APPLICATION_JSON_VALUE)
  @CrossOrigin(origins = "http://localhost:3000")
  @ResponseStatus(HttpStatus.OK)
  public List<TodoEntity> list()
  {
    List<TodoEntity> result = new ArrayList<TodoEntity>();
    todoItemRepository.findAll().forEach(result::add);
    return result;
  }
}
