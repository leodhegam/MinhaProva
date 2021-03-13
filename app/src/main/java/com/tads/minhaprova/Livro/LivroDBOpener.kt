package com.tads.minhaprova.Livro

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import com.tads.minhaprova.databinding.ActivityAcao2Binding

class LivroDBOpener(context: Context) : SQLiteOpenHelper(
    context,
    LivroContrato.DATABASE_NAME, null,
    LivroContrato.DATA_BASE_VERSION
) {

    val TAG = "sql"
    val SQL_CREATE_TABLE =
        "CREATE TABLE ${LivroContrato.LivroEntry.TABLE_NAME}" +
                "( ${BaseColumns._ID} INTEGER PRIMARY KEY, " +
                " ${LivroContrato.LivroEntry.NOME} TEXT," +
                " ${LivroContrato.LivroEntry.AUTOR} TEXT," +
                " ${LivroContrato.LivroEntry.NOTA} REAL," +
                " ${LivroContrato.LivroEntry.ANO} INTEGER" +
                ")"
    val SQL_DROP_TABLE =
        "DROP TABLE ${LivroContrato.LivroEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase?) {
        Log.i(TAG, "Banco de dados Criado")
        if (db != null) {
            db.execSQL(SQL_CREATE_TABLE)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db.execSQL(SQL_DROP_TABLE)
            db.execSQL(SQL_CREATE_TABLE)
        }
    }

    fun insert(livro: Livro) {
        var banco: SQLiteDatabase = writableDatabase
        try {

            var values = ContentValues()
            values.put(LivroContrato.LivroEntry.NOME, livro.nome)
            values.put(LivroContrato.LivroEntry.AUTOR, livro.autor)
            values.put(LivroContrato.LivroEntry.NOTA, livro.nota)
            values.put(LivroContrato.LivroEntry.ANO, livro.ano)

            banco.insert(LivroContrato.LivroEntry.TABLE_NAME, null, values)

        } finally {
            banco.close()
        }
    }

    fun update(livro: Livro) {
        var banco: SQLiteDatabase = writableDatabase
        try {

            var values = ContentValues()
            values.put(LivroContrato.LivroEntry.NOME, livro.nome)
            values.put(LivroContrato.LivroEntry.AUTOR, livro.autor)
            values.put(LivroContrato.LivroEntry.NOTA, livro.nota)
            values.put(LivroContrato.LivroEntry.ANO, livro.ano)

            var selection = "${BaseColumns._ID} = ?"
            var whereArgs = arrayOf("${livro.id}")

            banco.update(LivroContrato.LivroEntry.TABLE_NAME, values, selection, whereArgs)

        } finally {
            banco.close()
        }
    }
// pesquisa os livros pelo id
    fun findById(id: Int): Livro {
        var banco: SQLiteDatabase = readableDatabase
        try {

            var selection = "${BaseColumns._ID} = ?"
            var whereArgs = arrayOf("${id}")
            val cursor: Cursor = banco.query(
                LivroContrato.LivroEntry.TABLE_NAME,
                null,
                selection,
                whereArgs,
                null,
                null,
                null,
                null
            )

            cursor.moveToNext()

            var livro = Livro()
            livro.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            livro.nome = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.NOME))
            livro.autor = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.AUTOR))
            livro.nota = cursor.getFloat(cursor.getColumnIndex(LivroContrato.LivroEntry.NOTA))
            livro.ano = cursor.getInt(cursor.getColumnIndex(LivroContrato.LivroEntry.ANO))

            return livro

        } finally {
            banco.close()
        }
    }

    fun findAll(): ArrayList<Livro> {
        var banco: SQLiteDatabase = readableDatabase
        try {

            val cursor: Cursor = banco.query(
                LivroContrato.LivroEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )

            var lista = ArrayList<Livro>()

            while (cursor.moveToNext()) {
                var livro = Livro()

                livro.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
                livro.nome = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.NOME))
                livro.autor =
                    cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.AUTOR))
                livro.ano = cursor.getInt(cursor.getColumnIndex(LivroContrato.LivroEntry.ANO))
                livro.nota = cursor.getFloat(cursor.getColumnIndex(LivroContrato.LivroEntry.NOTA))

                lista.add(livro)
            }

            return lista

        } finally {
            banco.close()
        }

    }
}