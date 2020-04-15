package com.seccion.yugioh.DialogsFragments.RegistroUsuario;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.seccion.yugioh.Common.MyApp;
import com.seccion.yugioh.R;
import com.seccion.yugioh.Retrofit.Response.ResponseUsuario;
import com.seccion.yugioh.Retrofit.YugiohClient;
import com.seccion.yugioh.Retrofit.YugiohServerApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//TODO: Esta case se ha creado como un Fragment y posteriormente se cambio a DialogFragment
public class RegistroUsuarioNuevoFragment extends DialogFragment implements View.OnClickListener {

    //Crearemos los controladores visuales
    EditText user_editText, nombre_editText, email_editText, password_editText, edad_editText;
    Button btnRegistroUsuario;
    TextView textCancelar;
    ImageView imagen_close;

    //Debemos crear dos variables, una sera para nuestro cliente y otra para nuestro servidor
    YugiohClient yugiohClient;
    YugiohServerApi yugiohServerApi;

    //Vamos a crear primero su metodo onCreate
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //En el metodo onCreate vamos a pasarle un estilo personalizado para que ocupe la pantalla completa
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Cambiamos la sentencia original y colocamos un view para retornalo y recuperar los elementos visuale
        View view = inflater.inflate(R.layout.fragment_registro_usuario_nuevo, container, false);
        //Recuperamos los controladores que declaramos al inicio de esta vista
        user_editText = view.findViewById(R.id.user_editText);
        nombre_editText = view.findViewById(R.id.nombre_editText);
        email_editText = view.findViewById(R.id.email_editText);
        password_editText = view.findViewById(R.id.password_editText);
        edad_editText = view.findViewById(R.id.edad_editText);
        //Botones
        btnRegistroUsuario = view.findViewById(R.id.btnRegistroUsuario);
        textCancelar = view.findViewById(R.id.textCancelar);
        imagen_close = view.findViewById(R.id.imagen_close);
        
        //Eventos on clic de cada imagen o boton que lo necesite
        btnRegistroUsuario.setOnClickListener(this);
        textCancelar.setOnClickListener(this);
        imagen_close.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegistroUsuario:
                registroUsuario();
                break;
            case R.id.textCancelar:
                getDialog().dismiss();
                break;
            case R.id.imagen_close:
                getDialog().dismiss();
                break;
        }
    }

    private void registroUsuario() {
        //Recuperamos el contenido de las cajas de texto para poder validar si estan vacias o no
        String usuario = user_editText.getText().toString();
        String nombre = nombre_editText.getText().toString();
        String email = email_editText.getText().toString();
        String password = password_editText.getText().toString();
        String edad = edad_editText.getText().toString();

        if (usuario.isEmpty() && nombre.isEmpty() && email.isEmpty() && password.isEmpty() && edad.isEmpty()){
            Toast.makeText(MyApp.getContext(), "Debes ingresar todos los datos para iniciar sesión", Toast.LENGTH_SHORT).show();
            user_editText.setError("Ingresa un usuario");
            nombre_editText.setError("Ingresa tu nombre completo");
            email_editText.setError("Ingresa un correo electronico");
            password_editText.setError("Ingresa una contraseña");
            edad_editText.setError("Ingresa tu edad");
        }else if (usuario.isEmpty()){
            user_editText.setError("Ingresa un usuario");
        } else if (nombre.isEmpty()) {
            nombre_editText.setError("Ingresa tu nombre completo");
        } else if (email.isEmpty()) {
            email_editText.setError("Ingresa un correo electronico");
        } else if (password.isEmpty()){
            password_editText.setError("Ingresa una contraseña");
        } else if (edad.isEmpty()) {
            edad_editText.setError("Ingresa tu edad");
        } else if (!usuario.isEmpty() && !nombre.isEmpty() && !email.isEmpty() && !password.isEmpty() && !edad.isEmpty()) {
            //Si todos los campos estan correctos, pasamos a hacer el registro de nuestro usuario, para esto debemos crear
            //nuestro servicio retrofit
            //Este metodo nos permitira iniciar nuestros valores iniciales de retrofit para poder generar la insersion de datos
            retrofitInit();
            //Una vez que inicializamos nuestras variables de retrofit client y service api debemos ejecutar una peticion Call
            //Si marca un error en la consuta, revisa el Json que estas devolviendo
            Call<ResponseUsuario> responseUsuarioCall = yugiohServerApi.doRegister(usuario, nombre, email, password, edad);
            responseUsuarioCall.enqueue(new Callback<ResponseUsuario>() {
                @Override
                public void onResponse(Call<ResponseUsuario> call, Response<ResponseUsuario> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(MyApp.getContext(), "Te has regitrado con exito", Toast.LENGTH_LONG).show();
                        getDialog().dismiss();
                    }else {
                        Toast.makeText(MyApp.getContext(), "Error al realizar la consulta", Toast.LENGTH_SHORT).show();
                        Log.d("HIDEO","Error: "+response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<ResponseUsuario> call, Throwable t) {
                    Toast.makeText(MyApp.getContext(), "Ha ocurrido un error inesperado, intentalo nuevamente", Toast.LENGTH_SHORT).show();
                    Log.d("HIDEO","Error: "+t.getLocalizedMessage());
                }
            });

        }else {
            Toast.makeText(MyApp.getContext(), "Ha ocurrido un error inesperado, vuelve a intentarlo mas tarde", Toast.LENGTH_SHORT).show();
        }

    }

    private void retrofitInit() {
        //Inicializamos nuestra variable de yugiohClient, le pasamos nuestra instancia creada desde la clase de YugiohClient
        yugiohClient = YugiohClient.getINSTANCE();
        //Tambien inicializamos nuestra variable de yugiohServerApi y la igualamos a metodo getYugiohServerApi que esta en
        //nuestra clase de yugiohClient
        yugiohServerApi = yugiohClient.getYugiohServerApi();
    }
}
