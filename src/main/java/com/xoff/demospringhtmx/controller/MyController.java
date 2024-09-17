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

  public static <E> List<E> makeCollection(Iterable<E> iter) {
    List<E> list = new ArrayList<E>();
    for (E item : iter) {
      list.add(item);
    }
    return list;
  }

  public MyController(){ }
  @GetMapping("/")
  public String items(Model model) {
    System.out.println("BEGIN items");
    model.addAttribute("itemList", todoItemRepository.findAll());
    System.out.println("todoItemRepository "+todoItemRepository);
    return "index";
  }
  @PostMapping("/todos/{id}/complete")
  public String completeTodo(@PathVariable Integer id, Model model) {
    TodoEntity item = null;
    for (TodoEntity existingItem :  todoItemRepository.findAll()) {
      if (existingItem.getId()==id) {
        item = existingItem;
        break; 
      }
    }
    if (item != null) {
      item.setCompleted(!item.isCompleted());
    }
    model.addAttribute("item",item);
    return "todo"; 
  }
  @PostMapping("/todos")
  public String createTodo(Model model, @ModelAttribute TodoEntity newTodo) {
    List<TodoEntity> items=makeCollection(todoItemRepository.findAll());
    int nextId = items.stream().mapToInt(TodoEntity::getId).max().orElse(0) + 1;
    newTodo.setId(nextId);
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

