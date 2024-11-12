package net.javaguides.todo.controller;

import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.entity.Todo;
import net.javaguides.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
        TodoDto savedTodoDto = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodoDto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER)")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId){
        TodoDto todoDto = todoService.getTodo(todoId);
        return new ResponseEntity<>(todoDto,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER)")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos(){
        List<TodoDto> alltodosDtos = todoService.getAllTodos();
        //return new ResponseEntity<>(alltodosDtos,HttpStatus.OK);
        return ResponseEntity.ok(alltodosDtos);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updatedTodo(@RequestBody TodoDto todoDto,@PathVariable("id") Long id){
        TodoDto updateTodo =  todoService.updateTodo(todoDto,id);
        return ResponseEntity.ok(updateTodo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Todo Deleted Succwssfully");
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER)")
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id){
        TodoDto completedTodo = todoService.completeTodo(id);
        return new ResponseEntity<>(completedTodo,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER)")
    @PatchMapping("{id}/incomplete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable Long id){
        TodoDto inCompletedTodo = todoService.inCompleteTodo(id);
        return new ResponseEntity<>(inCompletedTodo,HttpStatus.OK);
    }

}