package keyone.keytwo.lesson2_2kotlin.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import keyone.keytwo.lesson2_2kotlin.domain.Weather
import keyone.keytwo.lesson2_2kotlin.repository.Repository
import keyone.keytwo.lesson2_2kotlin.repository.RepositoryImplement
import java.lang.IllegalStateException
import java.lang.Thread.sleep
import kotlin.random.Random

// это хранилище всех ViewModel

class MainViewModel(private val liveDataToObserve:MutableLiveData<AppState> = MutableLiveData(),
                    val repositoryImplement: RepositoryImplement = RepositoryImplement()
)
    :ViewModel() {

    // создали liveDataToObserve и будем в нее что-то добавлять

//        fun getLiveData():LiveData<Any> {
//            return liveDataToObserve;
//        }
        // сокращаем
    fun getLiveData() = liveDataToObserve;
// на liveDataToObserve будем подписывать


    // добавляем функцию, которая эмулирует запрос на сервер
    // потом /заглушки/ на состояния загрузка, удача, ошибка
    fun getDataFromRemoteSource(){
        liveDataToObserve.postValue(AppState.Loading)
        Thread{
            sleep(2000)
            val r = Random(10).nextInt()

            if (r > 5){
                liveDataToObserve.postValue(AppState.Success(repositoryImplement.getWeatherFromLocalSource()))
            }

            else {
                liveDataToObserve.postValue(AppState.Error(IllegalStateException()))
            }

        }.start()
    }

}