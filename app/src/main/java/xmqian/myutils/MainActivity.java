package xmqian.myutils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import xmqian.myutils.simple.DensitySimpleActivity;
import xmqian.myutils.simple.PhoneInfoActivity;
import xmqian.myutils.simple.ToastActivity;


public class MainActivity extends AppCompatActivity {

    private List<String> items;
    public List<Class<?>> activitys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] stringArray = getResources().getStringArray(R.array.item_name);
        items = Arrays.asList(stringArray);
        activitys = new ArrayList<>();
        addActivity();
        ListView listView = (ListView) findViewById(R.id.lv_list);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.print("MainList  position:" + items.get(position));
                goActivity(activitys.get(position));
            }
        });
    }

    private void addActivity() {
        activitys.add(ToastActivity.class);
        activitys.add(DensitySimpleActivity.class);
        activitys.add(PhoneInfoActivity.class);
    }

    private void goActivity(Class activity) {
        startActivity(new Intent(this, activity));
    }
}
