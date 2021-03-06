# QuizSystem

Система тестирования студентов. 

За основу взята система Assist, которой пользуются в рамках обучения курсов переподготовки МРЦПК Инженерная школа ДВФУ (бывший ТЦПВ).

Выполняется в соответствии с требованиями по задаче.

Разработка многопользовательского сервиса для проведения тестирования студентов (слушателей групп профессиональной переподготовки и переквалификации), который включает консольный интерфейс, web-интерфейс и мобильное приложение.

Для эксплуатации Системы должны быть определены следующие роли:
1.	Студент (он же слушатель группы переподготовки);
2.	Преподаватель - субъект, контролирующий процесс тестирования;
3.	Администратор системы;

Для роли «Студент» должен быть реализован следующий функционал:
1.	Аутентификация и авторизация;
2.	Выбор теста из списка доступных;
3.	Загрузку теста с вопросами и вариантами ответов (из внешнего хранилища в слой приложения, взаимодействующий с пользователем);
4.	Вывод вопросов и вариантов ответов через интерфейс с пользователем;
5.	Регистрация ввода ответа на вопрос;
6.	Расчет оценки за каждый ответ;
7.	Расчет общей оценки за прохождение тестирования;
8.	Сохранения результата во внешнее хранилище;
9.	Вывод статистики по пройденным тестам.

Для роли «Преподаватель» должен быть реализован следующий функционал:
1.	Аутентификация и авторизация;
2.	Список тестов, доступных преподавателю на чтение и/или редактирование;
3.	Создание нового теста;
4.	Задания ряда вопросов с вариантами ответов и внесение правильных ответов;
5.	Задание списка тестов для каждого студента;
6.	Вывод статистики по пройденным тестам с указанием студентов.

Для роли «Администратор» должен быть реализован следующий функционал:
1.	Аутентификация и авторизация;
2.	Управление пользователями;
3.	Управление общими параметрами тестов.

Интерфейсы:
1. Консольный интерфейс
2. Веб-интерфейс
3. Мобильное приложение
