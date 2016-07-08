package android.fancy.reader.fragment;

import android.fancy.reader.R;
import android.fancy.reader.tool.Constants;
import android.fancy.reader.activity.BaseActivity;
import android.fancy.reader.adapter.BookAdapter;
import android.fancy.reader.bean.Book;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import observable.BookListFromSourceObservable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by inx95 on 16-7-7.
 */
public class BookListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, Observer<List<Book>> {

    private List<Book> bookList = new ArrayList<>();

    private BookAdapter mAdapter;

    private boolean isNative;
    private boolean isRefreshed = false;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Bundle bundle = getArguments();
            isNative = bundle.getBoolean(Constants.BundleKeys.IS_NATIVE);
            setRetainInstance(true);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.book_list);
        recyclerView.setHasFixedSize(!isNative);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        mAdapter = new BookAdapter(isNative, bookList);
        recyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.color_primary);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        BookListFromSourceObservable.ofSource(isNative)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    //todo auto refresh or not?
    }

    @Override
    public void onRefresh() {
        doRefresh();
    }

    private void doRefresh() {
        BookListFromSourceObservable.ofSource(isNative)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void onCompleted() {
        isRefreshed = true;

        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.updateBookList(bookList);
        //todo save bookList
    }

    @Override
    public void onError(Throwable e) {
        mSwipeRefreshLayout.setRefreshing(false);
        if (isAdded()) {
            ((BaseActivity)getActivity()).showSnackbar(R.string.network_error);
        }
    }

    @Override
    public void onNext(List<Book> bookList) {
        this.bookList = bookList;
    }
}
