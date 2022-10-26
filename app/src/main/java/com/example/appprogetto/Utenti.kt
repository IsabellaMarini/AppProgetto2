package com.example.appprogetto

import android.widget.EditText

data class Utenti(
    val nome: String?=null, val cognome: String?=null, val username: String?=null,
    val email: String?=null, val password: String?=null, val data_nascita: String?=null,
    val calcio: Boolean?=null, val tennis: Boolean?=null, val basket: Boolean?=null,
    val nuoto: Boolean?=null, val pallavolo: Boolean?=null, val formula1: Boolean?=null)

