package imt3673.ass.groupexpenses

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

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

    override fun onResume() {
        super.onResume()
        populateTable(view)
        populateStatistics(view)
    }

    private fun populateTable(view: View?) {
        val expensesList = (activity as MainActivity).expenses.allExpenses()
        expensesList.forEach {
            addRow(it.person, it.amount, view)
        }
    }

    private fun addRow(person: String, amount: Long, view: View?) {
        val row = TableRow(context)
        row.layoutParams = TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT)
        row.setPadding(10, 10, 10, 10)

        val name = TextView(context)
        name.layoutParams = TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT, .7f)
        name.gravity = Gravity.START
        name.text = person

        val pAmount = TextView(context)
        pAmount.layoutParams = TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT, .3f)
        pAmount.gravity = Gravity.START
        pAmount.text = convertAmountToString(amount)

        row.addView(name)
        row.addView(pAmount)

        val expensesTable = view?.findViewById<TableLayout>(R.id.expenses_table)

        expensesTable?.addView(row)
    }

    private fun populateStatistics(view: View?) {
        val total = (activity as MainActivity).expenses.getTotal()
        val avg = (activity as MainActivity).expenses.getAvg()
        val txtTotal = view?.findViewById<TextView>(R.id.txt_expenses_total)
        val txtAvg = view?.findViewById<TextView>(R.id.txt_expenses_avr)

        txtTotal?.text = total
        txtAvg?.text = avg
    }
}
