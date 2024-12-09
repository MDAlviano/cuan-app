package com.alviano.cuan.beta.model

sealed class TransactionItemModel {
    data class DateHeader(val date: String): TransactionItemModel()
    data class Transaction(val transactionModel: TransactionModel): TransactionItemModel()
}