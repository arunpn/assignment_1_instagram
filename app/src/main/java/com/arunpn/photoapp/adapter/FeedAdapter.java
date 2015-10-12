package com.arunpn.photoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.arunpn.photoapp.CommentsActivity;
import com.arunpn.photoapp.R;
import com.arunpn.photoapp.model.Photo;
import com.arunpn.photoapp.utils.CircleTransform;
import com.squareup.picasso.Picasso;


import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by a1nagar on 10/10/15.
 */
public class FeedAdapter extends ArrayAdapter<Photo> {
    Context mContext;
    int screenWidth,screenLength;
    WindowManager wm;
    Display display;
    Point size;

    public FeedAdapter(Context context,  List<Photo> objects) {
        super(context, 0, objects);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        }
        else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_feed_layout,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

            holder.commentsURL.setOnClickListener( new urlOnClickListener(getContext())
            );

        }

        Photo photo = getItem(position);
        holder.userName.setText(photo.getUser().getUsername());
        holder.relativeTS.setText(photo.getRelativeDate());

        Spannable text2 = new SpannableString(photo.getCaption().getUser().getUsername()+" : "+photo.getCaption().getCaptionText());
        text2.setSpan(new ForegroundColorSpan(Color.parseColor("#2d5d82")),0,photo.getCaption().getUser().getUsername().length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.captionUsername.setText(text2,TextView.BufferType.SPANNABLE);

        if(photo.getLikes()!=null) {
            holder.likeCount.setText(photo.getLikes().getCount());
        }

        if (photo.getComments()!=null && photo.getComments().getCount()>2) {
            holder.commentsURL.setTag(photo);
            holder.commentsURL.setText("view all "+photo.getComments().getCount() + " comments");
            holder.commentsURL.setVisibility(View.VISIBLE);

            if(photo.getComments().getData()!=null && photo.getComments().getData().get(0).getCommentText()!=null) {
                Spannable commentOneText = new SpannableString(photo.getComments().getData().get(0).getUser().getUsername()+" : "+photo.getComments().getData().get(0).getCommentText());
                commentOneText.setSpan(new ForegroundColorSpan(Color.parseColor("#2d5d82")),0,photo.getComments().getData().get(0).getUser().getUsername().length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.comment_one.setText(commentOneText,TextView.BufferType.SPANNABLE);
                holder.comment_one.setVisibility(View.VISIBLE);
            }

            if(photo.getComments().getData()!=null && photo.getComments().getData().get(1).getCommentText()!=null) {
                Spannable commentTwoText = new SpannableString(photo.getComments().getData().get(1).getUser().getUsername()+" : "+photo.getComments().getData().get(1).getCommentText());
                commentTwoText.setSpan(new ForegroundColorSpan(Color.parseColor("#2d5d82")),0,photo.getComments().getData().get(1).getUser().getUsername().length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.comment_two.setText(commentTwoText,TextView.BufferType.SPANNABLE);
                holder.comment_two.setVisibility(View.VISIBLE);
            }

        }
        Picasso.with(mContext)
                .load(photo.getImages().getResolution().getUrl()).placeholder(mContext.getResources()
                .getDrawable(R.drawable.ic_default))
                .resize(1080, 1080).centerInside()
                .into(holder.mainImage);

        if(photo.getUser()!=null && photo.getUser().getProfilePicture()!=null) {
            Picasso.with(mContext).load(photo.getUser().getProfilePicture()).resize(75,75).transform(new CircleTransform()).into(holder.userImage);
        }

        return convertView;
    }

    public static class ViewHolder{
        @Bind(R.id.userImage) ImageView userImage;
        @Bind(R.id.commentImage) ImageView comments;
        @Bind(R.id.moreImage) ImageView reportPicture;
        @Bind(R.id.likeImage) ImageView likes;
        @Bind(R.id.userName) TextView userName;
        @Bind(R.id.relativeTS) TextView relativeTS;
        @Bind(R.id.likeCount) TextView likeCount;
        @Bind(R.id.captionUsername) TextView captionUsername;
        @Bind(R.id.mainImage) ImageView mainImage;
        @Bind(R.id.allCommentsURL) TextView commentsURL;
        @Bind(R.id.comment_one) TextView comment_one;
        @Bind(R.id.comment_two) TextView comment_two;


        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }

    public class urlOnClickListener implements View.OnClickListener {
        String mediaId;
        Context context;


        public urlOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            Photo photo = (Photo) view.getTag();
            Intent intent = new Intent(context, CommentsActivity.class);
            intent.putExtra("mediaId", photo.getMediaID());
            context.startActivity(intent);

        }
    }
}
