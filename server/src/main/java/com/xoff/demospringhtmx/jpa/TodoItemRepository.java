package com.xoff.demospringhtmx.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

  public interface TodoItemRepository extends CrudRepository<TodoEntity, Long> {

    List<TodoEntity> findByDescription(String description);

    TodoEntity findById(long id);
  }
