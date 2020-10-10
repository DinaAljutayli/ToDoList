package com.example.todolist.ui.main;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.todolist.R;

public class Fragment2 extends Fragment {


    public Fragment2(){}

    TextView mTextViewAmount;
    Button buttonIncrease;
    Button buttonDecrease;
    int mAmount=0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v= inflater.inflate(R.layout.fragment2_layout,container,false);

       buttonIncrease = v.findViewById(R.id.button_increase);
       buttonDecrease = v.findViewById(R.id.button_decrease);
       mTextViewAmount = v.findViewById(R.id.textview_amount);


       buttonIncrease.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               mAmount++;
               mTextViewAmount.setText(String.valueOf(mAmount));

           }
       });

       buttonDecrease.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(mAmount>0){
                   mAmount--;
                   mTextViewAmount.setText(String.valueOf(mAmount));
               }
           }
       });

       /* private void addItem() {
            if (mEditTextName.getText().toString().trim().length() == 0 || mAmount == 0) {
                return;
            }
            String name = mEditTextName.getText().toString();
            ContentValues cv = new ContentValues();
            cv.put(GroceryContract.GroceryEntry.COLUMN_NAME, name);
            cv.put(GroceryContract.GroceryEntry.COLUMN_AMOUNT, mAmount);
            mDatabase.insert(GroceryContract.GroceryEntry.TABLE_NAME, null, cv);
            mAdapter.swapCursor(getAllItems());
            mEditTextName.getText().clear();
        }

*/
    return v;

    }
}
