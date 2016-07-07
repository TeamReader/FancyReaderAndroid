package android.fancy.reader.adapter;

import android.content.Context;
import android.fancy.reader.R;
import android.fancy.reader.bean.Book;
import android.graphics.Bitmap;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by inx95 on 16-7-6.
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.CardViewHolder> {

    private boolean mIsNative;
    private List<Book> bookList;

    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.noimage)
            .showImageOnFail(R.drawable.noimage)
            .showImageForEmptyUri(R.drawable.noimage)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .build();
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public BookAdapter(boolean isNative, List<Book> bookList) {
        this.mIsNative = isNative;
        this.bookList = bookList;
        setHasStableIds(true);
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void updateBookList(List<Book> bookList) {
        setBookList(bookList);
        notifyDataSetChanged();
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        View itemView = LayoutInflater
                .from(context)
                .inflate(R.layout.book_list_item, parent, false);
        return new CardViewHolder(itemView, new CardViewHolder.ClickResponseListener() {
            @Override
            public void onWholeClick(int position) {
                //todo read it
            }

            @Override
            public void onOverflowClick(View v, int position) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(
                        mIsNative ?
                                R.menu.overflow_book_item_native
                                : R.menu.overflow_book_item_store,
                        popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_delete_url:
                                //todo delete book
                                break;
                            case R.id.action_download_url:
                                //todo add book
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Book book = bookList.get(position);
        imageLoader.displayImage(book.getIcoUrl(), holder.icoImage, options, animateFirstListener);
        holder.title.setText(book.getBookName());
        holder.description.setText(book.getDescription());
    }

    @Override
    public int getItemCount() {
        return bookList == null ? 0 : bookList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView icoImage;
        public TextView title;
        public TextView description;
        public ImageView overflow;

        private ClickResponseListener mClickResponseListener;

        public CardViewHolder(View itemView, ClickResponseListener clickResponseListener) {
            super(itemView);
            this.mClickResponseListener = clickResponseListener;
            icoImage = (ImageView) itemView.findViewById(R.id.thumbnail_image);
            title = (TextView) itemView.findViewById(R.id.book_name);
            description = (TextView) itemView.findViewById(R.id.description);
            overflow = (ImageView) itemView.findViewById(R.id.card_option_overflow);

            itemView.setOnClickListener(this);
            overflow.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v == overflow) {
                mClickResponseListener.onOverflowClick(v, getAdapterPosition());
            } else {
                mClickResponseListener.onWholeClick(getAdapterPosition());
            }

        }

        public interface ClickResponseListener {
            void onWholeClick(int position);

            void onOverflowClick(View v, int position);
        }
    }

    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {
        static final List<String> displayImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayImages.add(imageUri);
                }
            }
        }
    }

}
