package com.seccion.yugioh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.seccion.yugioh.Common.MyApp;
import com.seccion.yugioh.Common.SharedPreferencesManager;
import com.seccion.yugioh.Retrofit.Response.ResponseUpdateUser;
import com.seccion.yugioh.Retrofit.Response.ResponseUsuarioLogin;
import com.seccion.yugioh.Retrofit.YugiohClient;
import com.seccion.yugioh.Retrofit.YugiohServerApi;
import com.seccion.yugioh.ui.cuadros_dialogo.CerrarSesionFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tituloNombre, tituloCreditos;
    private EditText nombre_nuevo, creditos_nuevo;
    private Button btnAddCreditos, btnSave, btnClose;

    private Double creditosActuales;
    
    private YugiohClient yugiohClient;
    private YugiohServerApi yugiohServerApi;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        
        retrofirInit();

        tituloNombre = findViewById(R.id.tituloNombre);
        tituloCreditos = findViewById(R.id.tituloCreditos);
        nombre_nuevo = findViewById(R.id.nombre_nuevo);
        creditos_nuevo = findViewById(R.id.creditos_nuevo);
        btnAddCreditos = findViewById(R.id.btnAddCreditos);
        btnSave = findViewById(R.id.btnSave);
        btnClose = findViewById(R.id.btnClose);

        nombre_nuevo.setText(SharedPreferencesManager.getSomeStringValue("user_login"));
        creditos_nuevo.setText(SharedPreferencesManager.getSomeStringValue("user_creditos"));

        btnAddCreditos.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnClose.setOnClickListener(this);


        creditosActuales = Double.valueOf(SharedPreferencesManager.getSomeStringValue("user_creditos"));


    }

    private void retrofirInit() {
        yugiohClient = YugiohClient.getINSTANCE();
        yugiohServerApi = yugiohClient.getYugiohServerApi();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddCreditos:

                SharedPreferences preferenceManagerCreditos = getApplicationContext().getSharedPreferences("APP_SETTINGS", Context.MODE_PRIVATE);
                SharedPreferences.Editor editorCreditos = preferenceManagerCreditos.edit();
                editorCreditos.remove("user_creditos");
                editorCreditos.putString("user_creditos", String.valueOf(Double.valueOf(creditos_nuevo.getText().toString()) + creditosActuales));
                editorCreditos.commit();
                creditos_nuevo.setText(SharedPreferencesManager.getSomeStringValue("user_creditos"));
                Toast.makeText(this, "Creditos agregados", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnSave:
                if (nombre_nuevo.getText().toString() != "") {
                    //Primero guardamos en local
                    SharedPreferences preferenceManager = getApplicationContext().getSharedPreferences("APP_SETTINGS", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferenceManager.edit();
                    editor.remove("user_login");
                    editor.putString("user_login", nombre_nuevo.getText().toString());
                    editor.commit();

                    //Despues guardamos en la base de datos remota
                    Call<ResponseUpdateUser> call = yugiohServerApi.updateUserName(SharedPreferencesManager.getSomeStringValue("user_id"), nombre_nuevo.getText().toString());
                    call.enqueue(new Callback<ResponseUpdateUser>() {
                        @Override
                        public void onResponse(Call<ResponseUpdateUser> call, Response<ResponseUpdateUser> response) {
                            Toast.makeText(SettingsActivity.this, "Tus datos se an actualizado correctamente", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ResponseUpdateUser> call, Throwable t) {
                            Toast.makeText(SettingsActivity.this, "Error de conexion: "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    finish();

                }else if (creditos_nuevo.getText().toString() != null) {

                    SharedPreferences preferenceManager = getApplicationContext().getSharedPreferences("APP_SETTINGS", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferenceManager.edit();
                    editor.remove("user_creditos");
                    editor.putString("user_creditos", String.valueOf(Double.valueOf(creditos_nuevo.getText().toString()) + creditosActuales));
                    editor.commit();
                    creditos_nuevo.setText(SharedPreferencesManager.getSomeStringValue("user_creditos"));
                    Toast.makeText(this, "Creditos agregados", Toast.LENGTH_SHORT).show();
                    finish();

                }else{
                    Toast.makeText(MyApp.getContext(), "No se realizara ningun cambiando", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;

            case R.id.btnClose:
                FragmentManager fragmentManager = getSupportFragmentManager();
                CerrarSesionFragment cerrarSesionFragment = new CerrarSesionFragment();
                cerrarSesionFragment.show(fragmentManager, "CerrarSessionFragment");
                break;
            default:
                break;
        }
    }
}
