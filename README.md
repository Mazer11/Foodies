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

![ezgif com-resize](https://github.com/Mazer11/Foodies/assets/86118013/03a09c42-77e5-4bb0-8624-0b00cf3c05e3)

<li>Каталог</li>

![ezgif com-resize (3)](https://github.com/Mazer11/Foodies/assets/86118013/35a1b884-eae4-41f1-b9cd-baa4aed1f2aa)

<li>Корзина</li>

![ezgif com-resize (2)](https://github.com/Mazer11/Foodies/assets/86118013/f7c0ae7a-1331-4e19-8cb3-a0cd21c2f5c8)

<li>Поиск</li>

![ezgif com-resize (1)](https://github.com/Mazer11/Foodies/assets/86118013/9a386114-6334-4cfc-8d95-299facc566fc)

<br/>
Экраны скриншоты:
<li>Каталог</li>

![photo_2_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/1851e0fc-55eb-4001-86c8-627660ba22ed) ![photo_3_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/a56da2b2-3ff9-4f25-9744-5073fb9a4f3a)

![photo_7_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/258e1048-a876-4a6f-b609-43255530efac) ![photo_8_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/a526771b-52b2-4ea6-8626-20e293645ebb)

![photo_12_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/0a7a5e93-2297-47e7-9506-2d6e616da74c)

<li>Экран продукта</li>

![photo_5_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/9c6d15a9-43c7-4c46-bbd6-ca88d68d2c65) ![photo_6_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/a867b00a-9302-4f93-a2a5-9c308f617a82)

<li>Корзина</li>

![photo_1_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/124713a3-c5c0-42af-aa82-cb4c42455cb8)

<li>Поиск</li>

![photo_4_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/530815fc-da36-4920-9e25-2485f0d477b1) ![photo_13_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/a6c6d267-9537-47da-b07c-233f9776f21a)

<li>Фильтры</li>

![photo_9_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/017ce526-956c-40fc-b149-52001e533ffd) ![photo_10_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/f4a34906-f3a4-4bbf-bae3-cbd5762e6a42)

![photo_11_2023-05-19_23-15-01](https://github.com/Mazer11/Foodies/assets/86118013/9572c1ef-04f5-4765-bf42-2698f26f63dc)
