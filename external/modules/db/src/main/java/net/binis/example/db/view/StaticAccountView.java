package net.binis.example.db.view;

import net.binis.example.core.objects.User;
import net.binis.example.core.objects.types.AccountType;

public interface StaticAccountView {

    String getAccountNumber();
    double getBalance();
    boolean getActive();
    AccountType getType();

    User getUser();

}
