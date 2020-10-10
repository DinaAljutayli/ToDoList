package com.example.todolist.ui.main;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.DatabaseHelper;
import com.example.todolist.GroceryAdapter;
import com.example.todolist.GroceryContract;
import com.example.todolist.R;

public class Fragment2 extends Fragment {


    public Fragment2(){}

    TextView mTextViewAmount;
    Button buttonIncrease;
    Button buttonDecrease;
    Button buttonAdd;
    EditText mEditTextName;
    SQLiteDatabase mDatabase;
    GroceryAdapter mAdapter;
    RecyclerView recyclerView;
    int mAmount=0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment2_layout, container, false);

        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        mDatabase = dbHelper.getWritableDatabase();
        mAdapter = new GroceryAdapter(getActivity(), getAllItems());
        recyclerView = v.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);


        mEditTextName = v.findViewById(R.id.edittext_name);
        mTextViewAmount = v.findViewById(R.id.textview_amount);
        Button buttonIncrease = v.findViewById(R.id.button_increase);
        Button buttonDecrease = v.findViewById(R.id.button_decrease);
        Button buttonAdd = v.findViewById(R.id.button_add);
        buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increase();
            }
        });
        buttonDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrease();
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        onDelete();


        return v;
    }
    private void increase() {
        mAmount++;
        mTextViewAmount.setText(String.valueOf(mAmount));
    }
    private void decrease() {
        if (mAmount > 0) {
            mAmount--;
            mTextViewAmount.setText(String.valueOf(mAmount));
        }
    }
    private void addItem() {
        if (mEditTextName.getText().toString().trim().length() == 0 || mAmount == 0) {
            return;
        }
        String name = mEditTextName.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(GroceryContract.GroceryEntry.COLUMN_NAME, name);
        cv.put(GroceryContract.GroceryEntry.COLUMN_AMOUNT, mAmount);
        mDatabase.insert(GroceryContract.GroceryEntry.TABLE_NAME2, null, cv);
        mAdapter.swapCursor(getAllItems());
        mEditTextName.getText().clear();
    }

    private Cursor getAllItems() {
        return mDatabase.query(
                GroceryContract.GroceryEntry.TABLE_NAME2,
                null,
                null,
                null,
                null,
                null,
                GroceryContract.GroceryEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }

    public void onDelete(){
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
               mDatabase.delete(GroceryContract.GroceryEntry.TABLE_NAME2,
                       GroceryContract.GroceryEntry._ID + "=" + (int)viewHolder.itemView.getTag(),null );

                mAdapter.swapCursor(getAllItems());
            }
        }).attachToRecyclerView(recyclerView);
    }

    private void removeItem(long id) {
        mDatabase.delete(GroceryContract.GroceryEntry.TABLE_NAME2,
                GroceryContract.GroceryEntry._ID + "=" + id, null);
        mAdapter.swapCursor(getAllItems());
    }




}
