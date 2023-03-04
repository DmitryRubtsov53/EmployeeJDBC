package org.example.service;

import org.example.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    // Добавление объекта
    void create(Employee employee);
    // Получение объекта по id
    void readById(int id);

    // Получение всех объектов
    void readAll();
    // Изменение объекта по id
    void updateById(int id);
    // Удаление объекта по id
    void deleteById(int id);
}
