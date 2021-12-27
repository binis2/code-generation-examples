package net.binis.example.db.view;

import net.binis.example.core.objects.types.AccountType;
import org.springframework.beans.factory.annotation.Value;

public interface DynamicAccountView {

    String getAccountNumber();
    double getBalance();
    boolean getActive();
    AccountType getType();

    @Value("#{target.user.getFirstName() + ' ' + target.user.getLastName()}")
    String getCustomerName();

}
