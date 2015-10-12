package com.arunpn.photoapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.arunpn.photoapp.R;
import com.arunpn.photoapp.model.CommentDetail;
import com.arunpn.photoapp.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by a1nagar on 10/11/15.
 */
public class CommentsAdapter extends ArrayAdapter<CommentDetail> {
    Context context;
    public CommentsAdapter(Context context,List<CommentDetail> objects) {
        super(context, 0, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView!=null) {
            holder = (ViewHolder) convertView.getTag();
        }
        else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_comments_layout,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        CommentDetail commentDetail = getItem(position);
        if(commentDetail.getCommentText()!=null && commentDetail.getUser()!=null ) {
            Spannable commentText = new SpannableString(commentDetail.getUser().getUsername()+" : "+commentDetail.getCommentText());
            commentText.setSpan(new ForegroundColorSpan(Color.parseColor("#2d5d82")),0,commentDetail.getUser().getUsername().length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.userName.setText(commentText,TextView.BufferType.SPANNABLE);
        }

        if(commentDetail.getUser().getProfilePicture()!=null ) {
            Picasso.with(context).load(commentDetail.getUser().getProfilePicture()).resize(75,75).transform(new CircleTransform()).into(holder.userImage);
        }
        holder.relativeTS.setText(commentDetail.getRelativeTime());

        return convertView;
    }

    public static class ViewHolder{
        @Bind(R.id.userImage)
        ImageView userImage;
        @Bind(R.id.userName)
        TextView userName;
        @Bind(R.id.relativeTS) TextView relativeTS;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
