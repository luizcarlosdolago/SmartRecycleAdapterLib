package br.com.k2sistemas.library.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import br.com.k2sistemas.library.annotations.BindImageView;
import br.com.k2sistemas.library.annotations.BindTextView;
import br.com.k2sistemas.library.annotations.LayoutRow;

/**
 * Created by luizcarlos on 28/12/2017.
 */


public class SmartRecyclerViewAdapter<T> extends RecyclerView.Adapter<SmartRecyclerViewAdapter.ViewHolder> {

    protected List<T> mItems;
    protected Context mContext;
    protected LayoutInflater mInflater;

    protected final int LAYOUT_NOT_FOUND = -1;

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View layout) {
            super(layout);
        }

    }

    public SmartRecyclerViewAdapter(Context context, List<T> items) {
        mContext = context;
        mItems = items;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if ( mItems != null && mItems.size() > 0 ) {
            T item = mItems.get(position);
            Class<?> clazz = item.getClass();
            if ( clazz.isAnnotationPresent(LayoutRow.class) ) {
                Annotation layoutRowAnnotation = clazz.getAnnotation(LayoutRow.class);
                LayoutRow layoutRow = (LayoutRow) layoutRowAnnotation;
                return layoutRow.value();
            }
        }
        return LAYOUT_NOT_FOUND;
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int layoutId) {
        if ( layoutId != LAYOUT_NOT_FOUND ) {
            View layout = mInflater.inflate(layoutId, null);
            return new ViewHolder(layout);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        View layout = holder.itemView;
        T item = mItems.get(position);
        Class<?> clazz = item.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if ( field.isAnnotationPresent(BindTextView.class) ) {
                bindTextView(layout, field, item);
            }
            else if ( field.isAnnotationPresent(BindImageView.class) ) {
                bindImageView(layout, field, item);
            }
        }
    }

    protected void bindTextView(View view, Field field, T item) {
        BindTextView bindTextView = (BindTextView) field.getAnnotation(BindTextView.class);
        TextView textView = (TextView) view.findViewById(bindTextView.value());
        try {
            if ( !field.isAccessible() ) {
                field.setAccessible(true);
            }
            Object value = field.get(item);
            textView.setText(String.valueOf(value));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected void bindImageView(View view, Field field, T item) {
        BindImageView biv = (BindImageView) field.getAnnotation(BindImageView.class);
        ImageView imageView = (ImageView) view.findViewById(biv.value());
        try {
            if ( !field.isAccessible() ) {
                field.setAccessible(true);
            }
            Object value = field.get(item);
            Class<?> type = field.getType();
            if ( type == String.class || type == Integer.class || type == int.class ) {
                Glide.with(mContext).load(value).into(imageView);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
