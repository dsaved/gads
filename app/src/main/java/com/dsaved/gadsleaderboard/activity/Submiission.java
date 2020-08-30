package com.dsaved.gadsleaderboard.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dsaved.gadsleaderboard.MyApplication;
import com.dsaved.gadsleaderboard.R;
import com.dsaved.gadsleaderboard.utils.Constant;
import com.dsaved.gadsleaderboard.utils.Navigator;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Submiission extends AppCompatActivity {
    Navigator navigator;
    @BindView(R.id.firstname)
    EditText first_name;
    @BindView(R.id.lastname)
    EditText last_name;
    @BindView(R.id.email)
    EditText emailV;
    @BindView(R.id.project_link)
    EditText project_link;
    @BindView(R.id.submit)
    Button submit;

    private String firstName, lastName, projectLink, email;
    SweetAlertDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submiission);
        navigator = new Navigator(this);
        ButterKnife.bind(this);
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Submiission.this.validate();
            }
        });
    }

    private void validate() {
        firstName = first_name.getText().toString();
        lastName = last_name.getText().toString();
        email = emailV.getText().toString();
        projectLink = project_link.getText().toString();

        if (firstName.isEmpty()) {
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText("Please provide first name")
                    .show();
            return;
        }

        if (lastName.isEmpty()) {
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText("Please provide last name")
                    .show();
            return;
        }

        if (email.isEmpty()) {
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText("Please provide last email")
                    .show();
            return;
        }

        Pattern emailPattern = Patterns.EMAIL_ADDRESS;
        if (!emailPattern.matcher(email).matches()) {
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText("Email is not valid")
                    .show();
            return;
        }

        if (projectLink.isEmpty()) {
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText("Please provide project link")
                    .show();
            return;
        }

        Pattern linkPattern = Patterns.WEB_URL;
        if (!linkPattern.matcher(projectLink).matches()) {
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText("project link is not a valid link")
                    .show();
            return;
        }

        this.confirm();
    }

    private void confirm() {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Submit form now")
                .setConfirmText("Yes!")
                .setCancelText("No")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        Submiission.this.submitForm();
                    }
                })
                .show();
    }

    private void submitForm() {
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Submitting form now");
        pDialog.setCancelable(false);
        pDialog.show();
        String url = Constant.formUrl;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pDialog.hide();
                if (response.contains("Your response has been recorded.")) {
                    new SweetAlertDialog(Submiission.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("All Done!")
                            .setContentText("Submission Successful")
                            .show();
                    Submiission.this.clearForm();
                } else {
                    new SweetAlertDialog(Submiission.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText("Submission Not Successful")
                            .show();
                }
                Log.d("response", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.hide();
                Log.d("error", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();
                params.put(Constant.firstNameEntry, firstName);
                params.put(Constant.lastNameEntry, lastName);
                params.put(Constant.emailEntry, email);
                params.put(Constant.projectLinkEntry, projectLink);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Adding request to request queue
        stringRequest.setShouldCache(false);

        new MyApplication().addToRequestQueue(stringRequest);
    }

    private void clearForm() {
        first_name.setText("");
        emailV.setText("");
        last_name.setText("");
        project_link.setText("");
    }

    @Override
    public boolean onSupportNavigateUp() {
        navigator.close();
        return true;
    }

}