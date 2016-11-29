package com.supriyalahade.expense_manager.Model;

/**
 * Created by BIDWAI on 15-09-2016.
 */
public class Expense_Details {

    double income,expense,savings;

    public Expense_Details() {
    }

    public Expense_Details(double income, double expense, double savings) {
        this.income = income;
        this.expense = expense;
        this.savings = savings;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }
}
