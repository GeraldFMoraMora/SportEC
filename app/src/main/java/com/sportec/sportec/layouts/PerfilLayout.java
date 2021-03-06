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
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sportec.sportec.Informacion.Usuario;
import com.sportec.sportec.MainActivity;
import com.sportec.sportec.R;

/**
 * Created by GeraldMM on 10/05/2018.
 */

public class PerfilLayout extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener{
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;

    private EditText mNombreEdit;
    private EditText mCorreoEdit;
    private EditText mContrasenaEdit;
    private String mUserId;

    private FirebaseAuth mAuth;
    private static final String TAG = "CustomAuthActivity";
    private String mCustomToken;

    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mFirebaseAuthListener;

    private EditText nombreUsuario1;
    private EditText correoUsuario1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_perfil);

        this.mNombreEdit = (EditText) findViewById(R.id.usuario_nombre);
        this.mCorreoEdit = (EditText) findViewById(R.id.usuario_correo);
        this.mContrasenaEdit = (EditText) findViewById(R.id.usuario_contrasena);

        this.mDatabase = FirebaseDatabase.getInstance();
        this.mDatabaseReference = mDatabase.getReference();

        this.mAuth = FirebaseAuth.getInstance();



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        this.mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        /** El siguiente es el listener que escucha los cambios de estado para el autenticador de Firebase*/
        this.mFirebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    startActivity(new Intent(PerfilLayout.this,MainActivity.class));
                }
            }
        };


    }
    public void onClick(View view){

        //EditText nombreUsuario1 = (EditText) findViewById(R.id.usuario_nombre_formulario_perfil_textedit);
        //EditText correoUsuario1 = (EditText) findViewById(R.id.usuario_correo_formulario_perfil_textedit);
        FirebaseUser user = mAuth.getCurrentUser();
        switch (view.getId()){

            case R.id.actualizar_formulario_registro_button:
                //createAccount(mCorreoEdit.getText().toString(),mContrasenaEdit.getText().toString());
                this.guardarUsuario(user.getUid(), mNombreEdit.getText().toString(), mCorreoEdit.getText().toString(),"https://firebasestorage.googleapis.com/v0/b/sportec-cf3d1.appspot.com/o/logos%2Fapplogo.png?alt=media&token=23851c7f-9a06-469b-92b0-831364336591");
                startActivity(new Intent(PerfilLayout.this, MainActivity.class));
                break;
            case R.id.salir_perfil_textview:
                startActivity(new Intent(PerfilLayout.this, MainActivity.class));
                break;
        }
    }

    /**
     * Metodo para crear una cuenta, recive el correo y la contrasena, con eso es capaz de crear una cuenta en
     * el Auth de Firebase.
     * @param email
     * @param password
     */
    public void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(PerfilLayout.this, "Cuenta creada correctamente",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(PerfilLayout.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }

                });
    }
    /**
     * Metodo para guardar un usuario en la base de datos
     * @param idUsuario
     * @param nombreUsuario
     * @param correoUsuario
     * @param foto
     */
    private void guardarUsuario(String idUsuario, String nombreUsuario, String correoUsuario, String foto){
        FirebaseUser user;
        Usuario usuario = new Usuario(Usuario.IMAGE_TYPE,nombreUsuario,correoUsuario, foto);
        mDatabaseReference.child("usuario").child(idUsuario).setValue(usuario);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Revisa que exista un usuario y actualiza la activity.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    /**
     * Metodo que actualiza los elementos de la interfaz.
     * @param user
     */
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            EditText mNombreEdit = (EditText) findViewById(R.id.usuario_nombre);
            EditText mCorreoEdit = (EditText) findViewById(R.id.usuario_correo);
            mNombreEdit.setText(user.getDisplayName());
            mCorreoEdit.setText(user.getEmail());
        } else {
            Toast.makeText(PerfilLayout.this, "Error: sign in failed.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
