package com.xuweida.daweilibrary;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by wdxu on 3/5/2015.
 */
public class AppHelper {
    public static void hideSoftKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showSoftKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static void showProgress(final boolean show, final View showView, final View hideView, int shortAnimTime) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {

            hideView.setVisibility(show ? View.GONE : View.VISIBLE);
            hideView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    hideView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            showView.setVisibility(show ? View.VISIBLE : View.GONE);
            showView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    showView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            showView.setVisibility(show ? View.VISIBLE : View.GONE);
            hideView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public static void showPicturePicker(final Activity activity, final int requestCode, final File temp) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View imgEntryView = inflater.inflate(R.layout.dialog_photo_entry, null);
        final String[] items = new String[]{activity.getString(R.string.txt_title_from_camera), activity.getString(R.string.txt_title_from_external)};
        final AlertDialog dialog = new AlertDialog.Builder(activity).setTitle(activity.getString(R.string.txt_title_add_picture)).setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    takePictureFromCamera(activity, requestCode, temp);
                } else if (which == 1) {
                    choosePictureFromExternal(activity, requestCode);
                }
            }
        }).create();
        dialog.show();
    }

    public static void chooseMediaFromExternal(Activity activity, int requestCode) {
        Intent choosePictureIntent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(choosePictureIntent, requestCode);
    }

    public static void takePictureFromCamera(Activity activity, int requestCode, File temp) {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (temp != null) {
            takePicture.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(temp));
        }
        activity.startActivityForResult(takePicture, requestCode);
    }

    public static void choosePictureFromExternal(Activity activity, int requestCode) {
        Intent choosePictureIntent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(choosePictureIntent, requestCode);
    }

    public static void chooseVideoFromExternal(Activity activity, int requestCode) {
        Intent choosePictureIntent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(choosePictureIntent, requestCode);
    }
//
//    public static Bitmap getBitmapFromData(Activity activity, Intent data) {
//        Uri selectedImage = data.getData();
//        String[] filePathColumn = {MediaStore.Images.Media.DATA};
//        Cursor cursor = activity.getContentResolver().query(selectedImage, null, null, null, null);
//        cursor.moveToFirst();
//        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//        String picturePath = cursor.getString(columnIndex);
//        cursor.close();
//
//        return BitmapFactory.decodeFile(picturePath);
//    }

//    public static String[] getNameAndPathFromData(Activity activity, Intent data) {
//        String[] result = new String[2];
//        Uri selectedImage = data.getData();
//        String[] filePathColumn = {MediaStore.Images.Media.DATA};
//        Cursor cursor = activity.getContentResolver().query(selectedImage, null, null, null, null);
//        cursor.moveToFirst();
//        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//        int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
//        result[1] = cursor.getString(columnIndex);
//        result[0] = cursor.getString(nameIndex);
//        cursor.close();
//
//        return result;
//    }

    public static void showBigImageDialog(Context context, Uri uri) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View imgEntryView = inflater.inflate(R.layout.dialog_photo_entry, null);

        ImageView img = (ImageView) imgEntryView.findViewById(R.id.large_image);
        img.setImageURI(uri);

        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setView(imgEntryView);
        dialog.show();

        imgEntryView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramView) {
                dialog.cancel();
            }
        });
    }

    public static void showBigImageDialog(Context context, String url) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View imgEntryView = inflater.inflate(R.layout.dialog_photo_entry, null);

        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        ImageView img = (ImageView) imgEntryView.findViewById(R.id.large_image);

        Picasso.with(context).load(url).error(android.R.drawable.stat_notify_error).into(img);

        dialog.setView(imgEntryView);
        dialog.show();

        imgEntryView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramView) {
                dialog.cancel();
            }
        });
    }

    public static void showVideoDialog(Context context) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View imgEntryView = inflater.inflate(R.layout.dialog_video_entry, null);
//
//        final AlertDialog dialog = new AlertDialog.Builder(context).create();
////        ImageView img = (ImageView) imgEntryView.findViewById(R.id.large_video);
////
////        img.setImageBitmap(bm);
//
//        dialog.setView(imgEntryView);
//        dialog.show();
//
//        imgEntryView.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View paramView) {
//                dialog.cancel();
//            }
//        });
    }

    public static MaterialDialog showExceptionDialog(Context context, int strId) {
        return new MaterialDialog.Builder(context)
                .content(strId)
                .negativeText(android.R.string.ok)
                .show();
    }

    public static MaterialDialog showExceptionDialog(Context context, int strId, MaterialDialog.ButtonCallback callback) {
        return new MaterialDialog.Builder(context)
                .content(strId)
                .negativeText(android.R.string.ok)
                .callback(callback)
                .show();
    }

    public static void showExceptionDialog(Context context, String msg) {
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setMessage(msg)
                .setPositiveButton(R.string.action_ok,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.cancel();
                            }
                        }).create();
        dialog.show();
    }

    public static void showExceptionDialog(Context context, String tag, Exception e) {
        Log.e("error dialog", tag, e);
//        Log.e("error dialog", tag);
    }

    public static String getDistance(String location) {
        return "2.1km";
    }
}
