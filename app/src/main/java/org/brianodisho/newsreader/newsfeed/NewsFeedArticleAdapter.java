package org.brianodisho.newsreader.newsfeed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.brianodisho.newsreader.R;
import org.brianodisho.newsreader.model.NewsFeed;
import org.brianodisho.newsreader.newsfeed.NewsFeedArticleAdapter.NewsFeedArticleHolder.OnArticleClickListener;
import org.brianodisho.newsreader.util.Formatter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class NewsFeedArticleAdapter extends RecyclerView.Adapter<NewsFeedArticleAdapter.NewsFeedArticleHolder> {

    private final LayoutInflater inflater;
    private final OnArticleClickListener listener;

    private List<NewsFeed.Article> data;

    @Inject
    Picasso picasso;


    NewsFeedArticleAdapter(@NonNull Context context, OnArticleClickListener articleClickListener) {
        inflater = LayoutInflater.from(context);
        listener = articleClickListener;
    }

    @Override
    public NewsFeedArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsFeedArticleHolder(inflater.inflate(R.layout.item_news_feed_article, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(NewsFeedArticleHolder holder, int position) {
        NewsFeed.Article article = data.get(position);
        picasso.load(article.getUrlToImage()).into(holder.image);
        holder.textTitle.setText(article.getTitle());
        holder.textDate.setText(Formatter.toRelativeTimeSpanString(article.getPublishedAt()));
        holder.textAuthor.setText(article.getAuthor());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    public void setData(List<NewsFeed.Article> newsFeedArticles) {
        if (data == null) {
            data = new ArrayList<>();
        }
        if (!data.isEmpty()) {
            data.clear();
        }
        data.addAll(newsFeedArticles);
        notifyDataSetChanged();
    }


    NewsFeed.Article getItem(int position) {
        if (position < 0 || data == null || position >= data.size()) {
            throw new IllegalArgumentException();
        }
        return data.get(position);
    }


    static class NewsFeedArticleHolder extends RecyclerView.ViewHolder {

        interface OnArticleClickListener {
            void onArticleClick(int position);
        }


        final ImageView image;
        final TextView textTitle, textDate, textAuthor;


        NewsFeedArticleHolder(View itemView, @NonNull final OnArticleClickListener onArticleClickListener) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image_news_feed_article_thumbnail);
            textTitle = (TextView) itemView.findViewById(R.id.text_news_feed_article_title);
            textDate = (TextView) itemView.findViewById(R.id.text_news_feed_article_date);
            textAuthor = (TextView) itemView.findViewById(R.id.text_news_feed_article_author);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onArticleClickListener.onArticleClick(getLayoutPosition());
                }
            });
        }
    }
}
