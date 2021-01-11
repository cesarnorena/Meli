# Meli test project
![checks](https://github.com/cesarnorena/meli/workflows/Checks/badge.svg?branch=main)

Android app developed using the [public API](https://api.mercadolibre.com/) of Mercado Libre (Meli) to search products and get some extra details.


## Architecture

MVVM (Model View ViewModel) in the presentation layer with LiveData and Coroutines + Clean Architecture.

Big flows or features are meant to belong to an Android module, but since this is a small flow, I didn't create a independient module for it. Multi-module apps also require common libraries or modules, an example of it would be the `StatefulViewModel` and `StafeulActivity` classes.

![Architecture diagram](./.images/meli_architecture.png)


## Tests

Running unit tests:
```
./gradlew test
``` 

Running instrumented tests:
```
./gradlew connectedAndroidTest
```
 

## Linter
Kotlin code style uses the default rules of ktlint project.

Running ktlint check:
```
./gradlew ktlintCheck
```

Running ktlint format
```
./gradlew ktlintFormat
```

## Disclaimer
Some considerations:
- I didn't test everything because with the two tests I created, you can understand the types of tests I would do and which other components would have coverage.
- I would work with a multi-module project, but for the test it wouldn't make much sense to have a single feaature module project.
- I didn't invest time in the visual part of the second screen, but with the first one you can understand the general construction of screens. I can use Anko to create screens with code, but today it's deprecated and Jetpack Compose is not production ready yet.
- I left some TODOs on what I would do with a little more time.
