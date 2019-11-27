package com.develop.movy.ui.PersonInfo;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.develop.movy.model.Actors;
import com.develop.movy.model.Profiles;
import com.develop.movy.R;
import com.develop.movy.databinding.ActivityPersonInfoBinding;
import com.develop.movy.utils.DialogProfile;
import com.develop.movy.utils.Image;

import java.util.List;

public class PersonInfoView extends AppCompatActivity {

    ActivityPersonInfoBinding binding;
    ImageView profile;
    private Actors actors;
    private ProfilesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_person_info);

        setupActorInfo();

        setupRecyclerProfiles();

        setupViewModel();
    }

    private void setupViewModel() {
        PersonInfoViewModel personInfoViewModel = ViewModelProviders.of(this).get(PersonInfoViewModel.class);
        personInfoViewModel.getProfiles(actors.getId());
        personInfoViewModel.mutableLiveData.observe(this, new Observer<List<Profiles>>() {
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
                new DialogProfile(PersonInfoView.this,profiles).show();
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

    }

    private void setupActorInfo() {
        actors = getIntent().getParcelableExtra("actorInfo");
        binding.setActors(actors);
        profile = findViewById(R.id.actor_profile);

        Image image = new Image(actors.getProfilePath());
        Glide.with(this).load(image.getLowQualityImagePath()).apply(new RequestOptions().error(R.drawable.ic_launcher_background))
                .into(profile);
    }
}
