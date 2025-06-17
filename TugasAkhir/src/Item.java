public class Item {
    private String name;
    private int quantity;
    private boolean isBought;

    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.isBought = false;
    }

    public void markAsBought() {
        this.isBought = true;
    }

    public boolean isBought() {
        return isBought;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + " x" + quantity + (isBought ? " [Dibeli]" : " [Belum dibeli]");
    }
}

// copyrightby informatika