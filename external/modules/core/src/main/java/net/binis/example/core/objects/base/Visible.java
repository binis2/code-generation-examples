package net.binis.example.core.objects.base;

public interface Visible {

    boolean isVisible();

    default boolean isHidden() {
        return !isVisible();
    }

}
