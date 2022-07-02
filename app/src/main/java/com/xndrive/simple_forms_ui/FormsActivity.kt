package com.xndrive.simple_forms_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*


class FormsActivity : AppCompatActivity() {
    var formsActivityLayout: View? = null
    var dropdownView: AutoCompleteTextView? = null
    var datepickerview: TextInputEditText? = null
    var button_kirim: MaterialButton? = null

    var activity_forms_textinputedittext_namalengkap: TextInputEditText? = null

    var activity_forms_textinputedittext_namapanggilan: TextInputEditText? = null

    var activity_forms_textinputlayout_alamat: TextInputLayout? = null
    var activity_forms_textinputedittext_alamat: TextInputEditText? = null

    var activity_forms_textinputlayout_hobby: TextInputLayout? = null
    var activity_forms_textinputedittext_hobby: TextInputEditText? = null

    var activity_forms_textinputlayout_pekerjaan: TextInputLayout? = null
    var activity_forms_textinputedittext_pekerjaan: TextInputEditText? = null

    var activity_forms_result: TextView? = null

    var formdata_namalengkap = ""
    var formdata_namapanggilan = ""
    var formdata_jeniskelamin = ""
    var formdata_tanggallahir = ""
    var formdata_alamat = ""
    var formdata_hobby = ""
    var formdata_pekerjaan = ""

    var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = LayoutInflater.from(this)



        formsActivityLayout = inflater.inflate(R.layout.activity_forms2, null)
        setContentView(formsActivityLayout)
        setTitle("Form Isi Biodata")

        //dropdown view
        dropdownView =
            formsActivityLayout!!.findViewById(R.id.activity_forms_jeniskelamin_dropdown)

        //datepicker view di layout
        datepickerview =
            formsActivityLayout!!.findViewById(R.id.activity_forms_tanggallahir_dropdown)

        button_kirim = formsActivityLayout!!.findViewById(R.id.activity_forms_button_kirim)

        activity_forms_textinputedittext_namalengkap = formsActivityLayout!!.findViewById(R.id.activity_forms_textinputedittext_namalengkap)

        activity_forms_textinputedittext_namapanggilan = formsActivityLayout!!.findViewById(R.id.activity_forms_textinputedittext_namapanggilan)

        activity_forms_textinputlayout_alamat = formsActivityLayout!!.findViewById(R.id.activity_forms_textinputlayout_alamat)
        activity_forms_textinputedittext_alamat = formsActivityLayout!!.findViewById(R.id.activity_forms_textinputedittext_alamat)

        activity_forms_textinputlayout_hobby = formsActivityLayout!!.findViewById(R.id.activity_forms_textinputlayout_hobby)
        activity_forms_textinputedittext_hobby = formsActivityLayout!!.findViewById(R.id.activity_forms_textinputedittext_hobby)

        activity_forms_textinputlayout_pekerjaan = formsActivityLayout!!.findViewById(R.id.activity_forms_textinputlayout_pekerjaan)
        activity_forms_textinputedittext_pekerjaan = formsActivityLayout!!.findViewById(R.id.activity_forms_textinputedittext_pekerjaan)

        activity_forms_result = formsActivityLayout!!.findViewById(R.id.activity_forms_result)


        //dropdown
        val array_jenis_kelamin = resources.getStringArray(R.array.jenis_kelamin)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, array_jenis_kelamin)
        dropdownView!!.setAdapter(arrayAdapter)

        dropdownView!!.setOnItemClickListener { adapterView, view, i, l ->
            val selected = adapterView.getItemAtPosition(i) as String
            formdata_jeniskelamin = selected ?: "Pria"
            //println("xndrive: $selected")
        }

        //datepicker init
        datepickerview!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val materialDateBuilder: MaterialDatePicker.Builder<*> =
                    MaterialDatePicker.Builder.datePicker()

                var calendar : Calendar = Calendar.getInstance();
                var currentYear = calendar.get(Calendar.YEAR);

                materialDateBuilder.setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR) //set input mode
                materialDateBuilder.setTitleText("Pilih tanggal lahir")

//                val constraintsBuilder: CalendarConstraints.Builder =
//                    setupConstraintsBuilder(currentYear) //pass current year

//                materialDateBuilder.setCalendarConstraints(constraintsBuilder.build())

                val materialDatePicker = materialDateBuilder.build()

                materialDatePicker.show(supportFragmentManager, "MATERIAL_CALENDER_DATE_PICKER")

                materialDatePicker.addOnPositiveButtonClickListener {
                    datepickerview!!.setText(materialDatePicker.headerText)
//                    Toast.makeText(application, "${materialDatePicker.headerText}", Toast.LENGTH_SHORT).show()

                }
            }

        })

        //implementasi clear teks
        activity_forms_textinputlayout_alamat!!.setEndIconOnClickListener {
            activity_forms_textinputedittext_alamat!!.setText("")
        }

        activity_forms_textinputlayout_hobby!!.setEndIconOnClickListener {
            activity_forms_textinputedittext_hobby!!.setText("")
        }

        activity_forms_textinputlayout_pekerjaan!!.setEndIconOnClickListener {
            activity_forms_textinputedittext_pekerjaan!!.setText("")
        }

        //implementasi tombol kirim
        button_kirim!!.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                var stringHasil = StringBuilder()
                stringHasil.append("Nama lengkap:  "+ activity_forms_textinputedittext_namalengkap!!.text + "\n")
                    .append("Nama panggilan:  "+ activity_forms_textinputedittext_namapanggilan!!.text + "\n")
                    .append("Jenis kelamin:  "+ formdata_jeniskelamin!! + "\n")
                    .append("Tanggal lahir:  "+ datepickerview!!.text + "\n")
                    .append("Alamat:  "+ activity_forms_textinputedittext_alamat!!.text + "\n")
                    .append("Hobby:  "+ activity_forms_textinputedittext_hobby!!.text + "\n")
                    .append("Pekerjaan:  "+ activity_forms_textinputedittext_pekerjaan!!.text + "\n")
                activity_forms_result!!.setText(stringHasil.toString())
                Toast.makeText(application,"${stringHasil.toString()}", Toast.LENGTH_SHORT).show()
            }

        })
    }

}
