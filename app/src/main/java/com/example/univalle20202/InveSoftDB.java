package com.example.univalle20202;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class InveSoftDB extends SQLiteOpenHelper {

    public static class  DataBaseIS implements BaseColumns {

        public static final String TABLE_NAME = "agenda";
        public static final String COLUMN_ID_TRABAJO_AGENDA = "id_trabajo_agenda";
        public static final String COLUMN_FECHA = "fecha";
        public static final String COLUMN_HORA_INICIO = "hora_inicio";
        public static final String COLUMN_HORA_FIN = "hora_fin";


        public static final String COLUMN_ESPACIO = "espacio";
        public static final String COLUMN_ID_ESPACIO = "id_espacio";
        public static final String COLUMN_DESCRIPCION = "descripcion";
        public static final String COLUMN_URL_WEBINAR = "url_webinar";


        public static final String COLUMN_CAMPUS = "campus";
        public static final String COLUMN_ID_CAMPUS = "id_campus";
        public static final String COLUMN_NOMBRE_CAMPUS = "nombre_campus";


        public static final String COLUMN_TRABAJO = "trabajo";
        public static final String COLUMN_ID_TRABAJO = "id_trabajo";
        public static final String COLUMN_NOMBRE_TRABAJO = "nombre_trabajo";


        public static final String COLUMN_SEMILLERO = "semillero";
        public static final String COLUMN_ID_SEMILLERO = "id_semillero";
        public static final String COLUMN_NOMBRE_SEMILLERO = "nombre_semillero";


        public static final String COLUMN_TIPO_TRABAJO = "tipo_de_trabajo";
        public static final String COLUMN_ID_TIPO_DE_TRABAJO = "id_tipo_de_trabajo";
        public static final String COLUMN_NOMBRE_TIPO_DE_TRABAJO = "nombre_tipo_de_trabajo";


        public static final String COLUMN_SEDE = "sede";
        public static final String COLUMN_ID_SEDE = "id_sede";
        public static final String COLUMN_NOMBRE_SEDE = "nombre_sede";


        public static final String COLUMN_INSTITUCION = "institucion";
        public static final String COLUMN_ID_INSTITUCION = "id_institucion";
        public static final String COLUMN_NOMBRE_INSTITUCION = "nombre_institucion";


        public static final String COLUMN_TRABAJOS_PONENTES = "trabajos_ponentes";
        public static final String COLUMN_PONENTE = "ponente";


        public static final String COLUMN_PERSONA = "persona";
        public static final String COLUMN_ID_PERSONA = "id_persona";
        public static final String COLUMN_NOMBRE_PERSONA = "nombre_persona";


        public static final String COLUMN_TRABAJOS_AGENDAS_EVALUADORES = "trabajos_agendas_evaluadores";
        public static final String COLUMN_EVALUADOR = "evaluador";

    }


    public static final String SQL_CREATE_ENTRIES =

            "CREATE TABLE " + DataBaseIS.TABLE_NAME + " (" +
                    DataBaseIS._ID + " INTEGER PRIMARY KEY," +
                    DataBaseIS.COLUMN_ID_TRABAJO_AGENDA + "INTEGER AUTOINCREMENT" +
                    DataBaseIS.COLUMN_FECHA + "TEXT," +
                    DataBaseIS.COLUMN_HORA_INICIO + " TEXT," +
                    DataBaseIS.COLUMN_HORA_FIN + " TEXT," +
                    DataBaseIS.COLUMN_ESPACIO + " TEXT," +
                    DataBaseIS.COLUMN_ID_ESPACIO + "INTEGER AUTOINCREMENT" +
                    DataBaseIS.COLUMN_DESCRIPCION + "TEXT," +
                    DataBaseIS.COLUMN_URL_WEBINAR + " TEXT," +
                    DataBaseIS.COLUMN_CAMPUS + " TEXT," +
                    DataBaseIS.COLUMN_ID_CAMPUS + "INTEGER AUTOINCREMENT" +
                    DataBaseIS.COLUMN_NOMBRE_CAMPUS + "TEXT," +
                    DataBaseIS.COLUMN_TRABAJO + " TEXT," +
                    DataBaseIS.COLUMN_ID_TRABAJO + "INTEGER AUTOINCREMENT" +
                    DataBaseIS.COLUMN_NOMBRE_TRABAJO + "TEXT," +
                    DataBaseIS.COLUMN_SEMILLERO + " TEXT," +
                    DataBaseIS.COLUMN_ID_SEMILLERO + "INTEGER AUTOINCREMENT" +
                    DataBaseIS.COLUMN_NOMBRE_SEMILLERO + "TEXT," +
                    DataBaseIS.COLUMN_TIPO_TRABAJO + " TEXT," +
                    DataBaseIS.COLUMN_ID_TIPO_DE_TRABAJO + "INTEGER AUTOINCREMENT" +
                    DataBaseIS.COLUMN_NOMBRE_TIPO_DE_TRABAJO + "TEXT," +
                    DataBaseIS.COLUMN_SEDE + " TEXT," +
                    DataBaseIS.COLUMN_ID_SEDE + "INTEGER AUTOINCREMENT" +
                    DataBaseIS.COLUMN_NOMBRE_SEDE + "TEXT," +
                    DataBaseIS.COLUMN_INSTITUCION + " TEXT," +
                    DataBaseIS.COLUMN_ID_INSTITUCION + "INTEGER AUTOINCREMENT" +
                    DataBaseIS.COLUMN_NOMBRE_INSTITUCION + "TEXT," +
                    DataBaseIS.COLUMN_TRABAJOS_PONENTES + " TEXT," +
                    DataBaseIS.COLUMN_PONENTE + " TEXT," +
                    DataBaseIS.COLUMN_PERSONA + " TEXT," +
                    DataBaseIS.COLUMN_ID_PERSONA + "INTEGER AUTOINCREMENT" +
                    DataBaseIS.COLUMN_NOMBRE_PERSONA + "TEXT," +
                    DataBaseIS.COLUMN_TRABAJOS_AGENDAS_EVALUADORES + " TEXT," +
                    DataBaseIS.COLUMN_EVALUADOR + " TEXT)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DataBaseIS.TABLE_NAME;


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "inveSoft.db";



    public InveSoftDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
