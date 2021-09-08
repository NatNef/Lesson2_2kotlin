package keyone.keytwo.lesson2_2kotlin.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

// это хранилище всех ViewModel

class MainViewModel(private val liveDataToObserve:MutableLiveData<Any> = MutableLiveData())
    :ViewModel() {

//        fun getLiveData():LiveData<Any> {
//            return liveDataToObserve;
//        }
        // сокращаем
    fun getLiveData() = liveDataToObserve;
// на liveDataToObserve будем подписывать


    // добавляем функцию, которая эмулирует запрос на сервер
    fun getDataFromRemoteSource(){
        Thread{
            sleep(2000)
            liveDataToObserve.postValue(Any())
        }.start()
    }

}