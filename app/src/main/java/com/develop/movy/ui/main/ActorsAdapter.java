package com.develop.movy.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.develop.movy.utils.Image;
import com.develop.movy.R;
import com.develop.movy.model.Actors;

public class ActorsAdapter extends PagedListAdapter<Actors, ActorsAdapter.ActorViewHolder> {

    Context mContext;
    private final onItemClick onItemClick;

    public interface onItemClick {
        void onItemClick(Actors item, ImageView profile);
    }
    public ActorsAdapter(onItemClick onItemClick) {
        super(ACTOR_COMPARATOR);
        this.onItemClick=onItemClick;
    }

    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.actor_item, parent, false);

        return new ActorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder holder, int position) {
        holder.bind(getItem(position),onItemClick);


    }

    class ActorViewHolder extends RecyclerView.ViewHolder {

        ImageView actorProfile;
        TextView actorName;

        ActorViewHolder(@NonNull View itemView) {
            super(itemView);
            actorName = itemView.findViewById(R.id.actor_name);
            actorProfile = itemView.findViewById(R.id.Card_profile);
        }

        void bind(final Actors item, final onItemClick onItemClick) {
            actorName.setText(item.getName());

            Image image = new Image(item.getProfilePath());
            Glide.with(itemView.getContext()).load(image.getLowQualityImagePath()).apply(new RequestOptions().error(R.drawable.ic_launcher_background))
                    .into(actorProfile);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.onItemClick(item,actorProfile);
                }
            });
        }
    }

    private static final DiffUtil.ItemCallback<Actors> ACTOR_COMPARATOR = new DiffUtil.ItemCallback<Actors>() {
        @Override
        public boolean areItemsTheSame(@NonNull Actors oldItem, @NonNull Actors newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Actors oldItem, @NonNull Actors newItem) {
            return oldItem.equals(newItem);
        }
    };

}
