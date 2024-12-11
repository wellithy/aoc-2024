
fun main() {
    fun MutableMap<Long, Long>.update(key: Long, n: Long) =
        compute(key) { _, v -> n + (v ?: 0) }

    fun blink(numbers: Map<Long, Long>) = mutableMapOf<Long, Long>().apply {
        numbers.forEach { (x, n) ->
            val str = x.toString()
            when {
                x == 0L -> update(1L, n)
                str.length % 2 == 0 -> {
                    update(str.substring(0, str.length / 2).toLong(), n)
                    update(str.substring(str.length / 2).toLong(), n)
                }
                else -> update(x * 2024, n)
            }
        }
    }

    fun blink(numbers: List<Long>, count: Int): Long {
        var map = mutableMapOf<Long, Long>().apply {
            numbers.forEach { put(it, 1L) }
        }
        repeat(count) {
            map = blink(map)
        }
        return map.values.sum().also(::println)
    }

    require(blink(listOf(125L, 17L), 25) == 55312L)
    val input = input().first().numbers().map(String::toLong)
    require(listOf(25, 75).map { blink(input, it) } == output().map(String::toLong))
}
