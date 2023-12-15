import java.io.File

val verificationNumber = File("input")
    .readText()
    .split(",")
    .sumOf {
        var currentValue = 0
        it.forEach { character ->
            currentValue += character.code
            currentValue *= 17
            currentValue = currentValue.rem(256)
        }
        currentValue
    }
println(verificationNumber)