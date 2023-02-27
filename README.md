## Проект
### Закрепление полученных навыков на практике
#### Технологии и инструменты:
<p align="center">
<a href="https://www.jetbrains.com/idea/"><img src="/design/Intelij_IDEA.png" width="50" height="50"  alt="IDEA"/></a>
<a href="https://www.java.com/"><img src="/design/Java.png" width="50" height="50"  alt="Java"/></a>
<a href="https://github.com/"><img src="/design/GitHub-Mark.png" width="50" height="50"  alt="Github"/></a>
<a href="https://junit.org/junit5/"><img src="/design/JUnit5.png" width="50" height="50"  alt="JUnit 5"/></a>
<a href="https://gradle.org/"><img src="/design/Gradle.png" width="50" height="50"  alt="Gradle"/></a>
<a href="https://selenide.org/"><img src="/design/Selenide.png" width="50" height="50"  alt="Selenide"/></a>
<a href="https://aerokube.com/selenoid/"><img src="/design/Selenoid.png" width="50" height="50"  alt="Selenoid"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="/design/Allure_Report.png" width="50" height="50"  alt="Allure"/></a>
<a href="https://www.jenkins.io/"><img src="/design/Jenkins.png" width="50" height="50"  alt="Jenkins"/></a>
<a href="https://telegram.org/"><img src="/design/Telegram.png" width="50" height="50"  alt="Telegram"/></a>
</p>


#### Используемые зависимости:
```
testImplementation(
            "org.slf4j:slf4j-simple:$slf4jVersion"
            ,"io.rest-assured:rest-assured:5.3.0"
            ,"io.rest-assured:rest-assured-common:5.0.1"
            ,"io.rest-assured:json-path:5.0.1"
            ,"io.rest-assured:xml-path:5.0.1"
            ,"org.junit.jupiter:junit-jupiter:$junitVersion"
            ,"org.assertj:assertj-core:3.11.1"
            ,"org.hamcrest:hamcrest-all:1.3"
            ,"com.codeborne:selenide:$selenideVersion"
            ,"org.selenide:selenide-selenoid:2.3.4"
            ,"io.qameta.allure:allure-selenide:$allureVersion"
            ,"io.qameta.allure:allure-rest-assured:$allureVersion"
            ,"io.qameta.allure:allure-junit5:$allureVersion"
            ,"com.google.code.gson:gson:2.2.4"
            ,"org.aeonbits.owner:owner:1.0.12"
            ,"com.github.javafaker:javafaker:1.0.2"
            ,"com.google.code.gson:gson:2.2.4"
    )
```
## Запуск тестов
#### Локальный запуск тестов:
```
gradle clean regression_test -Dselenide.location=local
```
#### Удаленный запуск:
```
gradle clean regression_test -Dselenide.location=remote
```
#### Удаленный запуск в многопоточном режиме (3 потока):
```
gradle clean regression_test -Dselenide.location=remote -Dthreads=3
```
#### Параметры сборки подключаемые с помощью библиотеки [OWNER](https://github.com/matteobaccan/owner):
<code>selenoid.url</code> – адрес удаленного сервера, на котором будут запускаться тесты. </br>
<code>browser.name</code> – наименование браузера для запуска автотестов. </br>
<code>browser.version</code> – версия браузера для запуска автотестов. </br>
<code>browser.size</code> – настройка разрешения окна браузера для запуска тестов. </br>
<code>browser.timeout</code> – время ожидания появления элементов во время выполнения UI тестев. </br>
<code>browser.headless</code> – настройка, позволяющая отключить отображение браузера, при выполнении тестов. </br>
<code>browser.hold.open</code> – насторойка, позволяющая оставить окно браузера открытым после окончания выполнения тестов, используется для отладки тестов. </br>
## Подключение Allure
#### build.gradle:
```
plugins {
    id 'io.qameta.allure' version '2.11.2'
}
allure {
    version.set(allureVersion)
    adapter { //отвечает за прявление папочки build/allure-results
        aspectjWeaver.set(true) //обработка аннотации @Step
        frameworks {
            junit5 { //название фреймворка
                adapterVersion.set(allureVersion) //варсия интеграции фреймворка и Allure
            }
        }
    }
}
```
#### jenkins:
- В разделе "Послесборочные операции" указать Path: build/allure-results
![]()
![]()

## Подключение нотификаций о результатах тестов в телеграм
#### В телеграм:
- создать бота (сохранить токен)
- добавить бота в нужный чат
- сделать бота админом
- получить chat_id при помощи: https://api.telegram.org/bot{secret_bot}/getUpdates

#### В структуру проекта добавить:
[notifications/allure-notifications-4.2.1.jar](https://github.com/glazmaikh/hh/blob/master/notifications/allure-notifications-4.2.1.jar)
#### jenkins:
- В разделе "Сборка" добавить шаг сборки "Create/Update Text File"
- Указать File Path: notifications/telegram.json
- Проставить галки для Create at Workspace и Overwrite file
- Добавить telegram.json:
```
{
  "base": {
    "project": "${JOB_BASE_NAME}",
    "environment": "{your_environment}",
    "comment": "{your_telegram_name}",
    "reportLink": "${BUILD_URL}",
    "language": "en",
    "allureFolder": "allure-report/",
    "enableChart": true
  },
  "telegram": {
    "token": "{secret_bot}",
    "chat": "{chat_id}",
    "replyTo": ""
  }
}
```
