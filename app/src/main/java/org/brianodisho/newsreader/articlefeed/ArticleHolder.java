package org.brianodisho.newsreader.articlefeed;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.brianodisho.newsreader.R;

class ArticleHolder extends RecyclerView.ViewHolder {

    final ImageView image;
    final TextView textDate, textTitle;


    ArticleHolder(View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.image_item_article);
        textDate = (TextView) itemView.findViewById(R.id.text_item_article_date);
        textTitle = (TextView) itemView.findViewById(R.id.text_item_article_title);
    }
}
