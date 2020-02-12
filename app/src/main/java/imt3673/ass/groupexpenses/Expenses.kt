package imt3673.ass.groupexpenses

/**
 * Represents all the expenses of the group of people.
 *
 * TODO implement the functionality of this class
 */
class Expenses (private val expensesMap: MutableMap<String, Pair<Long, String>> = mutableMapOf()){

    // NOTE: Expenses MUST have a default, non-argument constructor.

    // Adds new expense to the expenses list.
    // If the Person does not exist in the expenses,
    //   the person is added to the list, and false is returned.
    // If the Person already exist in the expenses,
    // the new expense amount is added to the person's existing amount and true is returned.
    // There should only be
    // one instance of SingleExpense associated with a single person in the expenses.
    // No duplicates.
    fun add(expense: SingleExpense): Boolean {
        if (!expensesMap.containsKey(expense.person)) {
            expensesMap[expense.person] = Pair(expense.amount, expense.description)
            return false
        }

        expensesMap[expense.person] = Pair(expense.amount + expensesMap[expense.person]!!.first, expense.description)
        return true
    }

    // Replaces the expense for a given person
    // This method is similar to #addExpense, however, instead of adding
    // the claim amount to the existing person, it replaces it instead.
    fun replace(expense: SingleExpense): Boolean {
        val exists = expensesMap.containsKey(expense.person)
        expensesMap[expense.person] = Pair(expense.amount, expense.description)

        return exists
    }

    // Removes an expense association for this person.
    // If the person exists in the expenses, it returns true.
    // Otherwise, it returns false.
    fun remove(person: String): Boolean {
        val exists = expensesMap.containsKey(person)
        expensesMap.remove(person)

        return exists
    }

    // Returns the amount of expenses for a given person.
    // If the person does not exist, the function returns failed result.
    fun amountFor(person: String): Result<Long> {

        if (!expensesMap.containsKey(person)) return Result.failure(Throwable("Person doesn't exist"))
        return Result.success(expensesMap[person]!!.first)
    }

    // Returns the list of all expenses.
    // If no expenses have been added yet, the function returns an empty list.
    fun allExpenses(): List<SingleExpense> {
        var expensesList: List<SingleExpense> = listOf()
        for ((k, v) in expensesMap) {
            expensesList += SingleExpense(k, v.first, v.second)
        }

        return expensesList
    }

    // Makes a deep copy of this expense instance
    fun copy(): Expenses {
        val exp = Expenses()
        allExpenses().forEach {
            exp.add(SingleExpense(it.person, it.amount, it.description))
        }
        return exp
    }
}