import java.io.File
import kotlin.math.pow

data class Card(val id: Int, val winners: Set<Int>, val numbers: Set<Int>)

val spacesRegex = "\\s+".toRegex()
val cardRegex = "Card\\s+(\\d+):\\s+([\\d+\\s+?]+)\\|\\s+([\\d+\\s+?]+)".toRegex()

val cards = File("input")
    .readLines()
    .map {
        val matches = cardRegex.find(it.replace(spacesRegex, " "))!!.groupValues
        Card(
            matches[1].toInt(),
            matches[2].trim().split(" ").map(String::toInt).toSet(),
            matches[3].trim().split(" ").map(String::toInt).toSet()
        )
    }
val points = cards
    .sumOf {
        val wins = it.winners.intersect(it.numbers).size
        if (wins >= 2) {
            2.toDouble().pow(wins.toDouble() - 1).toInt()
        } else {
            wins
        }
    }

println(points)

val copies = mutableListOf<Card>()
fun addWinners(card: Card) {
    val wins = card.winners.intersect(card.numbers)
    if (wins.isEmpty()) {
        return
    }
    for (i in 1..wins.size) {
        val cardToAdd = cards[card.id + i - 1]
        copies.add(cardToAdd)
        addWinners(cardToAdd)
    }
}
cards.forEach{ card ->
    addWinners(card)
}
println((copies + cards).size)