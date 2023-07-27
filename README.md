# Тестовое задание "Справочник автомобилей REST API"
Задание выполнено при помощи Java и Spring framework.
Данное REST-приложение позволяет хранить информацию об автомобилях. Присутсвуют следующие функции: получение всех машин, получение одной машины, добавление или удаление машины, получение статистики базы.

Используемые технологии: Spring Boot, Postgresql, Liquibase

Структура проекта:
    -controllers
    -dto
    -entities
    -exceptions 
    -repositorires
    -services
    -validators
    -main
  -resources

## Запуск
1. Клонировать репозиторий
2. Настройка базы данных в application.yaml
3. Запустить приложение

## Руководство

1. HTTP GET /cars - вовзращает список автомобилей в порядке регистрационных номеров
  - ![image](https://github.com/Comrade1l/CarsDirectory/assets/110826937/8f63be38-2e6f-4d69-97cb-fff5af949d0b)
2. HTTP GET /cars/{id} - возвращает конкретный автомобиль
  - ![image](https://github.com/Comrade1l/CarsDirectory/assets/110826937/8aa96762-6080-423f-99fc-a2769522face)
3. HTTP POST /cars/add - добавляет автомобиль
  - ![image](https://github.com/Comrade1l/CarsDirectory/assets/110826937/1b92bbb1-0d03-4c97-bb05-67dada45fae1)
  - ![image](https://github.com/Comrade1l/CarsDirectory/assets/110826937/88c38319-f07a-4c7f-aa6d-fa2c7445a54a)
4. HTTP DELETE /cars/{1} - удаляет автомобиль по id
  - ![image](https://github.com/Comrade1l/CarsDirectory/assets/110826937/ed55ade9-8903-4a43-8243-f17b5ae4105b)
5. HTTP DELETE /cars/deleteByRegNumber/{regNumber} - удаляет машину по регистрационному номеру
  - ![image](https://github.com/Comrade1l/CarsDirectory/assets/110826937/992ef052-cb4d-4939-9399-300b48c8cbd5)
6. HTTP GET /stats - получает статистику базы
  - ![image](https://github.com/Comrade1l/CarsDirectory/assets/110826937/98438430-569f-482e-88af-71af2e698427)
7. HTTP GET /stats/count - получает количество записей в базе
  - ![image](https://github.com/Comrade1l/CarsDirectory/assets/110826937/4cae164e-e4e9-45ba-900c-7902f18af6bc)
8. HTTP GET /stats/firstRecord - вовзращает объект, который был записан в базу первым
  - ![image](https://github.com/Comrade1l/CarsDirectory/assets/110826937/7322acbf-e44c-4ee4-99bc-36e2a8b46e27)
9. HTTP GET /stats/lastRecord - вовзращает объект, который был записан в базу последним
  - ![image](https://github.com/Comrade1l/CarsDirectory/assets/110826937/c1dd36a9-067a-423a-9232-1bd474640e65)
