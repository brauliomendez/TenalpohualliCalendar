package viewmodel

import model.Calendar

class CalendarViewModel(private val calendar: Calendar) {

    private fun verifyDate(): Boolean {
        return if (calendar.year > 1583) {
            true
        } else {
            calendar.day > 5 && calendar.month >= 10 && calendar.year == 1583
        }
    }

    private fun getJulianDate(): Int {
        val julianDay: Int = (14 - calendar.month) / 12
        val julianMonth: Int = calendar.month + (12 * julianDay - 3)
        val julianYear: Int = calendar.year + 4800 - julianDay

        return if (verifyDate()) {
            calendar.day + (153 * julianMonth + 2) / 5 + 365 * julianYear + julianYear / 4 - julianYear / 100 + julianYear / 400 - 32045
        } else {
            calendar.day + (153 * julianMonth + 2) / 5 + 365 * julianYear + julianYear / 4 - 32083
        }
    }

    private fun getMayaDay(): Int = (Math.floorMod(getJulianDate() - 2276184, 13) + 13) % 13 + 1

    private fun getMayaYear(): Int {
        val mayaYear: Int = getJulianDate() - 2276184
        val aux: Int = Math.round((mayaYear / 365).toFloat())
        return if ((Math.floorMod(aux, 13) + 13) % 13 + 3 > 13) {
            (Math.floorMod(aux, 13) + 13) % 13 + 3 - 13
        } else {
            (Math.floorMod(aux, 13) + 13) % 13 + 3
        }
    }

    private fun mayaSign(): Int {
        val mayaDaySign: Int = getJulianDate() - 2276184
        return if ((Math.floorMod(mayaDaySign, 20) + 20) % 20 < 0) {
            (Math.floorMod(mayaDaySign, 20) + 20) % 20 + 20
        } else {
            (Math.floorMod(mayaDaySign, 20) + 20) % 20
        }
    }

    private fun setMayaSign(): Int {
        val mayaYearSign: Double = (getJulianDate() - 2276184).toDouble()
        return (Math.floorMod(Math.round(mayaYearSign / 365).toInt(), 4) + 4) % 4
    }

    fun setCalendarSign(): String {
        var spanishDay = ""
        var nahuatlDay = ""
        var spanishYear = ""
        var nahuatlYear = ""
        when (mayaSign()) {
            0 -> {
                spanishDay = "Lagarto"
                nahuatlDay = "Cipactli"
            }
            1 -> {
                spanishDay = "Viento"
                nahuatlDay = "Ehecatl"
            }
            2 -> {
                spanishDay = "Casa"
                nahuatlDay = "Calli"
            }
            3 -> {
                spanishDay = "Lagartija"
                nahuatlDay = "Cuetzpalin"
            }
            4 -> {
                spanishDay = "Serpiente"
                nahuatlDay = "Cóatl"
            }
            5 -> {
                spanishDay = "Muerte"
                nahuatlDay = "Miquztli"
            }
            6 -> {
                spanishDay = "Venado"
                nahuatlDay = "Mazatl"
            }
            7 -> {
                spanishDay = "Conejo"
                nahuatlDay = "Tochtli "
            }
            8 -> {
                spanishDay = "Agua"
                nahuatlDay = "Atl"
            }
            9 -> {
                spanishDay = "Perro"
                nahuatlDay = "Itzcuintli"
            }
            10 -> {
                spanishDay = "Mono"
                nahuatlDay = "Ozomatli"
            }
            11 -> {
                spanishDay = "Hierba"
                nahuatlDay = "Malinalli"
            }
            12 -> {
                spanishDay = "Caña"
                nahuatlDay = "Ácatl"
            }
            13 -> {
                spanishDay = "Jaguar"
                nahuatlDay = "Ocelotl"
            }
            14 -> {
                spanishDay = "Águila"
                nahuatlDay = "Cuauhtli"
            }
            15 -> {
                spanishDay = "Buitre"
                nahuatlDay = "Cozcaquauhtli"
            }
            16 -> {
                spanishDay = "Movimiento"
                nahuatlDay = "Ollin"
            }
            17 -> {
                spanishDay = "Pedernal"
                nahuatlDay = "Técpatl"
            }
            18 -> {
                spanishDay = "Lluvia"
                nahuatlDay = "Quiahuitl"
            }
            19 -> {
                spanishDay = "Flor"
                nahuatlDay = "Xochitl"
            }
        }
        when (setMayaSign()) {
            0 -> {
                spanishYear = "Casa"
                nahuatlYear = "Calli"
            }
            1 -> {
                spanishYear = "Conejo"
                nahuatlYear = "Tochtli"
            }
            2 -> {
                spanishYear = "Caña"
                nahuatlYear = "Ácatl"
            }
            3 -> {
                spanishYear = "Pedernal"
                nahuatlYear = "Técpatl"
            }
        }
        return "Día ${getMayaDay()} $spanishDay del año ${getMayaDay()} $spanishYear o ${getMayaDay()} $nahuatlDay ${getMayaYear()} $nahuatlYear"
    }

}