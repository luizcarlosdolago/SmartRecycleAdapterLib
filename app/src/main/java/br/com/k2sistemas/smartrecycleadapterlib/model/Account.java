package br.com.k2sistemas.smartrecycleadapterlib.model;

import br.com.k2sistemas.library.annotations.BindTextView;
import br.com.k2sistemas.library.annotations.LayoutRow;
import br.com.k2sistemas.smartrecycleadapterlib.R;

/**
 * Created by luizcarlos on 02/01/2018.
 */

@LayoutRow(R.layout.layout_account_row)
public class Account {

    @BindTextView(R.id.tvName)
    String mName;

    @BindTextView(R.id.tvSurname)
    String mSurname;

    public Account() {

    }

    public Account(String name, String surname) {
        mName = name;
        mSurname = surname;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSurname() {
        return mSurname;
    }

    public void setSurname(String surname) {
        mSurname = surname;
    }

}
