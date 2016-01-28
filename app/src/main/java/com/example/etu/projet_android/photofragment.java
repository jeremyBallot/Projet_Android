package com.example.etu.projet_android;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;


public class photofragment extends Fragment {

       public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

       public File imageFile;

       @Override
       public void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
       }

       public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // relation avec le XML
           View view = inflater.inflate(R.layout.photofragmentview, container, false);
            // liaison entre le bouton et l'action de prendre la photo
           Button button = (Button) view.findViewById(R.id.takePicture);
           button.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                // accès a l'appareil photo du smartphone
                   Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                   imageFile = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), "photo.jpg");
                   intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
                   startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
               }
           });
           return view;
       }

       @Override
       //permet d'enregistrer la photo
       public void onActivityResult(int requestCode, int resultCode, Intent data) {
           if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
               if (resultCode == Activity.RESULT_OK) {
                   if (imageFile.exists()){
                       Toast.makeText(getActivity(), "Le fichier a été enregistré dans" + imageFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                   } else {
                       Toast.makeText(getActivity(), "Erreur lors de l'enregistrement du fichier"+imageFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                   }
               }
           }
       }
/*
       @Override
       public void onAttach(Context c) {
           super.onAttach(c);
           Log.w("test", "c'est bon?");
       }
*/
   }

// marche avec genymotion mais pas avec l'émulateur android... 
// autre code pour la photo, 
/*

    private File imageFile;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view_ = inflater.inflate(R.layout.photofragmentview, container, false);

        return view_;


    }

    @Override
    public void prendrephoto(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imageFile = new File(
                Environment
                        .getExternalStorageDirectory().getAbsoluteFile(), "photo.jpg");
        Uri tempuri = Uri.fromFile(imageFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, tempuri);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intent, 100);


    // autre choix

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    if (imageFile.exists()) {

                        Toast.makeText(getActivity(), "fichier sauvegarder " + imageFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "test ", Toast.LENGTH_LONG).show();
                    }
                    break;
                case Activity.RESULT_CANCELED:
                    break;
                default:
                    break;
            }
        }*/
    /*
    @Override
    public void prendrephoto(View v) {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        image= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"test.jpg");
        Uri temp= Uri.fromFile(image);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, temp);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intent, 0);
    }



    
    }

    }


}*/
