package org.kscy.db.kscydatabase.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import org.kscy.db.kscydatabase.R;
import org.kscy.db.kscydatabase.model.Hits;
import org.kscy.db.kscydatabase.model.SearchResult;
import org.kscy.db.kscydatabase.module.Search;
import org.kscy.db.kscydatabase.widget.ListViewAdapter;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment {

    private final static String TAG = "ShareFragment";
    private final int type = 1;
    private int page_cnt = 1;
    private ListViewAdapter mAdapter;

    @BindView(R.id.swipyrefreshlayout) SwipyRefreshLayout refreshLayout;
    @BindView(R.id.list_view) ListView listView;
    @BindView(R.id.linlaHeaderProgress) LinearLayout linlaHeaderProgress;

    @BindString(R.string.search_api_url) String search_api_url;
    @BindString(R.string.load_failed) String load_failed;

    public SearchFragment() {
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);

        linlaHeaderProgress.setVisibility(View.GONE);

        mAdapter = new ListViewAdapter(getContext());
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getContext(), InfoActivity.class);
//                intent.putExtra("id", mAdapter.getId(i));
//                startActivity(intent);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                loadList(page_cnt++);
            }
        });

        loadList(page_cnt++);

        return view;
    }

    void loadList(int page) {
    }

    public void getList(String key) {
        linlaHeaderProgress.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(search_api_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Search search = retrofit.create(Search.class);

        Call<SearchResult> call = search.search(200, key);
        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                Hits result = response.body().getHits();

                linlaHeaderProgress.setVisibility(View.GONE);
                if(result != null) {
                    for (int i = 0; i < result.getTotal(); i++) {
                        Hits res = result.getHits().get(i);
                        mAdapter.addItem(res.get_source().getResearch_name(), res.get_source().getOrg(), res.get_source().getResearcher_name(), res.get_id());
                        mAdapter.dataChange();
                    }
                }
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                Toast.makeText(getContext(), load_failed, Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }
}
