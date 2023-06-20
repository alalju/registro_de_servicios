import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

object ManejoFechas {
    @RequiresApi(Build.VERSION_CODES.O)
    private fun fechaActual(): String {
        val fecha = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        return fecha.format(formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Throws(Exception::class)
    fun obtenerFechas(uno: String, dos: String): Boolean {
        return compararFechas(fechaDate(uno), fechaDate(dos))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Throws(Exception::class)
    fun obtenerRestarFechas(uno: String, dos: String): Long {
        return restarFechas(fechaDate(uno), fechaDate(dos))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Throws(Exception::class)
    private fun fechaDate(string: String): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        return LocalDate.parse(string, formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun compararFechas(date1: LocalDate, date2: LocalDate): Boolean {
        return date1.isBefore(date2)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun restarFechas(date1: LocalDate, date2: LocalDate): Long {
        val diff = date2.toEpochDay() - date1.toEpochDay()
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
    }
}