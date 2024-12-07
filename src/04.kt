private typealias Point = Pair<Int, Int>
private typealias Trail = List<Point>

fun main() {

    fun Trail(direction: Point, length: Int): Trail =
        (0..<length).map { it * direction.first to it * direction.second }

    operator fun Point.plus(other: Point): Point =
        first + other.first to second + other.second

    operator fun List<String>.get(point: Point): Char? = getOrNull(point.first)?.getOrNull(point.second)
    operator fun List<String>.get(point: Point, trail: Trail): String? =
        trail.map { get(point + it) ?: return@get null }.joinToString("")

    fun List<String>.search(words: Set<String>, possible: List<Trail>): Int {
        val firsts = words.map(String::first).toSet()
        return mapIndexed { row, line ->
            line.mapIndexed { column, c ->
                val point = Point(row, column)
                if (c !in firsts) 0
                else possible
                    .mapNotNull { get(point, it) }
                    .count(words::contains)
            }.sum()
        }.sum()
    }

    fun part1(input: List<String>): Int {
        val word = "XMAS"
        val directions = listOf(0 to 1, 1 to 1, 1 to 0, 1 to -1)
        val trails = directions.map { Trail(it, word.length) }
        return input.search(setOf(word, word.reversed()), trails)
    }

    fun part2(input: List<String>): Int {
        val word = "MAS"
        val rev = word.reversed()
        val words = setOf(word + word, word + rev, rev + word, rev + rev)
        val trail = listOf(0 to 0, 1 to 1, 2 to 2, 0 to 2, 1 to 1, 2 to 0)
        return input.search(words, listOf(trail))
    }

    val input = input()
    val one = part1(input).also(::println)
    val two = part2(input).also(::println)
    require(listOf(one, two) == output().map(String::toInt))
}
