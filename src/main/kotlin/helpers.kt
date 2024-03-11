import java.io.File

fun readFileAsText(fileName: String): String = File(fileName).readText(Charsets.UTF_8)
