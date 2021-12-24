package net.binis.example.service.view;

import net.binis.example.service.objects.types.AccountType;

public interface AccountView {

    String getAccountNumber();
    double getBalance();
    boolean getActive();
    AccountType getType();
    String getUserFirstName();

}
