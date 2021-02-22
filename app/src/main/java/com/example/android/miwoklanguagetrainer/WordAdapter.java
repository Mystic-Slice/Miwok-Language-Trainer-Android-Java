package com.example.android.miwoklanguagetrainer;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    /** Resource ID for the background color of this list of words*/
    private int mColorResourceId;


    /**
     * Create a new {@link WordAdapter} object
     * @param context is the current context(Activity) that the adapter is being created in
     * @param words is the list of {@link Word}s to be displayed
     * @param colorResourceId is the resource Id of the colour of each list item
     *
     */

    public WordAdapter(Activity context, ArrayList<Word> words,int colorResourceId){
        super(context,0,words);
        mColorResourceId = colorResourceId;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_word_text_view
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_word_text_view);
        // Get the Miwok word from the current Word object and
        // set this text on the miwok word TextView
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID default_translation_text_view
        TextView translatedTextView = (TextView) listItemView.findViewById(R.id.default_translation_text_view);
        // Get the default translation from the current Word object and
        // set this text on the default translation TextView
        translatedTextView.setText(currentWord.getDefaultTranslation());

        // Find the ImageView in the list_item.xml layout with the ID list_item_image_view
        ImageView Image = listItemView.findViewById(R.id.list_item_image_view);
        if(currentWord.hasImage()){// Get the image from the current Word object and
            // Set this image on the ImageView
            Image.setImageResource(currentWord.getImageResourceId());

            //Make sure the view is visible
            Image.setVisibility(View.VISIBLE);
        }
        else{
            // Otherwise hide the ImageView (set visibility to GONE)
            Image.setVisibility(View.GONE);
        }

        //Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        //Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(),mColorResourceId);
        //Set the background color of the text container View
        textContainer.setBackgroundColor(color);



        // Return the whole list item layout (containing 2 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }

}
