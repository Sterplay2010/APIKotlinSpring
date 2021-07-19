package com.sterplay2010.api.mongodb.kotlinmongoapi.data

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface GameRepository:MongoRepository<Game,String> {
    fun findGameById(id:ObjectId):Game
}