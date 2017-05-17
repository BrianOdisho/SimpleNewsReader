package org.brianodisho.newsreader.articlefeed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import org.brianodisho.newsreader.R;
import org.brianodisho.newsreader.model.ArticlesResponse;
import org.brianodisho.newsreader.util.Formatter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ArticleFeedAdapter extends RecyclerView.Adapter<ArticleHolder> {

    private final LayoutInflater inflater;
    private final List<ArticlesResponse.Article> data;
    private final ArticleHolder.OnArticleClickListener listener;

    @Inject
    Picasso picasso;

    ArticleFeedAdapter(@NonNull Context context, ArticleHolder.OnArticleClickListener listener) {
        inflater = LayoutInflater.from(context);
        data = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArticleHolder(inflater.inflate(R.layout.item_article, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(ArticleHolder holder, int position) {
        ArticlesResponse.Article article = data.get(position);
        picasso.load(article.urlToImage).into(holder.image);
        holder.textTitle.setText(article.title);
        holder.textDate.setText(Formatter.fromTimestampToString(article.publishedAt));
        holder.textAuthor.setText(article.author);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    public void setData(List<ArticlesResponse.Article> data) {
        if (!this.data.isEmpty()) {
            this.data.clear();
        }
        this.data.addAll(data);
        notifyDataSetChanged();
    }


    public ArticlesResponse.Article getItem(int position) {
        if (position < 0 || data == null || position >= data.size()) {
            throw new IllegalArgumentException();
        }
        return data.get(position);
    }
}
