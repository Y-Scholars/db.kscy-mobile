package org.kscy.db.kscydatabase.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.kscy.db.kscydatabase.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";

    private String title, author, org, email, type, abstract_kor;

    @BindView(R.id.title_tv) TextView title_tv;
    @BindView(R.id.author_tv) TextView author_tv;
    @BindView(R.id.org_tv) TextView org_tv;
    @BindView(R.id.email_tv) TextView email_tv;
    @BindView(R.id.category_tv) TextView category_tv;
    @BindView(R.id.abstract_tv) TextView abstract_tv;

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
    }
}
