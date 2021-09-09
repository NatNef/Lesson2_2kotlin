package keyone.keytwo.lesson2_2kotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import keyone.keytwo.lesson2_2kotlin.R
import keyone.keytwo.lesson2_2kotlin.databinding.FragmentMainBinding
import keyone.keytwo.lesson2_2kotlin.domain.Weather
import keyone.keytwo.lesson2_2kotlin.viewmodel.AppState
import keyone.keytwo.lesson2_2kotlin.viewmodel.MainViewModel

class MainFragment:Fragment() {

    // представление файла fragment_main.xml в виде кода
 //   private lateinit var binding: FragmentMainBinding // FIXME утечка памяти

    private var _binding: FragmentMainBinding? = null
    private val binding:FragmentMainBinding
    get(){
        return _binding!!
    }

    //---------------------------------------------
    // реализуем viewmodel, ссылку
    //lateinit реализовать ссылку чуть позже

private lateinit var viewModel:MainViewModel



//---------------------------------------------
    // резервация, прописываем фрагмент
    companion object{
//        fun newInstance():Fragment{
//            return MainFragment()
//       }
        // или сократим запись если возвращаем
        fun newInstance()=MainFragment()
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //1 инфлейтим макет
       // return inflater.inflate(R.layout.fragment_main, container, false)
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        //binding.TextView.text = "kakoito tekst"
        return binding.root
    }

    // 2 все остальное
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //ViewModelProvider жанглирует моделями и переживают смерть MainFragment
        //сохраняет состояние View
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //обработчикБ передаем viewLifecycleOwner
        // передаем наши состояния приложения ( до этого была заглушка Observer<Any>
        viewModel.getLiveData().observe(viewLifecycleOwner,Observer<AppState>{
           // Toast.makeText(context,"its work", Toast.LENGTH_LONG).show()
            renderData(it)
        })

        //вызовем якобы запрос на сервер
        viewModel.getDataFromRemoteSource()
        //-----------------------------

    }

    // функции делаем видимыми
    fun renderData(appState: AppState){
        when(appState){
            is AppState.Error -> {
                val throwable = appState.error
            }

            // делаем видимым прогресс бар призагрузке
            // добавляем сообщение, когда нет значенний
            // прописываем значения
            AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
               // binding.message.text = ""

            }
            // при удаче делаем прогресс бар Не видимым
            // добавляем сообщение, когда нет значенний
            // прописываем значения
            // добавляем Snackbar окошко снизу для ответа
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                val weather = appState.weatherData
               // binding.message.text = "Готово"
                setData(weather)
                Snackbar.make(binding.mainView, "Success", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    // прописываем выводим значенния
    private fun setData(weather: Weather) {
        binding.cityName.text = weather.city.name
        binding.cityCoordinates.text = "lat ${weather.city.lat}\n lon ${weather.city.lon}"
        binding.temperatureValue.text = weather.temperature.toString()
        binding.feelsLikeLabel.text =  weather.feelsLike.toString()
       // binding.feelsLikeLabel.text = "${weather.feelsLike}"
    }

    //_________________________________

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }


    }




