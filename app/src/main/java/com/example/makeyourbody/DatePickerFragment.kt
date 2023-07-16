package com.example.makeyourbody

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import java.util.Calendar

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener  {

    private val _requestKey: String
    get() = requireArguments().getString("request_key", "default")

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireContext(),R.style.AppTheme, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        val date = String.format("%d/%02d/%02d", year, month + 1, dayOfMonth)

        //別フラグメントが値を取得できるようにセットする。
        val bundle = bundleOf("date_picker_value" to date)
        setFragmentResult("request_key", bundle)

    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        Toast.makeText(context, "Cancelボタン押下", Toast.LENGTH_LONG).show()
    }

}