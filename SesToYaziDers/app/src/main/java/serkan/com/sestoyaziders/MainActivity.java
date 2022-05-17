package serkan.com.sestoyaziders;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final int recordCheckNumber=1;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=(ListView)findViewById(R.id.listView11);


    }

    public void dinle(View view){

        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH); //Activity nin türünü seçiyoruz. Ne acitivity si açılacak
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM); //Konuşmanın dinleneceği dil ayarlarının yapıldığı
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Konuş...!"); // Ekrandaki yazı. ne yapmamızı söylüyor...
        startActivityForResult(intent,recordCheckNumber); // intent i başlatıyoruz default kendi verdiğimiz kod ile
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==recordCheckNumber && resultCode==RESULT_OK ){

            ArrayList<String> results=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            //gelen birden çok değer olduğu için gelen ilk değeri alıyoruz...
            String deneme=results.get(0);

            if(deneme.equalsIgnoreCase("Elginkan")){ //söylenen değer ile buradaki değer eşitse çalışıyor..

                Toast.makeText(this, "Giriş Başarılı", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Giriş Hatalı", Toast.LENGTH_SHORT).show();
            }

            lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,results));


        }



        super.onActivityResult(requestCode, resultCode, data);
    }
}
