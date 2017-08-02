package cn.aorise.database.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.aorise.database.R;
import cn.aorise.database.db.LangDbHelper;
import cn.aorise.database.db.entity.LangEntity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.btn_database);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDatabase();
            }
        });
    }

    private void createDatabase() {
        // 创建数据库文件
        LangDbHelper.getInstance().getLangEntityDao()
                .insert(createEntity("sample_title_activity_dblang", "多语言(DB)"));

        LangDbHelper.getInstance().getLangEntityDao()
                .insert(createEntity("sample_multi_lang", "大家好，我是中国人！"));
    }

    @NonNull
    private LangEntity createEntity(@NonNull String key, @NonNull String value) {
        LangEntity entity = new LangEntity();
        entity.setKey(key);
        entity.setValue(value);
        return entity;
    }
}
