package com.sportec.sportec.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.sportec.sportec.Informacion.ConstantInterface;
import com.sportec.sportec.MainActivity;
import com.sportec.sportec.R;


/**
 * Created by GeraldMM on 08/05/2018.
 */

public class SessionLayout extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, ConstantInterface {

    private SignInButton mButtomGoogle;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mFirebaseAuthListener;

    private GoogleApiClient mGoogleApiClient;

    private EditText mNombreEdit;
    private EditText mCorreoEdit;
    private EditText mContrasenaEdit;

    private static final String TAG = "CustomAuthActivity";

    private FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_session);

        this.mCorreoEdit = (EditText) findViewById(R.id.usuario_correo_iniciosession_edittext);
        this.mContrasenaEdit = (EditText) findViewById(R.id.usuario_contrasena_inicio_session_edittext);

        /** Se obtiene la instancia de FirebaseAut*/
        this.mFirebaseAuth = FirebaseAuth.getInstance();

        /** Se debe configurar Google SignIn para obtener el Id de usuario, correo, foto de perfil e
         inclusive otra informacion basica, cada request obtiene alguna de estas peticiones.*/
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        this.mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        this.mButtomGoogle = (SignInButton) findViewById(R.id.signInGoogleButton);

        this.mButtomGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        /** El siguiente es el listener que escucha los cambios de estado para el autenticador de Firebase*/
        this.mFirebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    goMainScreen();
                }
            }
        };
    }

    /**
     * Metodo autogenerado
     * @param connectionResult
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    /**
     * Metodo que maneja el resultado de conexion, indicara que pasa si hay una conexion exitosa
     * o fallida.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                firebaseAuthWithGoogle(result.getSignInAccount());
            } else {
                Toast.makeText(this, R.string.no_google_session_abrir, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Metodo que se encargara de de obtener el token que identifica al usuario
     * @param signInAccount
     */
    private void firebaseAuthWithGoogle(final GoogleSignInAccount signInAccount) {

        mButtomGoogle.setVisibility(View.GONE);

        AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                mButtomGoogle.setVisibility(View.VISIBLE);

                if (!task.isSuccessful()) {
                    //En caso de que no se haya podido autenticar con Firebase Auth.
                    Toast.makeText(getApplicationContext(), R.string.no_google_auth, Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(SessionLayout.this,MainActivity.class));
                }
            }
        });
    }

    /**
     * Metodo que me envia a la pagina principal de la app una vez hay un usuario conectado
     */
    private void goMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
    public void connectAccount(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(SessionLayout.this, "Conectado correctamente",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            mFirebaseAuthListener = new FirebaseAuth.AuthStateListener() {
                                @Override
                                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    if (user != null) {

                                        goMainScreen();
                                    }
                                }
                            };
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SessionLayout.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }

                });

    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Toast.makeText(SessionLayout.this, "User ID: " + user.getUid(),
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SessionLayout.this, "Error: sign in failed.",
                    Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * Metodo autogenerado
     */
    @Override
    protected void onStart() {
        super.onStart();

        mFirebaseAuth.addAuthStateListener(mFirebaseAuthListener);
    }

    /**
     * Metodo Autogenerado
     */
    @Override
    protected void onStop() {
        super.onStop();

        if (mFirebaseAuthListener != null) {
            mFirebaseAuth.removeAuthStateListener(mFirebaseAuthListener);
        }
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iniciar_session_buttom:
                connectAccount(mCorreoEdit.getText().toString(),mContrasenaEdit.getText().toString());
                startActivity(new Intent(SessionLayout.this,MainActivity.class));
                break;
            case R.id.registrarse_session_buttom:
                startActivity(new Intent(SessionLayout.this,FormularioRegistroLayout.class));
                break;
        }
    }
}
