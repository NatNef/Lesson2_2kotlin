package keyone.keytwo.lesson2_2kotlin.viewmodel

sealed class AppState {

    // sealed class можем менять, разные конструкторы, от них можно наследоваться
    //создавать разные экземпляры, объекты,
    //для записи много разных объектов, разные сигнатуры,классы

    //____________________________
    // состояния нашего приложения
    // загpузка
//    class Loading:AppState() //FIXME object
//    // Fo запрещает создавать много лоэдинг
    object Loading:AppState() // состояние, оно есть и оно одно

// загркзка
    data class Success(val weatherData:Any):AppState()

    // ошибка
    data class Error(val error:Throwable):AppState()

}
