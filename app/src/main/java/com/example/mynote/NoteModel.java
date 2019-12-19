package com.example.mynote;

import android.graphics.drawable.Drawable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class NoteModel {

    private int id;
    private Drawable itemImage;
    private String  itemText;
    private String itemDescriptionText;
    private Boolean   isLongPressed , Done ,isSwitch;
    private LinearLayout menuLinearLayout;
    private ImageButton deleteButton,editButton;



    public String getItemDescriptionText() {
        return itemDescriptionText;
    }

    public void setItemDescriptionText(String itemDescriptionText) {
        this.itemDescriptionText = itemDescriptionText;
    }

    public ImageButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(ImageButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public ImageButton getEditButton() {
        return editButton;
    }

    public void setEditButton(ImageButton editButton) {
        this.editButton = editButton;
    }

    public LinearLayout getMenuLinearLayout() {
        return menuLinearLayout;
    }

    public void setMenuLinearLayout(LinearLayout menuLinearLayout) {
        this.menuLinearLayout = menuLinearLayout;
    }

    public Drawable getItemImage() {
        return itemImage;
    }

    public void setItemImage(Drawable itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    public Boolean getItemSwitch() {
        return isSwitch;
    }

    public void setItemSwitch(Boolean isSwitch) {
        this.isSwitch = isSwitch;
    }

    public Boolean getLongPressed() {
        return isLongPressed;
    }

    public void setLongPressed(Boolean longPressed) {
        isLongPressed = longPressed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
