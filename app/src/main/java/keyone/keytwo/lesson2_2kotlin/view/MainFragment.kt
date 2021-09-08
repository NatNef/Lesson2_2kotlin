package keyone.keytwo.lesson2_2kotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import keyone.keytwo.lesson2_2kotlin.R
import keyone.keytwo.lesson2_2kotlin.viewmodel.MainViewModel

class MainFragment:Fragment() {

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

        //ViewModelProvider жанглирует моделями и переживают смерть MainFragment
        //сохраняет состояние View
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //обработчикБ передаем viewLifecycleOwner
        viewModel.getLiveData().observe(viewLifecycleOwner,Observer<Any>{
            Toast.makeText(context,"its work", Toast.LENGTH_LONG).show()
        })

        //вызовем якобы запрос на сервер
        viewModel.getDataFromRemoteSource()

        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    //-----------------------------
    }
