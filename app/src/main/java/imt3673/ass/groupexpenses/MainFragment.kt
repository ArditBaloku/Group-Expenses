package imt3673.ass.groupexpenses


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_main, container, false)

        val btnAdd = view.findViewById<Button>(R.id.btn_add_data)
        val btnSettlements = view.findViewById<Button>(R.id.btn_settlement)

        btnAdd.setOnClickListener {
            (activity as MainActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_view, DataEntryFragment())
                .addToBackStack(null)
                .commit()
        }

        btnSettlements.setOnClickListener {
            (activity as MainActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_view, SettlementsFragment())
                .addToBackStack(null)
                .commit()
        }

        return view
    }


}
