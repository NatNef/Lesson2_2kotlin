package keyone.keytwo.lesson2_2kotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class MainFragment:Fragment() {

    // резервация
    companion object{
//        fun newInstance():Fragment{
//            return MainFragment()
//       }
        // или сократим если возвращаем
        fun newInstance()=MainFragment()
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    }
