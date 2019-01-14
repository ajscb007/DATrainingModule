package com.pratham.showcaseview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.amlcurran.showcaseview.ShowcaseView;

import java.util.ArrayList;

/**
 * Created by AJ on 08-01-2019.
 */

public class CustomGridViewAdapter extends ArrayAdapter<Item> {
    Context context;
    int layoutResourceId;
    Bitmap bitmap;
    Activity activity;
    ArrayList<Item> data = new ArrayList<Item>();

    public CustomGridViewAdapter(Context context, int layoutResourceId,
                                 ArrayList<Item> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new RecordHolder();
            holder.imageItem = (ImageView) row.findViewById(R.id.item_image);
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }

        Item item = data.get(position);
        holder.imageItem.setImageBitmap(item.getImage());
        holder.imageItem.setId(Integer.parseInt(item.getTitle()));

        return row;

    }

    static class RecordHolder {
        TextView txtTitle;
        ImageView imageItem;

    }
}