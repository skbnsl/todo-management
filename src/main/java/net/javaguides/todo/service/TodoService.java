package net.javaguides.todo.service;

import net.javaguides.todo.dto.TodoDto;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodo(Long id);
}
