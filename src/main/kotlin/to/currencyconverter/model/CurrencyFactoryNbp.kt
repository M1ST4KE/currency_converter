package to.currencyconverter.model

import to.currencyconverter.Client

class CurrencyFactoryNbp : CurrenciesFactory {
    override fun generateSet(): HashSet<Currency> {
        val client = Client()
        val deserializer = XmlDeserializer()

        client.request()

        deserializer.parseXml(client.currenciesXml)

        return deserializer.currencies.toHashSet()
    }
}
