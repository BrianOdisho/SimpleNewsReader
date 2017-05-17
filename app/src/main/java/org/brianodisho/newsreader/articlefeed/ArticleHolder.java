package org.brianodisho.newsreader.articlefeed;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.brianodisho.newsreader.R;

class ArticleHolder extends RecyclerView.ViewHolder {

    interface OnArticleClickListener {
        void onArticleClick(int position);
    }


    final ImageView image;
    final TextView textTitle, textDate, textAuthor;


    ArticleHolder(View itemView, @NonNull final OnArticleClickListener onArticleClickListener) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.image_item_article);
        textTitle = (TextView) itemView.findViewById(R.id.text_item_article_title);
        textDate = (TextView) itemView.findViewById(R.id.text_item_article_date);
        textAuthor = (TextView) itemView.findViewById(R.id.text_item_article_author);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onArticleClickListener.onArticleClick(getLayoutPosition());
            }
        });
    }
}
