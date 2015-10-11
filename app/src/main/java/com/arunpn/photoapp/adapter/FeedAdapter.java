package com.arunpn.photoapp.adapter;

import android.content.Context;
import android.content.Intent;
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
//        this.wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        this.display = wm.getDefaultDisplay();
//        this.size = wm

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

        Spannable captionUser = new SpannableString(photo.getCaption().getUser().getUsername());
        captionUser.setSpan(new ForegroundColorSpan(R.color.text_color_gray), 0, captionUser.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.captionUsername.setText(captionUser);
        holder.captionUsername.append(" : ");
        Spannable captionText = new SpannableString(photo.getCaption().getCaptionText());
        captionText.setSpan(new ForegroundColorSpan(R.color.text_color_black), 0, captionText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.captionUsername.append(captionText);

        if(photo.getLikes()!=null) {
            holder.likeCount.setText(photo.getLikes().getCount());
        }

        if (photo.getComments()!=null && photo.getComments().getCount()>2) {
            holder.commentsURL.setTag(photo);
            holder.commentsURL.setText("view all "+photo.getComments().getCount() + " comments");
            holder.commentsURL.setVisibility(View.VISIBLE);

            if(photo.getComments().getData()!=null && photo.getComments().getData().get(0).getCommentText()!=null) {
                holder.comment_one.setText(photo.getComments().getData().get(0).getUser().getUsername()+" : ");
                holder.comment_one.append(photo.getComments().getData().get(0).getCommentText());
                holder.comment_one.setVisibility(View.VISIBLE);
            }
            if(photo.getComments().getData()!=null && photo.getComments().getData().get(1).getCommentText()!=null) {
                holder.comment_two.setText(photo.getComments().getData().get(1).getUser().getUsername()+" : ");
                holder.comment_two.append(photo.getComments().getData().get(1).getCommentText());
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
