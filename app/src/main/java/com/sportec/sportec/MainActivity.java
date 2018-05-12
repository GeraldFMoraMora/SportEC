package com.sportec.sportec;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sportec.sportec.Informacion.Adapter.BusquedaAdapter;
import com.sportec.sportec.Informacion.Adapter.MiembroAdapter;
import com.sportec.sportec.Informacion.Adapter.NoticiaMainAdapter;
import com.sportec.sportec.Informacion.ConstantInterface;
import com.sportec.sportec.Informacion.Model.MiembroModel;
import com.sportec.sportec.Informacion.Model.NoticiaMainModel;
import com.sportec.sportec.Informacion.Noticia;
import com.sportec.sportec.Informacion.Usuario;
import com.sportec.sportec.fragments.DeporteFavoritoFragment;
import com.sportec.sportec.fragments.FormularioResgistroFragment;
import com.sportec.sportec.fragments.NoticiaFragment;
import com.sportec.sportec.gui.TabActivity;
import com.sportec.sportec.layouts.BusquedaLayout;
import com.sportec.sportec.layouts.DeporteLayout;
import com.sportec.sportec.layouts.EquipoLayout;
import com.sportec.sportec.layouts.OpcionLayout;
import com.sportec.sportec.layouts.PerfilLayout;
import com.sportec.sportec.layouts.ResultadoLayout;
import com.sportec.sportec.layouts.SessionLayout;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener,
        NavigationView.OnNavigationItemSelectedListener,
        NoticiaFragment.OnFragmentInteractionListener,
        FormularioResgistroFragment.OnFragmentInteractionListener,
        DeporteFavoritoFragment.OnFragmentInteractionListener{

    private Intent mScreen;
    private Toolbar mToolbar;
    private ImageView mLogoNav;

    private GridView mGridView;

    private ImageView mImagenNoticia;
    private TextView mTituloNoticia;

    public String mTituloNoticiaDia;
    public String mFotoNoticiaDia;
    public String mDescripcionDia;

    private ImageView mFotoPerfil;
    private LinearLayout mLinearLayout;
    private TextView mNombreUsuario;
    private TextView mCorreoUsuario;
    private TextView mTokenUsuario;

    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mFirebaseAuthListener;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;

    private ArrayList<NoticiaMainModel> list;

    private CardView mCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        this.mLogoNav= (ImageView) findViewById(R.id.nav_logo);

        this.mLinearLayout = (LinearLayout) findViewById(R.id.contenedor_nav_header_main_linearlayout);

        this.mNombreUsuario = (TextView) findViewById(R.id.nombre_usuario_nav_header_main_textview);
        this.mCorreoUsuario = (TextView) findViewById(R.id.correo_usuario_nav_header_main_textview);

        this.mImagenNoticia=(ImageView) findViewById(R.id.noticia_foto_dia_imageview);
        this.mTituloNoticia=(TextView) findViewById(R.id.noticia_titulo_dia_textview);

        //this.mNombreUsuario.setText("");
        //this.mCorreoUsuario.setText("");

        //BjjnZXxWjeO5DqcSv6eXXihjd8G53
        this.mDatabase = FirebaseDatabase.getInstance();
        this.mDatabaseReference = mDatabase.getReference();


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
                    TextView nombreUsuario1 = (TextView) findViewById(R.id.nombre_usuario_nav_header_main_textview);
                    TextView correoUsuario1 = (TextView) findViewById(R.id.correo_usuario_nav_header_main_textview);
                    if (nombreUsuario1!=null){
                        correoUsuario1.setText(user.getEmail());
                        //guardarUsuario(user.getUid(),user.getDisplayName(),user.getEmail(),user.getPhotoUrl().toString());
                        if (user.getDisplayName()!=null){
                            nombreUsuario1.setText(user.getDisplayName());
                            Toast.makeText(getApplicationContext(),"Bienvenido "+user.getDisplayName().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }else{
                    }

                }else{
                    goLogInScreen();
                }
            }
        };



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.list= new ArrayList();
        // Read from the database
        //DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = this.mDatabaseReference.child("noticia");

        //Query phoneQuery = ref.equalTo("usuario");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    NoticiaMainModel noticia = singleSnapshot.getValue(NoticiaMainModel.class);
                    list.add(new NoticiaMainModel(NoticiaMainModel.IMAGE_TYPE,noticia.titulo,noticia.foto,noticia.descripcion,noticia.dia,noticia.id));

                    if (noticia.dia){
                        Picasso.get().load(noticia.foto).into(mImagenNoticia);
                        mTituloNoticia.setText(noticia.titulo);
                        mTituloNoticiaDia=noticia.titulo;
                        mFotoNoticiaDia=noticia.foto;
                        mDescripcionDia=noticia.descripcion;
                    }
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    NoticiaMainModel noticia = singleSnapshot.getValue(NoticiaMainModel.class);
                    //list.add(new NoticiaMainModel(NoticiaMainModel.IMAGE_TYPE,noticia.titulo,noticia.foto,noticia.descripcion,noticia.dia,noticia.id));


                    //Picasso.get().load(noticia.foto).into(mImagenNoticia);
                    //mTituloNoticia.setText(noticia.titulo);
                    //mTituloNoticiaDia=noticia.titulo;
                    //mFotoNoticiaDia=noticia.foto;
                    //mDescripcionDia=noticia.descripcion;

                }
                NoticiaMainAdapter adapter = new NoticiaMainAdapter(list, MainActivity.this, new ConstantInterface() {
                    @Override
                    public void onClick(View v, final int position) {
                        DatabaseReference ref = mDatabaseReference.child("noticia");
                        ref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                                    NoticiaMainModel noticia = singleSnapshot.getValue(NoticiaMainModel.class);

                                    if (String.valueOf(noticia.id.equals(String.valueOf(position)))=="true"){
                                        mTituloNoticiaDia=noticia.titulo;
                                        mFotoNoticiaDia=noticia.foto;
                                        mDescripcionDia=noticia.descripcion;
                                        getSupportFragmentManager()
                                                .beginTransaction()
                                                .replace(R.id.main_activity_fragment,
                                                        NoticiaFragment.newInstance(noticia.titulo,noticia.foto, noticia.descripcion))
                                                .commit();
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, OrientationHelper.VERTICAL, false);

                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.noticia_app_bar_main_recyclerview);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
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
        if (id == R.id.action_search) {
            startActivity(new Intent(MainActivity.this, BusquedaLayout.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view){
        this.mCardView= (CardView) findViewById(R.id.card_view);
        TextView textView= (TextView) findViewById(R.id.noticia_titulo_layout_card_textview);
        switch (view.getId()){
            case R.id.noticia_dia_app_bar_main:
                this.mLogoNav.setVisibility(View.VISIBLE);
                //this.showNoticiaFragment(mTituloNoticiaDia,mFotoNoticiaDia,mDescripcionDia);
                break;
        }
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
        }else if (id == R.id.nav_share) {
            this.mScreen=new Intent(this, TabActivity.class);
            startActivity(this.mScreen);
            this.mLogoNav.setVisibility(View.VISIBLE);
        }else if (id == R.id.nav_send) {
            this.mScreen=new Intent(this, DeporteLayout.class);
            startActivity(this.mScreen);
            this.mLogoNav.setVisibility(View.VISIBLE);
        }else if (id == R.id.nav_send1) {
            this.mScreen=new Intent(this, MainActivity.class);
            startActivity(this.mScreen);
            this.mLogoNav.setVisibility(View.VISIBLE);
        }else if (id == R.id.nav_send2) {
            this.showDeporteFavoritoFragment();
            this.mLogoNav.setVisibility(View.VISIBLE);
        }else if (id == R.id.nav_salir) {
            finish();
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
            this.mLogoNav.setVisibility(View.VISIBLE);
        }else if (id == R.id.nav_perfil) {
            this.mScreen=new Intent(this, PerfilLayout.class);
            startActivity(this.mScreen);
            this.mLogoNav.setVisibility(View.VISIBLE);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void showNoticiaFragment(String TituloNoticiaDia,String FotoNoticiaDia,String DescripcionDia) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity_fragment,
                        NoticiaFragment.newInstance(TituloNoticiaDia,FotoNoticiaDia, DescripcionDia))
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
        //mNombreUsuario.setText(user.getDisplayName());
        //mCorreoUsuario.setText(user.getEmail());
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
