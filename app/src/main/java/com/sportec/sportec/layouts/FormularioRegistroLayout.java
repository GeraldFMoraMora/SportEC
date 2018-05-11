package com.sportec.sportec.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sportec.sportec.Informacion.Adapter.ResultadoAdapter;
import com.sportec.sportec.Informacion.Model.ResultadoModel;
import com.sportec.sportec.Informacion.Usuario;
import com.sportec.sportec.MainActivity;
import com.sportec.sportec.R;
import com.sportec.sportec.fragments.FormularioResgistroFragment;

import java.util.ArrayList;

/**
 * Created by GeraldMM on 10/05/2018.
 */

public class FormularioRegistroLayout extends AppCompatActivity implements View.OnClickListener{
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;

    private EditText mNombreEdit;
    private EditText mCorreoEdit;
    private EditText mContrasenaEdit;
    private String mUserId;

    private FirebaseAuth mAuth;
    private static final String TAG = "CustomAuthActivity";
    private String mCustomToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_formulario_resgistro);

        this.mNombreEdit = (EditText) findViewById(R.id.usuario_nombre_formulario_registro_textedit);
        this.mCorreoEdit = (EditText) findViewById(R.id.usuario_correo_formulario_registro_textedit);
        this.mContrasenaEdit = (EditText) findViewById(R.id.usuario_contrasena_formulario_registro_edittex);

        this.mDatabase = FirebaseDatabase.getInstance();
        this.mDatabaseReference = mDatabase.getReference();

        this.mAuth = FirebaseAuth.getInstance();


    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rigistrar_formulario_registro_button:
                createAccount(mCorreoEdit.getText().toString(),mContrasenaEdit.getText().toString());
                startActivity(new Intent(FormularioRegistroLayout.this,MainActivity.class));
                break;
            case R.id.cancelar_formulario_registro_textview:
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
                            Toast.makeText(FormularioRegistroLayout.this, "Cuenta creada correctamente",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            guardarUsuario(user.getUid().toString(), mNombreEdit.getText().toString(), mCorreoEdit.getText().toString(),"https://firebasestorage.googleapis.com/v0/b/sportec-cf3d1.appspot.com/o/logos%2Fapplogo.png?alt=media&token=23851c7f-9a06-469b-92b0-831364336591");

                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(FormularioRegistroLayout.this, "Authentication failed.",
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
            Toast.makeText(FormularioRegistroLayout.this, "User ID: " + user.getUid(),
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(FormularioRegistroLayout.this, "Error: sign in failed.",
                    Toast.LENGTH_SHORT).show();
        }
    }


}
