package com.example.sudoku;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageView;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;


public class MainActivity extends Activity
{
    private Button verifier = null;
    static boolean victoire = false;
    private static TextView victoireText = null;


    private int nomEditText;
    private String userEntry = new String();

    // Matrice de base

    private int matriceSudoku [][] = {
            {9,0,0,0,0,0,0,0,3},
            {0,4,0,2,0,9,0,5,1},
            {3,0,0,4,7,5,0,0,0},
            {4,9,0,0,6,7,0,8,0},
            {6,2,0,1,0,8,0,4,7},
            {0,7,0,5,4,0,0,9,6},
            {0,0,0,7,1,6,0,0,5},
            {5,3,0,9,0,4,0,7,0},
            {7,0,0,0,0,0,0,0,4}
    };

    // Matrice contenant la solution

    private final int matriceSolution [][]={
            {9,5,7,6,8,1,4,2,3},
            {8,4,6,2,3,9,7,5,1},
            {3,1,2,4,7,5,8,6,9},
            {4,9,5,3,6,7,1,8,2},
            {6,2,3,1,9,8,5,4,7},
            {1,7,8,5,4,2,3,9,6},
            {2,8,4,7,1,6,9,3,5},
            {5,3,1,9,2,4,6,7,8},
            {7,6,9,8,5,3,2,1,4}
    };

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connexion au bouton Vérifier créé en XML

        verifier = (Button) findViewById(R.id.vérifier);


        // Initialisation de chaque EditText dont on récupère l'ID de la forme X1_Y1, avec chaque élément correspondant de matriceSudoku

        for (int j = 1; j <= 9; j++)
        for (int k = 1; k <= 9; k++)
        {
            if (matriceSudoku[j - 1][k - 1] != 0)
            {
                String bufferK = Integer.toString(k);
                String bufferJ = Integer.toString(j);

                nomEditText = getResources().getIdentifier("X" + bufferK + "_Y" + bufferJ, "id", getPackageName());

                EditText editTextEnCours = findViewById(nomEditText);

                editTextEnCours.setText(Integer.toString(matriceSudoku[j - 1][k - 1]));
            }

        }

        verifier.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // On vérifie dans la suite du code que le joueur a gagné

                victoire = true;

                for (int j = 1; j <= 9; j++)
                    for (int k = 1; k <= 9; k++)
                    {
                            String bufferK = Integer.toString(k);
                            String bufferJ = Integer.toString(j);

                            nomEditText = getResources().getIdentifier("X" + bufferK + "_Y" + bufferJ, "id", getPackageName());

                            EditText editTextEnCours = findViewById(nomEditText);

                            userEntry = editTextEnCours.getText().toString();



                        // On vérifie d'abord qu'il y ait du texte dans l'editText, sinon = perdu

                            if (editTextEnCours.getText().toString().trim().length() > 0)
                            {
                                // On vérifie ensuite que le contenu de l'editText corresponde à la solution
                                // S'il correspond on change la couleur du texte en noir, sinon en rouge

                                if (Integer.parseInt(userEntry) != matriceSolution[j - 1][k - 1]) {
                                    editTextEnCours.setTextColor(Color.RED);
                                    victoire = false;
                                }

                                else
                                    editTextEnCours.setTextColor(Color.BLACK);
                            }
                            else victoire = false;









                    }

                victoireText = findViewById(R.id.victoire);

                if (victoire == true)
                    victoireText.setVisibility(View.VISIBLE);

                else
                    victoireText.setVisibility(View.INVISIBLE);


                return false;
            }
        });




    }
}