import org.jsoup.Jsoup
import java.io.File

val inputFolder = "./input"
val outputFolder = "./output"

fun main(args: Array<String>) {
    val inputFileFolder = File(inputFolder)

    if (!inputFileFolder.exists()) {
        println("没有输入文件夹")
        return
    }

    val outputFileFolder = File(outputFolder)
    if (!outputFileFolder.exists()) {
        outputFileFolder.mkdirs()
    }

    val xmlFiles = inputFileFolder.listFiles { dir, name ->
        name.endsWith(".xml")
    }
    xmlFiles.forEach { transformFile(it) }
}

fun transformFile(xmlFile: File) {
    val document = Jsoup.parse(xmlFile, Charsets.UTF_8.displayName())
    val resourceElement = document.getElementsByTag("resources").firstOrNull()

    if (resourceElement == null) {
        println("资源文件没有 resource 节点")
        return
    }

    val propertiesFile = File(outputFolder, xmlFile.nameWithoutExtension + ".properties")
    if (propertiesFile.exists()) {
        propertiesFile.delete()
    }
    propertiesFile.createNewFile()

    val writer = propertiesFile.writer()
    for (element in resourceElement.allElements) {
        writer
                .append(element.attr("name"))
                .append("=")
                .appendln(element.text())
        writer.flush()
    }
    writer.close()
}
