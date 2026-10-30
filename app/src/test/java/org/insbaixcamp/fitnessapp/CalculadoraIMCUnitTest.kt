package org.insbaixcamp.fitnessapp

import org.insbaixcamp.fitnessapp.utils.CalculadoraIMC
import org.junit.Assert.assertEquals
import org.junit.Test

class CalculadoraIMCUnitTest {

    @Test
    fun testCalcularIMC_ValorsStandard() {
        val pes = 70.0
        val alçada = 170
        val imc = CalculadoraIMC.calcularIMC(pes, alçada)
        assertEquals(24.22, imc, 0.01)
    }

    @Test
    fun testCalcularIMC_AlçadaBaixa() {
        val pes = 70.0
        val alçada = 100
        val imc = CalculadoraIMC.calcularIMC(pes, alçada)
        assertEquals(70.0, imc, 0.01)
    }

    @Test
    fun testCalcularIMC_AlçadaAlta() {
        val pes = 70.0
        val alçada = 250
        val imc = CalculadoraIMC.calcularIMC(pes, alçada)
        assertEquals(11.2, imc, 0.01)
    }

    @Test
    fun testCalcularIMC_PesVariat() {
        // Pes de 60 kg i alçada de 170 cm -> 20.76
        val imc1 = CalculadoraIMC.calcularIMC(60.0, 170)
        assertEquals(20.76, imc1, 0.01)

        // Pes de 85 kg i alçada de 180 cm -> 26.23
        val imc2 = CalculadoraIMC.calcularIMC(85.0, 180)
        assertEquals(26.23, imc2, 0.01)
    }

    @Test
    fun testCalcularIMC_PesZero() {
        val imc = CalculadoraIMC.calcularIMC(0.0, 170)
        assertEquals(0.0, imc, 0.0)
    }

    @Test
    fun testCalcularIMC_AlçadaZeroOInvalida() {
        // Alçada 0 o negativa no ha de provocar divisió per zero (divisió per 0 torna 0.0)
        val imcZero = CalculadoraIMC.calcularIMC(70.0, 0)
        assertEquals(0.0, imcZero, 0.0)

        val imcNegatiu = CalculadoraIMC.calcularIMC(70.0, -170)
        assertEquals(0.0, imcNegatiu, 0.0)
    }

    @Test
    fun testCalcularIMC_PesNegatiu() {
        val imc = CalculadoraIMC.calcularIMC(-70.0, 170)
        assertEquals(0.0, imc, 0.0)
    }
}
