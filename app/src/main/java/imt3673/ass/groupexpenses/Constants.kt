package imt3673.ass.groupexpenses

import java.text.DecimalFormatSymbols
import kotlin.math.roundToLong

/**
 * Keep all the package level functions and constants here.
 * Keep public classes in their respective files, one per file, with consistent
 * naming conventions.
 */


/**
 * Sanitize the name text entries following the specification.
 * See wiki and tests for details.
 */
fun sanitizeName(name: String): String {
    return name
            .replace("[^\\s-a-zA-Z]".toRegex(), "")
            .trim()
            .split("\\s+".toRegex())
            .map(){token -> token.toLowerCase().capitalize()}
            .joinToString(separator = " ", limit = 2, truncated = "")
            .split("-")
            .map {token -> token.capitalize()}
            .joinToString(separator = "-")
            .trimEnd()
}

/**
 * Utility method for settlement calculations.
 * Takes the Expenses instance, and produces a list of Transactions.
 */
fun calculateSettlement(expenses: Expenses): List<Transaction> {
    // TODO implement the logic

    // dummy implementation for a simple single case
    // Alice -> 20
    // Bob -> 20
    // Charlie -> 30
    // David -> 50

    // Only one resonable solution:
    // Alice to David -> 10
    // Bob to David -> 10
    return listOf(
        Transaction("Alice", "David", 1000),
        Transaction("Bob", "David", 1000)
    )
}


/**
 * Converts a given Long amount into a formatted String, with
 * two decimal places. Note, the decimal place separator can be
 * dot or comma, subject to the current locale used.
 */
fun convertAmountToString(amount: Long): String {
    val separatorChar: Char = DecimalFormatSymbols.getInstance().decimalSeparator
    return "%.2f".format((amount.toFloat() / 100)).replace('.', separatorChar)
}

/**
 * Convert from String to Amount. If error, return failed result with
 * appropriate error string.
 */
fun convertStringToAmount(value: String): Result<Long> {
    
    val localisedPrice: String = value.replace(',', '.')

    return try {
        Result.success((localisedPrice.toFloat() * 100).roundToLong())
    } catch (e: NumberFormatException) {
        Result.failure(e)
    }
}