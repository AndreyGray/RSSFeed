package andreyskakunenko.rssfeed.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import andreyskakunenko.rssfeed.R;
import andreyskakunenko.rssfeed.interfaces.ItemClickListener;
import andreyskakunenko.rssfeed.model.RSSObject;

class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView txtTitle, txtPubDate, txtContent;
    private ItemClickListener itemClickListener;
    ImageView mImageView;


    FeedViewHolder(@NonNull View itemView) {
        super(itemView);

        mImageView = itemView.findViewById(R.id.m_image);

        txtTitle = itemView.findViewById(R.id.txt_title);
        txtPubDate = itemView.findViewById(R.id.txt_pub_date);
        txtContent = itemView.findViewById(R.id.txt_content);

        itemView.setOnClickListener(this);

    }

    void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
/////
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }

}


public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {

    Context mContext;
    RSSObject mRSSObject;
    LayoutInflater mInflater;

    public FeedAdapter(Context context, RSSObject RSSObject) {
        mContext = context;
        mRSSObject = RSSObject;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.row, viewGroup, false);
        return new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder feedViewHolder, int position) {

        feedViewHolder.txtTitle.setText(mRSSObject.getItems().get(position).getTitle());
        feedViewHolder.txtPubDate.setText(mRSSObject.getItems().get(position).getPubDate());
        feedViewHolder.txtContent.setText(mRSSObject.getItems().get(position).getContent());

        feedViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (!isLongClick) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mRSSObject.getItems().get(position).getLink()));
                    mContext.startActivity(browserIntent);
                }

            }
        });

        Picasso.with(mContext)
                .load(mRSSObject.getItems().get(position).getEnclosure().getLink())
                .placeholder(R.drawable.zaglushka)
                .error(R.drawable.zaglushka)
                .into(feedViewHolder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mRSSObject.items.size();
    }
}
