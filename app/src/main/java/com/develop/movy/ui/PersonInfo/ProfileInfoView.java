package com.develop.movy.ui.PersonInfo;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.develop.movy.R;
import com.develop.movy.databinding.ActivityPersonInfoBinding;
import com.develop.movy.model.Actors;
import com.develop.movy.model.Profiles;
import com.develop.movy.ui.custom.DialogProfile;
import com.develop.movy.utils.Image;

import java.util.List;

public class ProfileInfoView extends AppCompatActivity {

    ActivityPersonInfoBinding binding;
    ImageView profile;
    private Actors actors;
    private ProfilesAdapter adapter;

    public ProfileInfoView() {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_person_info);

        actors = getIntent().getParcelableExtra("actorInfo");
        if (actors!=null){
            binding.setActors(actors);
            binding.setImageUrl((new Image(actors.getProfilePath()).getLowQualityImagePath()));
        }

        setupRecyclerProfiles();

        setupViewModel();
    }

    private void setupViewModel() {
        ProfileInfoViewModel profileInfoViewModel = ViewModelProviders.of(this).get(ProfileInfoViewModel.class);
        profileInfoViewModel.getProfiles(actors.getId());
        profileInfoViewModel.mutableLiveData.observe(this, new Observer<List<Profiles>>() {
            @Override
            public void onChanged(List<Profiles> profiles) {
                adapter.setList(profiles);
            }
        });
    }

    private void setupRecyclerProfiles() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter = new ProfilesAdapter(new ProfilesAdapter.onItemClick() {
            @Override
            public void onItemClick(Profiles profiles, ImageView profile) {
                new DialogProfile(ProfileInfoView.this,profiles).show();
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

    }


}
