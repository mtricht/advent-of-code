import java.io.File

enum class Type {
    GEAR, NUMBER
}

data class Part(val value: String, val type: Type, val range: IntRange, val row: Int)

val engineRegex = "\\d+|\\*".toRegex()

val engine = File("input")
    .readLines()
    .flatMapIndexed { index, it ->
        engineRegex.findAll(it).map { match ->
            Part(
                match.value,
                if (match.value == "*") {
                    Type.GEAR
                } else {
                    Type.NUMBER
                },
                match.range,
                index,
            )
        }
    }

var numbers = engine.filter { it.type == Type.NUMBER }

fun findNumbers(gear: Part): List<Part> = numbers.filter {
    it.row >= gear.row - 1 && it.row <= gear.row + 1 && it.range.first <= gear.range.last + 1 && it.range.last >= gear.range.first - 1
}

var gears = engine.filter { it.type == Type.GEAR }
    .map { findNumbers(it) }
    .filter { it.size == 2 }
    .sumOf { it.first().value.toInt() * it.last().value.toInt() }

print(gears)