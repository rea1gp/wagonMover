## WAGON MOVER

Схема жизненного пути вагонов

![schema](https://i.ibb.co/GV1ptM6/wagon-schema.png)

### БД
Приложение подключено к БД наполненой тестовыми данными  
Можно создать и подключить свою бд, в таком случае Flyway мигрирует схему и данные  
Развернуть тестовую бд:

```
docker run --name postgres -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=123 -e POSTGRES_DB=wgn_mover -d postgres:13.3
```

### Старт процесса

После старта приложения заходим в swagger http://localhost:8080/swagger-ui/#/  
Логин: admin , пароль: admin
Можно использовать тестовые данные, либо создаем новый груз, тип, лайн, станцию, вагон-паспорт  
Загружаем выбранным грузом вагон (в бд уже есть 10 вагонов, готовых к запуску)   
Статус меняется на "NEW"  
![new_wagon](https://i.ibb.co/bbbdnsB/new-wagon.png)  
Получаем список вагонов, готовых к загрузке  
![wagon_for_run](https://i.ibb.co/5K2Lntc/wagon-run.png)  
Запускаем вагоны на лайн станции  
Указываем номер лайна и в теле id вагонов с индексом их поступления на лайн  
Статус меняется на "STATION"  
![wagon_run](https://i.ibb.co/pJ0qhn9/wagon-run.png)  
Можем проверить поступившие вагоны на лайн в lineController, указать id лайна  
![wagon_by_line](https://i.ibb.co/6nhdzcm/wagon-for-line.png)  
По такому же принципу перемещаем вагоны между путями  
Указываем номер лайна и в теле id вагонов с с индексом их поступления на лайн  
Статус не меняется  
![wagon_top](https://i.ibb.co/4dxy8jd/move-top.png)  
Отправляем вагоны в сеть РЖД  
Указываем колличество вагонов из начала состава и путь с которого вагоны будут отправлены  
Статус меняется на "RZD"  
![to_rzd](https://i.ibb.co/BGMjy38/to-rzd.png)  
Получаем список вагонов отправленных в сеть РЖД  
![rzd_wagons](https://i.ibb.co/5jr8zFG/rzd-wagons.png)  

