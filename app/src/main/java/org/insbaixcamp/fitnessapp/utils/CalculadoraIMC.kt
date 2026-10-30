package org.insbaixcamp.fitnessapp.utils

import kotlin.math.round

class CalculadoraIMC {
    companion object {
        /**
         * Calcula l'índex de massa corporal (IMC) a partir del pes i la alçada.
         * @param pes El pes en kilogrames.
         * @param alçada La alçada en centímetres.
         * @return El valor del IMC en format decimal.
         */
        fun calcularIMC(pes: Double, alçada: Int): Double {
            if (alçada <= 0 || pes < 0) return 0.0
            val alturaMetros = alçada.toDouble() / 100
            val imc = pes / (alturaMetros * alturaMetros)
            return round(imc * 100) / 100
        }

    }
}