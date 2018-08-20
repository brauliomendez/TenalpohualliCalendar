package view

import model.Calendar
import viewmodel.CalendarViewModel

object TenalpohualliCalendarView {

    @JvmStatic
    fun main(args: Array<String>) {
        println("Introduce la fecha que desees conocer en el Calendario Tonalpohualli: (Formato dd/mmm/aaaa)")

        validateDateByUser()

        println(CalendarViewModel(Calendar(Integer.parseInt(getDateByUser()[0]), assignMonth(), Integer.parseInt(getDateByUser()[2]))).setCalendarSign())
    }

    private fun getDateByUser(): List<String> = readLine()?.split("/") ?: emptyList()

    private fun validateDateByUser() {
        if (getDateByUser().size < 3) {
            println("Introduce el formato dd/mmm/aaaa")
        }
    }

    private fun assignMonth(): Int {
        return when (getDateByUser()[1].toLowerCase()) {
            "ene" -> 1
            "feb" -> 2
            "mar" -> 3
            "abr" -> 4
            "may" -> 5
            "jun" -> 6
            "jul" -> 7
            "ago" -> 8
            "sep" -> 9
            "oct" -> 10
            "nov" -> 11
            "dic" -> 12
            else -> 0
        }
    }

}