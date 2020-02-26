package imt3673.ass.groupexpenses


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_data_entry.*

class DataEntryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_data_entry, container, false)
        //TODO: Check bug with "," in amount field
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        edit_person.addTextChangedListener(btnAddTextWatcher)
        edit_amount.addTextChangedListener(btnAddTextWatcher)
        edit_description.addTextChangedListener(btnAddTextWatcher)

        btn_add_expense.setOnClickListener {
            (activity as MainActivity).expenses.add(
                SingleExpense(
                    sanitizeName(edit_person.text.toString()),
                    convertStringToAmount(edit_amount.text.toString()).getOrThrow(),
                    edit_description.text.toString()
                )
            )

            goBackToMainFragment()
        }

        btn_cancel.setOnClickListener {goBackToMainFragment()}

        txt_add_expenses_error.text = savedInstanceState?.getString("errorMsg", "")
        edit_person.setText(savedInstanceState?.getString("txtName", ""))
        edit_amount.setText(savedInstanceState?.getString("txtAmount", ""))
        edit_description.setText(savedInstanceState?.getString("txtDescription", ""))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("errorMsg", txt_add_expenses_error?.toString())
        outState.putString("txtName", edit_person?.toString())
        outState.putString("txtAmount", edit_amount?.toString())
        outState.putString("txtDescription", edit_description?.toString())
    }

    private val btnAddTextWatcher: TextWatcher = object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val name = edit_person.text.toString()
            val amount = edit_amount.text.toString()
            val description = edit_description.text.toString()

            if (!name.contains("[^\\s-a-zA-Z]".toRegex()) && convertStringToAmount(amount).isSuccess && description.isNotEmpty()) {
                btn_add_expense.isEnabled = true
                btn_add_expense.isClickable = true
            } else {
                btn_add_expense.isEnabled = false
                btn_add_expense.isClickable = false
                txt_add_expenses_error.text = "There is an error with your inputs!"
            }
        }
    }

    private fun goBackToMainFragment() {
        (activity as MainActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_view, MainFragment())
            .commit()
    }

}
