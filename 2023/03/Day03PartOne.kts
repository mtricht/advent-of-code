import java.io.File

val engine = File("input")
    .readLines()
    .map(String::toCharArray)

var count = 0

fun isSpecialChar(char: Char?) = char != null && !(char.isDigit() || char == '.')

var buffer = ""
for ((row, values) in engine.withIndex()) {
    var isPart = false
    for ((column, value) in values.withIndex()) {
        if (value == '.' || !value.isDigit()) {
            if (isPart) {
                count += buffer.toInt()
            }
            buffer = ""
            isPart = false
            continue
        }
        buffer += value
        isPart = isPart || listOf(row - 1, row, row + 1).any { rowToCheck ->
            listOf(column - 1, column, column + 1).any { columnToCheck ->
                isSpecialChar(engine.getOrNull(rowToCheck)?.getOrNull(columnToCheck))
            }
        }
    }
    if (isPart) {
        count += buffer.toInt()
        buffer = ""
    }
}

print(count)