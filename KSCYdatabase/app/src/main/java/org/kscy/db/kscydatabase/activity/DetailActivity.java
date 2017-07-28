package org.kscy.db.kscydatabase.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.kscy.db.kscydatabase.R;
import org.kscy.db.kscydatabase.model.Mark;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";

    private String title, author, org, email, type, abstract_kor;
    private boolean m;

    @BindView(R.id.title_tv) TextView title_tv;
    @BindView(R.id.author_tv) TextView author_tv;
    @BindView(R.id.org_tv) TextView org_tv;
    @BindView(R.id.email_tv) TextView email_tv;
    @BindView(R.id.category_tv) TextView category_tv;
    @BindView(R.id.abstract_tv) TextView abstract_tv;
    @BindView(R.id.bookmark) ImageView bookmark;

    @OnClick(R.id.bookmark) void onBookmarkClicked(View view) {
        Mark mk = new Mark();

        mk.title = title;
        mk.author = author;
        mk.org = org;
        mk.email = email;
        mk.type = type;
        mk.abstract_kor = abstract_kor;

        if(m) {
            Glide.with(this).load(R.drawable.bookmark).into(bookmark);

            for(int k = 0 ; k < MainActivity.bookmark_data.size() ; k++) {
                if(mk.title.equals(MainActivity.bookmark_data.get(k).title) && mk.email.equals(MainActivity.bookmark_data.get(k).email)) {
                    MainActivity.bookmark_data.remove(k);
                    saveBookmark();
                }
            }
            m = false;
        } else {
            Glide.with(this).load(R.drawable.bookmark_selected).into(bookmark);

            MainActivity.bookmark_data.add(mk);
            saveBookmark();
            m = true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        title = intent.getStringExtra("title");
        author = intent.getStringExtra("author");
        org = intent.getStringExtra("org");
        email = intent.getStringExtra("email");
        type = intent.getStringExtra("type");
        abstract_kor = intent.getStringExtra("abstract_kor");

        title_tv.setText(title);
        author_tv.setText(author);
        org_tv.setText(org);
        email_tv.setText(email);
        category_tv.setText(type);
        abstract_tv.setText(abstract_kor);

        m = intent.getBooleanExtra("bookmark", false);
        if(m) {
            Glide.with(this).load(R.drawable.bookmark_selected).into(bookmark);
        }
    }

    public void saveBookmark() {
        SharedPreferences sp = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();

        edit.putInt("bookmark_size", MainActivity.bookmark_data.size());

        for(int i = 0 ; i < MainActivity.bookmark_data.size() ; i++) {
            edit.putString("title_" + i, MainActivity.bookmark_data.get(i).title);
            edit.putString("author_" + i, MainActivity.bookmark_data.get(i).author);
            edit.putString("org_" + i, MainActivity.bookmark_data.get(i).org);
            edit.putString("email_" + i, MainActivity.bookmark_data.get(i).email);
            edit.putString("type_" + i, MainActivity.bookmark_data.get(i).type);
            edit.putString("abstract_kor" + i, MainActivity.bookmark_data.get(i).abstract_kor);
        }

        edit.commit();
    }
}
