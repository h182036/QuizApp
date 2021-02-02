package com.example.dat153oblig1.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.BitmapDrawable;

import android.graphics.drawable.Drawable;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;

import android.provider.MediaStore;
import android.widget.ImageButton;
import android.widget.EditText;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.os.Bundle;
import android.os.Build;
import android.net.Uri;

import com.example.dat153oblig1.R;
import com.example.dat153oblig1.Spørsmål;

import java.util.UUID;

import static com.example.dat153oblig1.ui.main.MainActivity.SQLiteHelper;


public class NyKattActivity extends AppCompatActivity {

    private ImageButton imageButton;
    private Button saveButton;
    private EditText nameText;
    private Drawable savedImage;
    private String[] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE", "android.permission.SYSTEM_ALERT_WINDOW", "android.permission.CAMERA"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ny_katt);

        int requestCode = 200;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
        imageButton = findViewById(R.id.imageview);
        saveButton = findViewById(R.id.savebutton);
        nameText = findViewById(R.id.nameinputfield);


        /**
         *  Opening NyKatt activity
         */
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(NyKattActivity.this);
            }
        });

        /**
         *  If an image and a name are chosen they are stored in the global lists
         */
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (savedImage != null && !nameText.getText().toString().equals("")) {
                    save();
                } else {
                    Toast.makeText(NyKattActivity.this, "You need to add a name",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

    }


    /**
     * Saves the image and the name to the global lists
     */
    public void save() {
        String id = UUID.randomUUID().toString();
        ((Spørsmål) this.getApplication()).addKatt(id, nameText.getText().toString(), savedImage);
        SQLiteHelper.insertData(id, nameText.getText().toString(), savedImage);

        finish();
    }

    /**
     * Dialog for camera and gallery
     *
     * @param context
     */
    private void selectImage(Context context) {


        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    /**
     * Function for dealing with gallery and camera
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        Drawable d = new BitmapDrawable(getResources(), selectedImage);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            imageButton.setBackground(d);
                            savedImage = d;
                        }
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                Drawable d = new BitmapDrawable(getResources(), BitmapFactory.decodeFile(picturePath));
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    imageButton.setBackground(d);
                                    savedImage = d;
                                }
                                cursor.close();
                            }
                        }

                    }
                    break;
            }
        }
    }
}
