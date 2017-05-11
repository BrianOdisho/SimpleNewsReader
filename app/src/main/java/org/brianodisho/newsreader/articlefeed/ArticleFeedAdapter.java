package org.brianodisho.newsreader.articlefeed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import org.brianodisho.newsreader.R;
import org.brianodisho.newsreader.model.ArticlesResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ArticleFeedAdapter extends RecyclerView.Adapter<ArticleHolder> {

    private final LayoutInflater inflater;
    private final List<ArticlesResponse.Article> articles;

    @Inject
    Picasso picasso;

    ArticleFeedAdapter(@NonNull Context context) {
        inflater = LayoutInflater.from(context);
        articles = new ArrayList<>();
    }

    @Override
    public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArticleHolder(inflater.inflate(R.layout.item_article, parent, false));
    }

    @Override
    public void onBindViewHolder(ArticleHolder holder, int position) {
        ArticlesResponse.Article article = articles.get(position);
        picasso.load(article.urlToImage).into(holder.image);
        holder.textTitle.setText(article.title);
    }

    @Override
    public int getItemCount() {
        return articles == null ? 0 : articles.size();
    }


    public void setData(List<ArticlesResponse.Article> data) {
        if (!articles.isEmpty()) {
            articles.clear();
        }
        articles.addAll(data);
        notifyDataSetChanged();
    }
}
