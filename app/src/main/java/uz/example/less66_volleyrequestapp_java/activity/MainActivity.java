package uz.example.less66_volleyrequestapp_java.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

import uz.example.less66_volleyrequestapp_java.R;
import uz.example.less66_volleyrequestapp_java.model.Employee;
import uz.example.less66_volleyrequestapp_java.network.VolleyHandler;
import uz.example.less66_volleyrequestapp_java.network.VolleyHttp;

public class MainActivity extends AppCompatActivity {
    EditText et_get_id,et_update_id,et_update_name,et_update_salary,et_update_age,et_delete_id;
    EditText et_create_name,et_create_salary,et_create_age;
    Button btn_list,btn_get_one,btn_create,btn_update,btn_delete;
    TextView tv_res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    void initViews() {
        btn_list = findViewById(R.id.btn_list);
        btn_get_one = findViewById(R.id.btn_get_one);
        btn_create = findViewById(R.id.btn_create);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);
        et_get_id = findViewById(R.id.et_get_id);
        et_create_name = findViewById(R.id.et_create_name);
        et_create_salary = findViewById(R.id.et_create_salary);
        et_create_age = findViewById(R.id.et_create_age);
        et_update_name = findViewById(R.id.et_update_name);
        et_update_salary = findViewById(R.id.et_update_salary);
        et_update_age = findViewById(R.id.et_update_age);
        et_update_id = findViewById(R.id.et_update_id);
        et_delete_id = findViewById(R.id.et_delete_id);
        tv_res = findViewById(R.id.tv_res);

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEmployeeList();
            }
        });

        btn_get_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = et_get_id.getText().toString().trim();
                if (!id.isEmpty()){
                    getOneEmployee(id);
                }

            }
        });

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_create_name.getText().toString().trim();
                String salary= et_create_salary.getText().toString().trim();
                String age = et_create_age.getText().toString().trim();

                if (!salary.isEmpty()&&!age.isEmpty()){
                    Employee emp = new  Employee(name,Integer.parseInt(salary),Integer.parseInt(age));
                    employeeCreate(emp);
                }

            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = et_update_id.getText().toString().trim();
                String name = et_update_name.getText().toString().trim();
                String salary= et_update_salary.getText().toString().trim();
                String age = et_update_age.getText().toString().trim();

                if (!id.isEmpty()&&!salary.isEmpty()&&!age.isEmpty()){
                    Employee emp = new Employee(Integer.parseInt(id),name,Integer.parseInt(salary),Integer.parseInt(age));
                    employeeUpdate(emp);
                }

            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = et_delete_id.getText().toString().trim();
                if (!id.isEmpty()){
                    employeeDelete(id);
                }

            }
        });
    }

    private void getEmployeeList() {
        VolleyHttp.get(VolleyHttp.API_LIST_EMPLOYEES, VolleyHttp.paramsEmpty(), new VolleyHandler() {
            @Override
            public void onSuccess(String response) {
                tv_res.setText(response);
            }

            @Override
            public void onError(String error) {
                tv_res.setText(error);
            }
        });
    }

    private void getOneEmployee(String id){
        VolleyHttp.get(VolleyHttp.API_SINGLE_EMPLOYEE+id, VolleyHttp.paramsEmpty(), new VolleyHandler() {
            @Override
            public void onSuccess(String response) {
                 tv_res.setText(response);
                 et_get_id.setText("");
            }

            @Override
            public void onError(String error) {
                tv_res.setText(error);
            }
        });
    }

    private void employeeDelete(String id) {
        VolleyHttp.del(VolleyHttp.API_DELETE_EMPLOYEE + id, new VolleyHandler() {
            @Override
            public void onSuccess(String response) {
                 tv_res.setText(response);
                 et_delete_id.setText("");
            }

            @Override
            public void onError(String error) {
                 tv_res.setText(error);
            }
        });
    }
    private void employeeCreate(Employee employee){

        VolleyHttp.post(VolleyHttp.API_CREATE_EMPLOYEE, VolleyHttp.paramsCreate(employee), new VolleyHandler() {
            @Override
            public void onSuccess(String response) {
                 tv_res.setText(response);
                 et_create_age.setText("");
                 et_create_name.setText("");
                 et_create_salary.setText("");
            }

            @Override
            public void onError(String error) {
                 tv_res.setText(error);
            }
        });
    }
    private void employeeUpdate(Employee employee){
        VolleyHttp.put(VolleyHttp.API_UPDATE_EMPLOYEE + employee.getId(), VolleyHttp.paramsUpdate(employee), new VolleyHandler() {
            @Override
            public void onSuccess(String response) {
                 tv_res.setText(response);
                 et_update_age.setText("");
                 et_update_name.setText("");
                 et_update_salary.setText("");
                 et_update_id.setText("");
            }

            @Override
            public void onError(String error) {
                 tv_res.setText(error);
            }
        });
    }




}