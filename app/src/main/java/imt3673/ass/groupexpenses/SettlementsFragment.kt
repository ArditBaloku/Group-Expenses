package imt3673.ass.groupexpenses


import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 */
class SettlementsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settlements, container, false)
    }

    override fun onResume() {
        super.onResume()
        populateTable(view)
    }

    private fun populateTable(view: View?) {
        val transactions = calculateSettlement((activity as MainActivity).expenses)

        transactions.forEach {
            addRow(it.payer, it.payee, it.amount, view)
        }
    }

    private fun addRow(payer: String, payee: String, amount: Long, view: View?) {
        val row = TableRow(context)
        row.layoutParams = TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT)
        row.setPadding(10, 10, 10, 10)

        val newPayer = TextView(context)
        newPayer.layoutParams = TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT)
        newPayer.gravity = Gravity.CENTER
        newPayer.text = payer

        val newPayee = TextView(context)
        newPayee.layoutParams = TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT)
        newPayee.gravity = Gravity.CENTER
        newPayee.text = payee

        val newAmount = TextView(context)
        newAmount.layoutParams = TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT)
        newAmount.gravity = Gravity.CENTER
        newAmount.text = convertAmountToString(amount)

        row.addView(newPayer)
        row.addView(newPayee)
        row.addView(newAmount)

        val transactionsTable = view?.findViewById<TableLayout>(R.id.tbl_settlements)

        transactionsTable?.addView(row)
    }


}
