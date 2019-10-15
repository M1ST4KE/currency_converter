package to.currencyconverter.model

import java.io.StringReader
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xml.sax.InputSource

class XmlDeserializer {
    val currencies: MutableSet<Currency> = mutableSetOf()

    fun parseXml(xmlString: String) {
        // brakuje w liście złotówki
        currencies.add(Currency("nowy polski złoty", "PLN", 1.0, 1))

        val factory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
        val builder: DocumentBuilder
        builder = factory.newDocumentBuilder()
        val doc = builder.parse(InputSource(StringReader(xmlString)))
        val nodes = doc.getElementsByTagName("pozycja")

        for (i in 0 until nodes.length) {
            val node = nodes.item(i)
            if (node.nodeType == Node.ELEMENT_NODE) {
                val element = node as Element
                val name = element.getElementsByTagName("nazwa_waluty").item(0)
                    .textContent
                val shortcut = element.getElementsByTagName("kod_waluty").item(0)
                    .textContent
                val exchangeRate = element.getElementsByTagName("kurs_sredni").item(0)
                    .textContent
                    .replace(",", ".")
                    .toDouble()
                val convertRatio = element.getElementsByTagName("przelicznik").item(0)
                    .textContent
                    .toInt()
                currencies.add(Currency(name, shortcut, exchangeRate, convertRatio))
            }
        }
    }
}
