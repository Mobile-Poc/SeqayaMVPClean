package com.ntg.seqaya.seqayamvpclean.presentation.favourites;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.ntg.seqaya.seqayamvpclean.R;
import com.ntg.seqaya.seqayamvpclean.domain.entity.CartItem;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Product;
import com.ntg.seqaya.seqayamvpclean.domain.entity.ShoppingCart;
import com.ntg.seqaya.seqayamvpclean.domain.entity.User;
import com.ntg.seqaya.seqayamvpclean.utils.ViewUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.ProductViewHolder> {

    private List<Product> productList;
    private Context context;
    private PublishSubject<Product> unLikedProduct;

    public FavouritesAdapter(List<Product> productList,
                             Context context, PublishSubject<Product> unLikedProduct) {
        this.productList = productList;
        this.context = context;
        this.unLikedProduct = unLikedProduct;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_row, parent, false);
        view.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
        ViewUtil.addShadowToView(context, view);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final Product product = productList.get(position);
        Glide.with(context)
                .load(context.getResources()
                        .getIdentifier(product.getPhotoUrl(), "drawable",
                                context.getPackageName()))
                .into(holder.productImage);
        holder.name.setText(product.getName());
        holder.manufacturer.setText(product.getManufacturerName());
        holder.bottleSize.setText(String.valueOf(product.getBottleSize()) + "لتر");
        holder.numberInPackage.setText(String.valueOf(product.getNo_bpp()) + "زجاجة");
        holder.price.setText(String.valueOf(product.getPrice()) + " ريال");
        holder.increase.setOnClickListener(view -> {
            int number = Integer.parseInt(holder.numberOfItem.getText().toString());
            number++;
            holder.numberOfItem.setText(String.valueOf(number));
        });
        holder.decrease.setOnClickListener(view -> {
            int number = Integer.parseInt(holder.numberOfItem.getText().toString());
            if (number > 1) {
                number--;
                holder.numberOfItem.setText(String.valueOf(number));
            }

        });
        holder.addToCart.setOnClickListener(view -> {
            ShoppingCart shoppingCart = User.getShoppingCart();
            CartItem cartItem = new CartItem(product, Integer.parseInt(holder.numberOfItem.getText().toString()));
            for (CartItem item : shoppingCart.getCartItemList()) {
                if (item.getProduct().getId() == product.getId()) {
                    shoppingCart.getCartItemList().remove(item);
                }
            }
            shoppingCart.getCartItemList().add(cartItem);
        });
        holder.likeButton.setLiked(true);
        holder.likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {

            }

            @Override
            public void unLiked(LikeButton likeButton) {
                unLikedProduct.onNext(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void removeFavourite(int productId) {
        for (Product product : productList) {
            if (product.getId() == productId) {
                productList.remove(product);
                notifyDataSetChanged();
            }
        }
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_image)
        ImageView productImage;
        @BindView(R.id.product_name)
        TextView name;
        @BindView(R.id.product_manufacturer)
        TextView manufacturer;
        @BindView(R.id.bottle_size)
        TextView bottleSize;
        @BindView(R.id.number_in_package)
        TextView numberInPackage;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.decrease)
        ImageButton decrease;
        @BindView(R.id.number_of_item)
        TextView numberOfItem;
        @BindView(R.id.increase)
        ImageButton increase;
        @BindView(R.id.add_to_cart)
        Button addToCart;
        LikeButton likeButton;


        ProductViewHolder(View itemView) {
            super(itemView);
            likeButton = itemView.findViewById(R.id.fav);
            ButterKnife.bind(this, itemView);
        }
    }
}
