package com.example.univalle20202;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InveSoftAct extends AppCompatActivity {

    Button btnConsultar;
    String username, password;
    TextView tvApi;
    String url = "https://invessoft.com/api/eventos/2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inve_soft2);

        btnConsultar = findViewById(R.id.btnConsultar);

        tvApi = findViewById(R.id.tvApi);

        //
        Bundle dataReceive = getIntent().getExtras();
        username = dataReceive.getString("userName");
        password = dataReceive.getString("passwd");

    }

    public void consultarApi(View v) {

        InveSoftDB inveDB = new InveSoftDB(this);
        SQLiteDatabase db = inveDB.getWritableDatabase();
        Toast.makeText(getApplicationContext(), "Procesando", Toast.LENGTH_SHORT).show();
        if (db != null) {
            btnConsultar.setVisibility(v.INVISIBLE);
            tvApi.setVisibility(v.VISIBLE);
            Toast.makeText(getApplicationContext(), "Conectando al API de InveSoft...", Toast.LENGTH_SHORT).show();
//           Conectando al API de InveSoft
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                JSONArray jsonArray = response.getJSONArray("agenda");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    int id_trabajo_agenda = jsonObject.getInt("id_trabajo_agenda");
                                    String fecha = jsonObject.getString("fecha");
                                    String hora_inicio = jsonObject.getString("hora_inicio");
                                    String hora_fin = jsonObject.getString("hora_fin");


                                    JSONObject object_espacio = jsonObject.getJSONObject("espacio");
                                    int id_espacio = jsonObject.getInt("id_espacio");
                                    String descripcion = object_espacio.getString("descripcion");

                                    String url_webinar = object_espacio.getString("url_webinar");



                                    JSONObject object_campus = object_espacio.getJSONObject("campus");
                                    int id_campus = object_campus.getInt("id_campus");
                                    String nombre_campus = object_campus.getString("nombre_campus");


                                    JSONObject object_trabajo = jsonObject.getJSONObject("trabajo");
                                    int id_trabajo = object_trabajo.getInt("id_trabajo");
                                    String nombre_trabajo = object_trabajo.getString("nombre_trabajo");


                                    JSONObject object_semillero = object_trabajo.getJSONObject("semillero");
                                    int id_semillero = object_semillero.getInt("id_semillero");
                                    String nombre_semillero = object_semillero.getString("nombre_semillero");


                                    JSONObject object_tipo_de_trabajo = object_trabajo.getJSONObject("tipo_de_trabajo");
                                    int id_tipo_de_trabajo = object_tipo_de_trabajo.getInt("id_tipo_de_trabajo");
                                    String nombre_tipo_de_trabajo = object_tipo_de_trabajo.getString("nombre_tipo_de_trabajo");



                                    JSONObject object_sede = object_trabajo.getJSONObject("sede");
                                    int id_sede = object_sede.getInt("id_sede");
                                    String nombre_sede = object_sede.getString("nombre_sede");


                                    JSONObject object_institucion = object_sede.getJSONObject("institucion");
                                    int id_institucion = object_institucion.getInt("id_institucion");
                                    String nombre_institucion = object_institucion.getString("nombre_institucion");



                                    JSONArray array_trabajos_ponentes = object_trabajo.getJSONArray("trabajos_ponentes");
                                    JSONObject object_ponente = array_trabajos_ponentes.getJSONObject(0);



                                    JSONObject object_persona = object_ponente.getJSONObject("persona");
                                    int id_persona = object_persona.getInt("id_persona");
                                    String  nombre_persona = object_persona.getString("nombre_persona");
                                    Toast.makeText(getApplicationContext(), "Salió:  " + nombre_persona , Toast.LENGTH_SHORT).show();

                                    JSONArray array_trabajos_agendas_evaluadores = jsonObject.getJSONArray("trabajos_agendas_evaluadores");
                                    JSONObject object_evaluador = array_trabajos_agendas_evaluadores.getJSONObject(0);




//                                    Guardar data en mySql
                                    try {
                                        ContentValues cv = new ContentValues();
                                        cv.put(InveSoftDB.DataBaseIS.COLUMN_ID_TRABAJO_AGENDA, id_trabajo_agenda);

                                        cv.put(InveSoftDB.DataBaseIS.COLUMN_FECHA, fecha);
                                        cv.put(InveSoftDB.DataBaseIS.COLUMN_HORA_INICIO, hora_inicio);
                                        cv.put(InveSoftDB.DataBaseIS.COLUMN_HORA_FIN, hora_fin);


                                        //    cv.put(InveSoftDB.DataBaseIS.COLUMN_ESPACIO, espacio);
                                        cv.put(InveSoftDB.DataBaseIS.COLUMN_ID_ESPACIO, id_espacio);
                                        cv.put(InveSoftDB.DataBaseIS.COLUMN_DESCRIPCION, descripcion);
                                        cv.put(InveSoftDB.DataBaseIS.COLUMN_URL_WEBINAR, url_webinar);

                                        //    cv.put(InveSoftDB.DataBaseIS.COLUMN_CAMPUS, campus);
                                        cv.put(InveSoftDB.DataBaseIS.COLUMN_ID_CAMPUS, id_campus);
                                        cv.put(InveSoftDB.DataBaseIS.COLUMN_NOMBRE_CAMPUS, nombre_campus);

                                        //    cv.put(InveSoftDB.DataBaseIS.COLUMN_TRABAJO, trabajo);
                                        cv.put(InveSoftDB.DataBaseIS.COLUMN_ID_TRABAJO, id_trabajo);
                                        cv.put(InveSoftDB.DataBaseIS.COLUMN_NOMBRE_TRABAJO, nombre_trabajo);

                                        //    cv.put(InveSoftDB.DataBaseIS.COLUMN_SEMILLERO, semillero);
                                        cv.put(InveSoftDB.DataBaseIS.COLUMN_ID_SEMILLERO, id_semillero);
                                        cv.put(InveSoftDB.DataBaseIS.COLUMN_NOMBRE_SEMILLERO, nombre_semillero);

                                        //    cv.put(InveSoftDB.DataBaseIS.COLUMN_TIPO_TRABAJO, tipo_trabajo);
                                        cv.put(InveSoftDB.DataBaseIS.COLUMN_ID_TIPO_DE_TRABAJO, id_tipo_de_trabajo);
                                        cv.put(InveSoftDB.DataBaseIS.COLUMN_NOMBRE_TIPO_DE_TRABAJO, nombre_tipo_de_trabajo);

                                        //    cv.put(InveSoftDB.DataBaseIS.COLUMN_SEDE, sede);
                                        cv.put(InveSoftDB.DataBaseIS.COLUMN_ID_SEDE, id_sede);
                                        cv.put(InveSoftDB.DataBaseIS.COLUMN_NOMBRE_SEDE, nombre_sede);

                                        //   cv.put(InveSoftDB.DataBaseIS.COLUMN_INSTITUCION, institucion);
                                        cv.put(InveSoftDB.DataBaseIS.COLUMN_ID_INSTITUCION, id_institucion);
                                        cv.put(InveSoftDB.DataBaseIS.COLUMN_NOMBRE_INSTITUCION, nombre_institucion);

                                        //   cv.put(InveSoftDB.DataBaseIS.COLUMN_TRABAJOS_PONENTES, trabajos_ponentes);
                                        cv.put(InveSoftDB.DataBaseIS.COLUMN_PONENTE, String.valueOf(object_ponente));

                                        //    cv.put(InveSoftDB.DataBaseIS.COLUMN_PERSONA, persona);
                                        cv.put(InveSoftDB.DataBaseIS.COLUMN_ID_PERSONA, id_persona);
                                        cv.put(InveSoftDB.DataBaseIS.COLUMN_NOMBRE_PERSONA, nombre_persona);

                                        //    cv.put(InveSoftDB.DataBaseIS.COLUMN_TRABAJOS_AGENDAS_EVALUADORES, trabajos_agendas_evaluadores);
                                        cv.put(InveSoftDB.DataBaseIS.COLUMN_EVALUADOR, String.valueOf(object_evaluador));


                                        db.insert(InveSoftDB.DataBaseIS.TABLE_NAME, null, cv);

                                    } catch (Exception e) {
                                        Toast.makeText(getApplicationContext(), "Problem: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                        e.printStackTrace();
                                    }
                                }
                                tvApi.setText("Done! ... Consulta al API realizada con éxito");
                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    }
            );
            requestQueue.add(jsonObjectRequest);
        }
    }
}
