package com.example.appprogetto

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appprogetto.databinding.PaginaAccessoBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.Tag


class paginaAccesso : AppCompatActivity() {

    private lateinit var binding:PaginaAccessoBinding
    private lateinit var user: FirebaseAuth

    private lateinit var googleSignInClient: GoogleSignInClient

    companion object{

        private const val RC_SIGN_IN = 100
        private const val TAG = "GOOGLE_SIGN_IN_TAG"

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = PaginaAccessoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = FirebaseAuth.getInstance()

        checkIfUserIsLogged()

        val googleSignIn = findViewById<Button>(R.id.google_connect)

        val GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,GoogleSignInOptions)



        binding.googleConnect.setOnClickListener(){
            Log.d(TAG, "onCreate: begin Google SignIn")
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent, RC_SIGN_IN)

        }



        var indietro = findViewById<Button>(R.id.indietro3)
        indietro.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        binding.Accedi2.setOnClickListener() {
            utenteRegistrato()
        }
    }

    private fun checkIfUserIsLogged(){

        if(user.currentUser != null) {
            startActivity(Intent(this, pagina_home::class.java))
            finish()
        }

    }
    private fun utenteRegistrato(){
        val email = binding.email.text.toString()
        val password = binding.Password.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty()){


             user.signInWithEmailAndPassword(email,password)
                         .addOnCompleteListener{mTask->

                             if(mTask.isSuccessful){
                                 Toast.makeText(this,
                                     "utente collegato",
                                     Toast.LENGTH_SHORT).show()
                                 startActivity(Intent(this, pagina_home::class.java))
                                 finish()

                             } else {
                                 Toast.makeText(this,
                                     "email o password sono errati",
                                     Toast.LENGTH_SHORT).show()
                             }

                         }

        } else{
            Toast.makeText(this,
                "email e password non possono essere vuoti",
                Toast.LENGTH_SHORT).show()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RC_SIGN_IN){
            Log.d(TAG, "onActivityResult: Google SignIn intent result")
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = accountTask.getResult(ApiException::class.java)
                firebaseAuthWithGoogleAccount(account)

            }catch (e : Exception){


                Log.d(TAG, "onActivityResult: ${e.message}")
            }
        }
    }


    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?) {
        Log.d(TAG, "firebaseAuthWithGoogleAccount: begin firebase out with google account")

        val credential = GoogleAuthProvider.getCredential(account!!.idToken, null)
        user.signInWithCredential(credential)
            .addOnSuccessListener { authResult ->
                Log.d(TAG, "firebaseAuthWithGoogleAccount: LoggedIn")

                val firebaseUser = user.currentUser

                val uid = firebaseUser!!.uid
                val email = firebaseUser!!.email

                Log.d(TAG, "firebaseAuthWithGoogleAccount: Uid: $uid")
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Email: $email")

                //controllo se l'utente esiste già o è nuovo
                if(authResult.additionalUserInfo!!.isNewUser){
                    Log.d(TAG, "firebaseAuthWithGoogleAccount: Account creato..  \n$email")
                    Toast.makeText(this, "Account creato..", Toast.LENGTH_SHORT).show()
                }
                else{
                    Log.d(TAG, "firebaseAuthWithGoogleAccount: Account esistente.. \n$email" )
                    Toast.makeText(this, "Account esistente..", Toast.LENGTH_SHORT).show()
                }
                startActivity(
                    Intent(this, pagina_home::class.java)
                )
            }
            .addOnFailureListener{ e ->
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Login fallito da ${e.message}")
                Toast.makeText(this, "Login fallito da ${e.message}", Toast.LENGTH_SHORT).show()
            }

    }
}

