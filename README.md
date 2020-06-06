# Appetizer Coding Exam

Hi, this is david.
First of all, I would like to apologize for the incomplete coding exam because of the time constraint and our boss is here. Really a bad timing for taking the exam. 🙇‍♂️🙇‍♂️🙇‍♂️

Let me explain to you a little bit about the architecture that I used and the libraries that I use.
## Libraries (installed and used in the app):
*  Dagger2 - Used in dependency injection
*  RxJava/RxAndroid/RxKotlin - Used to pass and transform data in a reactive way. Using this to do network calls or other heavy lifting processes to prevent doing things in the main thread that would cause the app to freeze
*  Fresco - an Image loading library used to handle network image loading
*  Retrofit - using the version 2+ of the library. Used to do network calls
*  Jackson - JSON parser library to parse json string from the network requests.
## Libraries (installed but not yet used)
* kotlin coroutines - It has the same use as RxJava. Use to replace RxJava to make network calls or heavy lifting process more readable.

## Persistend storage
* Used SQLite with Room Database Implementation..

## UI Design
* I'm not really good with design. I just follow what's on the mock ups that was given by the designers.
 
## Architecture
The design pattern that I used is the Android team's recommendation, the **MVVM** or **Model-View-ViewModel** Design. The reason I used this because the Design pattern is able to cater activity or fragment configuration changes. And, you can isolate tests, especially testing the ViewModel and View. I've used **MVP**, the Design pattern is great, there's a separation of concern so, readability and testability is great. But, the thing is, the **view** is tightly coupled with the **presenter**. And that's a not so good thing especially in android that activities or fragments would react to configuration change (or could change from a small screen to a large/tablet screen. Remember Galaxy Fold).

