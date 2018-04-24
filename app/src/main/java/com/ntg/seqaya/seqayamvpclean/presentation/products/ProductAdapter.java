package com.ntg.seqaya.seqayamvpclean.presentation.products;

import android.content.Context;
import android.support.annotation.NonNull;
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
import com.ntg.seqaya.seqayamvpclean.presentation.main.CartItemsCountListener;
import com.ntg.seqaya.seqayamvpclean.utils.ViewUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private static String TAG = ProductAdapter.class.getSimpleName();
    private List<Product> productList;
    private Context context;
    private PublishSubject<Product> productPublishSubject;
    private PublishSubject<CartItem> cartItemPublishSubject;
    private List<Product> favouriteProducts;
    private CartItemsCountListener cartItemsCountListener;

    ProductAdapter(List<Product> productList, Context context, PublishSubject<Product> productPublishSubject, PublishSubject<CartItem> cartItemPublishSubject, CartItemsCountListener cartItemsCountListener) {
        this.productList = productList;
        this.context = context;
        this.productPublishSubject = productPublishSubject;
        this.cartItemPublishSubject = cartItemPublishSubject;
        this.cartItemsCountListener = cartItemsCountListener;
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_row, parent, false);
        view.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
        ViewUtil.addShadowToView(context, view);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, int position) {
        final Product product = productList.get(position);
        if (favouriteProducts != null) {
            for (int i = 0; i < favouriteProducts.size(); i++) {
                if (product.getId() == favouriteProducts.get(i).getId()) {
                    product.setLiked(true);
                }
            }
        }
        Glide.with(context)
                .load(context.getResources()
                        .getIdentifier(product.getPhotoUrl(), "drawable", context.getPackageName()))
                .into(holder.productImage);
        holder.name.setText(product.getName());
        holder.manufacturer.setText(product.getManufacturerName());
        holder.bottleSize.setText(String.valueOf(product.getBottleSize()) + "لتر");
        holder.numberInPackage.setText(String.valueOf(product.getNo_bpp()) + "زجاجة");
        holder.price.setText(String.valueOf(product.getPrice()));
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
//            ShoppingCart shoppingCart = User.getShoppingCart();
            CartItem cartItem = new CartItem(product, Integer.parseInt(holder.numberOfItem.getText().toString()));
            cartItemPublishSubject.onNext(cartItem);
//            for (CartItem item : shoppingCart.getCartItemList()) {
//                if (item.getProduct().getId() == product.getId()) {
//                    shoppingCart.getCartItemList().remove(item);
//                }
//            }
//            shoppingCart.getCartItemList().add(cartItem);
//            cartItemsCountListener.onCartItemsCountChanged(shoppingCart.getCartItemList().size());
        });


        holder.likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                productPublishSubject.onNext(product);
                product.setLiked(true);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                productPublishSubject.onNext(product);
            }
        });

        if (product.isLiked()) {
            holder.likeButton.setLiked(true);
        } else {
            holder.likeButton.setLiked(false);
        }
    }

    void setFavouriteProducts(List<Product> favouriteProducts) {
        this.favouriteProducts = favouriteProducts;
        notifyDataSetChanged();
    }

    void setProductList(List<Product> newList) {
        this.productList = newList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    void clear() {
        this.productList.clear();
        notifyDataSetChanged();
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
