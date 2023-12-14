import java.io.File

fun String.getFirstAndLastDigits() = first().digitToInt() to last().digitToInt()
fun getDigits(string: String): Pair<Int, Int> = string.filter(Char::isDigit).getFirstAndLastDigits()

val sum = File("input")
    .readText()
    .replace("one", "o1ne")
    .replace("two", "t2wo")
    .replace("three", "t3hree")
    .replace("four", "f4our")
    .replace("five", "f5ive")
    .replace("six", "s6ix")
    .replace("seven", "s7even")
    .replace("eight", "e8ight")
    .replace("nine", "n9ine")
    .split("\r\n")
    .filterNot(String::isBlank)
    .map { getDigits(it) }
    .sumOf {
        it.first * 10 + it.second
    }
println("Sum is $sum")