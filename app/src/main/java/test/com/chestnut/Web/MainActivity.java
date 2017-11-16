package test.com.chestnut.Web;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chestnut.Web.WebActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = ((EditText)findViewById(R.id.edit_url)).getText().toString();
//                intent.putExtra(WebActivity.URL,"https://www.baidu.com/");
                if (!TextUtils.isEmpty(s)) {
                    Intent intent = new Intent(MainActivity.this,WebActivity.class);
                    intent.putExtra(WebActivity.URL, "http://demo.mqphp.com/#/slider");
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this,"请输入网址",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
