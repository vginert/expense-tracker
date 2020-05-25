package com.vginert.expensetracker.presentation.features.transaction

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.tiper.MaterialSpinner
import com.vginert.expensetracker.R
import com.vginert.expensetracker.presentation.core.fragments.BaseFragment
import com.vginert.expensetracker.presentation.core.lifecycle.observe
import com.vginert.expensetracker.presentation.core.lifecycle.observeEvent
import com.vginert.expensetracker.presentation.core.models.AccountModel
import com.vginert.expensetracker.presentation.core.models.CategoryModel
import com.vginert.expensetracker.presentation.features.transaction.TransactionViewModel.ValidationError
import kotlinx.android.synthetic.main.transaction_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class TransactionFragment : BaseFragment() {

    override val viewModel: TransactionViewModel by viewModel()
    override val layout: Int = R.layout.transaction_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        observeEvent(viewModel.goBack) { navigateBack() }
        observe(viewModel.userAccounts) { renderUserAccounts(it) }
        observe(viewModel.categoryType) { renderCategoryType(it) }
        observe(viewModel.categories) { renderCategories(it) }
        observe(viewModel.accountValidationError) { renderUserAccountValidationError(it) }
        observe(viewModel.categoryValidationError) { renderCategoryValidationError(it) }
        observe(viewModel.amountValidationError) { renderAmountValidationError(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expenseRadioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked.not()) return@setOnCheckedChangeListener
            viewModel.onExpenseTypeClick()
        }
        incomeRadioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked.not()) return@setOnCheckedChangeListener
            viewModel.onIncomeTypeClick()
        }
        amountEditText.doOnTextChanged { text, _, _, _ -> viewModel.onAmountChange(text.toString()) }
        amountEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) viewModel.onSaveClick().run { true }
            false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.create_transaction, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.save_transaction_menu_item -> viewModel.onSaveClick().run { return true }
        else -> super.onOptionsItemSelected(item)
    }

    private fun renderUserAccounts(accounts: List<AccountModel>) {
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, accounts)
        accountsSpinner.adapter = adapter
        accountsSpinner.onItemClickListener = object : MaterialSpinner.OnItemClickListener {
            override fun onItemClick(
                parent: MaterialSpinner,
                view: View?,
                position: Int,
                id: Long
            ) {
                val account = adapter.getItem(position)
                account?.let { viewModel.onAccountSelected(account.id) }
            }
        }
    }

    private fun renderCategoryType(categoryType: CategoryModel.Type) = when (categoryType) {
        CategoryModel.Type.EXPENSE -> expenseRadioButton.isChecked = true
        CategoryModel.Type.INCOME -> incomeRadioButton.isChecked = true
    }.also {
        categoriesSpinner.editText?.setText("")
    }

    private fun renderCategories(categories: List<CategoryModel>) {
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, categories)
        categoriesSpinner.adapter = adapter
        categoriesSpinner.onItemClickListener = object : MaterialSpinner.OnItemClickListener {
            override fun onItemClick(
                parent: MaterialSpinner,
                view: View?,
                position: Int,
                id: Long
            ) {
                val category = adapter.getItem(position)
                category?.let { viewModel.onCategorySelected(category.id) }
            }
        }
    }

    private fun renderUserAccountValidationError(validationError: ValidationError?) {
        accountsSpinner.error = when (validationError) {
            ValidationError.Required -> getString(R.string.transaction_account_required_error)
            else -> null
        }
    }

    private fun renderCategoryValidationError(validationError: ValidationError?) {
        categoriesSpinner.error = when (validationError) {
            ValidationError.Required -> getString(R.string.transaction_category_required_error)
            else -> null
        }
    }

    private fun renderAmountValidationError(validationError: ValidationError?) {
        amountTextInputLayout.error = when (validationError) {
            ValidationError.Required -> getString(R.string.transaction_amount_required_error)
            else -> null
        }
    }

    private fun navigateBack() {
        findNavController().navigateUp()
    }
}
