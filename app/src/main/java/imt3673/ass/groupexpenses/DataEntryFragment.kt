package imt3673.ass.groupexpenses


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class DataEntryFragment : Fragment() {

    lateinit var errorMsg: TextView
    lateinit var txtName: TextInputEditText
    lateinit var txtAmount: TextInputEditText
    lateinit var txtDescription: TextInputEditText
    lateinit var btnAdd: Button
    lateinit var btnCancel: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_data_entry, container, false)

        errorMsg = view.findViewById(R.id.txt_add_expenses_error)
        txtName = view.findViewById(R.id.edit_person)
        txtAmount = view.findViewById(R.id.edit_amount)
        txtDescription = view.findViewById(R.id.edit_description)
        btnCancel = view.findViewById(R.id.btn_cancel)
        btnAdd = view.findViewById(R.id.btn_add_expense)

        //TODO: Show error message

        btnCancel.setOnClickListener {goBackToMainFragment()}

        btnAdd.setOnClickListener {
            (activity as MainActivity).expenses.add(
                SingleExpense(
                    sanitizeName(txtName.text.toString()),
                    convertStringToAmount(txtAmount.text.toString()).getOrThrow(),
                    txtDescription.text.toString()
                )
            )

            goBackToMainFragment()
        }

        txtName.addTextChangedListener(btnAddTextWatcher)
        txtAmount.addTextChangedListener(btnAddTextWatcher)
        txtDescription.addTextChangedListener(btnAddTextWatcher)

        return view
    }

    private val btnAddTextWatcher: TextWatcher = object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val name = txtName.text.toString()
            val amount = txtAmount.text.toString()
            val description = txtDescription.text.toString()

            if (sanitizeName(name).isNotEmpty() && convertStringToAmount(amount).isSuccess && description.isNotEmpty()) {
                btnAdd.isEnabled = true
                btnAdd.isClickable = true
            } else {
                btnAdd.isEnabled = false
                btnAdd.isClickable = false
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
