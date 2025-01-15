package com.interactive.appgames.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 15/01/2025
 */
@Database(entities = [GameEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase (){
    abstract fun getGameDao(): GameDao
}