package com.sterplay2010.api.mongodb.kotlinmongoapi

import com.sterplay2010.api.mongodb.kotlinmongoapi.data.Game
import com.sterplay2010.api.mongodb.kotlinmongoapi.data.GameRepository
import com.sterplay2010.api.mongodb.kotlinmongoapi.request.GameRequest
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/game")
//@CrossOrigin(origins = ["*"],maxAge = 3600,allowCredentials = "true")
class GameController(private val gameRepository: GameRepository) {

    @GetMapping
    fun getAllGames():ResponseEntity<List<Game>>{
        val games = gameRepository.findAll()
        return ResponseEntity.ok(games)
    }

    @GetMapping("/{id}")
    fun getOneGame(@PathVariable("id") id:String):ResponseEntity<Game>{
        val game = gameRepository.findGameById(ObjectId(id))
        return ResponseEntity.ok(game)
    }

    @PostMapping()
    fun createGame(@RequestBody request:GameRequest):ResponseEntity<Game>{
        val game = gameRepository.save(Game(
            name = request.name, specifications = request.specifications
        ))
        return ResponseEntity(game,HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateGame(@RequestBody request:GameRequest, @PathVariable("id") id:String):ResponseEntity<Game>{
        val game = gameRepository.findGameById(ObjectId(id))
        val updateGame = gameRepository.save(Game(
            id =game.id, name = request.name, specifications = request.specifications
            ))
        return ResponseEntity.ok(updateGame)
    }

    @DeleteMapping("/{id}")
    fun deleteGame(@PathVariable("id") id:String):ResponseEntity<Unit>{
        gameRepository.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}