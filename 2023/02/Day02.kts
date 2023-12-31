import java.io.File
import kotlin.math.max

data class Game(val id: Int, val rounds: List<Round>)
data class Round(val red: Int, val green: Int, val blue: Int)

val gameRegex = "Game (\\d+):".toRegex()
val redRegex = "(\\d+) red".toRegex()
val greenRegex = "(\\d+) green".toRegex()
val blueRegex = "(\\d+) blue".toRegex()

fun toGame(string: String): Game {
    val id = gameRegex.find(string)!!.groupValues[1]
    val rounds = string.replace("Game $id: ", "")
        .split(";")
        .map {
            Round(
                redRegex.find(it)?.groupValues?.get(1)?.toInt() ?: 0,
                greenRegex.find(it)?.groupValues?.get(1)?.toInt() ?: 0,
                blueRegex.find(it)?.groupValues?.get(1)?.toInt() ?: 0,
            )
        }
    return Game(id.toInt(), rounds)
}

val games = File("input")
    .readLines()
    .map { toGame(it) }

val partOne = games
    .filterNot { it.rounds.any { rounds -> rounds.red > 12 || rounds.green > 13 || rounds.blue > 14 } }
    .sumOf { it.id }
val partTwo = games
    .sumOf {
        var maxRed = 1
        var maxGreen = 1
        var maxBlue = 1
        it.rounds.map { round ->
            maxRed = max(maxRed, round.red)
            maxGreen = max(maxGreen, round.green)
            maxBlue = max(maxBlue, round.blue)
        }
        maxRed * maxGreen * maxBlue
    }
println("Part one: $partOne")
println("Part two: $partTwo")

