package model

class Currency(
    val name: String,
    val shortcut: String,
    val exchangeRate: Double,
    val convertRatio: Int
) {
    private constructor(builder: Builder) : this(
        builder.name,
        builder.shortcut,
        builder.exchangeRate,
        builder.convertRatio
    )

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {
        var name: String = ""
        var shortcut: String = ""
        var exchangeRate: Double = 0.0
        var convertRatio: Int = 0

        fun build() = Currency(this)
    }

    override fun equals(other: Any?): Boolean =
        if (other != null && other is Currency)
            other.shortcut == this.shortcut
        else
            false

    override fun hashCode(): Int =
        this.shortcut.hashCode()
}
