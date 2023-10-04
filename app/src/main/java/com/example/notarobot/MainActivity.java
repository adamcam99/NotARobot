package com.example.notarobot;




//packages
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        initialize layout and views
        LinearLayout container = findViewById(R.id.container);
        ScrollView scrollView = findViewById(R.id.scrollView);

        List<String> mainList = new ArrayList<>();
        List<String> catImages = new ArrayList<>();
        List<String> dogImages = new ArrayList<>();

        // catImages list
        catImages.add("cat1");
        catImages.add("cat2");
        catImages.add("cat3");

        // dogImages list allowing room for more
        for (int i = 1; i <= 6; i++) {
            dogImages.add("dog" + i);
        }

        // random cat image name and add to mainList
        Random random = new Random();
        String randomCatImage = catImages.get(random.nextInt(catImages.size()));
        mainList.add(randomCatImage);

        // random dog images and add to mainList
        Collections.shuffle(dogImages);
        mainList.addAll(dogImages.subList(0, 4));

        // Shuffle the mainList
        Collections.shuffle(mainList);

        // Iterate through mainList
        for (int i = 0; i < mainList.size(); i++) {
            // Get image name from mainList
            String imageName = mainList.get(i);

            // Create Drawable
            int imageResourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());
            Drawable imageDrawable = getResources().getDrawable(imageResourceId);

            // ImageView
            ImageView imageView = new ImageView(this);
            imageView.setImageDrawable(imageDrawable);
            imageView.setId(i); // Set the ImageView Id to the loop iteration value
            container.addView(imageView);

            // OnClickListener ImageView
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Get Id
                    int clickedId = view.getId();

                    // Get image clickedId index
                    String clickedImageName = mainList.get(clickedId);

                    // Check if cat or dog image then display the message
                    if (clickedImageName.startsWith("cat")) {

                        Toast.makeText(MainActivity.this, "Hurray, you are not a robot!", Toast.LENGTH_SHORT).show();
                        container.setVisibility(View.GONE);
                    } else if (clickedImageName.startsWith("dog")) {

                        Toast.makeText(MainActivity.this, "Oops, that’s not a cat!", Toast.LENGTH_SHORT).show();

                        container.setVisibility(View.GONE);
                    }
                }
            });
        }
    }
}
