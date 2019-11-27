package com.develop.movy.ui.PersonInfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.develop.movy.utils.Image;
import com.develop.movy.R;
import com.develop.movy.model.Profiles;

import java.util.ArrayList;
import java.util.List;


public class ProfilesAdapter extends RecyclerView.Adapter<ProfilesAdapter.ActorViewHolder> {

    private final onItemClick onItemClick;
    private List<Profiles> profilesList = new ArrayList<>();

    public interface onItemClick {
        void onItemClick(Profiles profiles, ImageView profile);
    }

    public ProfilesAdapter(onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setList(List<Profiles> profilesList ) {
        this.profilesList = profilesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_item, parent, false);

        return new ActorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder holder, int position) {
        holder.bind(position, onItemClick);
    }

    @Override
    public int getItemCount() {
        return profilesList.size();
    }

    class ActorViewHolder extends RecyclerView.ViewHolder {

        ImageView actorProfile;

        ActorViewHolder(@NonNull View itemView) {
            super(itemView);
            actorProfile = itemView.findViewById(R.id.Card_profile);
        }

        void bind(final int position, final onItemClick onItemClick) {

            Image image = new Image(profilesList.get(position).filePath);
            Glide.with(itemView.getContext()).load(image.getLowQualityImagePath()).apply(new RequestOptions().error(R.drawable.ic_launcher_background))
                    .into(actorProfile);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.onItemClick(profilesList.get(position), actorProfile);
                }
            });
        }
    }
}

