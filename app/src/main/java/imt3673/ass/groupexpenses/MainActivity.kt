package imt3673.ass.groupexpenses

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // The storage for all expenses
    val expenses: Expenses = Expenses()
    var settlement = listOf<Transaction>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
    }

    // implements the settlement calculation and keeps it in this.settlement
    fun updateSettlement() {
        this.settlement = calculateSettlement(this.expenses)
    }

    private fun setupUI() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.main_view, MainFragment())
                .commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("expenses", expenses)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val savedExpenses = savedInstanceState.getSerializable("expenses") as Expenses
        savedExpenses.allExpenses().forEach { expenses.add(SingleExpense(it.person, it.amount, it.description)) }
    }

}
