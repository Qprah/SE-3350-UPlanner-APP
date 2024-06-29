package com.example.uplanner.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.uplanner.R;
import com.example.uplanner.application.Services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class WelcomePageActivity extends Activity {

    private Button newAccountButton;
    private TextView loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        copyDatabaseToDevice();
        newAccountButton = findViewById(R.id.new_account_button);
        loginButton = findViewById(R.id.log_in_hyperlink_ek2);

        newAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("ButtonClick", "Get Started button clicked");
                Intent nextScreen = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(nextScreen);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("ButtonClick", "Get Started button clicked");
                Intent nextScreen = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(nextScreen);
            }
        });

    }

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        System.out.println("Data Directory: " + dataDirectory.toString());
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            if (assetNames == null || assetNames.length == 0) {
                System.out.println("The assetNames array is empty.");
            }
            for (int i = 0; i < Objects.requireNonNull(assetNames).length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
                System.out.println(assetNames[i]);
            }

            copyAssetsToDirectory(assetNames, dataDirectory);
            Services.setDBPathName(dataDirectory + "/" + Services.getDBPathName());

        } catch (final IOException ioe) {
            Log.e("HomeActivity", "I/O Exception");
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {

        AssetManager assetManager = getAssets();
        for (String asset : assets) {

            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {

                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);

                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);

                }

                out.close();
                in.close();
            }
        }
    }
}
	
	