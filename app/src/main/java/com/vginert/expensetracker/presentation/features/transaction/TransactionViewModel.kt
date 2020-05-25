package com.vginert.expensetracker.presentation.features.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import com.vginert.expensetracker.domain.core.exceptions.ExceptionHandler
import com.vginert.expensetracker.domain.features.accounts.Account
import com.vginert.expensetracker.domain.features.accounts.use_cases.GetUserAccountsUseCase
import com.vginert.expensetracker.domain.features.categories.Category
import com.vginert.expensetracker.domain.features.categories.Category.Type
import com.vginert.expensetracker.domain.features.categories.use_cases.GetCategoriesByTypeUseCase
import com.vginert.expensetracker.domain.features.transactions.use_cases.CreateTransactionUseCase
import com.vginert.expensetracker.presentation.core.fragments.BaseViewModel
import com.vginert.expensetracker.presentation.core.lifecycle.Event
import com.vginert.expensetracker.presentation.core.models.AccountModel
import com.vginert.expensetracker.presentation.core.models.CategoryModel
import com.vginert.expensetracker.presentation.core.models.toPresentation
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.withContext

class TransactionViewModel(
    private val getUserAccountsUseCase: GetUserAccountsUseCase,
    private val getCategoriesByTypeUseCase: GetCategoriesByTypeUseCase,
    private val createTransactionUseCase: CreateTransactionUseCase,
    exceptionHandler: ExceptionHandler
) : BaseViewModel(exceptionHandler) {

    companion object {
        private val DEFAULT_CATEGORY_TYPE = Type.EXPENSE
    }

    private val _goBack: MutableLiveData<Event<Unit>> = MutableLiveData()
    val goBack: LiveData<Event<Unit>> = _goBack

    private val _userAccounts: MutableLiveData<List<Account>> = MutableLiveData(emptyList())
    val userAccounts: LiveData<List<AccountModel>> = map(_userAccounts) {
        it.map(Account::toPresentation)
    }

    private val _categoryType: MutableLiveData<Type> = MutableLiveData(DEFAULT_CATEGORY_TYPE)
    val categoryType: LiveData<CategoryModel.Type> = map(_categoryType) { it.toPresentation() }

    private val _categories: MutableLiveData<List<Category>> = MutableLiveData(emptyList())
    val categories: LiveData<List<CategoryModel>> = map(_categories) {
        it.map(Category::toPresentation)
    }

    private val _accountValidationError: MutableLiveData<ValidationError?> = MutableLiveData(null)
    val accountValidationError: LiveData<ValidationError?> = _accountValidationError

    private val _categoryValidationError: MutableLiveData<ValidationError?> = MutableLiveData(null)
    val categoryValidationError: LiveData<ValidationError?> = _categoryValidationError

    private val _amountValidationError: MutableLiveData<ValidationError?> = MutableLiveData(null)
    val amountValidationError: LiveData<ValidationError?> = _amountValidationError

    private var selectedUserAccount: Account? = null
    private var selectedCategory: Category? = null
    private var amount: Float? = null

    init {
        fetchUserAccounts()
        fetchCategories()
    }

    fun onSaveClick() {
        val accountId = validateUserAccount()
        val categoryId = validateCategory()
        val amount = validateAmount()
        if (accountId == null || categoryId == null || amount == null) return

        createTransaction(accountId, categoryId, amount)
        _goBack.value = Event(Unit)
    }

    fun onExpenseTypeClick() = updateCategoryType(Type.EXPENSE)

    fun onIncomeTypeClick() = updateCategoryType(Type.INCOME)

    fun onAccountSelected(id: Int) {
        selectedUserAccount = _userAccounts.value?.find { it.id == id }
        validateUserAccount()
    }

    fun onCategorySelected(id: Int) {
        selectedCategory = _categories.value?.find { it.id == id }
        validateCategory()
    }

    fun onAmountChange(amountText: String) {
        amount = amountText.toFloatOrNull()
        validateAmount()
    }

    private fun validateUserAccount(): Int? {
        if (selectedUserAccount?.id == null) {
            _accountValidationError.value = ValidationError.Required
            return null
        }
        _accountValidationError.value = null
        return selectedUserAccount?.id
    }

    private fun validateCategory(): Int? {
        if (selectedCategory?.id == null) {
            _categoryValidationError.value = ValidationError.Required
            return null
        }
        _categoryValidationError.value = null
        return selectedCategory?.id
    }

    private fun validateAmount(): Float? {
        if (amount == null) {
            _amountValidationError.value = ValidationError.Required
            return amount
        }
        _amountValidationError.value = null
        return amount
    }

    private fun createTransaction(accountId: Int, categoryId: Int, amount: Float) = launch(Main) {
        withContext(IO) { createTransactionUseCase(accountId, categoryId, amount) }
    }

    private fun updateCategoryType(categoryType: Type) {
        if (_categoryType.value == categoryType) return
        _categoryType.value = categoryType
        selectedCategory = null
        fetchCategories()
    }

    private fun fetchUserAccounts() = launch(Main) {
        _userAccounts.value = withContext(IO) { getUserAccountsUseCase() }
    }

    private fun fetchCategories() = launch(Main) {
        val categoryType = _categoryType.value ?: DEFAULT_CATEGORY_TYPE
        _categories.value = withContext(IO) { getCategoriesByTypeUseCase(categoryType) }
    }

    sealed class ValidationError {
        object Required : ValidationError()
    }
}
