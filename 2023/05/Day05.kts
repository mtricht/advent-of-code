import java.io.File

data class Mapping(val source: LongRange, val destination: LongRange)

val input = File("input").readLines()
val digitsRegex = "\\d+".toRegex()
val seeds = digitsRegex.findAll(input[0]).map { it.value.toLong() }.toList()
val maps = mutableListOf<MutableList<Mapping>>()
input.subList(2, input.size)
    .filterNot(String::isBlank)
    .forEach {
        if (it.contains("map")) {
            maps.add(mutableListOf())
            return@forEach
        }
        val matches = digitsRegex.findAll(it).map { digit -> digit.value.toLong() }.toList()
        maps.last().add(
            Mapping(
                LongRange(matches[1], matches[1] + matches[2] - 1),
                LongRange(matches[0], matches[0] + matches[2] - 1)
            )
        )
    }

fun getLocation(seeds: List<Long>): Long {
    return seeds.minOf {
        var lastIndex = it
        maps.forEach { map ->
            val result = map.firstOrNull { mapping -> mapping.source.contains(lastIndex) }
            if (result == null) {
                return@forEach
            }
            lastIndex = result.destination.first + (lastIndex - result.source.first)
        }
        lastIndex
    }
}

fun getLocationReversed(seed: Long, mappings: List<List<Mapping>>): Long {
        var lastIndex = seed
        mappings.forEach { map ->
            val result = map.firstOrNull { mapping -> mapping.destination.contains(lastIndex) }
            if (result == null) {
                return@forEach
            }
            lastIndex = result.source.first + (lastIndex - result.destination.first)
        }
        return lastIndex
}

println(getLocation(seeds))

// Brute force starting from the lowest location
val seedsPartTwo = seeds.chunked(2).map { LongRange(it[0], it[0] + it[1] - 1) }
maps.last().sortBy { it.destination.first }
maps.last().first().let { locations ->
    val reversedMaps = maps.reversed()
    locations.destination.toList().forEach {location ->
        val potentialSeed = getLocationReversed(location, reversedMaps)
        val foundSeed = seedsPartTwo.firstOrNull { seed -> seed.contains(potentialSeed) }
        if (foundSeed != null) {
            print(location)
            return@let
        }
    }
}

// Would solve part 2 if I had enough RAM
// println(
//    seeds.chunked(2)
//        .map { LongRange(it[0], it[0] + it[1] - 1) }
//        .minOf { getLocation(it.toList()) }
//)