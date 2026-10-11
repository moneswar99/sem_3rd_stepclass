public class Cart {
    private final String cartId;
    private final Double[] itemPrices;

    public Cart(String cartId, int maximumItems) {
        if (cartId == null || cartId.trim().isEmpty()) {
            throw new IllegalArgumentException("Cart ID cannot be blank.");
        }
        if (maximumItems < 0) {
            throw new IllegalArgumentException("Maximum item count cannot be negative.");
        }
        this.cartId = cartId;
        this.itemPrices = new Double[maximumItems];
    }

    public String getCartId() {
        return cartId;
    }

    public boolean addItem(double price) {
        if (price < 0 || !Double.isFinite(price)) {
            throw new IllegalArgumentException("Item price must be a finite, non-negative amount.");
        }
        for (int i = 0; i < itemPrices.length; i++) {
            if (itemPrices[i] == null) {
                itemPrices[i] = price;
                return true;
            }
        }
        return false;
    }

    public double getTotal() {
        double total = 0.0;
        for (Double price : itemPrices) {
            if (price != null) {
                total += price;
            }
        }
        return total;
    }

    public int getItemCount() {
        int count = 0;
        for (Double price : itemPrices) {
            if (price != null) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Cart cart = new Cart("CART-5", 20);
        cart.addItem(250);
        cart.addItem(99);
        cart.addItem(151);
        System.out.println("Total: " + cart.getTotal());
        System.out.println("Item count: " + cart.getItemCount());
    }
}
