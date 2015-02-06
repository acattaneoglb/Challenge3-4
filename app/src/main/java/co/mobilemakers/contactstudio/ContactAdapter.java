package co.mobilemakers.contactstudio;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * TODO: Using info from http://developer.android.com/training/camera/photobasics.html
 *
 * Created by ariel.cattaneo on 05/02/2015.
 */
public class ContactAdapter extends ArrayAdapter<ContactModel> {

    Context mContext;
    List<ContactModel> mContactList;

    public ContactAdapter(Context context, List<ContactModel> todoList) {
        super(context, R.layout.contact_entry, todoList);

        mContext = context;
        mContactList = todoList;
    }

    private void setThumbnail(String imageURL, ImageView imageView) {
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageURL, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        //bmOptions.inPurgeable = true; // Deprecated

        Bitmap bitmap = BitmapFactory.decodeFile(imageURL, bmOptions);
        imageView.setImageBitmap(bitmap);
    }

    private void displayContentInRowView(final int position, View rowView) {
        if (rowView != null) {
            ContactModel contact = mContactList.get(position);
            TextView textName = (TextView)rowView.findViewById(R.id.text_name);
            String nickname = contact.getNickname();
            if (nickname.isEmpty()) {
                textName.setText(contact.getName());
            }
            else {
                textName.setText(nickname);
            }

            String photoURL = contact.getPhotoURL();
            if (!photoURL.isEmpty()) {
                setThumbnail(photoURL, (ImageView) rowView.findViewById(R.id.image_photo));
            }
        }
    }

    private View reuseOrGenerateRowView(View convertView, ViewGroup parent) {
        View rowView;
        if (convertView != null) {
            rowView = convertView;
        }
        else {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.contact_entry, parent, false);
        }
        return rowView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;

        rowView = reuseOrGenerateRowView(convertView, parent);

        displayContentInRowView(position, rowView);

        return rowView;
    }

}
