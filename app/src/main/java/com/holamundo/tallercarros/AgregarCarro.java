package com.holamundo.tallercarros;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Random;

public class AgregarCarro extends Activity {
    private EditText txtPlaca, txtPrecio;
    private Spinner cmbColor, cmbMarca;
    private ArrayAdapter<String> adapterColor, adapterMarca;
    private String opcColor[], opcMarca[];
    private ArrayList<Integer> fotos;
    private ImageView foto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_carro);

        txtPlaca = findViewById(R.id.txtPlaca);
        cmbColor = findViewById(R.id.cmbColor);
        cmbMarca = findViewById(R.id.cmbMarca);
        txtPrecio = findViewById(R.id.txtPrecio);

        fotos = new ArrayList<>();
        fotos.add(R.drawable.carro1);
        fotos.add(R.drawable.carro2);
        fotos.add(R.drawable.carro3);
        fotos.add(R.drawable.carro4);
        fotos.add(R.drawable.carro5);

        opcColor = getResources().getStringArray(R.array.colores);
        adapterColor = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,opcColor);
        cmbColor.setAdapter(adapterColor);

        opcMarca = getResources().getStringArray(R.array.marcas);
        adapterMarca = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,opcMarca);
        cmbMarca.setAdapter(adapterMarca);
    }

    public int fotoAleatoria(){
        int fotoSeleccionada;
        Random r = new Random();
        fotoSeleccionada = r.nextInt(this.fotos.size());
        return fotos.get(fotoSeleccionada);
    }

    public void guardar(View v){
        String placa;
        int foto, color, marca, precio;
        foto = this.fotoAleatoria();
        placa = txtPlaca.getText().toString();
        color = cmbColor.getSelectedItemPosition();
        marca = cmbMarca.getSelectedItemPosition();
        precio = Integer.parseInt(txtPrecio.getText().toString());
        Carro c = new Carro(foto,placa,color,marca,precio);
        c.guardar();
        limpiar();
        Snackbar.make(v,getResources().getString(R.string.guardado_exitoso),Snackbar.LENGTH_SHORT)
                .show();

    }


    public void onBackPressed(){
        finish();
        Intent i = new Intent(AgregarCarro.this,Principal.class);
        startActivity(i);
    }

    public void limpiar(){
        txtPlaca.setText("");
        cmbColor.setSelection(0);
        cmbMarca.setSelection(0);
        txtPrecio.setText("");
        txtPlaca.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void limpiar(View v){
        limpiar();
    }

}
