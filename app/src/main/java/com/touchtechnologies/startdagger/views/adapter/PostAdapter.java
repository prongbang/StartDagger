package com.touchtechnologies.startdagger.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.touchtechnologies.startdagger.R;
import com.touchtechnologies.startdagger.model.Post;
import com.touchtechnologies.startdagger.model.vo.FeedItem;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by dev-touch on 4/11/2017.
 */

public class PostAdapter extends BaseAdapter {

    private List<Post> posts;

    @Inject
    Context context;

    @Inject
    public PostAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return posts != null ? posts.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return posts != null ? posts.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);

            holder = new Holder();
            holder.tvMessage = (TextView) convertView.findViewById(R.id.tv_message);
            convertView.setTag(holder);

        } else {
            holder = (Holder) convertView.getTag();
        }

        Post post = posts.get(position);

        holder.tvMessage.setText(post.getText());

        return convertView;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    private class Holder {
        TextView tvMessage;
    }
}
