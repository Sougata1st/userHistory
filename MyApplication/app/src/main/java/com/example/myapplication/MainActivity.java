package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<userCurrentBookHistoryRVModel> list;
    userCurrentBookHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list = new ArrayList<>();
        adapter = new userCurrentBookHistoryAdapter(getApplicationContext(),list);
        binding.userCurrentBookHistoryRV.setAdapter(adapter);

        String userId = "fsaFJBZb69acnfUHwPvaPFxeTBu1";

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("History");

        ref.child("Passengers").child(userId).child("currentBook")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot ds :snapshot.getChildren()){
                    userCurrentBookHistoryModel model = ds.getValue(userCurrentBookHistoryModel.class);
                    Long TimeStamp = model.getTimeStamp();
                    String FromWhere = model.getFromWhere();
                    String ToWhere = model.getToWhere();
                    String DriverUid = model.getDriverUid();
                    fetchDriverData(TimeStamp,FromWhere,ToWhere,DriverUid);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void fetchDriverData(Long timeStamp, String fromWhere, String toWhere, String DriverUid) {

        FirebaseDatabase.getInstance().getReference(nodeDataClass.DRIVER_NODE).child(DriverUid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DriverModel driverModel = snapshot.getValue(DriverModel.class);
                        Double rating = driverModel.getRating();
                        String formattedNumber = String.format("%.1f", rating);
                        userCurrentBookHistoryRVModel model1 = new userCurrentBookHistoryRVModel(driverModel.getTimeStamp(),fromWhere
                                ,toWhere, driverModel.getUid(), driverModel.getName(),driverModel.getEmail(),
                                driverModel.getNumber(),formattedNumber,
                                MyApplication.formatTimestamp(driverModel.getTimeStamp()));

                        list.add(model1);

                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
}