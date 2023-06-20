import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment
import com.example.myapplication.R
import java.util.*

class DatePickerFragment : DialogFragment() {
    private lateinit var listener: DatePickerDialog.OnDateSetListener

    companion object {
        var day = 0
        var month = 0
        var year = 0

        fun newInstance(listener: DatePickerDialog.OnDateSetListener): DatePickerFragment {
            val fragment = DatePickerFragment()
            fragment.setListener(listener)
            return fragment
        }
    }

    private fun setListener(listener: DatePickerDialog.OnDateSetListener) {
        this.listener = listener
    }

    @NonNull
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //Mostramos al fecha actual en el datePicker
        val c = Calendar.getInstance()
        year = c.get(Calendar.YEAR)
        month = c.get(Calendar.MONTH)
        day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireActivity(), R.style.DatePickerStyle, listener, year, month, day)

    }
}