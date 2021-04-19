package com.example.recyclerviewprac1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements placeToGoRecyclerViewAdapter.OnRowClickListener {
    private TextView mTvTitle1, mTvTitle2;
//    FragmentManager fragmentManager = getSupportFragmentManager();
//    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    RecyclerView topDestinationRecyclerView;
    topDestinationRecyclerViewAdapter topDestinationRecyclerViewAdapter;
    List<topDestination> topDestinationList = new ArrayList<>();

    RecyclerView placeToGoRecyclerView;
    com.example.recyclerviewprac1.placeToGoRecyclerViewAdapter placeToGoRecyclerViewAdapter;
    List<placeToGo> placeToGoList = new ArrayList<>();
//
//    Object[] fragmentList = {new FirstPlaceFragment(), new SecondPlaceFragment(),
//            new ThirdPlaceFragment(), new FourthPlaceFragment()};

    // Top destination list
    Integer[] imageList = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d};

    // Place to go list
    Integer[] imageListPlaceToGo = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d};
    String[] nameList = {"Sydney Opera House", "Great Barrier Reef", "Kangaroo Island", "Kakadu National Park"};
    String[] descriptionList = {"The Sydney Opera House is a multi-venue performing arts centre at Sydney Harbour located in Sydney, New South Wales, Australia. It is one of the 20th century's most famous and distinctive buildings.",
            "The Great Barrier Reef is the world's largest coral reef system composed of over 2,900 individual reefs and 900 islands stretching for over 2,300 kilometres over an area of approximately 344,400 square kilometres. The reef is located in the Coral Sea, off the coast of Queensland, Australia.",
            "Kangaroo Island lies off the mainland of South Australia, southwest of Adelaide. Over a third of the island is protected in nature reserves, home to native wildlife like sea lions, koalas and diverse bird species. In the west, Flinders Chase National Park is known for penguin colonies and striking coastal rock formations, like the sculpted Remarkable Rocks and the stalactite-covered Admirals Arch.",
            "Kakadu National Park is an enormous, biodiverse nature reserve in Australia’s Northern Territory. With terrain encompassing wetlands, rivers and sandstone escarpments, it’s home to some 2,000 plant species and wildlife from saltwater crocodiles and flatback turtles to birds. Aboriginal rock paintings, dating to prehistoric times, can be viewed at sites such as Nourlangie, Nanguluwur and Ubirr."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvTitle1 = findViewById(R.id.tv_title1);
        mTvTitle2 = findViewById(R.id.tv_title2);

        // Top destination part
        topDestinationRecyclerView = findViewById(R.id.rv_horizontal);
        topDestinationRecyclerViewAdapter = new topDestinationRecyclerViewAdapter(topDestinationList, MainActivity.this);
        topDestinationRecyclerView.setAdapter(topDestinationRecyclerViewAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        topDestinationRecyclerView.setLayoutManager(layoutManager);

        for (int i = 0; i < imageList.length; i++) {
            topDestination topDestination = new topDestination(i, imageList[i]);
            topDestinationList.add(topDestination);
        }

        // Place to go part
        placeToGoRecyclerView = findViewById(R.id.rv_vertical);
        placeToGoRecyclerViewAdapter = new placeToGoRecyclerViewAdapter(placeToGoList, MainActivity.this, this);
        placeToGoRecyclerView.setAdapter(placeToGoRecyclerViewAdapter);

        RecyclerView.LayoutManager layoutManagerPlaceToGo = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        placeToGoRecyclerView.setLayoutManager(layoutManagerPlaceToGo);

        for (int i = 0; i < imageListPlaceToGo.length; i++) {
            placeToGo placeToGo = new placeToGo(i, imageListPlaceToGo[i], nameList[i], descriptionList[i]);
            placeToGoList.add(placeToGo);
        }
    }

    // Set fragment
    @Override
    public void onItemClick(int position) {
        Fragment fragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragment = PlaceFragment.newInstance(nameList[position],descriptionList[position],imageListPlaceToGo[position]);
        fragmentTransaction.add(R.id.firstFrameLayout1, fragment).commitAllowingStateLoss();

        placeToGoRecyclerView.setVisibility(View.INVISIBLE);
        topDestinationRecyclerView.setVisibility(View.INVISIBLE);
        mTvTitle1.setVisibility(View.INVISIBLE);
        mTvTitle2.setVisibility(View.INVISIBLE);
    }

    // Press back button to go back to the MainActivity
    @Override
    public void onBackPressed() {
        if (placeToGoRecyclerView.getVisibility() == View.INVISIBLE) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragmentManager.getFragments().get(0));
            fragmentTransaction.commit();
            placeToGoRecyclerView.setVisibility(View.VISIBLE);
            topDestinationRecyclerView.setVisibility(View.VISIBLE);
            mTvTitle1.setVisibility(View.VISIBLE);
            mTvTitle2.setVisibility(View.VISIBLE);
        }
    }
}