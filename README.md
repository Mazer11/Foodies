# Foodies
Начато: 14.05.2023 19:47 - Завершено: 19.05 23: 09

Использованные технологии:
 - Kotlin
 - Jetpack Compose
 - Dagger Hilt
 - Lottie
 - Retrofit
 - OkHttp
 - Moshi
 - JUnit

Все функциональные требования технического задания выполнены, кроме обработки лишних пробелов в поисковом запросе.<br/>
Для ViewModel написаны базовые тесты, а для api - тесты с использованием моков и Hilt.<br/>
BottomSheet реализован с помощью BottomSheetScaffold. Для достижения эффекта затенения фона при появлении BottomSheet был создан полупрозрачный Box, занимающий большую часть экрана. Это необходимо, так как в реализации от Material 3 такой эффект отсутствует. <br/>
Состояния экранов Каталога, Корзины, Поиска и Продукта хранятся в одной ViewModel.
<br/>Фильтрация по категориям сохраняется при переключении. При прокрутке каталога у топлайна появляется тень.<br/>
Написан интерфейс API для Retrofit, а для отдельных юз кейсов написаны соответствующие классы. В них, за неимением реального сервера, подключён конвертр JSON файлов (см. папку utils).<br/>
Работу ретрофита проверял андроид тестами (класс ApiTests).
<br/>На всякий случай, в апи написал post запрос на передачу заказа, но так как не знаю, каким в данном случае он должен быть, потому сделал простую заглушку (data class PostModel).<br/>
Retrofit, OkHttp, Api, api repository и use cases внедряю при помощи Dagger Hilt (папка di объект AppModule).

Экраны гифки:
<li>Сплеш скрин</li>

![ezgif com-resize](https://github.com/Mazer11/Foodies/assets/86118013/77e9bcc0-6424-4de2-9901-cdccfc8e3ee8)

<li>Каталог</li>

![ezgif com-resize (3)](https://github.com/Mazer11/Foodies/assets/86118013/025b6309-63ed-41b3-b84f-ae8c6304b302)

<li>Корзина</li>

![ezgif com-resize (2)](https://github.com/Mazer11/Foodies/assets/86118013/a16b6b94-0c77-40b7-87b0-a983a7dce9e6)

<li>Поиск</li>

![ezgif com-resize (1)](https://github.com/Mazer11/Foodies/assets/86118013/03f43830-23a0-472d-9283-02f151f3d7fa)

<br/>
Экраны скриншоты:
<li>Каталог</li>

![photo_2_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/047de1d5-d539-44d3-a350-ac713eb896e7) ![photo_3_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/567decdd-e74f-4911-a233-15b6922485b4)

![photo_7_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/34f8556c-152a-45a6-bca0-6c42150ff3c9) ![photo_8_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/ff72419d-e1cc-4e6a-aa61-44e6187794fb)

![photo_12_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/d9791c1d-2e06-46eb-9207-9ae50a760743)

<li>Экран продукта</li>

![photo_6_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/cb26be9a-800d-4294-84eb-c09bfe73679d) ![photo_5_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/198dd0eb-b00f-4c7d-84cd-608162831100)

<li>Корзина</li>

![photo_1_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/b6d88b30-8853-420a-a904-268f8fc63e20)

<li>Поиск</li>

![photo_4_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/fb09e6bb-6063-47a1-8641-d0407116a09e) ![photo_13_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/cb28d73a-632c-4c8a-aefc-084ad86109f9)

<li>Фильтры</li>

![photo_9_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/8f33a394-1cd1-43a5-8cd0-3271fb79b733) ![photo_10_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/4b4b6f8f-fca2-4102-bfa7-88eb82d585d5)

![photo_11_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/3a6487a5-b6c7-484d-bff1-722c70cc1ee8)

