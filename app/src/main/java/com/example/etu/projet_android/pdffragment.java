package com.example.etu.projet_android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;




public class pdffragment extends Fragment {


    Button lect_pdf;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pdffragment, container, false);

        // on recupère l'id du bouton que l'on a placé dans le XML
        lect_pdf = (Button) view.findViewById(R.id.lectpdf);
        lect_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try
                {
                    //Ici la methode utilisé pour pouvoir lire un pdf, est de tt d'abord de spécifier l'emplacement du pdf, de le stoker dans un fichier que l'on crépour ensuite pouvoir le lire
                    InputStream inputStream = getContext().getAssets().open("test.pdf");

                    File outFilePdf = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test/", "test.pdf");

                    OutputStream outputStream = new FileOutputStream(outFilePdf);

                    //creation d'un buffer pour pouvoir traiter les donnée par flot, et lire le pdf
                    byte[] buffer = new byte[1024];
                    int read;
                    while((read = inputStream.read(buffer)) != -1){

                        outputStream.write(buffer, 0, read);
                    }
                }
                catch(IOException e)
                {
                    Log.e("tag", "Failed to copy asset file: ", e);
                }

                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test/", "test.pdf");


                Uri path = Uri.fromFile(file);


                Intent intent = new Intent(Intent.ACTION_VIEW);

                //type
                intent.setDataAndType(path, "application/pdf");

                //debut de l'intence
                startActivity(intent);
            }
        });
        return view;
    }
}
