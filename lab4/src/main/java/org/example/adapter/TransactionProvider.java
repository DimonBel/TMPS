package org.example.adapter;

import org.example.model.BudgetUserInput;
import org.example.model.ExternalSource;

import java.util.ArrayList;
import java.util.List;

public class TransactionProvider {
    private final ExternalBankApi bankApi;
    private final CsvImporter csvImporter;
    private final FinancialServiceClient financialServiceClient;

    public TransactionProvider() {
        this.bankApi = new ExternalBankApi();
        this.csvImporter = new CsvImporter();
        this.financialServiceClient = new FinancialServiceClient();
    }

    public List<Transaction> collect(BudgetUserInput input) {
        List<Transaction> transactions = new ArrayList<>();

        if (input.getExternalSources().contains(ExternalSource.BANK_API)) {
            bankApi.fetchLatestTransactions("demo-account").stream()
                    .map(BankApiAdapter::new)
                    .forEach(transactions::add);
        }

        if (input.getExternalSources().contains(ExternalSource.CSV_IMPORT)) {
            csvImporter.parse("./demo.csv").stream()
                    .map(CsvTransactionAdapter::new)
                    .forEach(transactions::add);
        }

        if (input.getExternalSources().contains(ExternalSource.FINANCIAL_SERVICE)) {
            financialServiceClient.fetchSpendingForecast("demo-user").stream()
                    .map(FinancialServiceAdapter::new)
                    .forEach(transactions::add);
        }

        return transactions;
    }
}
