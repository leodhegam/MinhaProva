package com.tads.minhaprova.Livro

import android.provider.BaseColumns

object LivroContrato {

    const val DATABASE_NAME = "livrodb.sqlite"
    const val DATA_BASE_VERSION = 1

    object LivroEntry : BaseColumns {
        const val TABLE_NAME = "livro"
        const val NOME = "nome"
        const val AUTOR = "autor"
        const val NOTA = "nota"
        const val ANO = "ano"
    }
}