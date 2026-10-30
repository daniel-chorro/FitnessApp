package org.insbaixcamp.fitnessapp.data

import android.content.Context

class Profile {
    private var nom: String = ""
    var email: String = ""
    var dataNaixement: Long = 0
    var genere: String = ""
    var pes: Double = 0.0
    var pesObjectiu: Double = 0.0
    var alçada: Int = 0
    var objectiu: String = ""

    fun getNom(context: Context): String {
        nom = SharedPreferencesManager.getInstance(context).getString("nom", "")
        return nom
    }

    fun setNom(context: Context, nomNou: String) {
        nom = nomNou
        SharedPreferencesManager.getInstance(context).putString("nom", nomNou)
    }
}
