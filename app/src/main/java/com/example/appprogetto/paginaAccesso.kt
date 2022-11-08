package com.example.appprogetto

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.appprogetto.databinding.PaginaAccessoBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.Tag


class paginaAccesso : AppCompatActivity() {

    private lateinit var binding: PaginaAccessoBinding
    private lateinit var user: FirebaseAuth

    private lateinit var googleSignInClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = PaginaAccessoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = FirebaseAuth.getInstance()

        checkIfUserIsLogged()


        val GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, GoogleSignInOptions)


        findViewById<Button>(R.id.google_connect).setOnClickListener() {
            signInGoogle()
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

    private fun checkIfUserIsLogged() {

        if (user.currentUser != null) {
            startActivity(Intent(this, pagina_home::class.java))
            finish()
        }

    }

    private fun utenteRegistrato() {
        val email = binding.email.text.toString()
        val password = binding.Password.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {


            user.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { mTask ->

                    if (mTask.isSuccessful) {
                        Toast.makeText(
                            this,
                            "utente collegato",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(this, pagina_home::class.java))
                        finish()

                    } else {
                        Toast.makeText(
                            this,
                            "email o password sono errati",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }

        } else {
            Toast.makeText(
                this,
                "email e password non possono essere vuoti",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)

    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleResults(task)

            }
        }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account: GoogleSignInAccount? = task.result
            if (account != null) {
                updateUi(account)
            }

        } else {
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }

    }
    private fun updateUi(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        user.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){
                val intent : Intent = Intent(this, pagina_home::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }

    }

}

