import com.diacht.ktest.compose.startTestUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.example.helloworld.BuildConfig
import java.net.URL
import kotlin.math.ln
import kotlin.math.pow


fun seed(): String = "ivashchuk-v"

fun labNumber() : Int = BuildConfig.LAB_NUMBER

suspend fun getNumberFromServer(message: String): Int {
    return withContext(Dispatchers.IO) {
        val url = URL("http://diacht.2vsoft.com/api/send-number?message=$message")
        val connection = url.openConnection()
        connection.connect()
        val input = connection.getInputStream()
        val buffer = ByteArray(128)
        val bytesRead = input.read(buffer)
        input.close()
        String(buffer, 0, bytesRead).toInt()
    }
}


// функція
suspend fun serverDataCalculate(strList: List<String>): Double = coroutineScope {

    val deferredValues = strList.map { str ->
        async { getNumberFromServer(str) }
    }

    val values = deferredValues.awaitAll()

    val sum = values.sumOf { x ->
        x.toDouble().pow(3)
    }

    ln(sum)
}


fun main() = runBlocking {

    val data = listOf("strList[0]", "strList[1]", "strList[2]", "strList[3]", "strList[4]", "strList[5]")

    val result = serverDataCalculate(data)

    println("Result = $result")
}
