package org.kscy.db.kscydatabase.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.kscy.db.kscydatabase.R;
import org.kscy.db.kscydatabase.activity.DetailActivity;
import org.kscy.db.kscydatabase.activity.MainActivity;
import org.kscy.db.kscydatabase.model.Hits;
import org.kscy.db.kscydatabase.model.Mark;
import org.kscy.db.kscydatabase.model._source;
import org.kscy.db.kscydatabase.module.SwipeDismissListViewTouchListener;
import org.kscy.db.kscydatabase.widget.ListViewAdapter;

import java.util.concurrent.TimeUnit;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookmarkFragment extends Fragment {

    private final static String TAG = "BookmarkFragment";
    private ListViewAdapter mAdapter;
    View view;

    @BindView(R.id.list_view) ListView listView;

    @BindString(R.string.confirm) String confirm;
    @BindString(R.string.cancel) String cancel;
    @BindString(R.string.delete_bookmark) String delete_bookmark;

    public BookmarkFragment() {

    }

    public static BookmarkFragment newInstance() {
        BookmarkFragment fragment = new BookmarkFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bookmark, container, false);

        ButterKnife.bind(this, view);

        mAdapter = new ListViewAdapter(getContext());
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                _source src = (_source) mAdapter.getItem(i);
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("title", src.getResearch_name());
                intent.putExtra("author", src.getResearcher_name());
                intent.putExtra("org", src.getOrg());
                intent.putExtra("email", src.getEmail());
                intent.putExtra("type", src.getType());
                intent.putExtra("abstract_kor", src.getAbstract_kor());
                for(int k = 0 ; k < MainActivity.bookmark_data.size() ; k++) {
                    if(src.getResearch_name().equals(MainActivity.bookmark_data.get(k).title) && src.getEmail().equals(MainActivity.bookmark_data.get(k).email)) {
                        intent.putExtra("bookmark", true);
                    }
                }
                startActivity(intent);
            }
        });

        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(listView,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, final int[] reverseSortedPositions) {
                                Mark tmp;
                                for (final int position : reverseSortedPositions) {
                                    for(int k = 0; k < MainActivity.bookmark_data.size() ; k++) {
                                        _source src = (_source) mAdapter.getItem(position);
                                        if(src.getResearch_name().equals(MainActivity.bookmark_data.get(k).title) && src.getEmail().equals(MainActivity.bookmark_data.get(k).email)) {
                                            tmp = MainActivity.bookmark_data.get(k);
                                            MainActivity.bookmark_data.remove(k);
                                            saveBookmark();
                                        }
                                    }
                                }
                                reload();
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                                final Mark finalTmp = tmp;
                                builder.setMessage(delete_bookmark)
                                        .setCancelable(false)
                                        .setPositiveButton(confirm, new DialogInterface.OnClickListener(){
                                            public void onClick(DialogInterface dialog, int whichButton){
                                            }
                                        })
                                        .setNegativeButton(cancel, new DialogInterface.OnClickListener(){
                                            public void onClick(DialogInterface dialog, int whichButton){
                                                dialog.cancel();
                                                MainActivity.bookmark_data.add(finalTmp);
                                                reload();
                                            }
                                        });


                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        });
        listView.setOnTouchListener(touchListener);
        listView.setOnScrollListener(touchListener.makeScrollListener());

        loadList();

        return view;
    }

    void loadList() {
        if(MainActivity.bookmark_data != null) {
            for (int i = 0 ; i < MainActivity.bookmark_data.size(); i++) {
                mAdapter.addItem(MainActivity.bookmark_data.get(i));
                mAdapter.dataChange();
            }
        }
    }

    public void saveBookmark() {
        SharedPreferences sp = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
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

    @Override
    public void onResume() {
        super.onResume();
        reload();
    }

    private void reload() {
        while(true) {
            if(mAdapter.mListData.isEmpty()) {
                break;
            }
            mAdapter.remove(0);
        }
        loadList();
    }
}
