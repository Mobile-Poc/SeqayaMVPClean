package com.mesawer.chaty.seqayamvpclean.presentation.cart;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mesawer.chaty.seqayamvpclean.R;
import com.mesawer.chaty.seqayamvpclean.domain.entity.CartItem;
import com.mesawer.chaty.seqayamvpclean.utils.ViewUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ProductViewHolder> {
    private int total = 0;
    private List<CartItem> cartItemList;
    private Context context;
    private TotalListener totalListener;

    public CartAdapter(List<CartItem> cartItemList, Context context, TotalListener totalListener) {
        this.cartItemList = cartItemList;
        this.context = context;
        this.totalListener = totalListener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            view.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
        }
        ViewUtil.addShadowToView(context, view);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final CartItem cartItem = cartItemList.get(position);
        total += (cartItem.getQuantity() * cartItem.getProduct().getPrice());
        totalListener.onTotalChange(total);
        holder.name.setText(cartItem.getProduct().getName());
        holder.manufacturer.setText(cartItem.getProduct().getManufacturer());
        holder.bottleSize.setText(String.valueOf(cartItem.getProduct().getBottleSize()));
        holder.numberInPackage.setText(String.valueOf(cartItem.getProduct().getNo_bpp()));
        holder.price.setText(String.valueOf(cartItem.getProduct().getPrice()));
        holder.numberOfItem.setText(String.valueOf(cartItem.getQuantity()));
        holder.increase.setOnClickListener(view ->
        {
            int number = Integer.parseInt(holder.numberOfItem.getText().toString());
            number++;
            holder.numberOfItem.setText(String.valueOf(number));
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            total += cartItem.getProduct().getPrice();
            totalListener.onTotalChange(total);
        });
        holder.decrease.setOnClickListener(view -> {
            int number = Integer.parseInt(holder.numberOfItem.getText().toString());
            if (number > 1) {
                number--;
                holder.numberOfItem.setText(String.valueOf(number));
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                total -= cartItem.getProduct().getPrice();
                totalListener.onTotalChange(total);

            }

        });
        holder.errorBtn.setOnClickListener(view -> {
            total = 0;
            cartItemList.remove(cartItem);
            notifyDataSetChanged();
            if (cartItemList.isEmpty())
                totalListener.onTotalChange(total);
        });
    }


    @Override
    public int getItemCount() {
        return cartItemList.size();
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
        @BindView(R.id.no_in_package)
        TextView numberInPackage;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.quntity)
        TextView numberOfItem;
        @BindView(R.id.minusBtn)
        ImageButton decrease;
        @BindView(R.id.plusBtn)
        ImageButton increase;
        @BindView(R.id.errorBtn)
        ImageButton errorBtn;


        ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    interface TotalListener {
        void onTotalChange(int total);
    }
}