package com.sportec.sportec;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sportec.sportec.fragments.DeporteFavoritoFragment;
import com.sportec.sportec.fragments.FormularioResgistroFragment;
import com.sportec.sportec.fragments.NoticiaFragment;
import com.sportec.sportec.fragments.SessionFragment;
import com.sportec.sportec.gui.TabActivity;
import com.sportec.sportec.layouts.DeporteLayout;
import com.sportec.sportec.layouts.EquipoLayout;
import com.sportec.sportec.layouts.OpcionLayout;
import com.sportec.sportec.layouts.ResultadoLayout;
import com.sportec.sportec.layouts.SessionLayout;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener,
        NavigationView.OnNavigationItemSelectedListener,
        NoticiaFragment.OnFragmentInteractionListener,
        SessionFragment.OnFragmentInteractionListener,
        FormularioResgistroFragment.OnFragmentInteractionListener,
        DeporteFavoritoFragment.OnFragmentInteractionListener{

    private Intent mScreen;
    private Toolbar mToolbar;
    private ImageView mLogoNav;

    private GridView mGridView;

    private ImageView mImagenNoticia;
    private TextView mTituloNoticia;

    private ImageView mFotoPerfil;
    private TextView mNombreUsuario;
    private TextView mCorreoUsuario;
    private TextView mTokenUsuario;

    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mFirebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        this.mLogoNav= (ImageView) findViewById(R.id.nav_logo);

        this.mNombreUsuario = (TextView) findViewById(R.id.nombre_user_textview);
        this.mCorreoUsuario = (TextView) findViewById(R.id.correo_user_textview);
        this.mTokenUsuario = (TextView) findViewById(R.id.token_user_textview);

        /** Se obtiene la instancia de FirebaseAut*/
        this.mFirebaseAuth = FirebaseAuth.getInstance();

        /** Se debe configurar Google SignIn para obtener el Id de usuario, correo, foto de perfil e
         inclusive otra informacion basica, cada request obtiene alguna de estas peticiones.*/
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
                    setUserData(user);
                }else{
                    goLogInScreen();
                }
            }
        };

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            this.mScreen=new Intent(this, ResultadoLayout.class);
            startActivity(this.mScreen);
            this.mLogoNav.setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_gallery) {
            this.mLogoNav.setVisibility(View.VISIBLE);
            this.showPrincipalFragment();

        } else if (id == R.id.nav_slideshow) {
            this.mLogoNav.setVisibility(View.INVISIBLE);
            this.mScreen=new Intent(this, SessionLayout.class);
            startActivity(this.mScreen);

        } else if (id == R.id.nav_manage) {
            this.showRegistroFragment();
            this.mLogoNav.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_share) {
            this.mScreen=new Intent(this, TabActivity.class);
            startActivity(this.mScreen);
            this.mLogoNav.setVisibility(View.VISIBLE);
        }else if (id == R.id.nav_send) {
            this.mScreen=new Intent(this, DeporteLayout.class);
            startActivity(this.mScreen);
            this.mLogoNav.setVisibility(View.VISIBLE);
        }else if (id == R.id.nav_send1) {
            this.mScreen=new Intent(this, OpcionLayout.class);
            startActivity(this.mScreen);
            this.mLogoNav.setVisibility(View.VISIBLE);
        }else if (id == R.id.nav_send2) {
            this.showDeporteFavoritoFragment();
            this.mLogoNav.setVisibility(View.VISIBLE);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void showPrincipalFragment() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity_fragment,
                        NoticiaFragment.newInstance(""))
                .commit();
    }
    private void showSessionFragment() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity_fragment,
                        SessionFragment.newInstance(""))
                .commit();
    }
    private void showRegistroFragment() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity_fragment,
                        FormularioResgistroFragment.newInstance(""))
                .commit();
    }
    private void showDeporteFavoritoFragment() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity_fragment,
                        DeporteFavoritoFragment.newInstance(""))
                .commit();
    }
    private void setUserData(FirebaseUser user) {
        mNombreUsuario.setText(user.getDisplayName());
        mCorreoUsuario.setText(user.getEmail());
        mTokenUsuario.setText(user.getUid());
        //Glide.with(this).load(user.getPhotoUrl()).into(photoImageView);
    }

    private void goLogInScreen() {
        Intent intent = new Intent(this, SessionLayout.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void logOut(View view) {
        mFirebaseAuth.signOut();

        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    goLogInScreen();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.no_google_session_cerrar, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mFirebaseAuthListener);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (mFirebaseAuthListener != null) {
            mFirebaseAuth.removeAuthStateListener(mFirebaseAuthListener);
        }
    }
}
