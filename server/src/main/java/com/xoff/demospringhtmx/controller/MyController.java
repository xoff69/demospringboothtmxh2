package com.xoff.demospringhtmx.controller;
                    
import com.xoff.demospringhtmx.jpa.TodoEntity;
import com.xoff.demospringhtmx.jpa.TodoItemRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;

@Controller
public class MyController {

  @Autowired
  private TodoItemRepository todoItemRepository;


  public MyController(){ }
  @GetMapping("/")
  public String items(Model model) {
    System.out.println("BEGIN items");
    model.addAttribute("itemList", todoItemRepository.findAll());
    return "index";
  }

  @PostMapping("/todos")
  public String createTodo(Model model, @ModelAttribute TodoEntity newTodo) {
    System.out.println("newTodo: " + newTodo);
    todoItemRepository.save(newTodo);

    model.addAttribute("item", newTodo);
    return "todo";
  }
  @PostMapping("/todos/{id}/delete")
  @ResponseBody
  public String deleteTodo(@PathVariable long id) {
    todoItemRepository.deleteById(id);
    return "";
  }

}

