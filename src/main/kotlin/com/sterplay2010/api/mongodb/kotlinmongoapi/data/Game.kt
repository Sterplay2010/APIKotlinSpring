package com.sterplay2010.api.mongodb.kotlinmongoapi.data

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Game(
    @Id
    val id: String = ObjectId.get().toHexString(),
    val name:String,
    val specifications: Map<String,String>
)