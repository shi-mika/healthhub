package com.example.healthhub.ui.medicines;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.healthhub.BuyMedicineDetailsActivity;
import com.example.healthhub.CartBuyMedicineActivity;
import com.example.healthhub.MainActivity;
import com.example.healthhub.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MedicinesFragment extends Fragment {

    private final String[][] packages = {
            {"Uprise-D3 1000IU Capsule", "", "", "", "50"},
            {"HealthVit Chromium Picolinate 200mcg Capsule", "", "", "", "305"},
            {"Vitamin B Complex Capsules", "", "", "", "448"},
            {"Inlife Vitamin E Wheat Germ Oil Capsule", "", "", "", "539"},
            {"Dolo 650 Tablet", "", "", "", "30"},
            {"Crocin 650 Advance Tablet", "", "", "", "50"},
            {"Strepsils Medicated Lozenges for Sore Throat", "", "", "", "40"},
            {"Tata 1mg Calcium + Vitamin D3", "", "", "", "30"},
            {"Feronia -XT Tablet", "", "", "", "130"},
    };
    private final String[] package_details = {

            "Building and keeping the bones & teeth strong\n" +
                    "Reducing Fatigue/stress and muscular pains\n" +
                    "Boosting immunity and increasing resistance against infection",
            "Chromium is an essential trace mineral that plays an important role in helping insulin regulate blood glucose. ",
            "Provides relief from vitamin B deficiencies\n" +
                    "Helps in formation of red blood cells\n" +
                    "Maintains healthy nervous system",
            "It promotes health as well as skin benefit.\n" +
                    "It helps reduce skin blemish and pigmentation.\n" +
                    "It act as safeguard the skin from the harsh UVA and UVB sun rays.",
            "Dolo 650 Tablet helps relieve pain and fever by blocking the release of certain chemical messengers responsible for fever and pain.",
            "Helps relieve fever and bring down a high temperature\n" +
                    "Suitable for people with a heart condition or high blood pressure",
            "Relieves the symptoms of a bacterial throat infection and soothes the recovery process\n" +
                    "Provides a warm and comforting feeling during sore throat",
            "Reduces the risk of calcium deficiency, Rickets, and Osteoporosis\n" +
                    "Promotes mobility and flexibility of joints",
            "Helps to reduce the iron deficiency due to chronic blood loss or low intake of iron"

    };

    HashMap<String, String> item;
    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;
    Button btnBack,btnGoToCart;
    ListView lst;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_medicines, container, false);

        MedicinesViewModel medicinesViewModel = new ViewModelProvider(this).get(MedicinesViewModel.class);

        TextView textView = root.findViewById(R.id.textViewBMTitle);
        medicinesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        lst = root.findViewById(R.id.listViewBMCart);
        btnBack = root.findViewById(R.id.buttonBMBack);
        btnGoToCart = root.findViewById(R.id.buttonBMGoToCart);

        list = new ArrayList<>();
        for (String[] aPackage : packages) {
            item = new HashMap<>();
            item.put("line1", aPackage[0]);
            item.put("line2", aPackage[1]);
            item.put("line3", aPackage[2]);
            item.put("line4", aPackage[3]);
            item.put("line5", "Total Cost: " + aPackage[4] + "$");
            list.add(item);
        }

        sa = new SimpleAdapter(getActivity(), list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});

        lst.setAdapter(sa);

        lst.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent it = new Intent(getActivity(), BuyMedicineDetailsActivity.class);
            it.putExtra("text1", packages[i][0]);
            it.putExtra("text2", package_details[i]);
            it.putExtra("text3",packages[i][4]);
            startActivity(it);
        });

        btnGoToCart.setOnClickListener(view -> startActivity(new Intent(getActivity(), CartBuyMedicineActivity.class)));

        btnBack.setOnClickListener(view -> startActivity(new Intent(getActivity(), MainActivity.class)));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        lst.setOnItemClickListener(null);
        btnBack.setOnClickListener(null);
        btnGoToCart.setOnClickListener(null);
    }
}