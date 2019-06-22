package nju.androidchat.client.hw1.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.StyleableRes;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import nju.androidchat.client.R;
import nju.androidchat.client.component.ItemTextReceive;
import nju.androidchat.client.component.OnRecallMessageRequested;

public class ItemPicReceive  extends LinearLayout {
    @StyleableRes
    int index0 = 0;

    private TextView textView;
    private ImageView imageView;
    private Context context;
    private UUID messageId;
    private OnRecallMessageRequested onRecallMessageRequested;



    public ItemPicReceive(Context context, String text, UUID messageId){
        super(context);
        this.context = context;
        inflate(context, R.layout.item_img_receive, this);
        this.imageView = findViewById(R.id.chat_item_content_img);
        this.messageId = messageId;
        setPic(text);
    }
    public void init(Context context) {

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
        System.out.println(imageView);
        System.out.println(drawable[0]);
        imageView.setImageDrawable(drawable[0]);
    }
}
