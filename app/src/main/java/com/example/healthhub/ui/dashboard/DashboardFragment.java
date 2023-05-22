package com.example.healthhub.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.healthhub.BuyMedicineActivity;
import com.example.healthhub.BuyMedicineDetailsActivity;
import com.example.healthhub.CartBuyMedicineActivity;
import com.example.healthhub.HealthArticlesDetailsActivity;
import com.example.healthhub.LoginActivity;
import com.example.healthhub.OrderDetailsActivity;
import com.example.healthhub.R;
import com.bumptech.glide.Glide;
import com.example.healthhub.ui.medicines.MedicinesFragment;

public class DashboardFragment extends Fragment {

CardView cart, med, pill, out, health, order;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        TextView textView = root.findViewById(R.id.hubDesc);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        cart = root.findViewById(R.id.cartCard);
        med = root.findViewById(R.id.medCard);
        pill = root.findViewById(R.id.pillCard);
        out = root.findViewById(R.id.outCard);
        health = root.findViewById(R.id.healthCard);
        order = root.findViewById(R.id.orderCard);
        ImageView imageView = root.findViewById(R.id.health_hub_image);
        Glide.with(this).asGif().load(R.drawable.gif_logo).into(imageView);

        cart.setOnClickListener(view -> startActivity(new Intent(getActivity(), CartBuyMedicineActivity.class)));
        med.setOnClickListener(view -> startActivity(new Intent(getActivity(), BuyMedicineDetailsActivity.class)));
        pill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_navigation_dashboard_to_navigation_medicines);
            }
        });


        health.setOnClickListener(view -> startActivity(new Intent(getActivity(), HealthArticlesDetailsActivity.class)));
        order.setOnClickListener(view -> startActivity(new Intent(getActivity(), OrderDetailsActivity.class)));

        out.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        cart.setOnClickListener(null);
        med.setOnClickListener(null);
        pill.setOnClickListener(null);
        health.setOnClickListener(null);
        order.setOnClickListener(null);
        out.setOnClickListener(null);
    }
}