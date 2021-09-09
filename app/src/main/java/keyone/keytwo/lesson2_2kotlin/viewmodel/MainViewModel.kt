package keyone.keytwo.lesson2_2kotlin.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import keyone.keytwo.lesson2_2kotlin.domain.Weather
import java.lang.Thread.sleep

// это хранилище всех ViewModel

class MainViewModel(private val liveDataToObserve:MutableLiveData<AppState> = MutableLiveData())
    :ViewModel() {

//        fun getLiveData():LiveData<Any> {
//            return liveDataToObserve;
//        }
        // сокращаем
    fun getLiveData() = liveDataToObserve;
// на liveDataToObserve будем подписывать


    // добавляем функцию, которая эмулирует запрос на сервер
    // потом заглушки на состояния загрузка, удача, ошибка
    fun getDataFromRemoteSource(){
        liveDataToObserve.postValue(AppState.Loading)
        Thread{
            sleep(2000)
            liveDataToObserve.postValue(AppState.Success(Weather()))
        }.start()
    }

}