# Genelove
![logo](https://github.com/khasang/genelove/blob/development/web/WEB-INF/views/images/love2.jpg)


description

## Install 

(что надо для установки приложения)

## Java Mail Sending 

что надо установить чтобы JMS работал (Active MQ)

## AdminController

/admin - Экран администрирования

/admin/usersList - Список пользователей

/admin/user/id/{id} - пользователь с ID = {id}

/admin/user/login/{login} - пользователь с login = {login}

/admin/delete/id/{id} - удалить пользователя с ID = {id}



## UserController

### зарегистрироваться
`String registration()`
### войти в систему
`String login()`
### выйти из системы
`String logout()`

### отправить сообщение другому пользователю
`String messagePost()`
### получить сообщение от другого пользователя
`String messageGet()`
### удалить сообщение из своей переписки
`String messageDelete()`
### просмотреть все сообщения
`String messageAll()`

### добавить другого пользователя в закладки
`String favoriteAdd()`
### удалить другого пользователя из закладок
`favoriteDelete()`
### просмотреть все закладки
`favoriteAll()`

### добавить человека в генеалогическое дерево
`relativeNodeAdd()`
### удалить человека из генеалогического дерева
`relativeNodeDelete()`
### изменить данные человека в генеалогическом дереве
`relativeNodeUpdate()`
### посмотреть всё дерево
`relativeNodeAll()`

### добавить личную анкету
`persInfoAdd()`
### удалить личную анкету
`persInfoDelete()`
### изменить данные по личной анкете
`persInfoUpdate()`
### просмотреть анкету
`persInfoView()`

### добавить мультимедиа-альбом
`albumAdd()`
### удалить мультимедиа-альбом
`albumDelete()`
### изменить мультимедиа-альбом
`albumUpdate()`
### просмотреть альбом (все файлы)
`albumView()`
### просмотреть все альбомы
`albumAll()`

### добавить файл фото/видео в альбом
`fileAdd()`
### удалить файл фото/видео из альбома
`fileDelete()`
### изменить файл фото/видео в альбоме
`fileUpdate()`

## Сущности

User - пользователи

Role - роли пользователей

Authorisation - авторизация пользователей (связь между пользователем и ролью)

AuthorisationKey - составной ключ сущности Authorisation (в базе данных не создается)

Profile - анкеты пользователей

Message - личные сообщения пользователей

EMail - сообщения электронной почты пользователей и не только

Favourite - выбранные пользователем фавориты (друзья), т.е. другие пользователи

FavouriteKey - составной ключ сущности Favorite (в базе данных не создается)

Tree - генеалогическое дерево пользователя

Person - люди, составляющие генеалогическое дерево. Владелец дерева (т.е. пользователь) также представлен в этой таблице. Для него User (user_id) NOT NULL

Relationship - отношения между людьми, составляющими дерево

RelationshipType - типы отношений между людьми ("брак", "родительство" и т.д.)

RelationshipRole - роли людей в паре, составляющей отношение (например, для отношения "родительство" роль 1 = "мать", роль 2 = "дочь", для отношения "брак" - роль 1 = "муж", роль 2 = "жена")

Реализация дерева, с небольшими изменениями, заимствована отсюда:

http://databaseanswers.org/data_models/genealogy/genealogy_physical.htm

## Доступы к объектам

какие методы и зачем

## Подключение и тестирование отправки внешних сообщений - JMS

В программе используется асинхронный способ отправки/получения JMS сообщений "point to point".
Чтобы проверить работу JMS необходимо провести следующие действия:

### 1.Установка и запуск сервера(брокера) сообщений
 Для работы JMS необходим сервер(брокер). Мы используем готовый бесплатный сервер Apache ActiveMQ.
Для его установки необходимо пройти по ссылке:
http://activemq.apache.org/download.html
Затем скачать и распаковать в любую удобную папку на компьютере. Установка закончена.
Далее необходимо запустить сервер в работу. Тут надо смотреть в зависимости от операционной системы - для 64-й Windows это каталог:
"/bin/win64", файл "activemq.bat".
Проверить, работает ли сервер можно перейдя по адресу:
http://localhost:8161/admin/
login: admin
password: admin

### 2.Отправка сообщений с помощью программы "genelove"
 Открываем список пользователей: /admin/usersList .
У каждого пользователя из списка в колонке "Inspection" есть кнопка "Inspect", по нажатии на которую оправляется запрос
о проверке данного пользователя на предмет соответствия заданных характеристик истинным в другую испекционную программу.
Запрос отправляется в виде JMS-сообщения. Выбираем любого пользователя и нажимаем кнопку.  Если отправка прошла успешно
в окне браузера появится текст "JMS Message for inspection user was send".

### 3.Проверка очереди сообщений на сервере
 Для проверки пришло(ли) наше(и) сообщение(я) на сервер открываем адрес http://localhost:8161/admin/queues ,
и в списке очередей ищем очередь с наименованием "inspection-queue". Проверяем число в первом столбце - 
это и есть количество сообщений, оно должно отличаться от значения до отправки.

### 4.Получение сообщения с помощью программы "person-inspection"
 Клонируем исходники программы https://github.com/DenisGuzikov/person-inspection.git
себе в локальную папку, открываем в новом окне, и настраиваем параметры 
запуска из под среды Idea:
в меню "Open module settings" надо добавить только два модуля:
Web и Spring. Также чтобы не закрывать основную программу "genelove", надо 
изменить номера портов для запуска "project-inspection":
HTTP-port: 8081, JMX-port: 1099, либо закрыть "genelove", т.к. наличие 
одновременно 2-х открытых программ необязательно.
Запускаем программу. Теперь, чтобы проверить пришли ли сообщения открываем
адрес: http://localhost:8081/allMessages
в списке сообщений ищем сообщение с нужным пользователем (последние снизу).
Открываем опять очередь сообщений на сервере - если она пустая, значит всё работает правильно.


