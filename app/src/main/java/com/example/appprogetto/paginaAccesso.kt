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


    lateinit var mGoogleSignInClient: GoogleSignInClient
    val Req_Code:Int=123


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = PaginaAccessoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configure Google Sign In inside onCreate mentod
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        // getting the value of gso inside the GoogleSigninClient
        mGoogleSignInClient=GoogleSignIn.getClient(this,gso)
        // initialize the firebaseAuth variable
        user = FirebaseAuth.getInstance()


        checkIfUserIsLogged()

        findViewById<Button>(R.id.google_connect).setOnClickListener(){
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


    // signInGoogle() function
    private  fun signInGoogle(){

        val signInIntent:Intent=mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent,Req_Code)
    }
    // onActivityResult() function : this is where we provide the task and data for the Google Account
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==Req_Code){
            val task:Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }

    // handleResult() function -  this is where we update the UI after Google signin takes place
    private fun handleResult(completedTask: Task<GoogleSignInAccount>){
        try {
            val account: GoogleSignInAccount? =completedTask.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)
            }
        } catch (e:ApiException){
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show()
        }
    }

    // UpdateUI() function - this is where we specify what UI updation are needed after google signin has taken place.
    private fun UpdateUI(account: GoogleSignInAccount){
        val credential= GoogleAuthProvider.getCredential(account.idToken,null)
        user.signInWithCredential(credential).addOnCompleteListener {task->
            if(task.isSuccessful) {
                SavedPreference.setEmail(this,account.email.toString())
                SavedPreference.setUsername(this,account.displayName.toString())
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
    override fun onStart() {
        super.onStart()
        if(GoogleSignIn.getLastSignedInAccount(this)!=null){
            startActivity(Intent(this, pagina_home::class.java))
            finish()
        }
    }






}

