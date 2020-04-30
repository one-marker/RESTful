# RESTful

В качестве базы данных используется PostgreSQL (10.12) развернутая на (Ubuntu 18.04 LTS).

### БД - marker, таблица - users

    create table users
    (
        id       serial not null
            constraint users_pk
                primary key,
        name     text,
        surname  text,
        birthday varchar(10)
    );

    alter table users
        owner to marker;
Для настройки нашего проекта необходимо указать параметры к нашей бд в файле application.properties

    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.datasource.url=jdbc:postgresql://192.168.160.129:5432/marker
    spring.datasource.username=marker
    spring.datasource.password=1
    

### Для тестирования можно использовать приложение PostMan или реализованные JUnut тесты.

   #### Body request:
   
  
   JSON:
   
      {
        "name":"Maxim",
        "surname": "Evlentev",
        "birthday": "01.06.1999"
      }
  
  XML:
  
    <User>
      <name>Maxim</name>
      <surname>Evlentev</surname>
      <birthday>01.06.1999</birthday>
    </User>

  
  
