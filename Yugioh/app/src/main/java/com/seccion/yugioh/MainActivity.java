package com.seccion.yugioh;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.seccion.yugioh.Common.SharedPreferencesManager;
import com.seccion.yugioh.DialogsFragments.RegistroUsuario.RegistroUsuarioNuevoFragment;
import com.seccion.yugioh.Retrofit.Response.ResponseUsuarioLogin;
import com.seccion.yugioh.Retrofit.YugiohClient;
import com.seccion.yugioh.Retrofit.YugiohServerApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText user_text, password_text;
    Button btnLogin;
    TextView textViewRegistro;
    
    //Retrofit: Debemos iniciar nuestras variables de conexion a retrofit
    YugiohClient yugiohClient;
    YugiohServerApi yugiohServerApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        user_text = findViewById(R.id.user_editText);
        password_text = findViewById(R.id.password_userText);
        btnLogin = findViewById(R.id.btnLogin);
        textViewRegistro = findViewById(R.id.textView_registro);

        btnLogin.setOnClickListener(this);
        textViewRegistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                final String usuario = user_text.getText().toString();
                final String password = password_text.getText().toString();
                if (usuario.isEmpty() && password.isEmpty()){
                    Toast.makeText(this, "Debes ingresar tus datos para iniciar sesión", Toast.LENGTH_LONG).show();
                    user_text.setError("Debes ingresar tu usuario");
                    password_text.setError("Debes ingresar tu contraseña");
                } else if (usuario.isEmpty()) {
                    Toast.makeText(this, "Debes ingresar tu usuario para iniciar sesión", Toast.LENGTH_LONG).show();
                    user_text.setError("Debes ingresar tu usuario");
                }else if (password.isEmpty()){
                    Toast.makeText(this, "Debes ingresar tu contraseña", Toast.LENGTH_LONG).show();
                    password_text.setError("Debes ingresar tu contraseña");
                } else if (!usuario.isEmpty() && !password.isEmpty()) {
                    //Aqui ejecutaremos el login si el usuario complue con todos los requeitos
                    retrofitInit();
                    Call<ResponseUsuarioLogin> call = yugiohServerApi.doLogin(usuario, password);
                    call.enqueue(new Callback<ResponseUsuarioLogin>() {
                        @Override
                        public void onResponse(Call<ResponseUsuarioLogin> call, Response<ResponseUsuarioLogin> response) {
                            if (response.isSuccessful()) {
                                String usuario_consultado = response.body().getNombreUsuario();
                                String password_consultada = response.body().getPassword();
                                if (usuario.equals(usuario_consultado) && password.equals(password_consultada)) {
                                    SharedPreferencesManager.setSomeStringValue("user_login", usuario_consultado);
                                    if (SharedPreferencesManager.getSomeStringValue("user_creditos") == null){
                                        SharedPreferencesManager.setSomeStringValue("user_creditos", "10000");
                                    }else {
                                        Log.d("HIDEO", "onResponse: el usuario ya tiene creditos");
                                    }

                                    Intent intent = new Intent(MainActivity.this, UsuariosMainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    Toast.makeText(MainActivity.this, "Error de usuario o contraseña", Toast.LENGTH_LONG).show();
                                }
                            }else {
                                Toast.makeText(MainActivity.this, "Error al recuperar la informacion del usuario", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseUsuarioLogin> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    Toast.makeText(this, "Ha ocurrido un error inesperado, intentalo de nuevo", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.textView_registro:
                //Esta funcion va a llamar a un DialogFragment que tendra un estilo de pantalla full
                FragmentManager fragmentManager = getSupportFragmentManager();
                RegistroUsuarioNuevoFragment registroUsuarioNuevo = new RegistroUsuarioNuevoFragment();
                registroUsuarioNuevo.show(fragmentManager, "RegistroUsuarioNuevoFragment");
                break;
        }
    }

    private void retrofitInit() {
        yugiohClient = YugiohClient.getINSTANCE();
        yugiohServerApi = yugiohClient.getYugiohServerApi();
    }
}
