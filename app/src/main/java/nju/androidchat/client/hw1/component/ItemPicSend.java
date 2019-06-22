package nju.androidchat.client.hw1.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.StyleableRes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import lombok.Setter;
import nju.androidchat.client.R;
import nju.androidchat.client.component.ItemTextSend;
import nju.androidchat.client.component.OnRecallMessageRequested;

public class ItemPicSend extends LinearLayout implements View.OnLongClickListener{
    @StyleableRes
    int index0 = 0;

    private TextView textView;
    private ImageView imageView;
    private Context context;
    private UUID messageId;
    @Setter
    private OnRecallMessageRequested onRecallMessageRequested;

    public ItemPicSend(Context context, String text, UUID messageId, OnRecallMessageRequested onRecallMessageRequested){
        super(context);
        this.context = context;
        inflate(context, R.layout.item_img_send, this);
        this.imageView = findViewById(R.id.chat_item_content_img);
        this.messageId = messageId;
        this.onRecallMessageRequested = onRecallMessageRequested;

        this.setOnLongClickListener(this);
        setPic(text);
    }
    public String getText() {
        return textView.getText().toString();
    }

    public void setText(String text) {
        textView.setText(text);
    }

    public void setPic(String text) {
        final Drawable[] drawable = new Drawable[1];
        new Thread(() -> {
            try{
                URL thumb_u = new URL(text);
                drawable[0] = Drawable.createFromStream(thumb_u.openStream(), "src");
            }catch (IOException e){
                e.getStackTrace();
            }
        }).start();
        while (drawable[0] == null);
        imageView.setImageDrawable(drawable[0]);
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
