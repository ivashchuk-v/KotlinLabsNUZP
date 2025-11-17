import com.diacht.ktest.compose.startTestUi
import org.example.helloworld.BuildConfig
import kotlin.math.abs
import kotlin.math.cbrt
import kotlin.math.sqrt


fun seed(): String = "ivashchuk-v"

fun labNumber() : Int = BuildConfig.LAB_NUMBER

fun iCalculate(
    x0: Int = -92,
    x1: Int = -43,
    x2: Int = -14,
    x3: Int = 114,
    x4: Int = 67
): Double {
    val s = abs(x0) + abs(x1) + abs(x2) + abs(x3) + abs(x4)
    return cbrt(s.toDouble())
}

fun dCalculate(
    x0: Double = 16.32,
    x1: Double = -28.21,
    x2: Double = -40.12,
    x3: Double = 57.23
): Double {
    return sqrt(x0 * x1 * x2 * x3)
}

fun strCalculate(x0: String, x1: String): Int {
    val n = x0.length
    val half = n / 2
    var res = 0

    for (i in 0 until n) {
        val a = x0[i]
        if (a == 'T' || a == 'C') {
            if (a != x1[i]) {
                res += if (i >= half) 2 else 1
            }
        }
    }
    return res
}


fun main(args: Array<String>) {
    println("Лабораторна робота №${labNumber()} користувача ${seed()}")

    println("iCalculate() = ${iCalculate()}")
    println("dCalculate() = ${dCalculate()}")
    println("strCalculate() = ${strCalculate("ATCGTC", "ATGGTA")}")

    startTestUi(seed(), labNumber())

}
