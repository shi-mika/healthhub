package com.example.healthhub.ui.health_articles;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthhub.HealthArticlesDetailsActivity;
import com.example.healthhub.R;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthArticlesFragment extends Fragment {

    private final String[][] health_details = {
            {"Walking Daily", "", "", "", "Click for More Details"},
            {"Home Care", "", "", "", "Click for More Details"},
            {"Stop smoking", "", "", "", "Click for More Details"},
            {"Menstrual cramps", "", "", "", "Click for More Details"},
            {"Healthy gut", "", "", "", "Click for More Details"}
    };

    private final int[] images = {
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health1,
    };

    HashMap<String, String> item;
    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;

    ListView lst;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_health_articles, container, false);

        HealthArticlesViewModel healthArticlesViewModel = new ViewModelProvider(this).get(HealthArticlesViewModel.class);

        TextView textView = root.findViewById(R.id.textViewHATitle);
        healthArticlesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        lst = root.findViewById(R.id.listViewHA);

        list = new ArrayList<>();
        for (String[] health_detail : health_details) {
            item = new HashMap<>();
            item.put("line1", health_detail[0]);
            item.put("line2", health_detail[1]);
            item.put("line3", health_detail[2]);
            item.put("line4", health_detail[3]);
            item.put("line5", health_detail[4]);
            list.add(item);
        }

        sa = new SimpleAdapter(getActivity(), list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});

        lst.setAdapter(sa);

        lst.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent it = new Intent(getActivity(), HealthArticlesDetailsActivity.class);
            it.putExtra("text1", health_details[i][0]);
            it.putExtra("text2", images[i]);
            startActivity(it);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        lst.setOnItemClickListener(null);
    }
}
