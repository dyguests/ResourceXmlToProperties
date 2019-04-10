import org.jsoup.Jsoup
import java.io.File

const val EXTENSION_XML = ".xml"

val inputFolder = "./input"
val outputFolder = "./output"

fun main(args: Array<String>) {
    println("ResourceXmlToProperties start")
    //如果参数带文件，就直接 转换对应文件
    val argXml = args.firstOrNull { it.endsWith(EXTENSION_XML) }
    if (argXml != null) {
        val argXmlFile = File(argXml)
        if (argXmlFile.exists()) {
            transformFile(argXmlFile)
            return
        }
    }

    //否则就转换 input 目录下的xml文件

    val inputFileFolder = File(inputFolder)

    if (!inputFileFolder.exists()) {
        println("没有输入文件夹")
        return
    }

    val outputFileFolder = File(outputFolder)
    if (!outputFileFolder.exists()) {
        outputFileFolder.mkdirs()
    }

    val xmlFiles = inputFileFolder.listFiles { _, name ->
        name.endsWith(EXTENSION_XML)
    }
    xmlFiles.forEach { transformFile(it) }
    println("ResourceXmlToProperties end")
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
    for (element in resourceElement.children()) {
        writer
                .append(element.attr("name"))
                .append("=")
                .appendln(element.text())
        writer.flush()
    }
    writer.close()
}
