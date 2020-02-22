package imt3673.ass.groupexpenses

import java.text.DecimalFormatSymbols
import kotlin.math.abs
import kotlin.math.roundToLong
import kotlin.math.min

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
    if (expenses.allExpenses().size in (0..1)) return listOf()

    val transactionList = mutableListOf<Transaction>()
    var sortedExpenses = expenses.toSortedBalanceList()

    sortedExpenses = sortedExpenses.filter { it.second != 0L }.toMutableList()

    while (sortedExpenses.size > 1) {
        val amount = min(abs(sortedExpenses[0].second), abs(sortedExpenses.last().second))

        if (sortedExpenses[0].second > sortedExpenses.last().second ) {
            transactionList.add(Transaction(sortedExpenses.last().first, sortedExpenses[0].first, amount))

            sortedExpenses[0] = Pair(sortedExpenses[0].first, sortedExpenses[0].second - amount)
            sortedExpenses[sortedExpenses.lastIndex] = Pair(sortedExpenses.last().first, sortedExpenses.last().second + amount)
        } else {
            transactionList.add(Transaction(sortedExpenses[0].first, sortedExpenses.last().first, amount))

            sortedExpenses[0] = Pair(sortedExpenses[0].first, sortedExpenses[0].second + amount)
            sortedExpenses[sortedExpenses.lastIndex] = Pair(sortedExpenses.last().first, sortedExpenses.last().second - amount)
        }

        sortedExpenses = sortedExpenses.filter { it.second != 0L }.toMutableList()
    }

    return transactionList.toList()
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