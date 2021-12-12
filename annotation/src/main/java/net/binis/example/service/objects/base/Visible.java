package net.binis.example.service.objects.base;

public interface Visible {

    boolean isVisible();

    default boolean isHidden() {
        return !isVisible();
    }

}
