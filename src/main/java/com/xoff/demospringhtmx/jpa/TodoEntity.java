package com.xoff.demospringhtmx.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "todo")
@Getter
@Setter
public class TodoEntity {

  protected  TodoEntity(){

  }
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private boolean completed;
  private String description;
}
