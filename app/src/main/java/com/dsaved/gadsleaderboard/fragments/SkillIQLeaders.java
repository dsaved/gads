package com.dsaved.gadsleaderboard.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dsaved.gadsleaderboard.MyApplication;
import com.dsaved.gadsleaderboard.R;
import com.dsaved.gadsleaderboard.adapter.SkillIQAdapter;
import com.dsaved.gadsleaderboard.adapter.TopLearnersAdapter;
import com.dsaved.gadsleaderboard.model.SkillIQModel;
import com.dsaved.gadsleaderboard.utils.Constant;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SkillIQLeaders extends Fragment {

    private static final String TAG = LearningLeaders.class.getSimpleName();
    public ArrayList<String> name, country, badgeUrl;
    public ArrayList<Integer> score;
    public SkillIQAdapter adapter;

    private static ArrayList<SkillIQModel> skillIQDataModel;

    @BindView(R.id.skill_iq_leaders)
    RecyclerView skill_iq_leaders;
    LinearLayoutManager lm;
    FragmentActivity mContext;
    SweetAlertDialog pDialog;
    View view;

    @Override
    public void onAttach(Context context) {
        mContext = (FragmentActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.skill_iq__leaders, container, false);
        } else {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        ButterKnife.bind(this, view);
        adapter = new SkillIQAdapter(getActivity());
        pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lm = layoutManager;
        skill_iq_leaders.setLayoutManager(layoutManager);
        // set adapter over recyclerview
        skill_iq_leaders.setAdapter(adapter);

        name = new ArrayList<>();
        score = new ArrayList<>();
        country = new ArrayList<>();
        badgeUrl = new ArrayList<>();
        skillIQDataModel = new ArrayList<>();

        this.getData();
        return view;
    }

    void clearData() {
        name.clear();
        score.clear();
        country.clear();
        badgeUrl.clear();
        skillIQDataModel.clear();
    }

    public void getData() {
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
        String url = Constant.iqLeaders;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pDialog.hide();
                if (null != name && !name.isEmpty()) {
                    clearData();
                }
                try {
                    JSONArray result = new JSONArray(response);
                    JSONObject objJson;
                    for (int i = 0; i < result.length(); i++) {
                        objJson = result.getJSONObject(i);
                        name.add(objJson.getString("name"));
                        score.add(objJson.getInt("score"));
                        country.add(objJson.getString("country"));
                        badgeUrl.add(objJson.getString("badgeUrl"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                setAdapterToView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.hide();
                Log.d("error",error.toString());
            }
        });

        new MyApplication().addToRequestQueue(stringRequest);
    }

    // Populate the RecyclerView with data
    private void setAdapterToView() {
        for (int i = 0; i < name.size(); i++) {
            // add the items one by one into Top Learners Model
            skillIQDataModel.add(new SkillIQModel(name.get(i), score.get(i)
                    , country.get(i), badgeUrl.get(i)));
        }
        adapter.addItems(skillIQDataModel);
        adapter.notifyDataSetChanged();
    }

}
