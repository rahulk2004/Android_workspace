package com.example.taskslider

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.time.Year
import java.time.YearMonth
import java.util.Calendar

class DateFragment:DialogFragment(),DatePickerDialog.OnDateSetListener
{
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var c = Calendar.getInstance()
        var day = c.get(Calendar.DAY_OF_WEEK)
        var month = c.get(Calendar.MONTH)
        var year = c.get(Calendar.YEAR)

        return DatePickerDialog(requireActivity(),this,year,month,day)
    }

    override fun onDateSet(view:DatePicker?,year: Int ,month:Int ,dayOfMonth: Int) {

        Toast.makeText(requireActivity(), ""+dayOfMonth+"-"+month+"-"+year , Toast.LENGTH_SHORT).show()
    }


}