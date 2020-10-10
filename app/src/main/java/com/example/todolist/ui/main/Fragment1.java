package com.example.todolist.ui.main;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Adapter.ToDoAdapter;
import com.example.todolist.DatabaseHelper;
import com.example.todolist.DialogCloseListener;
import com.example.todolist.Model.ToDoModel;
import com.example.todolist.R;
import com.example.todolist.RecyclerItemTouchHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.List;

public class Fragment1 extends Fragment implements DialogCloseListener {



    public Fragment1(){



    }


    public DatabaseHelper db;

    public RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;
    private FloatingActionButton fab;

    private List<ToDoModel> taskList;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

      View v =inflater.inflate(R.layout.fragment1_layout, container, false);

        tasksRecyclerView.setLayoutManager(new LinearLayoutManager( getActivity()));
        tasksRecyclerView = v.findViewById(R.id.tasksRecyclerView);


      //  tasksAdapter = new ToDoAdapter(db,
         //       Fragment1.this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        fab = v.findViewById(R.id.fab);

        taskList = db.getAllTasks();
        Collections.reverse(taskList);

        tasksAdapter.setTasks(taskList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });




        return v;

    }


    @Override
    public void handleDialogClose(DialogInterface dialog){
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);
        tasksAdapter.notifyDataSetChanged();
    }

}


