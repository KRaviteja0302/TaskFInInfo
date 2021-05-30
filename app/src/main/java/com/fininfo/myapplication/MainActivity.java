package com.fininfo.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
TaskAdapter taskAdapter;
    ArrayList<Pojo> arrayList;
    EditText email,number;
    RecyclerView recyclerView;
    Button submit;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fininfo);
        email=findViewById(R.id.et_email);
        number=findViewById(R.id.et_number);
        recyclerView=findViewById(R.id.rv_task);
        submit=findViewById(R.id.btn_submit);

        arrayList=new ArrayList<>();


        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        taskAdapter=new TaskAdapter(arrayList);

        recyclerView.setAdapter(taskAdapter);
        submit.setOnClickListener(v->{
            if (email.getText().length()==0 && email.getText().toString().trim().matches(emailPattern))
                email.setError("!Required Email Pattern");
            else if(number.getText().length()==0)
                number.setError("!Required");
            else {
                Pojo pojo = new Pojo(email.getText().toString().trim(), number.getText().toString().trim());
            /*String mail=email.getText().toString().trim();
            String num=number.getText().toString().trim();*/
                email.setText("");
                number.setText("");

                arrayList.add(pojo);


                taskAdapter.notifyItemInserted(arrayList.size() - 1);
                Toast.makeText(this, "Data inserted", Toast.LENGTH_SHORT).show();
            }
        });




    }

    public void validation()
    {

            submit();


    }
   public  void submit()
   {

   }

    public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

      ArrayList<Pojo>arrayList;

        public TaskAdapter(ArrayList<Pojo> arrayList) {
            this.arrayList = arrayList;
        }

        @NonNull
        @Override
        public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
            return new TaskAdapter.TaskViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.task_adapter, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull  TaskAdapter.TaskViewHolder holder, int position) {
Pojo pojo=arrayList.get(position);
            holder.email.setText(pojo.getEmail());
            holder.number.setText(pojo.getNumber());
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class TaskViewHolder extends RecyclerView.ViewHolder {
            TextView email, number;

            public TaskViewHolder(@NonNull  View itemView) {
                super(itemView);
                email = itemView.findViewById(R.id.txtname);
                number = itemView.findViewById(R.id.txt_number);
            }
        }
    }

}