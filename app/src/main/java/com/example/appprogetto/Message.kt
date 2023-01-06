package com.example.appprogetto

class Message {
    var messaggio: String? = null
    var mittente: String? = null
    var destinatario:String?=null

    constructor(){}

    constructor(messaggio: String?, mittente: String?, destinatario: String?){
        this.messaggio = messaggio
        this.mittente = mittente
        this.destinatario = destinatario
    }
}