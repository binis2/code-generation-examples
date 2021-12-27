package net.binis.example.db.view;

import net.binis.example.core.objects.types.AccountType;

public interface StaticAccountView2 {

    double getBalance();
    boolean getActive();
    AccountType getType();

    String getUserFirstName();
    String getUserLastName();

}
