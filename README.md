Вариант 18.

Система Кинотеатр. Вы пишете интернет витрину маленького Кинотеатра 
с одним залом. В нем есть Расписание показа фильмов на все 7 дней 
недели с 9:00 до 22:00 (начало последнего фильма). 
Незарегистрированный пользователь может видеть: расписание, 
свободные места в зале, возможность зарегистрироваться. 
Зарегистрированный пользователь должен быть в состоянии выкупить 
билет на выбранное место. Администратор может: внести в расписание 
новый фильм, отменить фильм, просматривать посещаемость зала.

Для установки данного проекта откройте командную строку в удобной 
для вас папке и введите команду

git clone https://github.com/olegyosypenko/cinema.git

Потом запустите скрипт db_dump.sql Для доступа к базе даннных: 
Имя пользователя: cinema_admin Пароль: cinema_admin

Для запуска приложения откройте командную строку в корневой папке 
проекта и введите команду

mvn tomcat7:run

Приложение будет доступно по ссылке
http://localhost:8888/cinema/servlet/free/home

Для остановки приложения воспользуйтесь инструкцией по этой ссылке 
http://qaru.site/questions/148505/how-to-kill-a-currently-using-port-on-localhost-in-windows