# ConverterU

Тестовое задание в проект ШИФТ компании ЦФТ.
Приложение конвертер которые берёт данные из json файла.

Реализовано:
1) Отображение данных списком
2) Можно конвертировать валюту: Нажимаем на элемент списка и переходим на экран конвертации, вводим кол-во иностранной валюты и жмём кнопку "Convert"
3) Приложение сохраняет список в базу данных и не перегружает его после выхода из приложения
4) Можно обновить данные вручную(для это swipe to refresh)
5) Данные обновляется при запуске приложения если прошёл день с последней загрузки


Использованные библиотеки:
Room - для сохранения списка в базу данных.
Coroutins - для запросов в бд.
Dagger2 - для внедрения зависимостей.
Retrofit - для запроса в сеть(можно было использовать что-нибудь проще, но я хочу потом прикрутить полноценный API, поэтому решил использовать его.

Тестировался на Nexus 5 API 30
